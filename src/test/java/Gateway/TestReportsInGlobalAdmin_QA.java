package Gateway;

import Gateway.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

@Test
public class TestReportsInGlobalAdmin_QA {
    WebDriver driver;
    PageObj pageObj;
    MainPage mainPage;
    ServicePage servicePage;
    PlatformPage platformPage;
    AdminPage adminPage;
    GlobalAdminPage globalAdminPage;
    WebDriverWait wait;

    @BeforeClass
    void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        //System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        //driver = new FirefoxDriver();

        driver.navigate().to("https://www.rbdigitalqa.com/admin/liblist");
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
        driver.manage().window().maximize();
        pageObj = new PageObj(driver);
        mainPage = new MainPage(driver);
        servicePage = new ServicePage(driver);
        platformPage = new PlatformPage(driver);
        adminPage = new AdminPage(driver);
        globalAdminPage = new GlobalAdminPage(driver);
        adminPage.LoginInAdmin("pburinskiy", "pburinskiy123");
    }

    @AfterClass
    void afterClass() {
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    void beforeMethod() {
        driver.navigate().to("https://www.rbdigitalqa.com/admin/liblist");
        globalAdminPage.OpenReportsTab();
    }

    @AfterMethod
    void AfterMethod() {
    }

    @Test
    void Test_01_AllServices_NewPatronsReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'New Patrons Report')]")));
        adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("07/01/2017", "07/01/2017");
        adminPage.selectRegionFromSelect("form_location_region", "Asia");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/NewPatronsReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AllServices/NewPatronsReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_02_AllServices_GatewayServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Gateway Service Report')]")));
        globalAdminPage.gatewayServiceReport.click();
        adminPage.ClickOnCalendarIcon();
        adminPage.SetDatesInRepot("07/01/2017", "07/01/2017");
        adminPage.selectRegionFromSelect("form_location_region", "Asia");
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[class='ui-state-default'][href='#']")));
        //adminPage.includeLibraryCheckbox.click();
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/GatewayServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AllServices/GatewayServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_03_AllServices_GatewayServiceUsageReport() throws IOException {
        wait = new WebDriverWait(driver, 40);
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'Gateway Service Usage Report')]");
        adminPage.SetDatesInRepot("07/01/2017", "07/01/2017");
        adminPage.selectRegionFromSelect("form_location_region", "Asia");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/GatewayServiceUsageReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AllServices/GatewayServiceUsageReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_04_AllServices_PayPerUse() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'Pay Per Use')]");
        adminPage.SetDatesInRepot("06/01/2019", "07/01/2019");
        adminPage.selectServiceFromSelect("form_service", "acorntv");
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/PayPerUse/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AllServices/PayPerUse/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_05_AllServices_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'Video Usage')]");
        adminPage.SetDatesInRepot("04/01/2019", "04/10/2019");
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AllServices/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_06_AllServices_EducationUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'Education Usage')]");
        adminPage.SetDatesInRepot("01/01/2018", "01/01/2018");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/EducationUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AllServices/EducationUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_07_AllServices_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("04/01/2019", "04/17/2019");
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AllServices/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_08_AllServices_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/01/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AllServices/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_09_AllServices_SVODCostPerCheckout() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "all");
        adminPage.OpenReport("//a[contains(text(), 'SVOD Cost per Checkout')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/01/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AllServices/SVODCostPerCheckout/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AllServices/SVODCostPerCheckout/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_10_AudioBooksAndEBooks_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "rbdigital");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Illinois");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AudioBooksAndEBooks/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AudioBooksAndEBooks/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_11_AudioBooksAndEBooks_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "rbdigital");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Oregon");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AudioBooksAndEBooks/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AudioBooksAndEBooks/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_12_Magazines_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Europe", "form_location_country", "FI");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_13_Magazines_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Europe", "form_location_country", "BE");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Magazines/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_14_Magazines_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("04/01/2019", "05/04/2019");
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_15_Magazines_MagazineIssueDeliveryStatus() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        adminPage.OpenReport("//a[contains(text(), 'Magazine Issue Delivery Status')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/01/2017");
        adminPage.createReportBtn.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
    }

    @Test
    void Test_16_Magazines_MagazineCheckouts() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Magazine Checkouts')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/01/2017");
        adminPage.SelectCheckboxes(new WebElement[]{ adminPage.includeZerosCheckbox, adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox, adminPage.includeInactiveCheckbox, adminPage.includeIssue});
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/MagazineCheckouts/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/MagazineCheckouts/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test(enabled = false)
    void Test_17_Magazines_ArticleSearchCheckouts() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Article Search Checkouts')]");
        adminPage.SetDatesInRepot("01/01/2017", "02/01/2017");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/ArticleSearchCheckouts/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/ArticleSearchCheckouts/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_18_Magazines_GatewayStatisticReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Gateway Statistic Report')]");
        adminPage.SetDatesInRepot("04/01/2019", "04/16/2019");
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeInactiveCheckbox});
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/GatewayStatisticReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/GatewayStatisticReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_19_Magazines_MagazinesMasterReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        Wait<WebDriver> wait1 = new WebDriverWait(driver, 94);
        adminPage.OpenReport("//a[contains(text(), 'Magazines Master Report')]");
        adminPage.SetDatesInRepot("02/02/2017", "02/02/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeInactiveCheckbox});
        adminPage.createReportBtn.click();
        wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class='report_table wide_report']")));
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/MagazinesMasterReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/MagazinesMasterReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_20_Magazines_DeactivatedMagazines() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Deactivated Magazines')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "IN");
        adminPage.selectLibraryFromSelect("form_libraries", "1961");
        adminPage.createReport("//table[@class='report_table']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/DeactivatedMagazines/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/DeactivatedMagazines/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_21_Magazines_DiscontinuedMagazines() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Discontinued Magazines')]");
        adminPage.createReportBtn.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
    }

    @Test
    void Test_22_Magazines_LibraryParents() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "magazines");
        adminPage.OpenReport("//a[contains(text(), 'Library Parents')]");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Magazines/LibraryParents/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Magazines/LibraryParents/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_23_AcornTV_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Oklahoma");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AcornTV/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AcornTV/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_24_AcornTV_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Oklahoma");
        adminPage.createReport("//table[@class='report_table wide_report']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/AcornTV/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/AcornTV/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_25_AcornTV_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        adminPage.OpenReport("//a[contains(text(), 'Video Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "03/02/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AcornTV/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AcornTV/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
    }

    @Test
    void Test_26_AcornTV_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/20/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AcornTV/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AcornTV/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_27_AcornTV_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "acorntv");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/AcornTV/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/AcornTV/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_28_Artistworks_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "artistworks");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "CA", "form_location_state", "Saskatchewan");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Artistworks/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Artistworks/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_29_Artistworks_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "artistworks");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionFromSelect("form_location_region", "Other");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Artistworks/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Artistworks/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_30_Comics_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionFromSelect("form_location_region", "Other");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "IN");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Comics/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Comics/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_31_Comics_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "SG");
        adminPage.createReport("//table[@class='report_table wide_report']");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tbody")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Comics/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Comics/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_32_Comics_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Comics/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Comics/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_33_Comics_ComicIssueDeliveryStatus() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Comic Issue Delivery Status')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.createReportBtn.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
    }

    @Test
    void Test_34_Comics_ComicCheckouts() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Comic Checkouts')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeInactiveCheckbox, adminPage.includeZerosCheckbox, adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Comics/ComicCheckouts/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Comics/ComicCheckouts/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_35_Comics_GatewayStatisticReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Gateway Statistic Report')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/07/2017");
        adminPage.selectLibraryFromSelect("form_libraries", "46");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeInactiveCheckbox});
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Comics/GatewayStatisticReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Comics/GatewayStatisticReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_36_Comics_ComicsMasterReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Comics Master Report')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/08/2017");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Comics/ComicsMasterReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Comics/ComicsMasterReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_37_Comics_DeactivatedComics() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Deactivated Comics')]");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Comics/DeactivatedComics/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Comics/DeactivatedComics/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_38_Comics_DiscontinuedComics() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Discontinued Comics')]");
        adminPage.createReportBtn.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Report is generated. If downloading will not start, please click on the CSV icon.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
    }

    @Test
    void Test_39_Comics_LibraryParents() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comics");
        adminPage.OpenReport("//a[contains(text(), 'Library Parents')]");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Comics/LibraryParents/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Comics/LibraryParents/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_40_ComicsPlus_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comicsplus");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "UK", "form_location_country", "IE");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/ComicsPlus/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/ComicsPlus/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_41_ComicsPlus_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comicsplus");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Oklahoma");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/ComicsPlus/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/ComicsPlus/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_42_ComicsPlus_UsageReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "comicsplus");
        adminPage.OpenReport("//a[contains(text(), 'Usage Report')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/08/2017");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/ComicsPlus/UsageReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/ComicsPlus/UsageReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_43_Crunchyroll_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "crunchyroll");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Vermont");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Crunchyroll/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Crunchyroll/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_44_Crunchyroll_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "crunchyroll");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "OM");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Crunchyroll/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Crunchyroll/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_45_Eprep_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "rbtestprep");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Eprep/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Eprep/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_46_Eprep_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "rbtestprep");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Eprep/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Eprep/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_47_GreatCourses_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "UK", "form_location_country", "IE");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/GreatCourses/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/GreatCourses/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_48_GreatCourses_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "SG");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/GreatCourses/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/GreatCourses/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_49_GreatCourses_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        adminPage.OpenReport("//a[contains(text(), 'Video Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/GreatCourses/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/GreatCourses/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_50_GreatCourses_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/GreatCourses/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/GreatCourses/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_51_GreatCourses_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "great-courses");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/GreatCourses/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/GreatCourses/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_52_Hoonuit_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "hoonuit");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "IN");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Hoonuit/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Hoonuit/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_53_Hoonuit_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "hoonuit");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "TR");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Hoonuit/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Hoonuit/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_54_Indieflix_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionFromSelect("form_location_region", "Australia");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Indieflix/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Indieflix/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_55_Indieflix_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "HK");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Indieflix/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Indieflix/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_56_Indieflix_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        adminPage.OpenReport("//a[contains(text(), 'Video Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Indieflix/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Indieflix/VideoUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_57_Indieflix_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Indieflix/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Indieflix/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_58_Indieflix_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "indieflix");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Indieflix/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Indieflix/Turnaway/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_59_KidSpeak_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "kidspeak");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/KidSpeak/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/KidSpeak/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_60_KidSpeak_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "kidspeak");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Alabama");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/KidSpeak/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/KidSpeak/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_61_Lawdepot_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "lawdepot");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Illinois");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Lawdepot/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Lawdepot/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_62_Lawdepot_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "lawdepot");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Lawdepot/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Lawdepot/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_63_LearnItLive_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "New Hampshire");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/LearnItLive/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/LearnItLive/AdminOfServiceReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_64_LearnItLive_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Alabama");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/LearnItLive/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/LearnItLive/SalesRepReport/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_65_LearnItLive_EducationUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        adminPage.OpenReport("//a[contains(text(), 'Education Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/LearnItLive/EducationUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/LearnItLive/EducationUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_66_LearnItLive_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/LearnItLive/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/LearnItLive/LicenseUsage/expected.txt");
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_67_LearnItLive_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "learnitlive");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/LearnItLive/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/LearnItLive/Turnaway/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_68_MethodTestPrep_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Maine");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/MethodTestPrep/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/MethodTestPrep/AdminOfServiceReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_69_MethodTestPrep_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Wyoming");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/MethodTestPrep/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/MethodTestPrep/SalesRepReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_70_MethodTestPrep_EducationUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        adminPage.OpenReport("//a[contains(text(), 'Education Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/MethodTestPrep/EducationUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/MethodTestPrep/EducationUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_71_MethodTestPrep_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");

        adminPage.createReportBtn.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='error inline_error']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='error inline_error']")).getText().contains("This value should not be blank."));

        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/MethodTestPrep/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/MethodTestPrep/LicenseUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_72_MethodTestPrep_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "method-test-prep");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");

        adminPage.createReportBtn.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='error inline_error']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='error inline_error']")).getText().contains("This value should not be blank."));

        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/MethodTestPrep/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/MethodTestPrep/Turnaway/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_73_OnePlay_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "oneplay");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/OnePlay/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/OnePlay/AdminOfServiceReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_74_OnePlay_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "oneplay");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionFromSelect("form_location_region", "Europe");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/OnePlay/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/OnePlay/SalesRepReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_75_OnePlay_UsageReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "oneplay");
        adminPage.OpenReport("//a[contains(text(), 'Usage Report')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/OnePlay/UsageReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/OnePlay/UsageReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_76_OnePlay_PINCodeReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "oneplay");
        adminPage.OpenReport("//a[contains(text(), 'PIN Code Report')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/OnePlay/PINCodeReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/OnePlay/PINCodeReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_77_Pongalo_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Maine");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Pongalo/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Pongalo/AdminOfServiceReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_78_Pongalo_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Maine");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/Pongalo/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/Pongalo/SalesRepReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_79_Pongalo_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        adminPage.OpenReport("//a[contains(text(), 'Video Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Pongalo/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Pongalo/VideoUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_80_Pongalo_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Pongalo/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Pongalo/LicenseUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_81_Pongalo_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "pongalo");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Pongalo/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Pongalo/Turnaway/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_82_StingrayQello_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Asia", "form_location_country", "CN");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/StingrayQello/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/StingrayQello/AdminOfServiceReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_83_StingrayQello_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Europe", "form_location_country", "DK");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/StingrayQello/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/StingrayQello/SalesRepReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_84_StingrayQello_VideoUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        adminPage.OpenReport("//a[contains(text(), 'Video Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/StingrayQello/VideoUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/StingrayQello/VideoUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_85_StingrayQello_LicenseUsage() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        adminPage.OpenReport("//a[contains(text(), 'License Usage')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/StingrayQello/LicenseUsage/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/StingrayQello/LicenseUsage/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_86_StingrayQello_Turnaway() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "qello");
        adminPage.OpenReport("//a[contains(text(), 'Turnaway')]");
        adminPage.SetDatesInRepot("01/01/2017", "01/22/2017");
        adminPage.SelectCheckboxes(new WebElement[]{adminPage.includeBarcodeCheckbox, adminPage.includeEmailCheckbox, adminPage.includeLibraryCheckbox});
        adminPage.createReport("//table[@class='report_table auto_size']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/StingrayQello/Turnaway/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/StingrayQello/Turnaway/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_87_TransparentLanguage_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "transparent_language");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Iowa");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/TransparentLanguage/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/TransparentLanguage/AdminOfServiceReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_88_TransparentLanguage_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "transparent_language");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionAndCountry("form_location_region", "Europe", "form_location_country", "ES");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData20("//div[@id='report_refresh']", "GlobalAdminReports/TransparentLanguage/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile20("GlobalAdminReports/TransparentLanguage/SalesRepReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_89_Newspapers_AdminOfServiceReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "newspapers");
        adminPage.OpenReport("//span[contains(text(), 'Admin Of Service Report')]");
        adminPage.selectRegionFromSelect("form_location_region", "UK");
        adminPage.createReport("//table[@class='report_table']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Newspapers/AdminOfServiceReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Newspapers/AdminOfServiceReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_90_Newspapers_SalesRepReport() throws IOException {
        pageObj.SelectFromSelectByIdAndValue("service_t", "newspapers");
        adminPage.OpenReport("//a[contains(text(), 'Sales Rep Report')]");
        adminPage.selectRegionCountryState("form_location_region", "North America", "form_location_country", "US", "form_location_state", "Alabama");
        adminPage.createReport("//table[@class='report_table wide_report']");
        List<String> actualReport = adminPage.GetActualData("//div[@id='report_refresh']", "GlobalAdminReports/Newspapers/SalesRepReport/actual.txt");
        List<String> expectedReport = adminPage.GetDateFromFile("GlobalAdminReports/Newspapers/SalesRepReport/expected.txt");
        Assert.assertTrue(driver.findElement(By.id("submit")).getAttribute("value").contains("Create New Report"));
        Assert.assertEquals(actualReport, expectedReport);
    }

    @Test
    void Test_91_createNewGatewayAdmin() throws InterruptedException {
        globalAdminPage.openAdminsTab();
        driver.findElement(By.xpath("//a[@title='New Gateway Admin']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String timeStamp = adminPage.GetTimeStamp();
        adminPage.fillTheFieldsToCreateNewLibraryAdmin(timeStamp, timeStamp, "12345qw", timeStamp+"@gmail.com", "12345");
        adminPage.searchPatron(timeStamp+"@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Modify']")));
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actualEmail, timeStamp+"@gmail.com");
    }

    @Test
    void Test_92_createNewLibrary() throws InterruptedException {
        globalAdminPage.openLibrariesTab();
        driver.findElement(By.xpath("//a[@title='Create New Library']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("off_name")));
        String timeStamp = adminPage.GetTimeStamp();
        driver.findElement(By.id("name")).sendKeys("z_"+timeStamp);
        driver.findElement(By.id("off_name")).sendKeys("z_"+timeStamp);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("total_filial")));
        driver.findElement(By.id("total_filial")).sendKeys("3");
        driver.findElement(By.id("address_l1")).sendKeys("Washington street");
        driver.findElement(By.id("city")).sendKeys("test");
        driver.findElement(By.id("p_code")).sendKeys("12345");
        driver.findElement(By.id("phone")).sendKeys("12345");
        driver.findElement(By.id("submitButton")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Stop']")));
        driver.findElement(By.xpath("//a[contains(text(), '"+"z_"+timeStamp+"')]")).click();
        adminPage.SwitchToTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='withfields']")));
        String text = driver.findElement(By.xpath("//div[@class='content']")).getText();
        Assert.assertEquals(text, "There are no services currently available. Please contact your library.");
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '"+"z_"+timeStamp+"')]")).isDisplayed() );
    }
}
