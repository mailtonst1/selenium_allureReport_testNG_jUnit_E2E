package br.com.dbccompany.client.factory.datafaker;


import br.com.dbccompany.client.factory.dto.UserDto;
import br.com.dbccompany.util.DataFakerGeneretor;
import io.qameta.allure.Step;

public class UserData {

    // instanciar a ferramenta Faker
    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();


    // Gerar dados fakes e guardar no DTO correspondente
    @Step("Criando objeto com massa de dados validos!")
    public UserDto loginDadosValidos(){
        // Instanciar = conexão com LoginDto
        UserDto userDto = new UserDto();
        userDto.setEmail("vs@gmail.com");
        userDto.setSenha("123456");

        return userDto;
    }


    // Gerar dados fakes e guardar no DTO correspondente
    @Step("Criando objeto com massa de dados dinamicos!")
    public UserDto LoginDadoDinamicos(){
        // Instanciar = conexão com LoginDto
        UserDto userDto = new UserDto();
            userDto.setEmail(dataFakerGeneretor.emailFaker());
            userDto.setSenha(dataFakerGeneretor.senhaFaker());

        return userDto;
    }





}
