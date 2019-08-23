package Gateway;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseClass {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "username")
    public WebElement usernameField;

    @FindBy(xpath = "//a[contains(text(), 'Login')]")
    public WebElement loginBtn;

    @FindBy(id = "profile")
    public WebElement profileDropdown;

    @FindBy(xpath = "//a[contains(text(), 'Log out')]")
    public WebElement logoutBtn;

    public void Login(String username, String password) {
        //driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
        loginBtn.click();
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("pl_login")));
        //WebElement loginButton = driver.findElement(By.name("pl_login"));
        //Assert.assertTrue(loginButton.isDisplayed());
        usernameField.clear();
        TypeInFieldByName("username", username);
        TypeInFieldByName("password", password);
        ClickOnButtonByName("pl_login");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Welcome')]")));
    }

    public void Logout() {
        profileDropdown.click();
        logoutBtn.click();
        //Wait<WebDriver> wait7 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Create New Account')]")));
    }


    public void TypeInFieldByName(String name, String text) {
        //webDriver.findElement(By.name(name)).sendKeys(text);
        driver.findElement(By.name(name)).sendKeys(text);
    }

    public void ClickOnButtonByName(String name) {
        driver.findElement(By.name(name)).click();
    }

    public void CheckURL(String url){
        Wait<WebDriver> wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.urlToBe(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }



}
