package Gateway.pages;

import Gateway.BaseClass_TestRBDigital_Gateway;
import Gateway.PageObj;
import Gateway.Steps.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CollectionPage {
    public WebDriver driver;

    PageObj pageObj;
    CommonSteps commonSteps;


    public CollectionPage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class = 'image trash']")
    public WebElement trashBtn;

    public void clickTrashBtn() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class = 'image trash']")));
        trashBtn.click();
    }

    public void returnMagazineOrComics(){
        commonSteps = new CommonSteps(driver);
        clickTrashBtn();
        commonSteps.checkAlert("Are you sure?\nYou want to remove issue from your reading collection");
    }
}
