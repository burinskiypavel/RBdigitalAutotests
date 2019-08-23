package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

public class MagazinePage {
    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public MagazinePage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "//a[href='www.rbdigitalqa.com/test51/service/magazines/user-collection']")
    WebElement MyCollectionBtn;

    @FindBy(css = "//a[title*='next']")
    WebElement nextBtnPagination;

    @FindBy(css = "a[title = 'The previous page']")
    WebElement previousBtnPagination;

    @FindBy(css = "a[title = 'The last page']")
    WebElement lastPageBtnPagination;

    @FindBy(css = "a[title = 'The first page']")
    WebElement firstPageBtnPagination;


    public void UsePageObj(){
        pageObj = new PageObj(driver);
    }

    public void UseMainPage(){
        mainPage = new MainPage(driver);
    }

    public void OpenMagazinesPage() throws InterruptedException {
        //driver.findElement(By.xpath("//a[contains(@href, 'com/test51/service/magazines')]")).click()
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Learn more')]")));
        Thread.sleep(1500);

        WebElement learnMore = driver.findElements(By.xpath("//a[contains(text(), 'Learn more')]")).get(1);
        learnMore.click();
        driver.findElement(By.xpath("//a[contains(text(), 'Browse Magazines')]")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("title_search_line")));
    }

    public void SelectMagazine(String xpath) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void PressCheckoutBtn(){
        driver.findElement(By.xpath("//a[@class='button'][@title='Checkout Now']")).click();
    }

    public void PressStartReadingBtn(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Start Reading')]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'yoursupport@recordedbooks.com')]")));
        driver.findElement(By.xpath("//span[contains(text(), 'Start Reading')]")).click();
    }

    public void SwitchToSecondTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }


    public void SwitchToTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int readingPageIndex = tabs.size() - 1;
        driver.switchTo().window(tabs.get(readingPageIndex));
    }

    public void SwitchToIFrameOpenMagazineReadingPage(int magazinID) throws InterruptedException {
        Thread.sleep(5000);
        int size = driver.findElements(By.tagName("iframe")).size();
        //driver.switchTo().frame(0);
        Thread.sleep(4000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src = 'https://rb_reader.sbx.zinioapps.com/#/reader/readsvg/"+magazinID+"']")));
        //driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[src = 'https://rb_reader.sbx.zinioapps.com/#/reader/readsvg/438090']")));
        Wait<WebDriver> wait2 = new WebDriverWait(driver, 30);
        //Thread.sleep(1000);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[title = 'Bookmarks']")));
    }

    public void OpenMyCollection(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class*='my_collection_link']")));
        driver.findElement(By.cssSelector("a[class*='my_collection_link']")).click();
    }

    //public void OpenMagazinePageFromListWaitForSpinnerLoading(int magazineID, int pageNumber){
    //    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
    //    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[href = '#/reader/readsvg/"+magazineID+"/"+pageNumber+"']")));
     //   driver.findElement(By.cssSelector("a[href = '#/reader/readsvg/"+magazineID+"/"+pageNumber+"']")).click();
    //    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page_"+pageNumber+"")));

    //}

    public void SearchMagazine(String magazineName) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("title_search_line")));
        WebElement search = driver.findElement(By.id("title_search_line"));
        search.click();
        search.clear();
        search.sendKeys(magazineName);
        search.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("img[alt = '"+magazineName+"']")));
        driver.findElement(By.cssSelector("img[alt = '"+magazineName+"']")).click();
    }

    public void GoToPagePagination(int page){
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title = 'Go to page "+page+"']")));
        driver.findElement(By.cssSelector("a[title = 'Go to page "+page+"']")).click();
    }


    public void PressNextBtnPagination() throws InterruptedException {
        //nextBtnPagination.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Next')]")));
        WebElement element = driver.findElement(By.xpath("//div[@class='links']"));
        element.findElement(By.xpath("//a[contains(text(), 'Next')]")).click();

        //driver.findElement(By.cssSelector("a[onclick='OnMagazineCollectionSearch( '5'); return false;']")).click();
        int a = driver.findElements(By.xpath("a[title ='The last page']")).size();
    }

    public void PressPreviousBtnPagination(){
        previousBtnPagination.click();
    }

    public void GoToLastPagePagination() {
        //lastPageBtnPagination.click();
        driver.findElement(By.xpath("//a[contains(text(), '>>')]")).click();
    }

    public void CheckFirstPagePagination() {
        firstPageBtnPagination.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//strong[contains(text(), '[1]')]")));
        if(driver.findElements(By.cssSelector("div[class = 'magazine']")).size() == 0)
        {
            Assert.fail();
        }
    }

    public void openMagazineReadingPage(int magazineID) throws InterruptedException {
        SwitchToTab();
        SwitchToIFrameOpenMagazineReadingPage(magazineID);
    }






    }

