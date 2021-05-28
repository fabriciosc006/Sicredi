package Testresources;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.*;
import lombok.Builder;
import java.util.List;



public class Payloads  {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder

    @Data
    public static class User  {

        Faker faker = new Faker();

        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @JsonAlias("nome")
        private String nome = faker.name().firstName();//
        @JsonAlias("cpf")
        private String cpf = faker.number().numberBetween(100,999)+"."+faker.number().numberBetween(100,999)+"."+faker.number().numberBetween(100,999)+"-"+faker.number().numberBetween(1,99);
        @JsonAlias("email")
        private String email = faker.name().firstName()+"@"+faker.name().firstName()+".com.br";
        @JsonAlias("valor")
        private Integer valor = faker.number().numberBetween(500, 100000);
        @JsonAlias("parcelas")
        private Integer parcelas = faker.number().numberBetween(1, 100);
        @JsonAlias("seguro")
        private Boolean seguro = faker.bool().bool();

    }




}
