package br.com.dbccompany.capture;

import br.com.dbccompany.util.ConfigProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import static br.com.dbccompany.capture.BaseSetup.*;


public class ConfigCapture {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void capture() {

        ConfigProperties.initializePropertyFile();

        ChromeOptions options3 = new ChromeOptions();
        options3.addArguments("headless");
        driver = new ChromeDriver(options3);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

        Dimension size = new Dimension(1920, 1080); // largura x altura
        driver.manage().window().setSize(size);

        get(System.getenv("DOMINIO") + "/job/" + System.getenv("JOB_NAME") + "/" + System.getenv("BUILD_NUMBER") + "/allure/");

        try{ // caso encontre barreira ngrok
            clicar(By.cssSelector("#root > div > main > div > div > section.mb-4.border.border-gray-300.bg-white.drop-shadow-md > div > footer > button"));
            get(System.getenv("DOMINIO") + "/job/" + System.getenv("JOB_NAME") + "/" + System.getenv("BUILD_NUMBER") + "/allure/");
        } catch (Exception e) {
            System.out.println(e);
        }

        try { // caso encontre a tela de login do jenkins efetua login
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#j_username")));
            driver.findElement(By.cssSelector("#j_username")).sendKeys("convidado");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#j_password")));
            driver.findElement(By.cssSelector("#j_password")).sendKeys("123456");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[name=\"login\"] > button[type=\"submit\"]")));
            driver.findElement(By.cssSelector("form[name=\"login\"] > button[type=\"submit\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
            driver.get(System.getenv("DOMINIO") + "/job/" + System.getenv("JOB_NAME") + "/" + System.getenv("BUILD_NUMBER") + "/allure/");
        } catch (Exception e) {
            System.out.println(e);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35000));
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


}
