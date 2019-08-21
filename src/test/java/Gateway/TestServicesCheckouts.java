package Gateway;

import Gateway.pages.MainPage;
import Gateway.pages.PlatformPage;
import Gateway.pages.ServicePage;
import Gateway.pages.ServiceSitePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class TestServicesCheckouts {
    WebDriver driver;
    PageObj pageObj;
    MainPage mainPage;
    ServicePage servicePage;
    PlatformPage platformPage;
    ServiceSitePage serviceSitePage;

    @BeforeClass
    void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        Wait<WebDriver> wait0 = new WebDriverWait(driver, 30);
        wait0.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        servicePage = new ServicePage(driver);
        platformPage = new PlatformPage(driver);
        serviceSitePage = new ServiceSitePage(driver);
    }

    @AfterClass
    void afterClass() {
        //driver.close();
        driver.quit();
    }

    @BeforeMethod
    void beforeMethod() {

    }

    @AfterMethod
    void AfterMethod() {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        //driver.switchTo().defaultContent();//exit from iframe
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
    }

    @Test
    void Test_01_Audiobooks_and_eBooksCheckout() {
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.SelectServiceByPictureByXpath("//a[@href='#'][@class='service_image rbdigital']");
        //driver.findElement(By.xpath("//a[@href='#'][@class='service_image rbdigital']")).click();// css=E[A='t']
        platformPage.CheckRegisterSignInHelpWelcomeTextPresent();
    }

    @Test
    void Test_02_ArtistWorksService_test51() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.GoIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/artistworks']");
        servicePage.PressGetStartedButton();
        pageObj.CheckURL("https://test-artistworks.artistworks.com/");
        serviceSitePage.CheckText("a", "FREE SAMPLE LESSONS");
    }

    @Test
    void Test_03_Newspapers_test51() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.GoIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/newspapers']");
        servicePage.PressGetStartedButton();
        pageObj.CheckURL("https://www.pressreader.com/catalog");
        serviceSitePage.CheckText("span", "Sign in");
    }

    @Test
    void Test_04_Magazines_test51() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.GoIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/magazines']");
        servicePage.PressGetStartedButton();
        pageObj.CheckURL("https://www.rbdigitalqa.com/test51/service/magazines/landing?");
        serviceSitePage.CheckText("a", "My Collection");
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='magazine-card']")).isDisplayed());
    }







}
