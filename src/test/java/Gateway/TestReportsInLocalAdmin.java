package Gateway;

import Gateway.pages.AdminPage;
import Gateway.pages.MainPage;
import Gateway.pages.PlatformPage;
import Gateway.pages.ServicePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

@Test
public class TestReportsInLocalAdmin{

    WebDriver driver;
    PageObj pageObj;
    MainPage mainPage;
    ServicePage servicePage;
    PlatformPage platformPage;
    AdminPage adminPage;
    WebDriverWait wait;


    @BeforeClass
    void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();

        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
        driver.manage().window().maximize();
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

    @BeforeMethod
    void beforeMethod() {
        driver.navigate().to("https://www.rbdigitalqa.com/test51/admin");
        adminPage.OpenReportsTab();
        //mainPage.Login("may27@gmail.com", "12345qw");

    }

    @AfterMethod
    void AfterMethod() {
        //driver.close();
    }


    @Test
    void Test_01_AllServices_NewPatronReportCheckCalendar() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3[contains(text(), 'New Patrons Report')]")));
        adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("01/01/2019", "06/01/2019");
        adminPage.includeLibraryCheckbox.click();
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/AllServices/NewPatronReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AllServices/NewPatronReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_02_AllServices_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Video Usage')]")));
        adminPage.videoUsage_AllServices.click();
        adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("01/01/2018", "03/01/2018");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox});
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[class='ui-state-default'][href='#']")));
        adminPage.includeLibraryCheckbox.click();
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AllServices/VideoUsage/actual.txt");
        //List<String> actualReport = adminPage.GetActualData("//tbody", "LocalAdminReports/AllServices/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AllServices/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_03_AllServices_EducationUsageReports() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Education Usage')]")));
        adminPage.educationUsage_AllServices.click();
        adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("01/01/2018", "12/01/2018");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox});
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[class='ui-state-default'][href='#']")));
        adminPage.SelectCheckboxes(new WebElement[]{ adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AllServices/EducationUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AllServices/EducationUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_04_AllServices_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        //adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("01/01/2018", "01/08/2018");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[class='ui-state-default'][href='#']")));
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AllServices/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AllServices/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_05_AllServices_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway_AllServices.click();
        //adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("07/02/2017", "07/14/2019");
        adminPage.SelectCheckboxes(new WebElement[] {adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[class='ui-state-default'][href='#']")));
        adminPage.createReport("//table[@class='report_table auto_size']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AllServices/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AllServices/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_06_Magazines_ArticleSearchCheckouts() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Article Search Checkouts')]")));
        adminPage.SetDatesInRepot("01/27/2019", "06/02/2019");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table wide_report']", "LocalAdminReports/Magazines/ArticleSearchCheckouts/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Magazines/ArticleSearchCheckouts/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }
    @Test
    void Test_07_Magazines_MagazineCheckouts() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Magazine Checkouts')]")));
        adminPage.magazineCheckoutsReport.click();
        adminPage.SetDatesInRepot("08/01/2017", "08/01/2017");
        adminPage.SelectCheckboxes(new WebElement[] {adminPage.includeZerosCheckbox, adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox, adminPage.includeInactiveCheckbox, adminPage.includeIssue});
        adminPage.createReport("//table[@class='report_table wide_report']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table wide_report']", "LocalAdminReports/Magazines/MagazineCheckouts/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Magazines/MagazineCheckouts/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_08_Magazines_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnawayReportMagazines.click();
        adminPage.SetDatesInRepot("07/01/2011", "08/01/2019");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/Magazines/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Magazines/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_09_Magazines_RemainingCircs() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Remaining Circs')]")));
        adminPage.remainingCircs.click();
        adminPage.createReport("//table[@class='report_table']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/Magazines/RemainingCircs/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Magazines/RemainingCircs/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_10_Magazines_DeactivatedMagazines() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Deactivated Magazines')]")));
        adminPage.deactivatedMagazines_Magazines.click();
        adminPage.createReport("//table[@class='report_table']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/Magazines/DeactivatedMagazines/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Magazines/DeactivatedMagazines/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_11_AcornTV_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Video Usage')]")));
        adminPage.SetDatesInRepot("07/01/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AcornTV/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AcornTV/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_12_AcornTV_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage_AcornTV.click();
        adminPage.SetDatesInRepot("01/01/2018", "01/11/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AcornTV/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AcornTV/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
    }

    @Test
    void Test_13_AcornTV_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway_AcornTV.click();
        adminPage.SetDatesInRepot("01/06/2019", "06/02/2019");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/AcornTV/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/AcornTV/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_14_Artistworks_VideoUsage() throws IOException {
        wait = new WebDriverWait(driver, 50);
        pageObj.SelectFromSelectByIdAndValue("service_t", "artistworks");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Video Usage')]")));
        adminPage.SetDatesInRepot("01/06/2019", "01/29/2019");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/Artistworks/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Artistworks/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_15_Artistworks_WhatsPopular() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "artistworks");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Popular')]")));
        adminPage.whatsPopular_Artistworks.click();
        adminPage.SetDatesInRepot("06/01/2019", "06/06/2019");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/Artistworks/WhatsPopular/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Artistworks/WhatsPopular/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_16_Сomics_ComicCheckouts() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Comic Checkouts')]")));
        adminPage.SetDatesInRepot("06/01/2019", "06/02/2019");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeZerosCheckbox, adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox, adminPage.includeInactiveCheckbox });
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//table[@class='report_table']", "LocalAdminReports/Сomics/ComicCheckouts/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("LocalAdminReports/Сomics/ComicCheckouts/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_17_Сomics_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("06/01/2018", "12/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/Сomics/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Сomics/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_18_Comics_RemainingCircs() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Remaining Circs')]")));
        adminPage.remainingCircs.click();
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/Сomics/RemainingCircs/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Сomics/RemainingCircs/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_19_Сomics_DeactivatedComics() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Deactivated Comics')]")));
        adminPage.deactivatedComics.click();
        adminPage.createReport("//table[@class='report_table']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/Сomics/DeactivatedComics/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Сomics/DeactivatedComics/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_20_СomicsPlus_UsageReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comicsplus");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Usage Report')]")));
        adminPage.SetDatesInRepot("06/01/2018", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table']", "LocalAdminReports/СomicsPlus/UsageReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/СomicsPlus/UsageReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_21_GreatCourses_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Video Usage')]")));
        adminPage.SetDatesInRepot("06/01/2018", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/GreatCourses/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/GreatCourses/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_22_GreatCourses_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        adminPage.SetDatesInRepot("06/01/2014", "06/12/2019");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/GreatCourses/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/GreatCourses/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_23_GreatCourses_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("06/07/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/GreatCourses/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/GreatCourses/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_24_Indieflix_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Video Usage')]")));
        adminPage.SetDatesInRepot("06/07/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/Indieflix/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Indieflix/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_25_Indieflix_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        adminPage.SetDatesInRepot("05/07/2018", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//table[@class='report_table auto_size']", "LocalAdminReports/Indieflix/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Indieflix/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_26_Indieflix_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("05/21/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/Indieflix/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Indieflix/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_27_LearnItLive_EducationUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Education Usage')]")));
        adminPage.SetDatesInRepot("05/06/2016", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/LearnItLive/EducationUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/LearnItLive/EducationUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_28_LearnItLive_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        adminPage.SetDatesInRepot("05/06/2016", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/LearnItLive/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/LearnItLive/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_29_LearnItLive_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("05/15/2016", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/LearnItLive/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/LearnItLive/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_30_MethodTestPrep_EducationUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Education Usage')]")));
        adminPage.SetDatesInRepot("05/15/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/MethodTestPrep/EducationUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/MethodTestPrep/EducationUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_31_MethodTestPrep_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        adminPage.SetDatesInRepot("05/15/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/MethodTestPrep/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/MethodTestPrep/LicenseUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_32_MethodTestPrep_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("05/03/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/MethodTestPrep/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/MethodTestPrep/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_33_Oneplay_UsageReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "oneplay");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Usage Report')]")));
        adminPage.SetDatesInRepot("05/15/2015", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/Oneplay/UsageReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Oneplay/UsageReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_34_Oneplay_PINCodeReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "oneplay");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'PIN Code Report')]")));
        adminPage.PINCodeReport.click();
        adminPage.SetDatesInRepot("05/15/2017", "07/01/2018");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/Oneplay/PINCodeReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Oneplay/PINCodeReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_35_Pongalo_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Video Usage')]")));
        adminPage.SetDatesInRepot("05/15/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/Pongalo/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Pongalo/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_36_Pongalo_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        adminPage.SetDatesInRepot("05/15/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/Pongalo/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Pongalo/LicenseUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_37_Pongalo_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("05/21/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/Pongalo/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/Pongalo/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }


    @Test
    void Test_38_StingrayQello_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Video Usage')]")));
        adminPage.SetDatesInRepot("05/21/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/StingrayQello/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/StingrayQello/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_39_StingrayQello_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'License Usage')]")));
        adminPage.licenseUsage.click();
        adminPage.SetDatesInRepot("05/21/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/StingrayQello/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/StingrayQello/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_40_StingrayQello_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Turnaway')]")));
        adminPage.turnaway.click();
        adminPage.SetDatesInRepot("05/21/2017", "07/01/2018");
        adminPage.SelectCheckboxes(new WebElement[] { adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox });
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "LocalAdminReports/StingrayQello/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("LocalAdminReports/StingrayQello/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    }

