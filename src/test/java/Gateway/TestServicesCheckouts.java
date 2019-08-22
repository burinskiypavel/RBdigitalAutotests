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
public class TestServicesCheckouts extends BaseClass_TestServiceCheckout {
    //WebDriver driver;
    //WebDriverWait wait;
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
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));

        pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        servicePage = new ServicePage(driver);
        platformPage = new PlatformPage(driver);
        serviceSitePage = new ServiceSitePage(driver);
    }

    @Test(enabled = false)
    void test_01_Audiobooks_and_eBooksCheckout() {
        login("jun5@gmail.com", "12345qw");
        mainPage.selectServiceByPictureByXpath("//a[@href='#'][@class='service_image rbdigital']");
        //driver.findElement(By.xpath("//a[@href='#'][@class='service_image rbdigital']")).click();// css=E[A='t']
        platformPage.CheckRegisterSignInHelpWelcomeTextPresent();
    }

    @Test
    void test_02_ArtistWorksService_test51() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/artistworks']");
        servicePage.pressGetStartedButton();
        checkURL("https://test-artistworks.artistworks.com/");
        serviceSitePage.checkText("a", "FREE SAMPLE LESSONS");
    }

    @Test
    void test_03_Newspapers_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/newspapers']");
        servicePage.pressGetStartedButton();
        checkURL("https://www.pressreader.com/catalog");
        serviceSitePage.checkText("span", "Sign in");
    }

    @Test
    void test_04_Magazines_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/magazines']");
        servicePage.pressGetStartedButton();
        checkURL("https://www.rbdigitalqa.com/test51/service/magazines/landing?");
        serviceSitePage.checkText("a", "My Collection");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='magazine-card']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='magazine-card']")).isDisplayed());
    }

    @Test
    void test_05_Comics_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/comics']");
        servicePage.pressGetStartedButton();
        checkURL("https://www.rbdigitalqa.com/test51/service/comics/landing?");
        serviceSitePage.checkText("a", "My Collection");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='magazine-card']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='magazine-card']")).isDisplayed());
    }

    @Test
    void test_06_TransparentLanguage_rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        login("jul25@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/transparent_language']");
        servicePage.pressGetStartedButton();
        checkURL("http://192.168.254.49/rbdigitalinternal/game/rbdgLogin");
        //serviceSitePage.CheckText("a", "My Collection");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='magazine-card']")));
        //Assert.assertTrue(driver.findElement(By.cssSelector("div[class='magazine-card']")).isDisplayed());
    }

    @Test
    void test_07_ArtistWorksService_rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        login("jul25@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/artistworks']");
        servicePage.pressGetStartedButton();
        checkURL("https://test-artistworks.artistworks.com/");
        serviceSitePage.checkText("a", "FREE SAMPLE LESSONS");
    }

    @Test
    void test_08_Hoonuit_rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        login("jul25@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/hoonuit']");
        servicePage.pressGetStartedButton();
        checkURL("https://learnit.hoonuit.com/?from_auth=1");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("section_heading_Pathways")));
        serviceSitePage.checkText("h2", "Pathways");
    }

    @Test
    void test_09_Lawdepot_rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        login("jul25@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/lawdepot']");
        servicePage.pressGetStartedButton();
        checkURL("https://www.lawdepot.com/libraries/errorRBDG.aspx?errorcode=authentication&id=rbdg&rbid=f1662d2b-aa2a-495a-8de8-65d81c3915b7");
        serviceSitePage.checkText("h2", "LawDepot Library Subscription");
    }

    @Test
    void test_10_Newspapers_rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        login("jul25@gmail.com", "12345qw");
        mainPage.goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/newspapers']");
        servicePage.pressGetStartedButton();
        checkURL("https://www.pressreader.com/catalog");
        serviceSitePage.checkText("span", "Sign in");
    }

    //@AfterMethod
   // void AfterMethod() {
 //       driver.navigate().to("https://www.rbdigitalqa.com/test51/");
   //     //driver.switchTo().defaultContent();//exit from iframe
   //     if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
    //        //mainPage.Logout();
      //      logout();
    //    }
   // }
}
