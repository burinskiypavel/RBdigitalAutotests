package Gateway.EPAL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.lang.String;

import java.util.concurrent.TimeUnit;

/**
 * Created by Burinskiy.P on 6/3/2016.
 */
@Test
public class EPAL_FCTF1005_1_test {
    WebDriver driver;
    Com page;
    java.lang.String xpath = "//input[contains(@class,'srg')]";

    @BeforeClass
    void beforeClass() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);      // в ссылку присв. элем

        // initialize System
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        driver.navigate().to("https://www.google.com.ua"); //start page
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//a[contains(text(), 'FCTF Module')]")).click();
        //page = new Com(driver);
        //page.WaitWeb();

        //driver.findElement(By.xpath("//a[contains(text(), 'FCTF1005')]")).click();
        //page.waitForSpinnerLoading();

    }
    @AfterMethod
    void AfterMethod() {
        // clean up system after test
        //page.pressKey(Keys.F7);
    }

    @AfterClass
    void afterClass() {
        // stop the system
        driver.close();
    }


    @Test
    void test1() {
        //get etalon
        driver.findElement(By.id("lst-ib")).sendKeys("Hello");
        driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
        //driver.sendKeys(Keys.ENTER);
        //page.pressKey(Keys.ENTER);
        //page.waitForSpinnerLoading();
        Map <String, String> actualResult = page.GetValues(xpath);
        Map <String, String> expectedResults = page.getMapDataFromFile("GOTest1.txt");
        Assert.assertEquals(actualResult, expectedResults);




    }


}
