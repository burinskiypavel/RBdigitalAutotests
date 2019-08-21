package Gateway;

import Gateway.pages.AdminPage;
import Gateway.pages.MainPage;
import Gateway.pages.PlatformPage;
import Gateway.pages.ServicePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Settings {
    WebDriver driver;
    PageObj pageObj;
    MainPage mainPage;
    ServicePage servicePage;
    PlatformPage platformPage;
    AdminPage adminPage;

    @BeforeClass
    void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));

        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        servicePage = new ServicePage(driver);
        platformPage = new PlatformPage(driver);
        adminPage = new AdminPage(driver);
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
    }

    @AfterClass
    void afterClass() {
        driver.close();
        driver.quit();
    }
}
