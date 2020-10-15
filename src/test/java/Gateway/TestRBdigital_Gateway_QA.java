package Gateway;

import Gateway.BaseClass_TestRBDigital_Gateway;
import Gateway.Steps.CommonSteps;
import Gateway.pages.*;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.tools.ant.types.resources.selectors.Compare;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Test
public class TestRBdigital_Gateway_QA extends BaseClass_TestRBDigital_Gateway {
    //public WebDriver driver;
    //PageObj pageObj;
    MainPage mainPage;
    MagazinePage magazinePage;
    ComicPage comicPage;
    ReadingPage readingPage;
    CollectionPage collectionPage;
    AdminPage adminPage;
    CommonSteps commonSteps;
    String magazineUrl3;
    String comicsUrl3;
    String magazineUrl4;
    String comicsUrl4;
    WebDriverWait wait;
    public BrowserMobProxy proxy;


    @BeforeClass
    void beforeClass() throws MalformedURLException {


        String path = System.getProperty("user.dir") + "/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);

        //старт прокси
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(9095);

        //получить обьект Selenium
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        //настройка для драйвера
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors", "--user-data-dir=somedirectory5");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //создание драйвера
        driver = new ChromeDriver(capabilities);

        //включить более детальный захват HAR
        proxy.newHar("www.rbdigitalqa.com");


        //docker
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());

        //selenium server
        //driver = new RemoteWebDriver(new URL("http://192.168.32.51:4444/wd/hub"), DesiredCapabilities.chrome());

        //chrome browser
        //System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        //ChromeOptions chromeOptions = new ChromeOptions();
        //driver = new ChromeDriver(chromeOptions);

        //firefox browser
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();

