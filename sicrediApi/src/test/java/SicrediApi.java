
import Testresources.Pl_Email;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import Testresources.Payloads.User;
import static org.hamcrest.Matchers.*;



public class SicrediApi {
    public String cpf,id,url;
    public String nome, email;
    Integer valor,parcelas;
    Boolean seguro;

    @BeforeClass
    public static void base(){
        baseURI = "http://localhost:8080/api/v1";
    }

    @Test
    public void search_all_simulations(){
        given().when().get("/simulacoes").then().log().all();
    }

    @Test
    public void cpf_restriction(){
            String cpf = "12345678932";

            long[] restriction = {97093236014L, 60094146012L, 84809766080L, 62648716050L, 26276298085L, 55856777050L, 19626829001L, 24094592008L, 58063164083L};
            String rest1 = "01317496094";
            String url = baseURI + "/restricoes/";
            String url1 = url + cpf;
            int status = 0;

            for (int pos = 0; pos < restriction.length; pos++) {
                long l = restriction[pos];
                String cpfchanged = Long.toString(l);
                if (cpf.equals(cpfchanged) || cpf.equals(rest1)) {
                    status = 200;
                    break;
                } else {
                    status = 204;
                }
            }
        Response response =
        given()
        .when().get(url1);
        response.then()
                .statusCode(status);

        if(status==204) System.out.println("O CPF Não possui restrição");
        if(status==200) System.out.println(response.body().prettyPrint());
    }

    @Test
    public void business_rules(){
        User user = new User("fabricio","123.456.789-32","fabricio@bol.com.br",1200,3,true);

        String cpf = user.getCpf();
        String email = user.getEmail();
        Integer valor = user.getValor();
        Integer parcelas = user.getParcelas();

        if(cpf == null || cpf.trim().isEmpty()) System.out.println("Digite o numero do cpf");
        else cpf = cpf.replaceAll("[^\\d ]", "");
        if(cpf.length()<11) System.out.println("CPF tem que ter pelo menos 11 digitos");
        System.out.println(cpf);
        if(cpf.equals(cpf)) {
            System.out.println("CPF já Existente");
        }

        Pl_Email email1 = new Pl_Email(email);
        boolean b = email1.valida_email();
        if(!b)
            System.out.println("Email Inválido");
        else
            System.out.println("Email Válido");
       // Imprime o que esta antes do @.
        String nome = email1.obt_nome();
        System.out.println(nome);

        if (valor>=1000 && valor<=40000) valor = valor;
        else System.out.println("Valor não pode ser inferior a RS1000 nem superior a R$40.000");
        if (parcelas>=2 || parcelas<=48) parcelas = parcelas;
        else System.out.println("Valor não pode ser inferior a 2 nem superior a 48");

    }

    @Test
    public void cpf_simulations(){

        User user = new User("fabricio","12345678932","fabricio@bol.com.br",1200,3,true);

        Response response =
                given()
                        .contentType("application/json")
                        .body(user)
                        .when().post("/simulacoes");
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("nome", is("fabricio"))
                .log().all();
    }

    @Test
    public void search_one_simulation(){
        cpf = "12345678932";
        url = "/simulacoes/"+cpf;

        Response response =
        given()
        .when().get(url);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("cpf", is("12345678932"))
                .log().all();

        cpf = response.jsonPath().getString("cpf");
        id = response.jsonPath().getString("id");

        System.out.println("ID Retornado => "+ id);
        System.out.println("CPF Retornado => "+ cpf);

    }

    @Test
    public void delete_one_simulation(){
        search_one_simulation();

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("id",id)
                        .when().delete("/simulacoes/{id}");
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

    @Test
    public void update_one_simulation(){
        search_one_simulation();
        User user = new User("fabricio","12345678932","fabricio@bol.com.br",1200,3,true);

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("cpf",cpf)
                        .body(user)
                        .when().put("/simulacoes/{cpf}");
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

}