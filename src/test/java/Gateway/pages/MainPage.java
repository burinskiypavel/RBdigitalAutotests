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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainPage {
    //WebDriver webDriver;
    //public MainPage webDriver;
    public WebDriver driver;

    private WebDriverWait wait;
    PageObj pageObj;


    @FindBy(name = "username")
    public WebElement usernameField;

    @FindBy(xpath = "//a[contains(text(), 'Login')]")
    public WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(), 'Create New Account')]")
    public WebElement createNewAccountBtn;

    @FindBy(id = "profile")
    public WebElement profileDropdown;

    @FindBy(xpath = "//a[contains(text(), 'Log out')]")
    public WebElement logoutBtn;

    @FindBy(xpath = "//div[contains(text(), 'Welcome')]")
    public WebElement welcome;

    //public MainPage(WebDriver driver2001) {
    //   this.driver = driver2001;
    //}

    public void UsePageObj() {
        pageObj = new PageObj(driver);
    }

    public MainPage(WebDriver driver1) {
        driver = driver1;
        wait = new WebDriverWait(driver, 30);

        PageFactory.initElements(driver, this);
    }
    //page  = new Pageobj(driver);


    public void Register(String accessKey, String selectValueFromSelect, String firstName, String lastName, String email, String password) throws InterruptedException {
        UsePageObj();
        pageObj.ClickInFieldByLinkText("Create New Account");

        Wait<WebDriver> wait2 = new WebDriverWait(driver, 35);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("access_key")));

        pageObj.TypeInFieldById("access_key", accessKey);
        pageObj.ClickOnButtonById("pl_next");

        Wait<WebDriver> wait4 = new WebDriverWait(driver, 35);
        wait4.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("child_lib_id")));

        pageObj.SelectFromSelectByIdAndValue("child_lib_id", selectValueFromSelect);
        //String timeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        pageObj.TypeInFieldById("pname", firstName);
        pageObj.TypeInFieldById("plname", lastName);
        pageObj.TypeInFieldById("email", email);
        pageObj.TypeInFieldById("r_email", email);
        pageObj.TypeInFieldById("password", password);
        pageObj.TypeInFieldById("r_password", password);
        pageObj.ClickOnButtonById("pl_create_account");

        Wait<WebDriver> wait5 = new WebDriverWait(driver, 45);
        wait5.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("tos_agree")));

        pageObj.SelectCheckboxById("tos_agree");
        pageObj.ClickOnButtonById("pl_ok");
    }

    public void RegisterCardNumber(String cardNumber, String accessKey, String libraryResidence, String firstName, String lastName, String email, String password) throws InterruptedException {
        UsePageObj();
        pageObj.ClickInFieldByLinkText("Create New Account");

        Wait<WebDriver> wait2 = new WebDriverWait(driver, 40);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("access_key")));

        pageObj.TypeInFieldById("pbcode", cardNumber);
        pageObj.TypeInFieldById("access_key", accessKey);
        pageObj.ClickOnButtonById("pl_next");

        Wait<WebDriver> wait4 = new WebDriverWait(driver, 35);
        wait4.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("child_lib_id")));

        pageObj.SelectFromSelectByIdAndValue("child_lib_id", libraryResidence);
        //String timeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        pageObj.TypeInFieldById("pname", firstName);
        pageObj.TypeInFieldById("plname", lastName);
        pageObj.TypeInFieldById("email", email);
        pageObj.TypeInFieldById("r_email", email);
        pageObj.TypeInFieldById("password", password);
        pageObj.TypeInFieldById("r_password", password);
        pageObj.ClickOnButtonById("pl_create_account");
        Wait<WebDriver> wait5 = new WebDriverWait(driver, 35);
        wait5.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("tos_agree")));
        pageObj.SelectCheckboxById("tos_agree");
        pageObj.ClickOnButtonById("pl_ok");
    }

    public void Login(String username, String password) {
        UsePageObj();
        //driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
        loginBtn.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("pl_login")));
        //WebElement loginButton = driver.findElement(By.name("pl_login"));
        //Assert.assertTrue(loginButton.isDisplayed());
        usernameField.clear();
        pageObj.TypeInFieldByName("username", username);
        pageObj.TypeInFieldByName("password", password);
        pageObj.ClickOnButtonByName("pl_login");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Welcome')]")));
    }

    public void LoginUnsuccessful(String username, String password) {
        UsePageObj();
        //driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
        loginBtn.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("pl_login")));
        //WebElement loginButton = driver.findElement(By.name("pl_login"));
        //Assert.assertTrue(loginButton.isDisplayed());
        usernameField.clear();
        pageObj.TypeInFieldByName("username", username);
        pageObj.TypeInFieldByName("password", password);
        pageObj.ClickOnButtonByName("pl_login");
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Welcome')]")));
    }

    public void Logout() {
        UsePageObj();
        profileDropdown.click();
        logoutBtn.click();
        Wait<WebDriver> wait7 = new WebDriverWait(driver, 30);
        wait7.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Create New Account')]")));
    }

    public void selectServiceByPictureByXpath(String serviceXpath) {
        Wait<WebDriver> wait3 = new WebDriverWait(driver, 20);
        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(serviceXpath)));
        pageObj.ClickInFieldByXpath(serviceXpath);
    }

    public void goIntoServiceByButtonByXpath(String serviceXpath) throws InterruptedException {
        Thread.sleep(900);
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(serviceXpath)));
        //int count = 0;
        //while(driver.findElements(By.xpath(serviceXpath)).size() == 0 && count < 3){
        //    Thread.sleep(1000);
         //   count++;
        //}
        //int count1 = 0;
        //while(!driver.findElement(By.xpath(serviceXpath)).isDisplayed() && count1 < 3){
        //    Thread.sleep(1000);
       //     count1++;
        //}
            pageObj.ClickInFieldByXpath(serviceXpath);

    }

    public void CheckWelcomeText(String patron) {
        Wait<WebDriver> wait3 = new WebDriverWait(driver, 30);
        wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Welcome, " + patron + "')]")));
        WebElement Welcome = driver.findElement(By.xpath("//div[contains(text(), 'Welcome, " + patron + "')]"));
        Assert.assertTrue(Welcome.isDisplayed());
    }

    public char GenerateRadndomCharacters() {
        Random r = new Random();
        String alphabet = "123456789abcdefghijklmnopqrstuvwxyz";
        char character = 0;
        for (int i = 0; i < 50; i++) {
            character = alphabet.charAt(r.nextInt(alphabet.length()));
            //System.out.println(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return character;
    }

    public String GetTimeStamp() {
        String timeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

}
