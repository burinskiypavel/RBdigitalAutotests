package Gateway;

import Gateway.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

@Test
public class TestRBDigital_Gateway2 {
    public WebDriver driver;
    PageObj pageObj;
    MainPage mainPage;
    MagazinePage magazinePage;
    ComicPage comicPage;
    ReadingPage readingPage;
    CollectionPage collectionPage;
    AdminPage adminPage;
    String comicsUrl2;
    String magazineUrl2;
    String magazineUrl3;
    String comicsUrl3;
    String magazineUrl4;
    String comicsUrl4;
    WebDriverWait wait;


    @BeforeClass
    void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        //System.setProperty("webdriver.ie.driver","driver/IEDriverServer.exe");
        //WebDriver driver = new InternetExplorerDriver();

        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        driver.manage().window().maximize();
        pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        magazinePage = new MagazinePage(driver);
        comicPage = new ComicPage(driver);
        readingPage = new ReadingPage(driver);
        collectionPage = new CollectionPage(driver);
        adminPage = new AdminPage(driver);
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
        driver.switchTo().defaultContent();//exit from iframe
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
    }

    @Test
    void Test_01_Audiobooks_and_eBooks_check_on_homePage() {
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(), '8080 Test Library')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Audiobooks and eBooks')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).isDisplayed());
    }

    @Test
    void Test_02_2_Registration_test51() throws InterruptedException {
        String timeStamp = pageObj.GetTimeStamp();
        mainPage.Register("hotdog", "3755", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("");
    }

    @Test
    void Test_02_3_Registration_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        String timeStamp = pageObj.GetTimeStamp();
        mainPage.Register("pointbreak", "3802", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        //char character = mainPage.GenerateRadndomCharacters();
        //mainPage.RegisterCardNumber("scl" + character + "", "pointbreak", "3802", timeStamp, timeStamp, timeStamp + "@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("");
    }

    @Test
    void Test_02_1_CheckTitle() {
        String actualTitle = driver.getTitle();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualTitle, "RBdigital Gateway");
        Assert.assertEquals(actualUrl, "https://www.rbdigitalqa.com/test51/");
    }

    @Test
    void Test_03_CorrectLogin() {
        mainPage.Login("jun5@gmail.com", "12345qw");
        mainPage.CheckWelcomeText("jun5@gmail.com");
    }

    @Test
    void Test_10_IncorrectLogin_IncorrectPassword() {
        mainPage.Login("jun5@gmail.com", "12345");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Username or password is incorrect')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), 'Username or password is incorrect')]")).getText().contains("Username or password is incorrect"));
    }

    @Test
    void Test_05_IncorrectLogin_EmptyUserNameAndPassword() {
        mainPage.Login("", "");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Registration required')]")));
        Assert.assertTrue(driver.findElement(By.id("login_dialog")).getText().contains("Registration required"));
    }

    @Test
    void Test_06_1_MagazineCheckoutRead() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        magazinePage.SelectMagazine("//img[@alt='Clean Eating']");
        magazinePage.PressCheckoutBtn();
        magazinePage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        magazinePage.SwitchToTab();
        magazinePage.SwitchToIFrameOpenMagazineReadingPage(446259);
        magazineUrl2 = driver.getCurrentUrl();
        readingPage.tableOfContentsBtn.click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(446259, 4);
        driver.findElement(By.id("thumbs-toggle")).click();
        Assert.assertTrue(actualUrl1.contains("/test51/service/magazines/landing?mag_id=548"));
        Assert.assertTrue(magazineUrl2.contains("com/reader.php#/reader/readsvg/446259/Cover"));
    }

    @Test
    void Test_06_2_MagazineCheckoutRead() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        magazinePage.SelectMagazine("//img[@alt='4 Wheel & Off Road']");
        magazinePage.PressCheckoutBtn();
        magazinePage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        magazinePage.SwitchToTab();
        magazinePage.SwitchToIFrameOpenMagazineReadingPage(431802);
        magazineUrl2 = driver.getCurrentUrl();
        readingPage.tableOfContentsBtn.click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(431802, 4);
        driver.findElement(By.id("thumbs-toggle")).click();
        Assert.assertTrue(actualUrl1.contains("/test51/service/magazines/landing?mag_id=347"));
        Assert.assertTrue(magazineUrl2.contains("com/reader.php#/reader/readsvg/431802/Cover"));
    }


    @Test
    void Test_07_ComicCheckoutRead() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        comicPage.SelectComics("//img[@alt='Army of Two, Vol. 1: Across The Border']");
        comicPage.PressCheckoutBtn();
        comicPage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        comicPage.SwitchToTab();
        comicPage.SwitchToIFrameOpenComicReadingPage(389796);
        comicsUrl2 = driver.getCurrentUrl();
        driver.findElement(By.id("thumbs-toggle")).click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(389796, 4);
        driver.findElement(By.cssSelector("div[title = 'Bookmarks']")).click();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h6[class = 'ModalSubtitle-id_1k6fsov dRFabQ']")));
        String actualText = driver.findElement(By.xpath("//h6[contains(text(), 'Select the page you want to bookmark')]")).getText();
        Assert.assertTrue(actualUrl1.contains("/test51/service/comics/landing?mag_id=1393"));
        Assert.assertTrue(comicsUrl2.contains("com/reader.php#/reader/readsvg/389796/Cover"));
        Assert.assertTrue(actualText.contains("Select the page you want to bookmark"));
    }

    @Test
    void Test_09_MagazineReturn() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        magazinePage.OpenMyCollection();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class = 'image trash']")));
        //String com = driver.findElement(By.xpath("//a[contains(text(), '"+comicsName+"')]")).getText();
        String magazineId = magazineUrl2.substring(55, 61);
        collectionPage.trashBtn.click();
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[href*='/magazines/proxy/test51/magazine-reader/" + magazineId + "']")));
        Assert.assertEquals(alertText, "Are you sure?\nYou want to remove issue from your reading collection");
    }

    @Test
    void Test_08_ComicReturn() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        comicPage.OpenMyCollection();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class = 'image trash']")));
        //String com = driver.findElement(By.xpath("//a[contains(text(), '"+comicsName+"')]")).getText();
        String comicsId = comicsUrl2.substring(56, 61);
        collectionPage.trashBtn.click();
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[href*='/magazines/proxy/test51/magazine-reader/" + comicsId + "']")));
        Assert.assertEquals(alertText, "Are you sure?\nYou want to remove issue from your reading collection");
        //Assert.assertEquals(com, "Angel: Old Friends");
    }

    @Test
    void Test_11_MagazineCheckoutRead_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        mainPage.Login("jul25@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        magazinePage.SelectMagazine("//img[@alt='The New Yorker']");//
        magazinePage.PressCheckoutBtn();
        magazinePage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        magazinePage.SwitchToTab();
        magazinePage.SwitchToIFrameOpenMagazineReadingPage(432288);
        magazineUrl3 = driver.getCurrentUrl();
        readingPage.tableOfContentsBtn.click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(432288, 4);
        driver.findElement(By.id("thumbs-toggle")).click();
        Assert.assertTrue(actualUrl1.contains("/service/magazines/landing?mag_id=6754"));
        Assert.assertTrue(magazineUrl3.contains("com/reader.php#/reader/readsvg/432288/Cover"));
    }

    @Test
    void Test_12_ComicCheckoutRead_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        mainPage.Login("jul25@gmail.com", "12345qw");
        comicPage.OpenComicsPageRbdigitalinternal();
        comicPage.SelectComics("//img[@alt='Transformers: Lost Light, Vol. 1']");
        comicPage.PressCheckoutBtn();
        comicPage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        comicPage.SwitchToTab();
        comicPage.SwitchToIFrameOpenComicReadingPage(455689);
        comicsUrl3 = driver.getCurrentUrl();
        driver.findElement(By.id("thumbs-toggle")).click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(455689, 4);

        driver.findElement(By.cssSelector("div[title = 'Bookmarks']")).click();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h6[class = 'ModalSubtitle-id_1k6fsov dRFabQ']")));

        String actualText = driver.findElement(By.xpath("//h6[contains(text(), 'Select the page you want to bookmark')]")).getText();
        Assert.assertTrue(actualUrl1.contains("/service/comics/landing?mag_id=2534"));
        Assert.assertTrue(comicsUrl3.contains("com/reader.php#/reader/readsvg/455689/Cover"));
        Assert.assertTrue(actualText.contains("Select the page you want to bookmark"));
    }

    @Test
    void Test_13_MagazineReturn_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        //mainPage.Login("jul25@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        magazinePage.OpenMyCollection();
        // Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class = 'image trash']")));
        //String com = driver.findElement(By.xpath("//a[contains(text(), '"+comicsName+"')]")).getText();
        String magazineId = magazineUrl3.substring(55, 61);
        collectionPage.trashBtn.click();
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[href*='/magazines/proxy/rbdigitalinternal/magazine-reader/" + magazineId + "']")));
        Assert.assertEquals(alertText, "Are you sure?\nYou want to remove issue from your reading collection");
    }

    @Test
    void Test_14_ComicReturn_Rbdigitalinternal() throws InterruptedException {
        driver.navigate().to("https://www.rbdigitalqa.com/rbdigitalinternal/");
        comicPage.OpenComicsPageRbdigitalinternal();
        comicPage.OpenMyCollection();
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class = 'image trash']")));
        //String com = driver.findElement(By.xpath("//a[contains(text(), '"+comicsName+"')]")).getText();
        String comicsId = comicsUrl3.substring(56, 61);
        collectionPage.trashBtn.click();
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[href*='/magazines/proxy/rbdigitalinternal/magazine-reader/" + comicsId + "']")));
        Assert.assertEquals(alertText, "Are you sure?\nYou want to remove issue from your reading collection");
    }

    @Test
    void Test_15_SearchMagazineCheckoutReadArrowNext() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();
        magazinePage.SearchMagazine("$10 DINNERS (OR LESS!)");
        magazinePage.PressCheckoutBtn();
        magazinePage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        magazinePage.SwitchToTab();
        magazinePage.SwitchToIFrameOpenMagazineReadingPage(179674);
        magazineUrl4 = driver.getCurrentUrl();
        readingPage.tableOfContentsBtn.click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(179674, 4);

        driver.findElement(By.id("page_4")).click();
        driver.findElement(By.id("arrow-next")).click();

        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page_6")));

        driver.findElement(By.id("thumbs-toggle")).click();
        Assert.assertTrue(actualUrl1.contains("/test51/service/magazines/landing?mag_id=333"));
        Assert.assertTrue(magazineUrl4.contains("com/reader.php#/reader/readsvg/179674/Cover"));
    }

    @Test
    void Test_16_SearchComicCheckoutReadArrowNext() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        comicPage.OpenComicsPage();
        comicPage.SearchComic("Fox Bunny Funny");
        comicPage.PressCheckoutBtn();
        comicPage.PressStartReadingBtn();
        String actualUrl1 = driver.getCurrentUrl();
        comicPage.SwitchToTab();
        comicPage.SwitchToIFrameOpenComicReadingPage(387479);
        comicsUrl4 = driver.getCurrentUrl();
        readingPage.tableOfContentsBtn.click();
        readingPage.OpenMagazinePageFromListWaitForSpinnerLoading(387479, 4);

        driver.findElement(By.id("page_4")).click();
        driver.findElement(By.id("arrow-next")).click();

        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page_6")));

        driver.findElement(By.id("thumbs-toggle")).click();
        Assert.assertTrue(actualUrl1.contains("/test51/service/comics/landing?mag_id=1439"));
        Assert.assertTrue(comicsUrl4.contains("com/reader.php#/reader/readsvg/387479/Cover"));
    }

    @Test
    void Test_17_OpenMagazinesCheckPagination() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        magazinePage.OpenMagazinesPage();

        //driver.findElement(By.cssSelector("a[title = 'Go to page 3']")).click();
        magazinePage.GoToPagePagination(3);
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
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
    void Test_18_OpenMagazinesCheckGenresCheckDetailPage() throws InterruptedException, IOException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        magazinePage.OpenMagazinesPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("genre_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='cycling']")));
        pageObj.SelectFromSelectByIdAndValue("genre_search_line", "cycling");
        magazinePage.SelectMagazine("//img[@alt='Bike France']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Cycling')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='architecture']")));
        pageObj.SelectFromSelectByIdAndValue("genre_search_line", "architecture");
        magazinePage.SelectMagazine("//img[@alt='AD Russia']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Architecture')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='art-photo']")));
        pageObj.SelectFromSelectByIdAndValue("genre_search_line", "art-photo");
        magazinePage.SelectMagazine("//img[@alt='Pro Photo']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Art & Photo')]")));

        List<String> actualReport = adminPage.GetActualDatadef("//div[@class='magazine_detail_content']", "TestRBDigital_Gateway/Test_18_OpenMagazinesCheckGenresCheckDetailPage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestRBDigital_Gateway/Test_18_OpenMagazinesCheckGenresCheckDetailPage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_19_OpenComicsCheckGenresCheckDetailPage() throws InterruptedException, IOException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        comicPage.OpenComicsPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("genre_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='crime-mystery']")));
        pageObj.SelectFromSelectByIdAndValue("genre_search_line", "crime-mystery");
        magazinePage.SelectMagazine("//img[@alt='Parker: The Hunter']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Crime & Mystery')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='horror']")));
        pageObj.SelectFromSelectByIdAndValue("genre_search_line", "horror");
        magazinePage.SelectMagazine("//img[@alt='Lore: Book 1']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: Horror')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='general']")));
        pageObj.SelectFromSelectByIdAndValue("genre_search_line", "general");
        magazinePage.SelectMagazine("//img[@alt='110 Percent']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'genre: General')]")));

        List<String> actualReport = adminPage.GetActualDatadef("//div[@class='magazine_detail_content']", "TestRBDigital_Gateway/Test_19_OpenComicsCheckGenresCheckDetailPage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFiledef("TestRBDigital_Gateway/Test_19_OpenComicsCheckGenresCheckDetailPage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_20_OpenMagazinesCheckLanguages() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        magazinePage.OpenMagazinesPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("language_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='afrikaans']")));
        pageObj.SelectFromSelectByIdAndValue("language_search_line", "afrikaans");
        magazinePage.SelectMagazine("//img[@alt='WegSleep']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: Afrikaans')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='chinese']")));
        pageObj.SelectFromSelectByIdAndValue("language_search_line", "chinese");
        magazinePage.SelectMagazine("//img[@alt='旭茉 Jessica']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: Chinese')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='danish']")));
        pageObj.SelectFromSelectByIdAndValue("language_search_line", "danish");
        magazinePage.SelectMagazine("//img[@alt='Datatid']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: Danish')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='dutch']")));
        pageObj.SelectFromSelectByIdAndValue("language_search_line", "german");
        magazinePage.SelectMagazine("//img[@alt='11 Freunde']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: German')]")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='french']")));
        pageObj.SelectFromSelectByIdAndValue("language_search_line", "french");
        magazinePage.SelectMagazine("//img[@alt='01net']");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'language: French')]")).isDisplayed());
    }

    @Test
    void Test_21_OpenComicsCheckLanguages() throws InterruptedException {
        mainPage.Login("jun5@gmail.com", "12345qw");
        //Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        comicPage.OpenComicsPage();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("language_search_line")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='english']")));
        pageObj.SelectFromSelectByIdAndValue("language_search_line", "english");
        magazinePage.SelectMagazine("//img[@alt='Angel: Old Friends']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'language: English')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'language: English')]")).isDisplayed());
    }

}



