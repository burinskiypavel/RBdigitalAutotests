package Gateway;

import Gateway.Steps.CommonSteps;
import Gateway.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        //firefox
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();


        driver.navigate().to("https://www.rbdigitalqa.com/test51/");
        wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Login')]")));
        driver.manage().window().maximize();

        //pageObj = new PageObj(driver);
        //mainPage = new MainPage(driver);
        //servicePage = new ServicePage(driver);
        //platformPage = new PlatformPage(driver);
        //serviceSitePage = new ServiceSitePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        MainPage mainPage = new MainPage(driver);
        mainPage = new MainPage(driver);
        if (driver.findElements(By.xpath("//div[contains(text(), 'Welcome')]")).size() != 0) {
            mainPage.Logout();
        }
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    void beforeMethod() {

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
        wait = new WebDriverWait(driver, 55);
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
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Welcome')]")));
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
        Wait<WebDriver> wait = new WebDriverWait(driver, 75);
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
        //clickInFieldByXpath(serviceXpath);
        clickInField(By.xpath(serviceXpath));

    }

    public void clickInFieldByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickInField(By locator) {
        driver.findElement(locator).click();
    }

    public void pressGetStartedButton() throws InterruptedException {
        clickInFieldByXpath("//a[@href='#'][@class='button']");
        Thread.sleep(1400);
        if(isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    public void checkText(String text) throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(), '"+text+"')]")).getAttribute("innerText"), text);
        //Assert.assertEquals(driver.findElement(By.xpath("//"+element+"[contains(text(), '"+text+"')]")).getAttribute("innerText"), text);
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
        //clickInFieldByXpath(serviceXpath);
        clickInField(By.xpath(serviceXpath));
    }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }   //try
        catch (NoAlertPresentException Ex){
            return false;
        }   //catch
    }    //isAlertPresent

    public void checkAlert(String  expectedAlertText) {
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText,  expectedAlertText);
    }

    public void checkAlertModal(String  expectedAlertText) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("error_dialog")));
        String alertText = driver.findElement(By.id("error_dialog")).getText();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='close']")));
        driver.findElement(By.cssSelector("button[class='close']")).click();
        Assert.assertEquals(alertText,  expectedAlertText);
    }
}
