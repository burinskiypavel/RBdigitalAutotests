package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class ComicPage {

    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public ComicPage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    public ComicPage OpenComicsPage() throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='service/comics']")));
        //driver.findElement(By.xpath("//a[contains(@href, 'service/comics')]")).click();
        driver.findElement(By.cssSelector("a[href*='service/comics']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Browse Comics')]")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("title_search_line")));
        return this;
    }

    public ComicPage pressKeepBrowsingBtn(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Keep Browsing')]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'yoursupport@recordedbooks.com')]")));
        driver.findElement(By.xpath("//a[contains(text(), 'Keep Browsing')]")).click();
        return this;
    }

    public ComicPage OpenComicsPageRbdigitalinternal() throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='service/comics']")));
        //driver.findElement(By.xpath("//a[contains(@href, 'service/comics')]")).click();
        driver.findElements(By.cssSelector("a[href*='service/comics']")).get(0).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Browse Comics')]")).click();
        return this;
    }

    public ComicPage SelectComics(String xpath) throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);

        int count=0;
        while(driver.findElements(By.cssSelector("div[class = 'magazine-card']")).size() == 0 && count < 5 ){
            Thread.sleep(1500);
            count++;
        }

        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class = 'magazine-card']")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public ComicPage PressCheckoutBtn(){
        driver.findElement(By.xpath("//a[@class='button'][@title='Checkout Now']")).click();
        return this;
    }

    public ComicPage PressStartReadingBtn(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 43);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Start Reading')]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'yoursupport@recordedbooks.com')]")));
        driver.findElement(By.xpath("//span[contains(text(), 'Start Reading')]")).click();
        return this;
    }

    public void SwitchToTab(int window){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(window));
    }

    public void SwitchToIFrameOpenComicReadingPage(int comicsID) throws InterruptedException {
        Thread.sleep(5000);
        int size = driver.findElements(By.tagName("iframe")).size();
        //driver.switchTo().frame(0);
        Thread.sleep(4000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src = 'https://rb_reader.sbx.zinioapps.com/#/reader/readsvg/"+comicsID+"']")));
        //driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[src = 'https://rb_reader.sbx.zinioapps.com/#/reader/readsvg/438090']")));
        Wait<WebDriver> wait2 = new WebDriverWait(driver, 30);
        //Thread.sleep(1000);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[title = 'Bookmarks']")));
    }

    public void OpenMyCollection(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[href*='service/comics/user-collection']")));
        driver.findElement(By.cssSelector("a[href*='service/comics/user-collection']")).click();
    }

    public void SwitchToTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int comicsPageIndex = tabs.size() - 1;
        driver.switchTo().window(tabs.get(comicsPageIndex));
    }

    public ComicPage SearchComic(String comicName){
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        WebElement search = driver.findElement(By.id("title_search_line"));
        search.sendKeys(comicName);
        search.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("img[alt = '"+comicName+"']")));
        driver.findElement(By.cssSelector("img[alt = '"+comicName+"']")).click();
        return this;
    }

    public void SwitchToIFrameOpenComicsReadingPage(int comicsID) throws InterruptedException {
        Thread.sleep(5000);
        int size = driver.findElements(By.tagName("iframe")).size();
        //driver.switchTo().frame(0);
        Thread.sleep(4000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src = 'https://rb_reader.sbx.zinioapps.com/#/reader/readsvg/"+comicsID+"']")));
        //driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[src = 'https://rb_reader.sbx.zinioapps.com/#/reader/readsvg/438090']")));
        Wait<WebDriver> wait2 = new WebDriverWait(driver, 30);
        //Thread.sleep(1000);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[title = 'Bookmarks']")));
    }

    public ComicPage openComicsReadingPage(int comicsID) throws InterruptedException {
        SwitchToTab();
        SwitchToIFrameOpenComicsReadingPage(comicsID);
        return this;
    }

}
