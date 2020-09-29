package Gateway.PROD;

import Gateway.BaseClass_TestServiceCheckout;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class TestServicesCheckouts_PROD extends BaseClass_TestServiceCheckout {

    @BeforeClass
    void beforeClass() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        //firefox
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();


        driver.navigate().to("https://www.rbdigital.com/test51/");
        wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        driver.manage().window().maximize();

        //pageObj = new PageObj(driver);
        //mainPage = new MainPage(driver);
        //servicePage = new ServicePage(driver);
        //platformPage = new PlatformPage(driver);
        //serviceSitePage = new ServiceSitePage(driver);
    }

    @Test(enabled = false)
    void test_01_audiobooksEBooksService_test51() {
        login("apr14@gmail.com", "12345qw");
        goIntoServiceByPictureByXpath("//a[@href='#'][@class='service_image rbdigital']");
        //platformPage.CheckRegisterSignInHelpWelcomeTextPresent();
        checkURL("https://test-artistworks.artistworks.com/");
    }

    @Test
    void test_02_artistWorksService_test51() throws InterruptedException {
        login("apr14@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/test51/service/artistworks']");
        pressGetStartedButton();
        //checkURL("https://test-mb3-awd7.pantheonsite.io/");
        checkText("Meet Your Teacher");
    }

    @Test
    void test_03_newspapers_test51() throws InterruptedException {
        login("apr14@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/test51/service/newspapers']");
        pressGetStartedButton();
        //checkURL("https://www.beta.pressreader.com/catalog");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class = 'toolbar-button-appmenu']")));
        checkText("Publications");
    }

    @Test
    void test_04_magazines_test51() throws InterruptedException {
        login("apr14@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/test51/service/magazines']");
        pressGetStartedButton();
        checkURL("https://www.rbdigital.com/test51/service/magazines/landing?");
        checkText("My Collection");
        checkElementIsPresent("div[class='magazine-card']");
    }

    @Test
    void test_05_comics_test51() throws InterruptedException {
        login("apr14@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/test51/service/comics']");
        pressGetStartedButton();
        checkURL("https://www.rbdigital.com/test51/service/comics/landing?");
        checkText("My Collection");
        checkElementIsPresent("div[class='magazine-card']");
    }

    @Test
    void test_06_pongalo_test51() throws InterruptedException {
        login("apr14@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/test51/service/pongalo']");
        pressGetStartedButton();
        checkURLcontains("https://www.rbdigital.com/test51/service/pongalo");
        checkText("Note: Pongalo service will be closed at the end of August. No new checkouts accepted.");
    }

    @Test
    void test_07_transparentLanguage_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/transparent_language']");
        pressGetStartedButton();
        checkURL("https://library.transparent.com/rbdigitalinternal/game/ng/#/agreements");
        //serviceSitePage.CheckText("a", "My Collection");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='magazine-card']")));
        //Assert.assertTrue(driver.findElement(By.cssSelector("div[class='magazine-card']")).isDisplayed());
    }

    @Test
    void test_08_artistWorksService_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/artistworks']");
        pressGetStartedButton();
        //checkURL("https://test-mb3-awd7.pantheonsite.io/");
        checkText("Meet Your Teacher");
    }

    @Test
    void test_09_hoonuit_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/hoonuit']");
        pressGetStartedButton();
        checkURLcontains("https://learnit.hoonuit.com");
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Pathways')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Pathways')]")));
        checkText("Pathways");
    }

    @Test
    void test_10_lawdepot_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/lawdepot']");
        pressGetStartedButton();
        checkURLcontains("https://www.lawdepot.com/libraries/errorRBDG.aspx");
        checkText("Free Legal Documents, Forms and Contracts");
    }

    @Test
    void test_11_newspapers_rbdigitalinternal() throws InterruptedException {
        wait = new WebDriverWait(driver, 35);
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/newspapers']");
        pressGetStartedButton();
        //checkURL("https://www.pressreader.com/catalog");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class = 'toolbar-button-appmenu']")));
        checkText("Publications");
    }

    @Test
    void test_12_AcornTV_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/acorntv']");
        pressGetStartedButton();
        checkURLcontains("https://signup.acorn.tv/extendsubscription.html?sessionid=");
        checkText("Welcome back to Acorn TV");
    }

    @Test
    void test_13_methodtestprep_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("qauser", "password1");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigital.com/rbdigitalinternal/service/method-test-prep']");
        pressGetStartedButton();
        checkURLcontains("https://app.methodtestprep.com/student/home/index_v4");
        checkText("Student Dashboard");
    }

    @AfterMethod
    void AfterMethod() {
        driver.navigate().to("https://www.rbdigital.com/test51/");
        //driver.switchTo().defaultContent();//exit from iframe
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            //mainPage.Logout();
            logout();
        }
    }
}
