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
import org.testng.Assert;

public class PlatformPage {
    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public PlatformPage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "media-header-audiobooks")
    WebElement audioBooksTab;

    @FindBy(xpath = "//span[@i18n='homepage_label_sign_in']")
    WebElement signIn;

    @FindBy(xpath = "//a[@i18n='homepage_label_register']")
    WebElement register;

    @FindBy(xpath = "//a[@i18n='homepage_label_help']")
    WebElement help;

    @FindBy(xpath = "//span[@class='au-target'][@au-target-id='109']")
    WebElement welcome;

    public void UsePageObj(){
        pageObj = new PageObj(driver);
    }

    public void CheckRegisterSignInHelpWelcomeTextPresent() {
        Wait<WebDriver> wait3 = new WebDriverWait(driver, 30);
        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@i18n='homepage_label_register']")));
        Assert.assertEquals(register.getAttribute("textContent"), "REGISTER");

        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@i18n='homepage_label_sign_in']")));
        Assert.assertEquals(signIn.getAttribute("innerText"), "SIGN IN");

        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@i18n='homepage_label_help']")));
        Assert.assertEquals(help.getAttribute("textContent"), "HELP");

        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='au-target'][@au-target-id='109']")));
        System.out.println(welcome.getAttribute("innerText"));//innerHTML, innerText
        Assert.assertEquals(welcome.getAttribute("textContent"), "WELCOME");
    }
}
