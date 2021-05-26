package Testresources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;



public class Payloads {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder

    @Data
    public static class User  {

        @JsonAlias("nome")
        private String nome;
        @JsonAlias("cpf")
        private String cpf;
        private String email;
        private Integer valor;
        private Integer parcelas;
        private Boolean seguro;
    }

}
