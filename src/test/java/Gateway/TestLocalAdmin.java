package Gateway;

import Gateway.pages.AdminPage;
import Gateway.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestLocalAdmin {
   public WebDriver driver;
    PageObj pageObj;
    MainPage mainPage;
    AdminPage adminPage;

    @BeforeClass
    void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
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
    public void Test_01_Licenses_OpenLicensesTab() {
        adminPage.licensesTab.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
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
    public void Test_02_Licenses_AddLinensesToService() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        adminPage.licensesTab.click();
        driver.findElement(By.cssSelector("a[href onclick = 'LicenseManager(); return false;']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'License Order Generator')]")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table[class = 'magazines_table']")));


    }
}
