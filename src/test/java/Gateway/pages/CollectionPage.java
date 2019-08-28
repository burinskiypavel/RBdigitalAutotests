package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollectionPage {
    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public CollectionPage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class = 'image trash']")
    public WebElement trashBtn;

    public void clickTrashBtn() {
        trashBtn.click();
    }
}
