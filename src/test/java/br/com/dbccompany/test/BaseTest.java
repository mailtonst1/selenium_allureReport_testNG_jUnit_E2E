package br.com.dbccompany.test;

import br.com.dbccompany.client.selenium.InitBrowser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaseTest extends InitBrowser {


    public void attachScreenshot() {
        Allure.addAttachment("Screenshot results",
                new ByteArrayInputStream(((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES)));
    }

    @Before
    public void abrirNavegador(){
        getBrowser();
    }

    @After
    public void fecharNavegador() throws IOException {
        attachScreenshot();
        tearDown();
    }

}
