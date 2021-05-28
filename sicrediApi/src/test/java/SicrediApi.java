
import Testresources.Pl_Email;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import Testresources.Payloads.User;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.*;
import com.fasterxml.jackson.databind.*;


public class SicrediApi {
    public String cpf,id,url;
    public String nome, email;
    Integer valor,parcelas;
    Boolean seguro;

    @BeforeClass
    public static void base(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "http://localhost:8080/api/v1";
       }


    @Test
    public void search_all_simulations(){
        Response response =
        given()
        .when().get("/simulacoes");
        response.then().log().all();

        cpf = response.jsonPath().getString("cpf");
        int index = cpf.lastIndexOf(',');
        cpf = cpf.substring(index +1).replace("]","").replace(" ","");
        id = response.jsonPath().getString("id");
        int index1 = id.lastIndexOf(',');
        id = id.substring(index1 +1).replace("]","").replace(" ","");
        System.out.println("ID Retornado => "+ id);
        System.out.println("CPF Retornado => "+ cpf);
    }

    @Test
    public void cpf_restriction(){

            String[] restriction = {"97093236014", "60094146012", "84809766080", "62648716050", "26276298085", "01317496094","55856777050", "19626829001", "24094592008", "58063164083"};
            String url = baseURI + "/restricoes/";
            int statuscode = 0;
            for (int pos = 0; pos < restriction.length; pos++) {
                String cpfrest = restriction[pos];
                String url1 = url + cpf;
                if (cpf.equals(cpfrest)){
                     statuscode = 200;
                }else  statuscode = 204;
                Response response =
                        given()
                       .when().get(url1);
                response.then()
                        .statusCode(statuscode);
                        if (statuscode == 200) response.then().body("mensagem", is("O CPF "+ cpf +" tem problema"))
                        .log().all();
            }
    }

    @Test
    public void business_rules() {
        Pl_Email email1 = new Pl_Email(email,cpf,valor, parcelas);

        cpf = email1.valida_cpf();
        System.out.println(cpf);
        boolean b = email1.valida_email();
        if(!b)
            System.out.println("Email Inválido");
        else
            System.out.println("Email Válido");
       // Imprime o que esta antes do @.
        String nome = email1.obt_nome();
        System.out.println(nome);
        email1.valida_valor();
        email1.valida_parcelas();
        Assert.assertEquals(nome,nome);
    }

    @Test
    public void cpf_simulations() {

        User user = new User();
        nome = user.getNome();
        cpf = user.getCpf();
        email = user.getEmail();
        valor = user.getValor();
        parcelas = user.getParcelas();
        seguro = user.getSeguro();

        business_rules();
        cpf_restriction();

        cpf = user.getCpf().replaceAll("[^\\d ]", "");
        //ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(new File("C:\\Users\\fabricio.carvalho\\Documents\\ProjetosJava\\sicrediApi\\src\\test\\java\\Testresources\\User.json"), user);
        String jsonInString = "{\"nome\": \""+nome+"\",\"cpf\": \""+cpf+"\",\"email\": \""+email+"\",\"valor\": \""+valor+"\",\"parcelas\": \""+parcelas+"\",\"seguro\": \""+seguro+"\"}";
        //System.out.println(user);
        //System.out.println(jsonInString);
        //System.out.println("teste2");

        Response response =
                given()
                        .contentType("application/json")
                        .body(jsonInString)
                        .when().post("/simulacoes");
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all();
           cpf = response.jsonPath().getString("cpf");
    }


    @Test
    public void search_one_simulation(){
        search_all_simulations();
        Response response =
        given().contentType("application/json")
                .pathParam("cpf",cpf)
        .when().get("/simulacoes/{cpf}");
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("cpf", is(cpf))
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

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("cpf",cpf)
                        //.body(user)
                        .when().put("/simulacoes/{cpf}");
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

}