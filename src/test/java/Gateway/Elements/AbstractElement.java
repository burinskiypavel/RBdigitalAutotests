package Gateway.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbstractElement {
    WebDriver driver;
    public WebElement findByXPath(String xpathLocator){
        return driver.findElement(By.xpath(xpathLocator));
    }
}
