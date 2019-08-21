package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ServicePage {
    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public ServicePage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='#'][@class='button']")
    WebElement getStartedBtn;


    public void UsePageObj(){
        pageObj = new PageObj(driver);
    }

    public void UseMainPage(){
        mainPage = new MainPage(driver);
    }

    public void PressGetStartedButton(){
        UsePageObj();
        pageObj.ClickInFieldByXpath("//a[@href='#'][@class='button']");
    }
}
