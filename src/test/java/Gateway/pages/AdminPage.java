package Gateway.pages;

import Gateway.PageObj;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminPage {
    public WebDriver driver;
    WebDriverWait wait;

    PageObj pageObj;
    MainPage mainPage;

    public AdminPage(WebDriver driver1) {
        driver = driver1;
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'Settings')]")
    WebElement settingTab;

    @FindBy(xpath = "//a[contains(text(), 'Reports')]")
    WebElement reportsTab;

    @FindBy(xpath = "//a[contains(text(), 'Licenses')]")
    public WebElement licensesTab;

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(name = "login")
    WebElement loginBtn;

    @FindBy(css = "a[title='Calendar'][href='#']")
    WebElement calendarBtn;

    @FindBy(id = "form_inc_library")
    public WebElement includeLibraryCheckbox;

    @FindBy(id = "form_inc_barcode")
    public WebElement includeBarcodeCheckbox;

    @FindBy(id = "form_inc_email")
    public WebElement includeEmailCheckbox;

    @FindBy(id = "form_inc_zeros")
    public WebElement includeZerosCheckbox;

    @FindBy(id = "form_inc_inactive")
    public WebElement includeInactiveCheckbox;

    @FindBy(id = "form_inc_issue")
    public WebElement includeIssue;

    @FindBy(id = "submit")
    public WebElement createReportBtn;

    @FindBy(id = "form_start_date")
    public WebElement startDate;

    @FindBy(id = "form_end_date")
    public WebElement endDate;

    @FindBy(xpath = "//a[contains(text(), 'Video Usage')]")
    public WebElement videoUsage_AllServices;

    @FindBy(xpath = "//a[contains(text(), 'Education Usage')]")
    public WebElement educationUsage_AllServices;

    @FindBy(xpath = "//a[contains(text(), 'License Usage')]")
    public WebElement licenseUsage;

    @FindBy(xpath = "//a[contains(text(), 'Turnaway')]")
    public WebElement turnaway_AllServices;

    @FindBy(xpath = "//a[contains(text(), 'Turnaway')]")
    public WebElement turnaway;

    @FindBy(xpath = "//a[contains(text(), 'Magazine Checkouts')]")
    public WebElement magazineCheckoutsReport;

    @FindBy(xpath = "//a[contains(text(), 'Turnaway')]")
    public WebElement turnawayReportMagazines;

    @FindBy(xpath = "//a[contains(text(), 'Remaining Circs')]")
    public WebElement remainingCircs;

    @FindBy(xpath = "//a[contains(text(), 'Deactivated Magazines')]")
    public WebElement deactivatedMagazines_Magazines;

    @FindBy(xpath = "//a[contains(text(), 'License Usage')]")
    public WebElement licenseUsage_AcornTV;

    @FindBy(xpath = "//a[contains(text(), 'Turnaway')]")
    public WebElement turnaway_AcornTV;

    @FindBy(xpath = "//a[contains(text(), 'Popular')]")
    public WebElement whatsPopular_Artistworks;

    @FindBy(xpath = "//a[contains(text(), 'Deactivated Comics')]")
    public WebElement deactivatedComics;

    @FindBy(xpath = "//a[contains(text(), 'PIN Code Report')]")
    public WebElement PINCodeReport;

    @FindBy(xpath = "//a[contains(text(), 'Service Subscriptions')]")
    public WebElement serviceSubscriptions;


    //public void UsePageObj() {
    //    pageObj = new PageObj(driver);
    //}

    public void UsePageObj() {
        pageObj = new PageObj(driver);
    }

    public void LoginInAdmin(String usernames, String passwords) {
        Wait<WebDriver> wait0 = new WebDriverWait(driver, 30);
        wait0.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
        username.sendKeys(usernames);
        password.sendKeys(passwords);
        loginBtn.click();
        wait0.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[class='first current']")));
    }

    public void OpenSettings() {
        settingTab.isDisplayed();
        settingTab.click();
    }

    public void OpenReportsTab() {
        reportsTab.click();
    }

    public void ClickOnCalendarIcon() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[title='Calendar'][href='#']")));
        //WebElement a = driver.findElement(By.cssSelector("a[title='Calendar'][href='#']"));
        calendarBtn.click();
        WebElement firstDayOfMonthInCalendar = driver.findElement(By.cssSelector("a[class='ui-state-default'][href='#']"));
        Assert.assertTrue(firstDayOfMonthInCalendar.isDisplayed());
        //Wait<WebDriver> wait0 = new WebDriverWait(driver, 30);
        //wait0.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class='ui-state-default ui-state-highlight']")));
    }

    public void SelectFirstDayOfMonth() {
        WebElement startDate = driver.findElement(By.cssSelector("a[class='ui-state-default'][href='#']"));
        startDate.click();
        //pageObj.ClickInFieldByCSS("a[class='ui-state-default'][href='#']");
    }

    public void SelectStartDate(String value) {
        driver.findElement(By.id("form_start_date")).sendKeys(value);
    }

    public void SelectEndDate(String value) {
        endDate.sendKeys(value);
    }

    public void SelectCurrentDayOfMonth() {
        //WebElement calendar = driver.findElement(By.xpath("//a[@title='Calendar'][@href='#'][2]"));
        //calendar.click();
        driver.findElement(By.id("form_end_date")).click();
        driver.findElement(By.id("form_end_date")).sendKeys("06/07/2019");
        //WebElement endDate = driver.findElement(By.cssSelector("a[class='ui-state-default ui-state-highlight'][href='#']"));
        //WebElement endDate = driver.findElement(By.xpath("//a[contains[@class,'ui-state-highlight']]"));
        //endDate.click();
        //pageObj.ClickInFieldByCSS("a[class='ui-state-default ui-state-highlight']");
    }

    public List<String> GetAllDataFromReport(String xpath) {
        //String css = "table[class='report_table']";
        List<String> table = new ArrayList<String>();
        List<String> currentOptions = new ArrayList<>();

        //WebElement select = driver.findElement(By.cssSelector(css));

        //List<WebElement> table2 = select.findElements(By.tagName("tbody"));
        List<WebElement> table2 = driver.findElements(By.xpath(xpath));

        for (WebElement match : table2) {
            currentOptions.add(match.getAttribute("innerText"));
            System.out.println(match.getAttribute("innerText"));
            //currentOptions.add("\r\n");
        }

        return currentOptions;
    }

    public List<String> GetAllDataFromReportNew(String xpath) {
        //String css = "table[class='report_table']";
        List<String> table = new ArrayList<String>();
        List<String> currentOptions = new ArrayList<>();

        //WebElement select = driver.findElement(By.cssSelector(css));

        //List<WebElement> table2 = select.findElements(By.tagName("tbody"));
        List<WebElement> table2 = driver.findElements(By.xpath(xpath));

        for (WebElement match : table2) {
            String text = match.getText();
            //currentOptions.add(match.getAttribute("innerText"));
            currentOptions.add(text);
            System.out.println(match.getAttribute("innerText"));
            //currentOptions.add("\r\n");
        }

        return currentOptions;
    }

    public List<String> GetAllDataFromReport20(String xpath) {
        //String css = "table[class='report_table']";
        List<String> table = new ArrayList<String>();
        List<String> currentOptions = new ArrayList<>();

        //WebElement select = driver.findElement(By.cssSelector(css));

        //List<WebElement> table2 = select.findElements(By.tagName("tbody"));
        List<WebElement> table2 = driver.findElements(By.xpath(xpath));


        for (WebElement match : table2) {
            String dateParsed[] = match.getAttribute("innerText").split("\n");

            int i = 0;
            for(int b = 0; b < dateParsed.length; b++) {
                currentOptions.add((dateParsed[i]));
                i++;
            }

            //currentOptions.add(match.getAttribute("innerText"));
            System.out.println(match.getAttribute("innerText"));

        }

        return currentOptions;
    }


    public List<String> GetDateFromFile20(String path) {
        List<String> table = new ArrayList<String>();
        //String fullPathToFile = "ExpectedDataRBDigital/AdminReports/PROD/" + path;//PROD
        String fullPathToFile = "ExpectedDataRBDigital/AdminReports/QA/" + path;//QA

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPathToFile), "UTF-8"))) {

            int count = 0;
            String line;
            while ((line = br.readLine()) != null ) {
                table.add(line);
                table.add("\r\n");
                count++;
                if(count == 20){
                    break;
                }


                //StringBuilder result = new StringBuilder();
                //  result.append('\n').append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }


    public List<String> GetDateFromFile(String path) {
        List<String> table = new ArrayList<String>();
        //String fullPathToFile = "ExpectedDataRBDigital/AdminReports/PROD/" + path;//PROD
        String fullPathToFile = "ExpectedDataRBDigital/AdminReports/QA/" + path;//QA

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPathToFile), "UTF-8"))) {

            String line;
            while ((line = br.readLine()) != null) {
                table.add(line);
                table.add("\r\n");


                //StringBuilder result = new StringBuilder();
                  //  result.append('\n').append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    public List<String> GetDateFromFiledef(String path) {
        List<String> table = new ArrayList<String>();
        //String fullPathToFile = "ExpectedDataRBDigital/AdminReports/PROD/" + path;//PROD
        String fullPathToFile = "ExpectedDataRBDigital/" + path;//QA

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPathToFile), "UTF-8"))) {

            String line;
            while ((line = br.readLine()) != null) {
                table.add(line);
                table.add("\r\n");


                //StringBuilder result = new StringBuilder();
                //  result.append('\n').append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

  public List<String> GetActualData(String xpath, String path) throws IOException {

      List<String> table = new ArrayList<String>();
      //String fullPathToFile = "ExpectedDataRBDigital/AdminReports/PROD/" + path; //Prod
      String fullPathToFile = "ExpectedDataRBDigital/AdminReports/QA/" + path;// QA

        List<String> actualReport = GetAllDataFromReport(xpath);
      //List<String> actualReport = GetAllDataFromReportNew(xpath);
      FileWriter writer = new FileWriter(fullPathToFile);
      for(String str: actualReport) {
          writer.write(str);

      }
      writer.close();
      actualReport = GetDateFromFile(path);
      return actualReport;
  }

    public List<String> GetActualDatadef(String xpath, String path) throws IOException {

        List<String> table = new ArrayList<String>();
        //String fullPathToFile = "ExpectedDataRBDigital/AdminReports/PROD/" + path; //Prod
        String fullPathToFile = "ExpectedDataRBDigital/" + path;// QA

        List<String> actualReport = GetAllDataFromReport(xpath);
        FileWriter writer = new FileWriter(fullPathToFile);
        for(String str: actualReport) {
            writer.write(str);

        }
        writer.close();
        actualReport = GetDateFromFiledef(path);
        return actualReport;
    }


    public List<String> GetActualData20(String xpath, String path) throws IOException {

        List<String> table = new ArrayList<String>();
        //String fullPathToFile = "ExpectedDataRBDigital/AdminReports/PROD/" + path; //Prod
        String fullPathToFile = "ExpectedDataRBDigital/AdminReports/QA/" + path;// QA

        List<String> actualReport = GetAllDataFromReport20(xpath);
        FileWriter writer = new FileWriter(fullPathToFile);
        int i = 0;
        for(String str: actualReport) {
            writer.write(str);
            writer.write("\r\n");
            i++;
            if(i == 20) {
                break;
            }
        }
        writer.close();
        actualReport = GetDateFromFile(path);
        return actualReport;
    }

  public void SetDatesInRepot(String startDate, String endDate){
      SelectStartDate(startDate);
      SelectEndDate(endDate);
  }

  public void SelectCheckboxes(WebElement[] checkboxes){
       for(int i=0; i < checkboxes.length; i++) {
           checkboxes[i].click();
       }

  }

  public void OpenReport(String xpath){
      Wait<WebDriver> wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
      driver.findElement(By.xpath(xpath)).click();
      //globalAdminPage.gatewayServiceUsageReport.click();
  }
  public void createReport(String xpath){
      Wait<WebDriver> wait = new WebDriverWait(driver, 30);
      createReportBtn.click();
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
  }

  public void selectServiceFromSelect(String id, String value){
        UsePageObj();
      pageObj.SelectFromSelectByIdAndValue(id, value);
  }

  public void selectLibraryFromSelect(String id, String value) {
        UsePageObj();
      pageObj.SelectFromSelectByIdAndValue(id, value);
  }

    public void selectRegionFromSelect(String id, String value) {
        UsePageObj();
        pageObj.SelectFromSelectByIdAndValue(id, value);
    }

    public void selectRegionAndCountry(String regionId, String regionValue, String countryId, String countryValue) {
        UsePageObj();
        pageObj.SelectFromSelectByIdAndValue(regionId, regionValue);
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='"+countryValue+"']")));
        pageObj.SelectFromSelectByIdAndValue(countryId, countryValue);
    }

    public void selectRegionCountryState(String regionId, String regionValue, String countryId, String countryValue, String stateId, String stateValue){
        UsePageObj();
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        pageObj.SelectFromSelectByIdAndValue(regionId, regionValue);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='"+countryValue+"']")));
        pageObj.SelectFromSelectByIdAndValue(countryId, countryValue);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='"+stateValue+"']")));
        pageObj.SelectFromSelectByIdAndValue(stateId, stateValue);

    }

}