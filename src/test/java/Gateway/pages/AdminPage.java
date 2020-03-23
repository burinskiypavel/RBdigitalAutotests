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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @FindBy(xpath = "//span[contains(text(), 'Magazine Checkouts')]")
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

    @FindBy(xpath = "//a[contains(text(), 'Patron')]")
    public WebElement patron;

    @FindBy(xpath = "//a[contains(text(), 'Admins')]")
    public WebElement adminsTab;

    @FindBy(xpath = "//a[contains(text(), 'Filters')]")
    public WebElement filtersTab;

    @FindBy(xpath = "//a[contains(text(), 'Settings')]")
    public WebElement settingsTab;

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

    public void LoginInAdminFailed(String usernames, String passwords) {
        Wait<WebDriver> wait0 = new WebDriverWait(driver, 30);
        wait0.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("login")));
        username.sendKeys(usernames);
        password.sendKeys(passwords);
        loginBtn.click();
        //wait0.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[class='first current']")));
    }

    public void OpenSettings() {
        settingTab.isDisplayed();
        settingTab.click();
    }

    public void OpenReportsTab() {
        reportsTab.click();
    }

    public AdminPage ClickOnCalendarIcon() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[title='Calendar'][href='#']")));
        //WebElement a = driver.findElement(By.cssSelector("a[title='Calendar'][href='#']"));
        calendarBtn.click();
        WebElement firstDayOfMonthInCalendar = driver.findElement(By.cssSelector("a[class='ui-state-default'][href='#']"));
        Assert.assertTrue(firstDayOfMonthInCalendar.isDisplayed());
        return this;
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
      Wait<WebDriver> wait = new WebDriverWait(driver, 80);
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

    public void checkAlert(String  expectedAlertText) {
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText,  expectedAlertText);
    }

    public void checkAlertModal(String  expectedAlertText) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("error_dialog")));
        String alertText = driver.findElement(By.id("error_dialog")).getText();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='close']")));
        driver.findElement(By.cssSelector("button[class='close']")).click();
        Assert.assertEquals(alertText,  expectedAlertText);
    }

    public void updateWeeklyOverallPatronCap(String value) throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        licensesTab.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("overall_patron_cap")));
        driver.findElement(By.id("overall_patron_cap")).clear();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("overall_patron_cap")));
        driver.findElement(By.id("overall_patron_cap")).clear();
        Thread.sleep(500);
        driver.findElement(By.id("overall_patron_cap")).sendKeys(value);
        driver.findElement(By.id("save")).click();
    }

    public void updateMonthlyOverallPatronCap(String value) throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        licensesTab.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("overall_patron_monthly_cap")));
        driver.findElement(By.id("overall_patron_monthly_cap")).clear();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("overall_patron_monthly_cap")));
        driver.findElement(By.id("overall_patron_monthly_cap")).clear();
        Thread.sleep(500);
        driver.findElement(By.id("overall_patron_monthly_cap")).sendKeys(value);
        driver.findElement(By.id("save")).click();
    }

    public String GetTimeStamp() {
        String timeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public AdminPage searchPatron(String patron) throws InterruptedException {
        Thread.sleep(300);
        Wait<WebDriver> wait = new WebDriverWait(driver, 39);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_line")));
        driver.findElement(By.id("search_line")).click();
        driver.findElement(By.id("search_line")).clear();
        driver.findElement(By.id("search_line")).sendKeys(patron);
        driver.findElement(By.id("search_button")).click();
        Thread.sleep(300);
        return this;
    }

    public AdminPage showInactiveUsers(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("a[title='Show Inactive Users']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Hide Inactive Users']")));
        return this;
    }

    public void fillTheFieldToCreateAPatron(String email, String firstName, String lastName, String username, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pname")).sendKeys(firstName);
        driver.findElement(By.id("plname")).sendKeys(lastName);
        driver.findElement(By.id("username")).sendKeys( username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("r_password")).sendKeys(password);
        driver.findElement(By.id("submitButton")).click();
    }

    public void fillTheFieldsToCreateNewLibraryAdmin(String username, String name, String password, String email, String phone) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("r_password")).sendKeys(password);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.id("submitButton")).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_button")));
    }

    public void openAdminsTab() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        adminsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New Library Admin']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='New Library Admin']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_button")));
    }

    public void Logout() {
        driver.findElement(By.id("logout")).click();
    }

    public AdminPage openPatronTab() throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        patron.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New Patron']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_line")));
        Thread.sleep(700);
        return this;
    }

    public AdminPage pressModify() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Modify']")));
        driver.findElement(By.cssSelector("span[title='Modify']")).click();
        return this;
    }

    public AdminPage updatePatronPassword(String password) throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        driver.findElement(By.id("password")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("r_password")));
        driver.findElement(By.id("r_password")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        driver.findElement(By.id("submitButton")).click();
        Thread.sleep(1500);
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(text(), 'Success')]")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("search_button")));
        return this;
    }

    public void SwitchToTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int comicsPageIndex = tabs.size() - 1;
        driver.switchTo().window(tabs.get(comicsPageIndex));
    }

    public int getDataFromLicensesOveral_getLicenses(int rows) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table[class='magazines_table']")));
        WebElement table = driver.findElement(By.cssSelector("table[class='magazines_table']"));
        String actual_licence;
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr")));
        WebElement row = table.findElements(By.cssSelector("tr")).get(rows);//AcornTV = 1
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td")));
        WebElement cell = row.findElements(By.cssSelector("td")).get(7);
        actual_licence = cell.getText();
        int result = Integer.parseInt(actual_licence);
        return result;
    }

    public void createLicensesForService(String service, String number, String description) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("service_key")));
        //pageObj.SelectFromSelectByIdAndValue("service_key", service);
        selectServiceFromSelect("service_key", service);
        driver.findElement(By.id("lic_limit")).clear();
        driver.findElement(By.id("lic_limit")).sendKeys(number);
        driver.findElement(By.id("lic_order_description")).sendKeys(description);
        driver.findElement(By.id("generateLisenses")).click();
        //WebElement lic_limit = driver.findElement(By.id("lic_limit"));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("lic_limit"), "0"));
    }

    public void goToLicenseManager() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='license-manager right']")));
        driver.findElement(By.cssSelector("div[class='license-manager right']")).click();
    }

    public void selectSubscriptionPeriodStartEnd(String startDate, String endDate) {
        driver.findElement(By.id("start_sub_date")).clear();
        driver.findElement(By.id("start_sub_date")).sendKeys(startDate);
        driver.findElement(By.id("exp_date")).clear();
        driver.findElement(By.id("exp_date")).sendKeys(endDate);
    }

    public void createAccesKeyStrikt(String key) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
        driver.findElement(By.id("access_key")).sendKeys(key);
        driver.findElement(By.id("submit")).click();
    }

    public void goToChildLibraryPage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='child_library_edit']")));
        driver.findElement(By.cssSelector("a[class='child_library_edit']")).click();
    }

    public void openFiltersTab() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 15);
        filtersTab.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Access Key Filtering')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Barcode Filtering')]")));
    }

    public void createBarcode(String pref, String len) {
        driver.findElement(By.id("barcode_pref")).sendKeys(pref);
        driver.findElement(By.id("barcode_len")).sendKeys(len);
        driver.findElement(By.id("submit")).click();
    }

    public void createIPFiltering(String first, String second, String third, String forth) {
        driver.findElement(By.id("ip4a")).sendKeys(first);
        driver.findElement(By.id("ip4b")).sendKeys(second);
        driver.findElement(By.id("ip4c")).sendKeys(third);
        driver.findElement(By.id("ip4d")).sendKeys(forth);
        driver.findElement(By.id("is_net")).click();
        driver.findElement(By.id("submit")).click();
    }

    public void goToBarcodeFiltering() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 15);
        driver.findElement(By.xpath("//a[contains(text(), 'Barcode Filtering')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
    }


    public void goToAccessKeyFiltering() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 15);
        driver.findElement(By.xpath("//a[contains(text(), 'Access Key Filtering')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
    }

}