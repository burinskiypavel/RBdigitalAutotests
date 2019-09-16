package Gateway;

import Gateway.pages.MainPage;
import Gateway.pages.PlatformPage;
import Gateway.pages.ServicePage;
import Gateway.pages.ServiceSitePage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

@Test
public class TestServicesCheckouts extends BaseClass_TestServiceCheckout {

    @Test(enabled = false)
    void test_01_audiobooksEBooksService_test51() {
        login("jun5@gmail.com", "12345qw");
        goIntoServiceByPictureByXpath("//a[@href='#'][@class='service_image rbdigital']");
        //platformPage.CheckRegisterSignInHelpWelcomeTextPresent();
        checkURL("https://test-artistworks.artistworks.com/");
    }

    @Test
    void test_02_artistWorksService_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/artistworks']");
        pressGetStartedButton();
        checkURL("https://test-artistworks.artistworks.com/");
        checkText("a", "FREE SAMPLE LESSONS");
    }

    @Test
    void test_03_newspapers_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/newspapers']");
        pressGetStartedButton();
        //checkURL("https://www.beta.pressreader.com/catalog");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("label[class = 'btn btn-action']")));
        checkText("button", "Select Publication");
    }

    @Test
    void test_04_magazines_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/magazines']");
        pressGetStartedButton();
        checkURL("https://www.rbdigitalqa.com/test51/service/magazines/landing?");
        checkText("a", "My Collection");
        checkElementIsPresent("div[class='magazine-card']");
    }

    @Test
    void test_05_comics_test51() throws InterruptedException {
        login("jun5@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/test51/service/comics']");
        pressGetStartedButton();
        checkURL("https://www.rbdigitalqa.com/test51/service/comics/landing?");
        checkText("a", "My Collection");
        checkElementIsPresent("div[class='magazine-card']");
    }

    @Test
    void test_06_transparentLanguage_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/transparent_language']");
        pressGetStartedButton();
        checkURL("http://192.168.254.49/rbdigitalinternal/game/rbdgLogin");
        //serviceSitePage.CheckText("a", "My Collection");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='magazine-card']")));
        //Assert.assertTrue(driver.findElement(By.cssSelector("div[class='magazine-card']")).isDisplayed());
    }

    @Test
    void test_07_artistWorksService_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/artistworks']");
        pressGetStartedButton();
        checkURL("https://test-artistworks.artistworks.com/");
        checkText("a", "FREE SAMPLE LESSONS");
    }

    @Test
    void test_08_hoonuit_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/hoonuit']");
        pressGetStartedButton();
        checkURL("https://learnit.hoonuit.com/?from_auth=1");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("section_heading_Pathways")));
        checkText("h2", "Pathways");
    }

    @Test
    void test_09_lawdepot_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/lawdepot']");
        pressGetStartedButton();
        checkURLcontains("https://www.lawdepot.com/libraries/errorRBDG.aspx");
        checkText("h2", "LawDepot Library Subscription");
    }

    @Test(enabled = false)
    void test_10_newspapers_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/newspapers']");
        pressGetStartedButton();
        //checkURL("https://www.pressreader.com/catalog");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("label[class = 'btn btn-action']")));
        checkText("button", "Select Publication");
    }

    @Test
    void test_11_AcornTV_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/acorntv']");
        pressGetStartedButton();
        checkURLcontains("https://signup-qa.acorn.tv/extendsubscription.html?sessionid=");
        checkText("h4", "Welcome back to Acorn TV");
    }

    @Test
    void test_12_pongalo_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/pongalo']");
        pressGetStartedButton();
        checkURLcontains("https://frontend-dev.pongalo.com/recorded-books/");
        checkText("p", "PONGALO está disponible en tu teléfono móvil, tableta, computador y TV.");
    }

    @Test
    void test_13_methodtestprep_rbdigitalinternal() throws InterruptedException {
        navigate("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            logout();
        }
        login("aug28@gmail.com", "12345qw");
        goIntoServiceByButtonByXpath("//a[@href='//www.rbdigitalqa.com/rbdigitalinternal/service/method-test-prep']");
        pressGetStartedButton();
        checkURLcontains("https://dev.methodtestprep.com/student/home/index_v4");
        checkText("h2", "Student Dashboard");
    }
}
