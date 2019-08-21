package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadingPage {
    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public ReadingPage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "thumbs-toggle")
    public WebElement tableOfContentsBtn;

    @FindBy(id = "bookmarks-toggle")
    public WebElement bookmarksBtn;

    public void OpenMagazinePageFromListWaitForSpinnerLoading(int magazineID, int pageNumber){
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[href = '#/reader/readsvg/"+magazineID+"/"+pageNumber+"']")));
        driver.findElement(By.cssSelector("a[href = '#/reader/readsvg/"+magazineID+"/"+pageNumber+"']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page_"+pageNumber+"")));
    }

    public void OpenComicsPageFromListWaitForSpinnerLoading(int comicsID, int pageNumber){
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[href = '#/reader/readsvg/"+comicsID+"/"+pageNumber+"']")));
        driver.findElement(By.cssSelector("a[href = '#/reader/readsvg/"+comicsID+"/"+pageNumber+"']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page_"+pageNumber+"")));
    }

    public void openMagazinePageFromTableOfContents(int magazineID, int pageNumber){
        tableOfContentsBtn.click();
        OpenMagazinePageFromListWaitForSpinnerLoading(magazineID, pageNumber);
    }

    public void openComicsPageFromTableOfContents(int comicsID, int pageNumber){
        tableOfContentsBtn.click();
        OpenComicsPageFromListWaitForSpinnerLoading(comicsID, pageNumber);
    }

    public void openBookmarks(){
        driver.findElement(By.cssSelector("div[title = 'Bookmarks']")).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h6[class = 'ModalSubtitle-id_1k6fsov dRFabQ']")));
    }
}
