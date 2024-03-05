package br.com.dbccompany;
import br.com.dbccompany.util.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.com.dbccompany.util.DefaultValue.BROWSER;

public class Capture {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void getBrowser() {

        ConfigProperties.initializePropertyFile();

        switch (ConfigProperties.properties.getProperty(BROWSER)) {
            default:
                ChromeOptions options3 = new ChromeOptions();
                options3.addArguments("headless");
                options3.addArguments("--no-sandbox");
                options3.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(options3);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                break;
        }
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    public static void createScreenshot() {
        // Captura a screenshot
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Define o nome do arquivo
        String screenshotName = "screenshot_allure.png";

        // Define o caminho onde o arquivo será salvo (pode ser ajustado conforme necessário)
        String filePath = "./screenshots/" + screenshotName;

        // Cria o arquivo
        File screenshotFile = new File(filePath);

        screenshotFile.getParentFile().mkdirs();

        // Escreve os bytes da screenshot no arquivo
        try (FileOutputStream outputStream = new FileOutputStream(screenshotFile)) {
            outputStream.write(screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Informa sobre a criação do arquivo no console
        System.out.println("Screenshot salva em: " + screenshotFile.getAbsolutePath());

        // Opcionalmente, você pode adicionar uma mensagem de log ou relatório
//        Reporter.log("Screenshot salva em: " + screenshotFile.getAbsolutePath());
    }

    public static void main(String[] args) {
        ConfigProperties.initializePropertyFile();

        getBrowser();
        driver.get("https://tightly-poetic-beagle.ngrok-free.app/login");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > main > div > div > section.mb-4.border.border-gray-300.bg-white.drop-shadow-md > div > footer > button")));
        driver.findElement(By.cssSelector("#root > div > main > div > div > section.mb-4.border.border-gray-300.bg-white.drop-shadow-md > div > footer > button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#j_username")));
        driver.findElement(By.cssSelector("#j_username")).sendKeys("convidado");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#j_password")));
        driver.findElement(By.cssSelector("#j_password")).sendKeys("123456");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[name=\"login\"] > button[type=\"submit\"]")));
        driver.findElement(By.cssSelector("form[name=\"login\"] > button[type=\"submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.get(System.getenv("DOMINIO") + "/job/" + System.getenv("JOB_NAME") + "/" + System.getenv("BUILD_NUMBER") + "/allure/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        createScreenshot();
        tearDown();
    }
}
