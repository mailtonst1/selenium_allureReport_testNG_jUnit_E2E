package br.com.dbccompany.client.selenium;

import br.com.dbccompany.util.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static br.com.dbccompany.util.DefaultValue.*;

public class InitBrowser {

    public static WebDriver driver;
    public static WebDriverWait wait;

    // inicia o browser
    public static void getBrowser() {

        ConfigProperties.initializePropertyFile();

        switch (ConfigProperties.properties.getProperty(BROWSER)) {
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                driver.get(ConfigProperties.properties.getProperty(BASE_URL));
                break;
            case "edge":
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                driver.get(ConfigProperties.properties.getProperty(BASE_URL));
                break;
            default:
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                driver.get(ConfigProperties.properties.getProperty(BASE_URL));
                break;
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
