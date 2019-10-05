package me.unmesh;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.hi.जब;
import io.cucumber.java.hi.तब;
import io.cucumber.java.hi.यदि;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

/**
 * @author upgundecha
 */
public class Steps {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @यदि("उपयोगकर्ता व्यक्तिगत ऋण पृष्ठ पर हैं")
    public void उपयोगकर्ता_व्यक्तिगत_ऋण_पृष्ठ_पर_हैं() {
        driver.get("https://www.bajajfinserv.in/hindi/personal-loan-emi-calculator");
    }

    @जब("जब वह ऋण के बारे में पूछी गई जानकारी बताते हैं")
    public void जब_वह_ऋण_के_बारे_में_पूछी_गई_जानकारी_बताते_हैं(Map<String, String> dataTable) {

        driver.findElement(By.cssSelector(".v1_salaryPLEM input"))
                .sendKeys(dataTable.get("राशि"));

        driver.findElement(By.cssSelector(".v1_PLEMI input"))
                .sendKeys(dataTable.get("अवधि"));

        driver.findElement(By.cssSelector(".v1_myEMIPLEM input"))
                .sendKeys(dataTable.get("ब्याज़") + Keys.TAB);
    }

    @तब("व्यक्तिगत ऋण कैलकुलेटर उन्हें किश्त, ब्याज़ कुल देय और कुल देय राशि की जानकारी देता हैं")
    public void व्यक्तिगत_ऋण_कैलकुलेटर_उन्हें_किश्त_ब्याज़_कुल_देय_और_कुल_देय_राशि_की_जानकारी_देता_हैं(Map<String, String> dataTable) {
        Assert.assertEquals(dataTable.get("किश्त"),
                driver.findElement(By.cssSelector("h2.v1_loanEmiAmt")).getText());
    }

    @After
    public void tearDown() {
        if(driver!=null) {
            driver.close();
            driver.quit();
        }
    }
}
