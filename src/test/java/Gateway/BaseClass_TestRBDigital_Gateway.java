package Gateway;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseClass_TestRBDigital_Gateway  {
    //public WebDriver driver;
    public WebDriver driver;

    @AfterClass
    void afterClass() {
        driver.close();
        driver.quit();
    }

    //public PageObj(WebDriver driver2001) {
    //    this.driver = driver2001;
    //}


    //public Page_obj(WebDriver driver) {
    //   webDriver = driver;
    //wait = new WebDriverWait(driver, 30);

    //   PageFactory.initElements(webDriver, this);
    // }

    public void pressKey(Keys key) {
        Actions act = new Actions(driver);
        act.sendKeys(key).perform();
    }

    public void TypeInFieldByName(String name, String text) {
        //webDriver.findElement(By.name(name)).sendKeys(text);
        driver.findElement(By.name(name)).sendKeys(text);
    }

    public void TypeInFieldById(String id, String text) {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    public void ClickOnButtonByName(String name) {
        driver.findElement(By.name(name)).click();
    }

    public void ClickOnButtonById(String id) {
        driver.findElement(By.id(id)).click();
    }

    public void ClickInFieldByLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public void ClickInFieldByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void ClickInFieldByCSS(String css) {
        driver.findElement(By.cssSelector(css)).click();
    }

    public void SelectFromSelectByIdAndValue(String id, String value) {
        WebElement selectElem = driver.findElement(By.id(id));
        Select select = new Select(selectElem);
        //Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='"+value+"']")));
        select.selectByValue(value);
    }

    public void SelectCheckboxById(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String GetTimeStamp() {
        String timeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void CheckURL(String url) {
        Wait<WebDriver> wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.urlToBe(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void checkUrlContains(String Url, String contains) {
        Assert.assertTrue(Url.contains(contains));
    }

    public String getTextFromElement(String xpath) {
        String text = driver.findElement(By.xpath(xpath)).getText();
        return text;
    }

    public void checkTextContains(String text, String contains){
        Assert.assertTrue(text.contains(contains));
    }


    public void openMagazineComicsPageStage(int page) {
        driver.findElement(By.id("thumbs-toggle")).click();
        driver.findElement(By.id("thumb_"+page+"")).click();
    }

    public void openMagazineComicsPage(int page) {
        driver.findElement(By.id("thumbs-toggle")).click();
        driver.findElement(By.id("toc-page-thumb-"+page+"")).click();
    }

    public void pressArrowNextFromPage(int pageNumber) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("page_"+pageNumber+"")));
        driver.findElement(By.id("page_"+pageNumber+"")).click();
        driver.findElement(By.id("arrow-next")).click();
    }

    public void checkAlert(String  expectedAlertText) {
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText,  expectedAlertText);
    }
}
    /*
    public void Register2(String accessKey, String selectValueFromSelect, String firstName, String lastName, String email, String password){
        ClickInFieldByLinkText("Create New Account");

        Wait<WebDriver> wait2 = new WebDriverWait(driver, 35);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("access_key")));

        TypeInFieldById("access_key", accessKey);
        ClickOnButtonById("pl_next");

        Wait<WebDriver> wait4 = new WebDriverWait(driver, 35);
        wait4.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("child_lib_id")));

        SelectFromSelectByIdAndValue("child_lib_id", selectValueFromSelect);
        //String timeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        TypeInFieldById("pname", firstName);
        TypeInFieldById("plname", lastName);
        TypeInFieldById("email", email);
        TypeInFieldById("r_email", email);
        TypeInFieldById("password", password);
        TypeInFieldById("r_password", password);
        ClickOnButtonById("pl_create_account");

        Wait<WebDriver> wait5 = new WebDriverWait(driver, 35);
        wait5.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("tos_agree")));

        SelectCheckboxById("tos_agree");
        ClickOnButtonById("pl_ok");
    }
    */

    //public void Login(String username, String password){
    //    driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
    //    WebElement loginButton = driver.findElement(By.name("pl_login"));
    //  Assert.assertTrue(loginButton.isDisplayed());
    //    TypeInFieldByName("username", username);
    //    TypeInFieldByName("password", password);
    //     ClickOnButtonByName("pl_login");
    //}

    //public void NavigateTo(String url){
    //     driver.navigate().to(url);
    //     Wait<WebDriver> wait = new WebDriverWait(driver, 30);
    //    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
    //}

