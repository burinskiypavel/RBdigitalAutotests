package Gateway;

import Gateway.pages.AdminPage;
import Gateway.pages.MainPage;
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

    @BeforeClass
    void beforeClass() {
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
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
    }

    @AfterClass
    void afterClass() {
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
        //page.pressKey(Keys.F7);
        //driver.close();
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
    public void test_02_licenses_AddLinensesToService() {
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        adminPage.licensesTab.click();
        driver.findElement(By.cssSelector("a[href onclick = 'LicenseManager(); return false;']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'License Order Generator')]")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table[class = 'magazines_table']")));
    }

    @Test
    public void test_03_serviceSubscriptions_checkServicesPresent() throws IOException {
        adminPage.serviceSubscriptions.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3[contains(text(), 'Audiobook and eBook Service Subscription')]")));
        List<String> actualReport = adminPage.GetActualDatadef("//ul[@class='left_menu']", "TestLocalAdmin/test_03_serviceSubscriptions_checkServicesPresent/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestLocalAdmin/test_03_serviceSubscriptions_checkServicesPresent/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    public void test_04_updateSubscriptionPeriodsSetEprepActive(){
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'EPREP')]")).click();
        driver.findElement(By.id("start_sub_date")).clear();
        driver.findElement(By.id("start_sub_date")).sendKeys("05/01/2016");
        driver.findElement(By.id("exp_date")).clear();
        driver.findElement(By.id("exp_date")).sendKeys("10/31/2024");
        pageObj.SelectFromSelectByIdAndValue("show_service", "1");
        driver.findElement(By.id("submit_update")).click();
        checkAlert("Are you sure you want to update?\nThis will erase the current subscription parameters.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully updated')]")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='//www.rbdigitalqa.com/test51/service/rbtestprep']")).isDisplayed());

    }

    @Test
    public void test_05_updateSubscriptionPeriodsSetEprepActive(){
        adminPage.serviceSubscriptions.click();
        driver.findElement(By.xpath("//a[contains(text(), 'EPREP')]")).click();
        driver.findElement(By.id("start_sub_date")).clear();
        driver.findElement(By.id("start_sub_date")).sendKeys("05/01/2016");
        driver.findElement(By.id("exp_date")).clear();
        driver.findElement(By.id("exp_date")).sendKeys("10/31/2017");
        //pageObj.SelectFromSelectByIdAndValue("show_service", "1");
        driver.findElement(By.id("submit_update")).click();
        checkAlert("Are you sure you want to update?\nThis will erase the current subscription parameters.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Subscription was successfully updated')]")));
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Assert.assertFalse(driver.findElements(By.xpath("//a[@href='//www.rbdigitalqa.com/test51/service/rbtestprep']")).size() != 0);

    }

    public void checkAlert(String  expectedAlertText) {
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText,  expectedAlertText);
    }
}
