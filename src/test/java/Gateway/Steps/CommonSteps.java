package Gateway.Steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonSteps {
    public WebDriver driver;

    public CommonSteps(WebDriver driver1) {
        driver = driver1;
    }

    public void checkAlert(String  expectedAlertText) {
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText,  expectedAlertText);
    }

    public void thenIShouldNotSee(String item){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img[alt*='"+item+"'")));
        Assert.assertFalse(driver.findElements(By.cssSelector("img[alt*='"+item+"'")).size() != 0);
    }

    public void thenIShouldSee(String item){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img[alt*='"+item+"'")));
        Assert.assertTrue(driver.findElements(By.cssSelector("img[alt*='"+item+"'")).size() != 0);
    }
}
