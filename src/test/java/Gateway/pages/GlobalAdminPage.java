package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalAdminPage {

    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public GlobalAdminPage(WebDriver driver1) {
        driver = driver1;
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'Reports')]")
    WebElement reportsTab;

    @FindBy(xpath = "//a[contains(text(), 'Gateway Service Report')]")
    public WebElement gatewayServiceReport;

    @FindBy(xpath = "//a[contains(text(), 'Gateway Service Usage Report')]")
    public WebElement gatewayServiceUsageReport;

    public void OpenReportsTab() {
        reportsTab.click();
    }
}
