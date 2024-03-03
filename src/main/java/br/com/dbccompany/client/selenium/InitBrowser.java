package br.com.dbccompany.client.selenium;

import br.com.dbccompany.util.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments("headless");
                options1.addArguments("--no-sandbox");
                options1.addArguments("--disable-dev-shm-usage");
                driver = new FirefoxDriver(options1);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                driver.get(ConfigProperties.properties.getProperty(BASE_URL));
                break;
            case "edge":
                EdgeOptions options2 = new EdgeOptions();
                options2.addArguments("headless");
                options2.addArguments("--no-sandbox");
                options2.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(options2);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                driver.get(ConfigProperties.properties.getProperty(BASE_URL));
                break;
            default:
                ChromeOptions options3 = new ChromeOptions();
                options3.addArguments("headless");
                options3.addArguments("--no-sandbox");
                options3.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(options3);
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
