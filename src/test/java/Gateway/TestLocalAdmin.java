package Gateway;

import Gateway.pages.AdminPage;
import Gateway.pages.MainPage;
import Gateway.pages.ServicePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
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
        adminPage.updateWeeklyOverallPatronCap("99999");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.updateMonthlyOverallPatronCap("99999");
        driver.close();
        driver.quit();
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
    public void test_01_licenses_OpenLicensesTab() {
        adminPage.licensesTab.click();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Manager')]")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Weekly Overall Patron Cap:')]")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Monthly Overall Patron Cap:')]")));

        String text1 = driver.findElement(By.cssSelector("div[class='group-name']")).getText();
        String text2 = driver.findElement(By.cssSelector("div[class='group-cap']")).getText();
        String text3 = driver.findElement(By.cssSelector("div[class='patron-cap']")).getText();

        Assert.assertTrue(text1.contains("Group:"));
        Assert.assertTrue(text2.contains("Weekly Group Cap:"));
        Assert.assertTrue(text3.contains("Weekly Group Patron Cap:"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Add New Group')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("save")).isEnabled());
    }

    @Test
    public void test_02_licenses_updateWeeklyOverallPatronCap() throws InterruptedException {
        adminPage.updateWeeklyOverallPatronCap("0");
        driver.navigate().to("https://www.rbdigitalqa.com/test51");
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/indieflix']");
        servicePage.pressGetStartedButton();
        adminPage.checkAlertModal("You have exceeded the number of services that you can access through your library this week.");
    }

    @Test
    public void test_03_licenses_updateMonthlyOverallPatronCap() throws InterruptedException {
        adminPage.updateWeeklyOverallPatronCap("9999");
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.updateMonthlyOverallPatronCap("0");
        driver.navigate().to("https://www.rbdigitalqa.com/test51");
        mainPage.Login("sep2@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/indieflix']");
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
    public void test_05_updateSubscriptionPeriodsSetEprepActive(){
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'EPREP')]")).click();
        driver.findElement(By.id("start_sub_date")).clear();
        driver.findElement(By.id("start_sub_date")).sendKeys("05/01/2016");
        driver.findElement(By.id("exp_date")).clear();
        driver.findElement(By.id("exp_date")).sendKeys("10/31/2024");
        pageObj.SelectFromSelectByIdAndValue("show_service", "1");
        driver.findElement(By.id("submit_update")).click();
        adminPage.checkAlert("Are you sure you want to update?\nThis will erase the current subscription parameters.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully updated')]")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='//www.rbdigitalqa.com/test51/service/rbtestprep']")).isDisplayed());

    }

    @Test
    public void test_06_updateSubscriptionPeriodsSetEprepActive(){
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'EPREP')]")).click();
        driver.findElement(By.id("start_sub_date")).clear();
        driver.findElement(By.id("start_sub_date")).sendKeys("05/01/2016");
        driver.findElement(By.id("exp_date")).clear();
        driver.findElement(By.id("exp_date")).sendKeys("10/31/2017");
        //pageObj.SelectFromSelectByIdAndValue("show_service", "1");
        driver.findElement(By.id("submit_update")).click();
        adminPage.checkAlert("Are you sure you want to update?\nThis will erase the current subscription parameters.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully updated')]")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Assert.assertFalse(driver.findElements(By.xpath("//a[@href='//www.rbdigitalqa.com/test51/service/rbtestprep']")).size() != 0);
    }

    @Test
    public void test_07_updatePatronPassword() throws InterruptedException {
        adminPage.patron.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_line")));
        Thread.sleep(1000);
        driver.findElement(By.id("search_line")).sendKeys("bjones@emmaus.edu");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_button")));
        driver.findElement(By.id("search_button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Modify']")));
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("12345qw");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("r_password")));
        driver.findElement(By.id("r_password")).sendKeys("12345qw");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        driver.findElement(By.id("submitButton")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_button")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Thread.sleep(1500);
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        Thread.sleep(2700);
        mainPage.Login("bjones@emmaus.edu", "12345qw");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("profile")));
        Assert.assertTrue(driver.findElement(By.id("profile")).isDisplayed());
    }

    @Test
    public void test_08_updatePatronPasswordBack() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(), 'Patron')]")).click();
        //adminPage.patron.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_line")));
        Thread.sleep(500);
        driver.findElement(By.id("search_line")).sendKeys("bjones@emmaus.edu");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_button")));
        driver.findElement(By.id("search_button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Modify']")));
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("qw12345");
        driver.findElement(By.id("r_password")).sendKeys("qw12345");
        driver.findElement(By.id("submitButton")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_button")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Thread.sleep(1500);
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        Thread.sleep(4700);
        mainPage.Login("bjones@emmaus.edu", "qw12345");
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
        adminPage.fillTheFieldToCreateAPatron(timeStamp + "@gmail.com", "te", "et", timeStamp+"@gmail.com", "12345qw");
        adminPage.searchPatron(timeStamp+"@gmail.com");
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actualEmail, timeStamp+"@gmail.com");
    }



    @Test
    public void test_10_imposibleLoginWithInactiveUser() throws InterruptedException {
        adminPage.patron.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New Patron']")));
        Thread.sleep(700);
        adminPage.searchPatron("kdeamandel@asdads.nl");
        driver.findElement(By.cssSelector("td[class='Stop']")).click();
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        mainPage.Login("kdeamandel@asdads.nl", "12345qw");
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='error']")));
        String errorText = driver.findElement(By.cssSelector("div[class='error']")).getText();
        Assert.assertEquals(errorText, "Your account is blocked");
    }

    @Test
    public void test_11_posibleLoginWithActiveUser() throws InterruptedException {
        adminPage.patron.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New Patron']")));
        Thread.sleep(700);
        adminPage.searchPatron("kdeamandel@asdads.nl");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td[class='Start']")));
        driver.findElement(By.cssSelector("td[class='Start']")).click();
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        mainPage.Login("kdeamandel@asdads.nl", "12345qw");
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='welcome']")));
        String welcomeKelvinText = driver.findElement(By.cssSelector("div[class='welcome']")).getText();
        Assert.assertEquals(welcomeKelvinText, "Welcome, Kevin");
    }

    @Test
    public void test_12_createNewLibraryAdmin() throws InterruptedException {
        adminPage.openAdminsTab();
        driver.findElement(By.xpath("//a[@title='New Library Admin']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String timeStamp = adminPage.GetTimeStamp();
        libraryAdminTimeStamp = timeStamp+"@gmail.com";
        libraryAdminTS = timeStamp;
        adminPage.fillTheFieldsToCreateNewLibraryAdmin(timeStamp, timeStamp, "12345qw", timeStamp+"@gmail.com", "12345");
        adminPage.searchPatron(timeStamp+"@gmail.com");
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actualEmail, timeStamp+"@gmail.com");
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
        adminPage.LoginInAdminFailed(libraryAdminTS+"test123", "qw12345");
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
        adminPage.LoginInAdmin(libraryAdminTS+"test123", "qw12345");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
        String text = driver.findElement(By.id("principal")).getText();
        Assert.assertEquals(text, libraryAdminTS+"test123");
    }

}
