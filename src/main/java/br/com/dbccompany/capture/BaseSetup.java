package br.com.dbccompany.capture;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseSetup extends ConfigCapture {

    public static WebElement element(By by){
        return driver.findElement(by);
    }
    // Metodo para esperar um elemento
    public static void esperarElemento(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected static void clicar(By by) {
        esperarElemento(by);
        element(by).click();
    }

    protected static void get(String url) {
        driver.get(url);
    }


}
