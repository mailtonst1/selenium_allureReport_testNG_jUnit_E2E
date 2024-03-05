package br.com.dbccompany.test;

import br.com.dbccompany.client.factory.datafaker.UserData;
import br.com.dbccompany.client.factory.dto.UserDto;
import br.com.dbccompany.page.LoginPage;
import io.qameta.allure.*;
import org.junit.Test;

import static br.com.dbccompany.validate.LoginValidate.*;

@Feature("Login")
@Story("Como usuário do sistema, " +
        "desejo fazer login na aplicação " +
        "quando inserir email e senha validos")

public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    UserData userData = new UserData();
    UserDto usu = new UserDto();

    // Fluxo positivo - Cenário automatizado com execução de passo a passo
    @Test
    //@Test(description = "TC0001 - validar login com dados Invalidos dinamicos")
    @Severity(SeverityLevel.CRITICAL)
    public void validarLoginDadosValidos(){
        usu =  userData.loginDadosValidos();               // 1. Criando a massa
        loginPage
                .preencherCampoEmail(usu.getEmail())                // 2.  Preenhce campo email
                .preencherCampoSenha(usu.getSenha())                // 3.  Preenhce campo senha
                .clicarBtnAcessar()                                 // 4.  clicar no botão
                .validarTextoBtnAposLogin(MENSAGEM_LOGIN_SUCESSO);  // 5.  validar resultado
    }


/*
    @Test
    @Description("TC002 - validar login com dados Invalidos dinamicos")
    @Severity(SeverityLevel.CRITICAL)
    public void validarLoginDadosInvalidos(){
      //  usu =  userData.LoginDadoDinamicos();                   // 1. Criando a massa
        loginPage
                .preencherCampoEmail(usu.getEmail())                    // 2.  Preenhce campo email
                .preencherCampoSenha(usu.getSenha())                    // 3.  Preenhce campo senha
                .clicarBtnAcessar()                                     // 4.  clicar no botão
                .validarMsgmEmailIncorreto(MENSAGEM_LOGIN_INCORRETO);   // 5.  validar resultado
    }


    @Test(description = "TC0003 - validar login com dados Invalidos dinamicos")
    @Severity(SeverityLevel.CRITICAL)
    public void validarLoginComDadosValidosTest3(){
        usu =  userData.loginDadosValidos();                    // 1. Criando a massa
        loginPage
                .fluxoDeLogin(                                          // 2. Faz login e valida o resultado
                        usu.getEmail(),
                        usu.getSenha(),
                        MENSAGEM_LOGIN_SUCESSO);
    }

    @Test(description = "TC0004 - validar login com dados Invalidos dinamicos")
    @Severity(SeverityLevel.CRITICAL)
    public void validarLoginDadosInvalidosTest4(){
        usu =  userData.LoginDadoDinamicos();                   // 1. Criando a massa
        loginPage.
                loginEmailIncorreto(                                    // 2. Faz login e valida o resultado
                        usu.getEmail(),
                        usu.getSenha(),
                        MENSAGEM_LOGIN_INCORRETO);
    }
/*/
}
