package br.com.dbccompany.test;

import br.com.dbccompany.client.selenium.InitBrowser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.IOException;


public class BaseTest extends InitBrowser {


    public void attachScreenshot() {
        Allure.addAttachment("Screenshot results",
                new ByteArrayInputStream(((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES)));
    }

    @BeforeMethod
    public void abrirNavegador(){
        getBrowser();
    }

    @AfterMethod
    public void fecharNavegador() throws IOException {
        attachScreenshot();
        tearDown();
    }


}
