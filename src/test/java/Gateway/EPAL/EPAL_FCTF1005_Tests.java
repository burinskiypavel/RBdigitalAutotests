package Gateway.EPAL;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.lang.String;

import java.util.concurrent.TimeUnit;


@Test
public class EPAL_FCTF1005_Tests {

    WebDriver driver;             // созд. ссылка
    Com page;
    String xpath = "//input[contains(@name,'FormModule:FCTF1005.Block:RMFC.Item:')]";



    @BeforeClass
    void beforeClass() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver1.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);      // в ссылку присв. элем

        // initialize System
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        //driver.navigate().to("localhost:8081"); //start page
        driver.navigate().to("http://aquamatrixautotest.azurewebsites.net/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(), 'FCTF Module')]")).click();
        page = new Com(driver);
        page.WaitWeb();

        driver.findElement(By.xpath("//a[contains(text(), 'FCTF1005')]")).click();
        page.waitForSpinnerLoading();

    }

    @AfterClass
    void afterClass() {
        // stop the system
        driver.close();

    }

    @BeforeMethod
    void beforeMethod() {

    }

    @AfterMethod
    void AfterMethod() {
        // clean up system after test
        page.pressKey(Keys.F7);
    }

    @Test(enabled = false)
    void test0_CheckScreenshots() {
        //Robot bot = new Robot();
        //bot.mouseMove(0, 0);

        try {

            //Screenshot screenshot = new AShot().takeScreenshot(driver); //без скрола
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver); // cо скролом
            //File actualFile = new File(actualDir + name + ".png");
            File actualFile = new File("E:\\myDir/page2.png");

            BufferedImage bi = screenshot.getImage();

            ImageIO.write(bi, "png", actualFile);

            //File file = new File("C:/myDir/page1.png");
            //BufferedImage image = ImageIO.read(file);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1() {
        //get etalon
        page.pressKey(Keys.F8);
        page.waitForSpinnerLoading();
        Map<String, String> actualResult = page.GetValues(xpath);

        page.pressKey(Keys.UP);

        page.waitForAlert(driver);
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
        page.pressKey(Keys.PAGE_DOWN);

        page.waitForAlert(driver);
        String alertText2 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
        page.pressKey(Keys.PAGE_UP);

        page.waitForAlert(driver);
        String alertText11 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();



        Map <String, String> expectedResults = page.getMapDataFromFile("test1.txt");
        Assert.assertEquals(actualResult, expectedResults);
        Assert.assertEquals(alertText, "No primeiro registo");
        Assert.assertEquals(alertText2, "Erro: No ultimo bloco");
        Assert.assertEquals(alertText11, "Erro: No primeiro bloco");
    }

    @Test
    void test2() {
        //get etalon
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys(">2000");
        page.pressKey(Keys.F8);

        Map <String, String> actualResult = page.GetValues(xpath);
        page.pressKey(Keys.PAGE_DOWN);

        page.waitForAlert(driver);

        String alertText21 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        page.pressKey(Keys.PAGE_UP);

        page.waitForAlert(driver);
        String alertText22 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        page.pressKey(Keys.DOWN);

        page.pressKey(Keys.F7);
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys("<2000");
        page.pressKey(Keys.F8);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Map <String, String> actualResult2 = page.GetValues(xpath);



        Map <String, String> expectedResults = page.getMapDataFromFile("test2.txt");
        Map <String, String> expectedResults2 = page.getMapDataFromFile("test2a1.txt");
        Assert.assertEquals(actualResult, expectedResults);
        Assert.assertEquals(actualResult2, expectedResults2);
        Assert.assertEquals(alertText21, "Erro: No ultimo bloco");
        Assert.assertEquals(alertText22, "Erro: No primeiro bloco");

    }

    @Test
    void test3() {  //Search by field "Utilizador"
        //get etalon
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:CDUTLRG")).sendKeys("LUISMAN");
        page.pressKey(Keys.F8);
        Map <String, String> actualResult = page.GetValues(xpath);
        page.pressKey(Keys.PAGE_DOWN);

        page.waitForAlert(driver);
        String alertText3 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        page.pressKey(Keys.PAGE_UP);

        page.waitForAlert(driver);
        String alertText4 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        Map <String, String> expectedResults = page.getMapDataFromFile("test3.txt");
        Assert.assertEquals(actualResult, expectedResults);
        Assert.assertEquals(alertText3, "Erro: No ultimo bloco");
        Assert.assertEquals(alertText4, "Erro: No primeiro bloco");
    }

    @Test
    void test4() {  //Search by field "Observacoes"
        //get etalon
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:OBSERVS")).sendKeys("NÃO SAIU DUPLICADO");
        page.pressKey(Keys.F8);
        Map <String, String> ActualResult = page.GetValues(xpath);

        page.pressKey(Keys.PAGE_DOWN);

        page.waitForAlert(driver);
        String alertText5 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        page.pressKey(Keys.PAGE_UP);

        page.waitForAlert(driver);
        String alertText6 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        Map <String, String> ExpectedResults = page.getMapDataFromFile("test4.txt");
        Assert.assertEquals(ActualResult, ExpectedResults);
        Assert.assertEquals(alertText5, "Erro: No ultimo bloco");
        Assert.assertEquals(alertText6, "Erro: No primeiro bloco");

    }

    @Test
    void test5() {  //Search by two fields
        //get etalon
        String Mode_normal = "FormMode:FCTF1005";
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys("2000");
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:OBSERVS")).sendKeys("MASHA");

        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys(Keys.F8);


        page.waitForAlert(driver);
        String alertText7 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:OBSERVS")).clear();
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:OBSERVS")).sendKeys("TESTE");
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys(Keys.F8);


        Map <String, String> actualResult5 =  page.GetValues(xpath);
        page.pressKey(Keys.F7);
        page.pressKey(Keys.F4);
        Assert.assertEquals(driver.findElement(By.name(Mode_normal)).getText(), "MODE: NORMAL");
        if (!(driver.findElement(By.name(Mode_normal)).getText().equals("MODE: NORMAL"))) {
            page.pressKey(Keys.F4);
        }
        Map <String, String> expectedResults5 = page.getMapDataFromFile("test5a1.txt");
        Assert.assertEquals(actualResult5, expectedResults5);
        Assert.assertEquals(alertText7, "A consulta não obteve registos. Repetir.");

    }

    /*
    @Test
    void test6() {
        //get etalon
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys("2000");
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:OBSERVS")).sendKeys("TEST");
        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys(Keys.F8);

        page.waitForAlert(driver);
        String alertText8 = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
       //waitForAlert(driver);
        //driver.switchTo().alert().dismiss();

        driver.findElement(By.name("FormModule:FCTF1005.Block:RMFC.Item:FACT_NRANOFT")).sendKeys(Keys.F4);

        String xpath = "//input[contains(@name,'FormModule:FCTF1005.Block:RMFC.Item:')]";
        Map <String, String> ActualResult = page.GetValues(xpath);
        Map <String, String> ExpectedResults = page.getMapDataFromFile("test6.txt");
        Assert.assertEquals(ActualResult, ExpectedResults);
        Assert.assertEquals(alertText8, "A consulta não obteve registos. Repetir.");

    }
    */
}







