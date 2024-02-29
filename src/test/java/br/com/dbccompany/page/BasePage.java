package br.com.dbccompany.page;

import br.com.dbccompany.client.selenium.Elements;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BasePage extends Elements {

    protected static void preencherInput(By by, String text) {
        esperarElemento(by);
        element(by).sendKeys(text);
    }

    protected static void clicar(By by) {
        esperarElemento(by);
        element(by).click();
    }

    protected static String lerTexto(By by) {
        esperarElemento(by);
        return element(by).getText();
    }

    protected static void tab(By by){
        esperarElemento(by);
        element(by).sendKeys("\t");
    }
}
