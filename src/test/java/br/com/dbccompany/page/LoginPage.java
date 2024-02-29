package br.com.dbccompany.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    public LoginPage LoginPage(){
        return new LoginPage();
    }

    // mapeamento (Padrão)
    private static final By CAMPO_EMAIL =
            By.cssSelector("input[data-qa=\"login-email\"]");

    private static final By CAMPO_SENHA =
            By.cssSelector("[data-qa=\"login-password\"]");

    private static final By BTN_LOGIN =
            By.cssSelector("#form  div div div.col-sm-4.col-sm-offset-1 div  form > button");

    private static final By LABEL_BTN =
            By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a");

    private static final By MSGM_LOGIN_INCORRETO =
            By.cssSelector("#form > div > div > div.col-sm-4.col-sm-offset-1 > div > form > p");


    // Ações (clicar, escrever etc)
    @Step("Preencher campo email")
    public LoginPage preencherCampoEmail(String email){
        preencherInput(CAMPO_EMAIL,email);
        return this;
    }

    @Step("Preencher campo senha")
    public LoginPage preencherCampoSenha(String senha){
        preencherInput(CAMPO_SENHA,senha);
        return this;
    }

    @Step("Clicar no botão acessar")
    public LoginPage clicarBtnAcessar(){
        clicar(BTN_LOGIN);
        return this;
    }

    @Step("Validar texto {text} no botão na tela")
    public LoginPage validarTextoBtnAposLogin(String text){
        Assert.assertEquals(text, lerTexto(LABEL_BTN));
        return this;
    }

    @Step("Validar mensagem {text} de erro de dados de login invalidos")
    public LoginPage validarMsgmEmailIncorreto(String text){
        Assert.assertEquals(text, lerTexto(MSGM_LOGIN_INCORRETO));
        return this;
    }

    @Step("Fluxo de login - preenchendo dados nos campos e clicando no botão")
    public LoginPage fluxoDeLogin(String email, String senha, String text){
        preencherCampoEmail(email);
        preencherCampoSenha(senha);
        clicarBtnAcessar();
        validarTextoBtnAposLogin(text);
        return this;
    }

    @Step("Fluxo de login - preenchendo dados invalidos nos campos e clicando no botão e validando texte {text} de erro")
    public LoginPage loginEmailIncorreto(String email, String senha, String text){
        preencherCampoEmail(email);
        preencherCampoSenha(senha);
        clicarBtnAcessar();
        validarMsgmEmailIncorreto(text);
        return this;
    }
}
