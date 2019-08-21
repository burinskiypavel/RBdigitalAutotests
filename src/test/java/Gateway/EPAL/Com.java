package Gateway.EPAL;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

public class Com {

    WebDriver driver;

    public Com(WebDriver driver2000) {
        this.driver = driver2000;
    }

    public Map<String, String> GetValues(String xpath) {
        Map<String, String> name = new HashMap<>();
        List<WebElement> name2 = driver.findElements(By.xpath(xpath));

        int i = 1;
        String s;
        s =  " ";

        for (WebElement k : name2) {
            String K;
            String V;
            K = k.getAttribute("name" );
            K = K + "@";
            if(s.contains(K)) {
                i++;
                s = K;
            }
            else {

                s = K + s;
            }
            K = K + i;
            V = k.getAttribute("value");
            name.put(K, V);
            //System.out.println(K + "=" + V);  //first time
        }
        return name;
    }

    public Map<String, String> getMapDataFromFile(String partPathToFile) {
        Map<String, String> map = new HashMap<>();
        String full_path_to_file = "Expected_data/" + partPathToFile;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(full_path_to_file), "UTF-8"))) {

            String line;
            while ((line =  br.readLine()) != null) {//readLine считывает из потока построчно, принадлежит BuferReader
                int index = line.indexOf('=');  //index нужен для работы substring
                if (index != -1) {   // -1 значит не нашли
                    String K = line.substring(0, index);    // имя //Метод substring() в Java имеет два варианта и возвращает новую строку, которая является подстрокой данной строки. Подстрока начинается с символа, заданного индексом, и продолжается до конца данной строки или до endIndex-1, если введен второй аргумент.
                    String V = line.substring(index + 1);  // значения
                    map.put(K, V);
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
    public void  waitForAlert(WebDriver driver)  {
        int i=0;
        while(i++<10)
        {
            try
            {
                Alert alert = driver.switchTo().alert();
                break;
            }
            catch(NoAlertPresentException e)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
        }
    }
     public void waitForSpinnerLoading() {
        new WebDriverWait(driver, 15)// 15 - time in seconds
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
    }
    public void pressKey(Keys key) {
        Actions act = new Actions(driver);
        act.sendKeys(key).perform();


    }
    public void WaitWeb(){
        new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'FCTF1005')]")));
    }


}



