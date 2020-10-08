package Gateway.PROD;

import Gateway.BaseClass_TestRBDigital_Gateway;
import Gateway.Steps.CommonSteps;
import Gateway.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Test
public class TestRBdigital_Gateway_PROD extends BaseClass_TestRBDigital_Gateway {
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


    @BeforeClass
    void beforeClass() throws MalformedURLException {

        //docker
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());

        //selenium server
        //driver = new RemoteWebDriver(new URL("http://192.168.32.51:4444/wd/hub"), DesiredCapabilities.chrome());


        //chrome browser
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        //firefox browser
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();

        //ie browser
        //System.setProperty("webdriver.ie.driver","driver/IEDriverServer.exe");
        //driver = new InternetExplorerDriver();

        driver.navigate().to("https://www.rbdigital.com/test51/");
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
        driver.navigate().to("https://www.rbdigital.com/test51/");
        driver.switchTo().defaultContent();//exit from iframe
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
    }

    @Test(enabled = false)
    void test_01_CheckTexOnHomePage() {
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(), '8080 Test Library')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Audiobooks and eBooks')]")).isDisplayed());
    }

    @Test
    void test_02_2_Registration_test51() throws InterruptedException {
        String timeStamp = GetTimeStamp();
        mainPage.Register("hotdog", "6659", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("");
    }

    @Test
    void test_02_2_Registration_rbdigitalinternal() throws InterruptedException {
        String timeStamp = GetTimeStamp();
        driver.navigate().to("https://www.rbdigital.com/rbdigitalinternal/");
        mainPage.Register("pointbreak", "6789", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("");
    }

    @Test
    void test_02_1_CheckTitle() {
        String actualTitle = driver.getTitle();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualTitle, "RBdigital Gateway");
        Assert.assertEquals(actualUrl, "https://www.rbdigital.com/test51/");
    }

    @Test
    void test_04_CorrectLogin_test51() {
        mainPage.Login("oct10@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("oct");
    }

    @Test
    void test_03_CorrectLogin_rbdigitalinternal() {
        driver.navigate().to("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("nov23@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("23");
    }

    @Test
    void test_05_IncorrectLogin_EmptyUserNameAndPassword() {
        mainPage.LoginUnsuccessful("", "");
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Registration required')]")));
        Assert.assertTrue(driver.findElement(By.id("login_dialog")).getText().contains("Registration required"));
    }

    @Test
    void test_06_MagazineCheckoutAndReadAreAvailable() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='Us Weekly']")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openMagazineReadingPageProd(480766);
        String magazineUrl2 = getCurrentUrl();
        readingPage.openMagazinePageFromTableOfContents(480766, 4);

        checkUrlContains(magazineUrl2, "com/reader.php#/reader/readsvg/480766/Cover");
    }

    @Test
    void test_07_MagazineCheckoutAndReturnAreAvailable() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='AppleMagazine']")
                .PressCheckoutBtn()
                .pressKeepBrowsingBtn()
                .OpenMyCollection();
        collectionPage.returnMagazineOrComics();

        commonSteps.thenIShouldNotSee("AppleMagazine");
    }

    @Test
    void test_08_ComicCheckoutAndReadAreAvailable() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        comicPage.OpenComicsPage()
                .SelectComics("//img[@alt='Judge Dredd, Vol. 2']")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openComicsReadingPageProd(387508);
        String comicsUrl2 = getCurrentUrl();
        readingPage.openComicsPageFromTableOfContents(387508, 4);
        readingPage.openBookmarks();
        String actualText = getTextFromElement("//h6[contains(text(), 'Select the page you want to bookmark')]");

        checkUrlContains(comicsUrl2, "com/reader.php#/reader/readsvg/387508/Cover");
        checkTextContains(actualText, "Select the page you want to bookmark");
    }

    @Test
    void test_09_ComicCheckoutAndReturnAreAvailable() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        comicPage.OpenComicsPage()
                .SelectComics("//img[@alt='Army of Two, Vol. 1: Across The Border']")
                .PressCheckoutBtn()
                .pressKeepBrowsingBtn()
                .OpenMyCollection();
        collectionPage.returnMagazineOrComics();

        commonSteps.thenIShouldNotSee("Army of Two, Vol. 1: Across The Border");
    }

    @Test(enabled = false)
    void test_10_IncorrectLogin_IncorrectPassword() {
        mainPage.Login("oct10@gmail.com", "12345");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'Username or password is incorrect')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), 'Username or password is incorrect')]")).getText().contains("Username or password is incorrect"));
    }

    @Test
    void test_11_MagazineCheckoutAndReturnAreAvailable_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigital.com/rbdigitalinternal/");
        mainPage.Login("qauser", "password1");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='Appetizers']")
                .PressCheckoutBtn()
                .pressKeepBrowsingBtn()
                .OpenMyCollection();
        collectionPage.returnMagazineOrComics();

        commonSteps.thenIShouldNotSee("Appetizers");
    }

    @Test
    void test_12_ComicCheckoutRead_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigital.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("qauser", "password1");
        comicPage.OpenComicsPageRbdigitalinternal()
                .SelectComics("//img[@alt='SPIDER-MAN: MILES MORALES VOL. 1 - Special']")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openComicsReadingPageProd(424635);
        comicsUrl3 = getCurrentUrl();
        readingPage.openComicsPageFromTableOfContents(424635, 4);
        readingPage.openBookmarks();
        String actualText = getTextFromElement("//h6[contains(text(), 'Select the page you want to bookmark')]");

        checkUrlContains(comicsUrl3, "com/reader.php#/reader/readsvg/424635/Cover");
        checkTextContains(actualText, "Select the page you want to bookmark");
    }

    @Test(enabled = false)
    void test_14_ComicReturn_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigital.com/rbdigitalinternal/");
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
    void test_15_SearchMagazineCheckoutReadArrowNextIsAvailable() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage()
                .SearchMagazine("Zen et bien dans ma vie")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openMagazineReadingPageProd(453469);
        magazineUrl4 = getCurrentUrl();
        readingPage.openMagazinePageFromTableOfContents(453469, 4);
        openMagazineComicsPage(6);
        pressArrowNextFromPage(6);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page-6")));

        checkUrlContains(magazineUrl4, "com/reader.php#/reader/readsvg/453469/Cover");
    }

    @Test
    void test_16_SearchComicCheckoutReadArrowNextIsAvailable() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        comicPage.OpenComicsPage()
                .SearchComic("Black Dynamite")
                .PressCheckoutBtn()
                .PressStartReadingBtn()
                .openComicsReadingPageProd(389797);
        comicsUrl4 = getCurrentUrl();
        readingPage.openComicsPageFromTableOfContents(389797, 2);
        openMagazineComicsPage(4);
        pressArrowNextFromPage(4);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page-6")));

        checkUrlContains(comicsUrl4, "com/reader.php#/reader/readsvg/389797/Cover");
    }


    @Test(enabled = false)
    void test_17_OpenMagazinesCheckPagination() throws InterruptedException {
        mainPage.Login("jan16@gmail.com", "12345qw");
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
    void test_18_OpenMagazinesCheckGenresCheckDetailPage() throws InterruptedException, IOException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("genre_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='cycling']")));
        SelectFromSelectByIdAndValue("genre_search_line", "cycling");
        magazinePage.SelectMagazine("//img[@alt='Bike']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Cycling')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='architecture']")));
        SelectFromSelectByIdAndValue("genre_search_line", "architecture");
        magazinePage.SelectMagazine("//img[@alt='AD France']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Architecture')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='art-photo']")));
        SelectFromSelectByIdAndValue("genre_search_line", "art-photo");
        magazinePage.SelectMagazine("//img[@alt='Outdoor Photographer']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Art & Photo')]")));

        List<String> actualReport = adminPage.GetActualDatadef("//div[@class='magazine_detail_content']", "TestRBDigital_Gateway/PROD/Test_18_OpenMagazinesCheckGenresCheckDetailPage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestRBDigital_Gateway/PROD/Test_18_OpenMagazinesCheckGenresCheckDetailPage/expected.txt");

        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void test_19_OpenComicsCheckGenresCheckDetailPage() throws InterruptedException, IOException {
        SoftAssert softAssert = new SoftAssert();

        mainPage.Login("oct10@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("genre_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='science-fiction']")));
        SelectFromSelectByIdAndValue("genre_search_line", "science-fiction");
        magazinePage.SelectMagazine("//img[@alt='Black Dynamite']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'Science Fiction')]")));
        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'genre: Science Fiction')]")).size() == 0, "ERROR - genre: Science Fiction");


        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='horror']")));
        SelectFromSelectByIdAndValue("genre_search_line", "horror");
        magazinePage.SelectMagazine("//img[@alt='Night Mary']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre')]")));
        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'genre: Horror')]")).size() == 0, "ERROR - genre: Horror");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='superheroes']")));
        SelectFromSelectByIdAndValue("genre_search_line", "superheroes");
        magazinePage.SelectMagazine("//img[@alt='X-MEN: SECOND COMING - Special']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre')]")));
        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'genre: Superheroes')]")).size() == 0, "ERROR - genre: Superheroe");

        List<String> actualReport = adminPage.GetActualDatadef("//div[@class='magazine_detail_content']", "TestRBDigital_Gateway/PROD/Test_19_OpenComicsCheckGenresCheckDetailPage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestRBDigital_Gateway/PROD/Test_19_OpenComicsCheckGenresCheckDetailPage/expected.txt");
        softAssert.assertAll();

        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void test_20_OpenMagazinesCheckLanguages() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        mainPage.Login("oct10@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("language_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='french']")));
        SelectFromSelectByIdAndValue("language_search_line", "french");
        magazinePage.SelectMagazine("//img[@alt='7 Jours']");

        softAssert.assertFalse(driver.findElements(By.xpath("//p[contains(text(), 'language: French')]")).size() == 0, "ERROR - language: Afrikaans is not present");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='english']")));
        SelectFromSelectByIdAndValue("language_search_line", "english");
        magazinePage.SelectMagazine("//img[@alt='The New Yorker']");
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
    void test_21_OpenComicsCheckLanguages() throws InterruptedException {
        mainPage.Login("oct10@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("language_search_line")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='english']")));
        SelectFromSelectByIdAndValue("language_search_line", "english");
        magazinePage.SelectMagazine("//img[@alt='Judge Dredd, Vol. 2']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: English')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'language: English')]")).isDisplayed());
    }

    @Test
    void  test_22_MagazineCheckoutFromBackIssues_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigital.com/rbdigitalinternal/");
        mainPage.Login("qauser", "password1");
        magazinePage.OpenMagazinesPage()
                .SelectMagazine("//img[@alt='The Economist']")
                .SelectMagazineFromBackIssues(1)
                .PressStartReadingBtn()
                .openMagazineReadingPageProd(463535);
        String magazineUrl2 = getCurrentUrl();
        readingPage.openMagazinePageFromTableOfContents(463535, 4);

        checkUrlContains(magazineUrl2, "com/reader.php#/reader/readsvg/463535/Cover");
    }

    @Test
    void test_23_ForgotPassword() throws InterruptedException {
        driver.navigate().to("https://www.rbdigital.com/test51/");
        String timeStamp = GetTimeStamp();
        mainPage.Register("hotdog", "6659", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.Logout();
        mainPage.forgotPassword( timeStamp+"@gmail.com");

        commonSteps.thenIShouldSeeText("sent an email to " + timeStamp);
    }

}



