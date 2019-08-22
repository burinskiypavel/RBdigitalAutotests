package Gateway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass_TestServiceCheckout  {
    public WebDriver driver;
    public WebDriverWait wait;
    //WebDriver driver;
    //WebDriverWait wait;
    //PageObj pageObj;
    //MainPage mainPage;
    //ServicePage servicePage;
    //PlatformPage platformPage;
    //ServiceSitePage serviceSitePage;


    @BeforeClass
    void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        driver.manage().window().maximize();

        //pageObj = new PageObj(driver);
        //mainPage = new MainPage(driver);
        //servicePage = new ServicePage(driver);
        //platformPage = new PlatformPage(driver);
        //serviceSitePage = new ServiceSitePage(driver);
    }

    @AfterClass
    void afterClass() {
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    void beforeMethod() {

    }

    @AfterMethod
    void AfterMethod() {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        //driver.switchTo().defaultContent();//exit from iframe
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            //mainPage.Logout();
            logout();
        }
    }


    @FindBy(name = "username")
    public WebElement usernameField;

    @FindBy(xpath = "//a[contains(text(), 'Login')]")
    public WebElement loginBtn;

    @FindBy(id = "profile")
    public WebElement profileDropdown;

    @FindBy(xpath = "//a[contains(text(), 'Log out')]")
    public WebElement logoutBtn;

    public void login(String username, String password) {
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
        //loginBtn.click();
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("pl_login")));
        //WebElement loginButton = driver.findElement(By.name("pl_login"));
        //Assert.assertTrue(loginButton.isDisplayed());
        //usernameField.clear();
        driver.findElement(By.name("username")).clear();
        //TypeInFieldByName("username", username);
        driver.findElement(By.name("username")).sendKeys(username);
        //TypeInFieldByName("password", password);
        driver.findElement(By.name("password")).sendKeys(password);
        clickOnButtonByName("pl_login");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Welcome')]")));
    }

    public void logout() {
        //profileDropdown.click();
        driver.findElement(By.id("profile")).click();
        //logoutBtn.click();
        driver.findElement(By.xpath("//a[contains(text(), 'Log out')]")).click();
        //Wait<WebDriver> wait7 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Create New Account')]")));
    }

    public void typeInFieldByName(String name, String text) {
        driver.findElement(By.name(name)).sendKeys(text);
    }

    public void clickOnButtonByName(String name) {
        driver.findElement(By.name(name)).click();
    }

    public void checkURL(String url){
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void checkURLcontains(String url){
        //Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertTrue(driver.getCurrentUrl().contains(url));
    }

    public void goIntoServiceByButtonByXpath(String serviceXpath) throws InterruptedException {
        Thread.sleep(500);
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(serviceXpath)));
        //int count = 0;
        //while(driver.findElements(By.xpath(serviceXpath)).size() == 0 && count < 3){
        //    Thread.sleep(1000);
        //   count++;
        //}
        //int count1 = 0;
        //while(!driver.findElement(By.xpath(serviceXpath)).isDisplayed() && count1 < 3){
        //    Thread.sleep(1000);
        //     count1++;
        //}
        clickInFieldByXpath(serviceXpath);

    }

    public void clickInFieldByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void pressGetStartedButton(){
        clickInFieldByXpath("//a[@href='#'][@class='button']");
    }

    public void checkText(String element, String text){
        //driver.findElement(By.xpath("//"+element+"[contains(text(), '"+text+"')]")).getAttribute("innerText");
        Assert.assertEquals(driver.findElement(By.xpath("//"+element+"[contains(text(), '"+text+"')]")).getAttribute("innerText"), text);
    }


    public void checkElementIsPresent(String css) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(css)));
        Assert.assertTrue(driver.findElement(By.cssSelector(css)).isDisplayed());
    }

    public void navigate(String url) {
        driver.navigate().to(url);
    }

    public void goIntoServiceByPictureByXpath(String serviceXpath) {
        Wait<WebDriver> wait3 = new WebDriverWait(driver, 20);
        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(serviceXpath)));
        clickInFieldByXpath(serviceXpath);
    }
}
