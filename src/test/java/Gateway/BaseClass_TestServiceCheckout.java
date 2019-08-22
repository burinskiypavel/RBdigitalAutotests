package Gateway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass_TestServiceCheckout  {
    public WebDriver driver;
    public WebDriverWait wait;

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
        //webDriver.findElement(By.name(name)).sendKeys(text);
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

}
