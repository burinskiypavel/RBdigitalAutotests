package Gateway;

import Gateway.pages.AdminPage;
import Gateway.pages.MainPage;
import Gateway.pages.ServicePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestLocalAdmin {
    public WebDriver driver;
    WebDriverWait wait;
    PageObj pageObj;
    MainPage mainPage;
    AdminPage adminPage;
    ServicePage servicePage;
    String libraryAdminTimeStamp;
    String libraryAdminTS;

    @BeforeClass
    void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();

        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
        driver.manage().window().maximize();
        pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        //servicePage = new ServicePage(driver);
        //platformPage = new PlatformPage(driver);
        adminPage = new AdminPage(driver);
        servicePage = new ServicePage(driver);
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.updateWeeklyOverallPatronCap("99999");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.updateMonthlyOverallPatronCap("99999");
    }

    @AfterClass
    void afterClass() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
        adminPage.Logout();
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.updateWeeklyOverallPatronCap("99999");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.updateMonthlyOverallPatronCap("99999");
        driver.close();
        //driver.quit();
    }

    @BeforeMethod
    void beforeMethod() {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        //adminPage.licensesTab.click();
    }

    @AfterMethod
    void AfterMethod() {
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
    }


    @Test
    public void test_01_licenses_OpenLicensesTabCheckTextisPresent() {
        SoftAssert softAssert = new SoftAssert();
        adminPage.licensesTab.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Manager')]")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Weekly Overall Patron Cap:')]")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Monthly Overall Patron Cap:')]")));

        String text1 = driver.findElement(By.cssSelector("div[class='group-name']")).getText();
        String text2 = driver.findElement(By.cssSelector("div[class='group-cap']")).getText();
        String text3 = driver.findElement(By.cssSelector("div[class='patron-cap']")).getText();

        softAssert.assertTrue(text1.contains("Group:"), "ERROR - Group not found");
        softAssert.assertTrue(text2.contains("Weekly Group Cap:"), "ERROR - Weekly Group Cap not found");
        softAssert.assertTrue(text3.contains("Weekly Group Patron Cap:"), "ERROR - Weekly Group Patron Cap not found");
        softAssert.assertTrue(driver.findElements(By.xpath("//span[contains(text(), 'Add New Groups')]")).size() == 0, "ERROR - Add New Groups not found");
        softAssert.assertTrue(driver.findElement(By.id("save")).isEnabled(), "ERROR - Save button not found");
        softAssert.assertAll();
    }

    @Test
    public void test_02_licenses_updateWeeklyOverallPatronCap() throws InterruptedException {
        adminPage.updateWeeklyOverallPatronCap("0");
        driver.navigate().to("https://www.rbdigitalqa.com/test51");
        mainPage.Login("sep18b@gmail.com", "12345qw")
                .goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/indieflix']");
        servicePage.pressGetStartedButton();
        adminPage.checkAlertModal("You have exceeded the number of services that you can access through your library this week.");
    }

    @Test
    public void test_03_licenses_updateMonthlyOverallPatronCap() throws InterruptedException {
        adminPage.updateWeeklyOverallPatronCap("9999");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.updateMonthlyOverallPatronCap("0");
        driver.navigate().to("https://www.rbdigitalqa.com/test51");
        mainPage.Login("sep18b@gmail.com", "12345qw")
                .goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/indieflix']");
        servicePage.pressGetStartedButton();
        adminPage.checkAlertModal("You have exceeded the number of services that you can access through your library this month.");
    }

    @Test
    public void test_04_serviceSubscriptions_checkServicesPresent() throws IOException {
        adminPage.serviceSubscriptions.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3[contains(text(), 'Audiobook and eBook Service Subscription')]")));
        List<String> actualReport = adminPage.GetActualDatadef("//ul[@class='left_menu']", "TestLocalAdmin/test_03_serviceSubscriptions_checkServicesPresent/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestLocalAdmin/test_03_serviceSubscriptions_checkServicesPresent/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    public void test_05_updateSubscriptionPeriodsSetEprepActive() {
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'EPREP')]")).click();
        adminPage.selectSubscriptionPeriodStartEnd("05/01/2016", "10/31/2024");
        pageObj.SelectFromSelectByIdAndValue("show_service", "1");
        driver.findElement(By.id("submit_update")).click();
        adminPage.checkAlert("Are you sure you want to update?\nThis will erase the current subscription parameters.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully updated')]")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='//www.rbdigitalqa.com/test51/service/rbtestprep']")).isDisplayed());
    }

    @Test
    public void test_06_1_updateSubscriptionPeriodsSetEprepActive() {
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'EPREP')]")).click();
        adminPage.selectSubscriptionPeriodStartEnd("05/01/2016", "10/31/2017");
        driver.findElement(By.id("submit_update")).click();
        adminPage.checkAlert("Are you sure you want to update?\nThis will erase the current subscription parameters.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully updated')]")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Assert.assertFalse(driver.findElements(By.xpath("//a[@href='//www.rbdigitalqa.com/test51/service/rbtestprep']")).size() != 0);
    }

    @Test
    public void test_06_2_createSubscriptionPeriodsForAcornTV() {
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'ACORN TV')]")).click();
        adminPage.selectSubscriptionPeriodStartEnd("05/01/2025", "10/31/2026");
        driver.findElement(By.cssSelector("input[type='button'][value='Save as new']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully created')]")));
        WebElement parent = driver.findElement(By.id("right_col"));
        String actualText = parent.findElements(By.xpath("//label[@class='medium']")).get(5).getText();
        Assert.assertTrue(actualText.contains("05/01/2025 — 10/31/2026"));
    }

    @Test
    public void test_06_3_deleteSubscriptionPeriodsForAcornTV() {
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'ACORN TV')]")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//strong[contains(text(), '05/01/2025 — 10/31/2026')]")));
        driver.findElements(By.cssSelector("a[title='Remove subscription']")).get(0).click();
        adminPage.checkAlert("Are you sure?\nYou want to delete service subscription.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[contains(text(), '05/01/2025 — 10/31/2026')]")));
    }

    @Test
    public void test_07_updatePatronPassword() throws InterruptedException {
        adminPage = new AdminPage(driver).openPatronTab()
                .searchPatron("d@mail.ru")
                .pressModify()
                .updatePatronPassword("12345qw");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("d@mail.ru", "12345qw");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("profile")));
        Assert.assertTrue(driver.findElement(By.id("profile")).isDisplayed());
    }

    @Test
    public void test_08_updatePatronPasswordBack() throws InterruptedException {
        adminPage = new AdminPage(driver).openPatronTab()
                .searchPatron("d@mail.ru")
                .pressModify()
                .updatePatronPassword("qw12345");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("d@mail.ru", "qw12345");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("profile")));
        Assert.assertTrue(driver.findElement(By.id("profile")).isDisplayed());
    }

    @Test
    public void test_09_createNewPatron() throws InterruptedException {
        adminPage.patron.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New Patron']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='New Patron']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_button")));
        driver.findElement(By.xpath("//a[@title='New Patron']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String timeStamp = adminPage.GetTimeStamp();
        adminPage.fillTheFieldToCreateAPatron(timeStamp + "@gmail.com", "te", "et", timeStamp + "@gmail.com", "12345qw");
        adminPage.searchPatron(timeStamp + "@gmail.com");
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actualEmail, timeStamp + "@gmail.com");
    }


    @Test
    public void test_10_imposibleLoginWithInactiveUser() throws InterruptedException {
        adminPage = new AdminPage(driver).openPatronTab()
                .searchPatron("11_27_2019_16_08@gmail.com")
                .showInactiveUsers();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='checkbox']")));
        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        mainPage.LoginUnsuccessful("11_27_2019_16_08@gmail.com", "12345qw");//kdeamandel@asdads.nl
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='error']")));
        String errorText = driver.findElement(By.cssSelector("div[class='error']")).getText();
        Assert.assertEquals(errorText, "Your account is blocked");
    }

    @Test
    public void test_11_posibleLoginWithActiveUser() throws InterruptedException {
        adminPage = new AdminPage(driver).openPatronTab()
                .searchPatron("11_27_2019_16_08@gmail.com")
                .showInactiveUsers();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='checkbox']")));
        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        mainPage.Login("11_27_2019_16_08@gmail.com", "12345qw");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='welcome']")));
        String welcomeText = driver.findElement(By.cssSelector("div[class='welcome']")).getText();
        Assert.assertEquals(welcomeText, "Welcome, 11_27_2019_16_08");
    }

    @Test
    public void test_12_createNewLibraryAdmin() throws InterruptedException {
        adminPage.openAdminsTab();
        driver.findElement(By.xpath("//a[@title='New Library Admin']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String timeStamp = adminPage.GetTimeStamp();
        libraryAdminTimeStamp = timeStamp + "@gmail.com";
        libraryAdminTS = timeStamp;
        adminPage.fillTheFieldsToCreateNewLibraryAdmin(timeStamp, timeStamp, "12345qw", timeStamp + "@gmail.com", "12345");
        adminPage.searchPatron(timeStamp + "@gmail.com");
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actualEmail, timeStamp + "@gmail.com");
    }

    @Test
    public void test_13_updateNewLibraryAdmin() throws InterruptedException {
        adminPage.openAdminsTab();
        adminPage.searchPatron(libraryAdminTimeStamp);
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        driver.findElement(By.id("username")).sendKeys("test123");
        driver.findElement(By.id("password")).sendKeys("qw12345");
        driver.findElement(By.id("r_password")).sendKeys("qw12345");
        driver.findElement(By.id("submitButton")).click();
        adminPage.searchPatron(libraryAdminTimeStamp);
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String newUsername = driver.findElement(By.id("username")).getAttribute("value");
        Assert.assertTrue(newUsername.contains("test123"));
    }

    @Test
    public void test_14_imposibleLoginWithInactiveAdmin() throws InterruptedException {
        adminPage.openAdminsTab();
        adminPage.searchPatron(libraryAdminTimeStamp);
        driver.findElement(By.cssSelector("td[class='Stop']")).click();
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.Logout();
        adminPage.LoginInAdminFailed(libraryAdminTS + "test123", "qw12345");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='error']")));
        String errorText = driver.findElement(By.cssSelector("div[class='error']")).getText();
        Assert.assertEquals(errorText, "Bad user name or password.");
    }

    @Test
    public void test_15_posibleLoginWithActiveAdmin() throws InterruptedException {
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.openAdminsTab();
        adminPage.searchPatron(libraryAdminTimeStamp);
        driver.findElement(By.cssSelector("td[class='Start']")).click();
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.Logout();
        adminPage.LoginInAdmin(libraryAdminTS + "test123", "qw12345");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
        String text = driver.findElement(By.id("principal")).getText();
        Assert.assertEquals(text, libraryAdminTS + "test123");
    }

    @Test
    public void test_16_addLicenseToAcornTV() {
        if (driver.findElements(By.id("logout")).size() != 0) {
            adminPage.Logout();
        }
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.licensesTab.click();
        adminPage.goToLicenseManager();
        int licensesBeforeAdd = adminPage.getDataFromLicensesOveral_getLicenses(2);
        adminPage.createLicensesForService("acorntv", "5", "test");
        int licensesAfterAdd = adminPage.getDataFromLicensesOveral_getLicenses(2);
        Assert.assertEquals(licensesAfterAdd, licensesBeforeAdd + 5);
    }

    @Test
    public void test_17_addLicenseToGreatCourses() {
        if (driver.findElements(By.id("logout")).size() != 0) {
            adminPage.Logout();
        }
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.licensesTab.click();
        adminPage.goToLicenseManager();
        int licensesBeforeAdd = adminPage.getDataFromLicensesOveral_getLicenses(4);
        adminPage.createLicensesForService("great-courses", "5", "test");
        int licensesAfterAdd = adminPage.getDataFromLicensesOveral_getLicenses(4);
        Assert.assertEquals(licensesAfterAdd, licensesBeforeAdd + 5);
    }

    @Test
    public void test_18_addLicenseToIndieflix() {
        if (driver.findElements(By.id("logout")).size() != 0) {
            adminPage.Logout();
        }
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.licensesTab.click();
        adminPage.goToLicenseManager();
        int licensesBeforeAdd = adminPage.getDataFromLicensesOveral_getLicenses(5);
        adminPage.createLicensesForService("indieflix", "5", "test");
        int licensesAfterAdd = adminPage.getDataFromLicensesOveral_getLicenses(5);
        Assert.assertEquals(licensesAfterAdd, licensesBeforeAdd + 5);
    }

    @Test
    public void test_19_addLicenseToLearnItLive() {
        if (driver.findElements(By.id("logout")).size() != 0) {
            adminPage.Logout();
        }
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
        adminPage.licensesTab.click();
        adminPage.goToLicenseManager();
        int licensesBeforeAdd = adminPage.getDataFromLicensesOveral_getLicenses(6);
        adminPage.createLicensesForService("learnitlive", "5", "test");
        int licensesAfterAdd = adminPage.getDataFromLicensesOveral_getLicenses(6);
        Assert.assertEquals(licensesAfterAdd, licensesBeforeAdd + 5);
    }

    @Test
    public void test_20_createAccesKey() {
        adminPage.openFiltersTab();
        adminPage.goToAccessKeyFiltering();
        adminPage.createAccesKeyStrikt("qa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='qa']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span[title='qa']")).isDisplayed());
    }

    @Test
    public void test_21_deleteAccesKey() throws InterruptedException {
        adminPage.openFiltersTab();
        adminPage.goToAccessKeyFiltering();
        driver.findElements(By.cssSelector("a[title='Remove']")).get(1).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span[title='qa']")));
        Assert.assertFalse(driver.findElements(By.cssSelector("span[title='qa']")).size() !=0);
    }

    @Test
    public void test_22_createChildLibrary() {//createClildLibrary_NewChildLibrary_succsesfulyCreated
        if (driver.findElements(By.cssSelector("a[class='child_library_edit']")).size() == 0) {
            adminPage.settingsTab.click();
        }
            adminPage.goToChildLibraryPage();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'New Child Library')]")));

            driver.findElement(By.xpath("//a[contains(text(), 'New Child Library')]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
            driver.findElement(By.id("off_name")).sendKeys("testqa");
            driver.findElement(By.id("submitButton")).click();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@title='Modify']")));

            String actual = driver.findElements(By.cssSelector("td[class='officialName']")).get(2).getText();
            Assert.assertEquals(actual, "testqa");
    }

    @Test
    public void test_23_updateChildLibrary() {
        if (driver.findElements(By.cssSelector("a[class='child_library_edit']")).size() == 0) {
            adminPage.settingsTab.click();
        }
            adminPage.goToChildLibraryPage();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Modify']")));
            driver.findElements(By.xpath("//span[@title='Modify']")).get(2).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
            driver.findElement(By.id("off_name")).sendKeys("0");
            driver.findElement(By.id("submitButton")).click();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@title='Modify']")));
            String actual = driver.findElements(By.cssSelector("td[class='officialName']")).get(2).getText();
            Assert.assertEquals(actual, "testqa0");
    }

    @Test
    public void test_24_deleteChildLibrary() throws InterruptedException {
        if (driver.findElements(By.cssSelector("a[class='child_library_edit']")).size() == 0) {
            adminPage.settingsTab.click();

        }
        adminPage.goToChildLibraryPage();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Delete']")));
        driver.findElements(By.xpath("//span[@title='Delete']")).get(2).click();
        adminPage.checkAlert("WARNING: All the patrons in this library will be deleted too.\nAre you sure?");
        Thread.sleep(1000);
        String actual = driver.findElements(By.cssSelector("td[class='officialName']")).get(0).getText();
        Assert.assertNotEquals(actual, "testqa0");
    }

    @Test
    public void test_25_createBarcode() {
        adminPage.openFiltersTab();
        adminPage.goToBarcodeFiltering();
        adminPage.createBarcode("abc", "5");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='abc']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span[title='abc']")).isDisplayed());
    }

    @Test
    public void test_26_deleteBarcode() throws InterruptedException {
        adminPage.openFiltersTab();
        adminPage.goToBarcodeFiltering();
        driver.findElements(By.cssSelector("a[title='Remove']")).get(0).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span[title='abc']")));
        Assert.assertFalse(driver.findElements(By.cssSelector("span[title='abc']")).size() !=0);
    }

    @Test
    public void test_27_createIPFiltering() {
        adminPage.openFiltersTab();
        adminPage.createIPFiltering("192", "192", "193", "194");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '192.192.193.194')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), '192.192.193.194')]")).isDisplayed());
    }

    @Test
    public void test_28_deleteIPFiltering() throws InterruptedException {
        adminPage.openFiltersTab();
        driver.findElements(By.cssSelector("a[title='Remove']")).get(0).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(), '192.192.193.194')]")));
        Assert.assertFalse(driver.findElements(By.xpath("//span[contains(text(), '192.192.193.194')]")).size() !=0);
    }
}