        //ie browser
        //System.setProperty("webdriver.ie.driver","driver/IEDriverServer.exe");
        //driver = new InternetExplorerDriver();

        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        driver.manage().window().maximize();
        //pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        magazinePage = new MagazinePage(driver);
        comicPage = new ComicPage(driver);
        readingPage = new ReadingPage(driver);
        collectionPage = new CollectionPage(driver);
        adminPage = new AdminPage(driver);
        commonSteps = new CommonSteps(driver);
    }

    @BeforeMethod
    void beforeMethod() {

    }

    @AfterMethod
    void AfterMethod() {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        driver.switchTo().defaultContent();//exit from iframe
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }

        //////////////////////
        Har har = proxy.getHar();
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            if(response.getStatus() == 500){
                org.junit.Assert.fail(request.getUrl() + " returns 500 error");
            }

            System.out.println(response.getStatus() + " : " + request.getUrl()
                    + ", " + entry.getTime() + "ms");

            //assertThat(response.getStatus(), is(200));
        }
        //////////////////////
    }

    @Test(enabled = false)
    void test_01_CheckTexOnHomePage() {
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(), '8080 Test Library')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Audiobooks and eBooks')]")).isDisplayed());
    }

    @Test
    void test_02_2_registrationIsAvailable_test51() throws InterruptedException {
        String timeStamp = GetTimeStamp();
        mainPage.Register("hotdog", "6659", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("");
    }

    @Test
    void test_02_2_registrationIsAvailable_rbdigitalinternal() throws InterruptedException {
        String timeStamp = GetTimeStamp();
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        mainPage.Register("pointbreak", "6786", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("");
    }

    @Test
    void test_02_1_checkTitle() {
        String actualTitle = driver.getTitle();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualTitle, "RBdigital Gateway");
        Assert.assertEquals(actualUrl, "https://www.rbdigitalqa.com/test51/");
    }

    @Test
    void test_04_correctLoginIsAvailable_test51() {
        mainPage.Login("mar13@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("mar13");
    }

    @Test(alwaysRun = true)
    void test_03_correctLoginIsAvailable_rbdigitalinternal() {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("mar12a@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("mar12a");
    }

    @Test
    void test_05_IncorrectLogin_EmptyUserNameAndPassword() {
        mainPage.LoginUnsuccessful("", "");
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Registration required')]")));
        Assert.assertTrue(driver.findElement(By.id("login_dialog")).getText().contains("Registration required"));
    }

    @Test
    void  test_06_magazineCheckoutAndReadAreAvailable() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='Your Crochet Home']")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openMagazineReadingPage(264457);
        String magazineUrl2 = getCurrentUrl();
        readingPage.openMagazinePageFromTableOfContents(264457, 4);

        checkUrlContains(magazineUrl2, "com/reader.php#/reader/readsvg/264457/Cover");
    }

    @DataProvider
    public Iterator<Object[]> checkoutForReturnList(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {"//img[@alt='Bride To Be - Your Day: Best Real Weddings']"});
        list.add(new Object[] {"//img[@alt='OK! Magazine']"});
        return list.iterator();
    }

    @Test(dataProvider = "checkoutForReturnList")
    void test_07_magazineCheckoutAndReturnAreAvailable(String magazine) throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine(magazine)
                .PressCheckoutBtn()
                .pressKeepBrowsingBtn()
                .OpenMyCollection();
        collectionPage.returnMagazineOrComics();

        commonSteps.thenIShouldNotSee2(magazine);
    }

    @Test
    void test_08_comicCheckoutAndReadAreAvailable() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        comicPage.OpenComicsPage()
                .SelectComics("//img[@alt='X-MEN: SECOND COMING - Special']")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openComicsReadingPage(424672);
        String comicsUrl2 = getCurrentUrl();
        readingPage.openComicsPageFromTableOfContents(424672, 4);
        readingPage.openBookmarks();
        String actualText = getTextFromElement("//h6[contains(text(), 'Select the page you want to bookmark')]");

        checkUrlContains(comicsUrl2, "com/reader.php#/reader/readsvg/424672/Cover");
        checkTextContains(actualText, "Select the page you want to bookmark");
    }

    @Test
    void test_09_comicCheckoutAndReturnAreAvailable() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        comicPage.OpenComicsPage()
                .SelectComics("//img[@alt='X-MEN: SECOND COMING - Special']")
                .PressCheckoutBtn()
                .pressKeepBrowsingBtn()
                .OpenMyCollection();
        collectionPage.returnMagazineOrComics();

        commonSteps.thenIShouldNotSee("X-MEN: SECOND COMING - Special");
    }

    @Test(enabled = false)
    void test_10_IncorrectLogin_IncorrectPassword() {
        mainPage.Login("mar13@gmail.com", "12345");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'Username or password is incorrect')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), 'Username or password is incorrect')]")).getText().contains("Username or password is incorrect"));
    }

    @Test
    void test_11_magazineCheckoutAndReturnAreAvailable_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        mainPage.Login("qauser", "password1");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='PEOPLE Bookazines']")
                .PressCheckoutBtn()
                .pressKeepBrowsingBtn()
                .OpenMyCollection();
        collectionPage.returnMagazineOrComics();

        commonSteps.thenIShouldNotSee("PEOPLE Bookazines");
    }

    @Test
    void test_12_comicCheckoutReadAreAvailable_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("qauser", "password1");
        comicPage.OpenComicsPageRbdigitalinternal()
                .SelectComics("//img[@alt='CAPTAIN MARVEL VOL. 1: HIGHER, FURTHER, FASTER, MORE - Special']")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openComicsReadingPage(424456);
        comicsUrl3 = getCurrentUrl();
        readingPage.openComicsPageFromTableOfContents(424456, 4);
        readingPage.openBookmarks();
        String actualText = getTextFromElement("//h6[contains(text(), 'Select the page you want to bookmark')]");

        checkUrlContains(comicsUrl3, "com/reader.php#/reader/readsvg/424456/Cover");
        checkTextContains(actualText, "Select the page you want to bookmark");
    }

    @Test(enabled = false)
    void test_14_comicReturnIsAvailable_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        comicPage.OpenComicsPageRbdigitalinternal();
        comicPage.OpenMyCollection();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class = 'image trash']")));
        //String com = driver.findElement(By.xpath("//a[contains(text(), '"+comicsName+"')]")).getText();
        String comicsId = comicsUrl3.substring(56, 61);
        collectionPage.clickTrashBtn();
        checkAlert("Are you sure?\nYou want to remove issue from your reading collection");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[href*='/magazines/proxy/rbdigitalinternal/magazine-reader/" + comicsId + "']")));
        Assert.assertFalse(driver.findElements(By.cssSelector("a[href*='/magazines/proxy/test51/magazine-reader/" + comicsId + "']")).size() != 0);
    }

    @Test
    void test_15_SearchMagazineCheckoutReadArrowNextAreAvailable() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage()
                .SearchMagazine("Zigzag Photo Journal")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openMagazineReadingPage(343054);
        magazineUrl4 = getCurrentUrl();
        readingPage.openMagazinePageFromTableOfContents(343054, 4);
        openMagazineComicsPage(6);
        pressArrowNextFromPage(6);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page-6")));

        checkUrlContains(magazineUrl4, "com/reader.php#/reader/readsvg/343054/Cover");
    }

    @Test
    void test_16_searchComicCheckoutReadArrowNextAreAvailable() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        comicPage.OpenComicsPage()
                .SearchComic("Black Dynamite")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openComicsReadingPage(389797);
        comicsUrl4 = getCurrentUrl();
        readingPage.openComicsPageFromTableOfContents(389797, 2);
        openMagazineComicsPage(4);
        pressArrowNextFromPage(4);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page-6")));

        checkUrlContains(comicsUrl4, "com/reader.php#/reader/readsvg/389797/Cover");
    }


    @Test(enabled = false)
    void test_17_openMagazinesCheckPagination() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        //driver.findElement(By.cssSelector("a[title = 'Go to page 3']")).click();
        magazinePage.GoToPagePagination(3);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//strong[contains(text(), '[3]')]")));

        magazinePage.PressNextBtnPagination();

        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//strong[contains(text(), '[4]')]")));

        magazinePage.PressPreviousBtnPagination();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//strong[contains(text(), '[2]')]")));

        magazinePage.GoToLastPagePagination();

        if (driver.findElements(By.cssSelector("div[class = 'magazine']")).size() == 0) {
            Assert.fail();
        }

        magazinePage.CheckFirstPagePagination();
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//strong[contains(text(), '[1]')]")));
        //if(driver.findElements(By.cssSelector("div[class = 'magazine']")).size() == 0)
        //{
        //    Assert.fail();
        //}

    }

    @Test
    void test_18_openMagazinesCheckGenresCheckDetailPage() throws InterruptedException, IOException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("genre_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='cycling']")));
        SelectFromSelectByIdAndValue("genre_search_line", "cycling");
        magazinePage.SelectMagazine("//img[@alt='Ultimate Adventure Bike']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Cycling')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='architecture']")));
        SelectFromSelectByIdAndValue("genre_search_line", "architecture");
        magazinePage.SelectMagazine("//img[@alt='Architectural Digest']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Architecture')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='art-photo']")));
        SelectFromSelectByIdAndValue("genre_search_line", "art-photo");
        magazinePage.SelectMagazine("//img[@alt='The Pastel Journal']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Art & Photo')]")));

        List<String> actualReport = adminPage.GetActualDatadef("//div[@class='magazine_detail_content']", "TestRBDigital_Gateway/QA/Test_18_OpenMagazinesCheckGenresCheckDetailPage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestRBDigital_Gateway/QA/Test_18_OpenMagazinesCheckGenresCheckDetailPage/expected.txt");

        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void test_19_openComicsCheckGenresCheckDetailPage() throws InterruptedException, IOException {
        SoftAssert softAssert = new SoftAssert();

        mainPage.Login("mar13@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("genre_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='science-fiction']")));
        SelectFromSelectByIdAndValue("genre_search_line", "science-fiction");
        magazinePage.SelectMagazine("//img[@alt='Black Dynamite']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'Science Fiction')]")));
        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'genre: Science Fiction')]")).size() == 0, "ERROR - genre: Science Fiction");


        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='horror']")));
        SelectFromSelectByIdAndValue("genre_search_line", "horror");
        magazinePage.SelectMagazine("//img[@alt='30 Days of Night']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre')]")));
        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'genre: Horror')]")).size() == 0, "ERROR - genre: Horror");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='superheroes']")));
        SelectFromSelectByIdAndValue("genre_search_line", "superheroes");
        magazinePage.SelectMagazine("//img[@alt='X-MEN: SECOND COMING - Special']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre')]")));
        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'genre: Superheroes')]")).size() == 0, "ERROR - genre: Superheroe");

        List<String> actualReport = adminPage.GetActualDatadef("//div[@class='magazine_detail_content']", "TestRBDigital_Gateway/QA/Test_19_OpenComicsCheckGenresCheckDetailPage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestRBDigital_Gateway/QA/Test_19_OpenComicsCheckGenresCheckDetailPage/expected.txt");
        softAssert.assertAll();

        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void test_20_openMagazinesCheckLanguages() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        mainPage.Login("mar13@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("language_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='french']")));
        SelectFromSelectByIdAndValue("language_search_line", "french");
        magazinePage.SelectMagazine("//img[@alt='All Natura']");

        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'language: French')]")).size() == 0, "ERROR - language: Afrikaans is not present");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='english']")));
        SelectFromSelectByIdAndValue("language_search_line", "english");
        magazinePage.SelectMagazine("//img[@alt='Your Crochet Home']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: English')]")));

        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'language: English')]")).size() == 0, "ERROR - language: Afrikaans is not present");

        softAssert.assertAll();


        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='danish']")));
        //SelectFromSelectByIdAndValue("language_search_line", "danish");
        //magazinePage.SelectMagazine("//img[@alt='Datatid']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: Danish')]")));

        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='dutch']")));
        //SelectFromSelectByIdAndValue("language_search_line", "german");
        //magazinePage.SelectMagazine("//img[@alt='Eltern Family']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: German')]")));

        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='french']")));
        //SelectFromSelectByIdAndValue("language_search_line", "french");
        //magazinePage.SelectMagazine("//img[@alt='01net']");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'language: French')]")).isDisplayed());
    }

    @Test
    void test_21_openComicsCheckLanguages() throws InterruptedException {
        mainPage.Login("mar13@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("language_search_line")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='english']")));
        SelectFromSelectByIdAndValue("language_search_line", "english");
        magazinePage.SelectMagazine("//img[@alt='Judge Dredd, Vol. 2']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: English')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'language: English')]")).isDisplayed());
    }

    @Test
    void  test_22_magazineCheckoutFromBackIssuesIsAvailable_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        mainPage.Login("qauser", "password1");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='The Economist']")
                .SelectMagazineFromBackIssues(2)
                .PressStartReadingBtn()
                .openMagazineReadingPage(463534);
        String magazineUrl2 = getCurrentUrl();
        readingPage.openMagazinePageFromTableOfContents(463534, 4);

        checkUrlContains(magazineUrl2, "com/reader.php#/reader/readsvg/463534/Cover");
    }

    @Test
    void test_23_forgotPasswordIsAvailable() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        String timeStamp = GetTimeStamp();
        mainPage.Register("hotdog", "6659", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.Logout();
        mainPage.forgotPassword( timeStamp+"@gmail.com");

        commonSteps.thenIShouldSeeText("sent an email to " + timeStamp);
    }

    @Test
    void test_24_getBrowserLogs(){
        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
    }

}



