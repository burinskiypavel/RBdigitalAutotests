package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ServiceSitePage {
    public WebDriver driver;

    PageObj pageObj;
    MainPage mainPage;

    public ServiceSitePage(WebDriver driver1) {
        driver = driver1;
        //wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }

    public void CheckText(String element, String text){
        //driver.findElement(By.xpath("//"+element+"[contains(text(), '"+text+"')]")).getAttribute("innerText");
        Assert.assertEquals(driver.findElement(By.xpath("//"+element+"[contains(text(), '"+text+"')]")).getAttribute("innerText"), text);
    }
}
