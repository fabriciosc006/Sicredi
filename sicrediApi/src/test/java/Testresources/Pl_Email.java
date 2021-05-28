package Testresources;

public class Pl_Email {

    public static String email,cpf;
    public static String Nome;
    public static int indiceEmail;
    public static int valor, parcelas;

    public Pl_Email (String email, String cpf, Integer valor, Integer parcelas){
          this.email = email;
          this.cpf = cpf;
          this.valor = valor;
          this.parcelas = parcelas;
    }

    public static boolean valida_email() {

        //Verifica se o email possui o @.
        indiceEmail = email.indexOf('@');
        if (indiceEmail > 0)
            return (true);
        else
            return (false);

    }
    public static String obt_nome() {

        if (indiceEmail > 0)
            return Nome = email.substring(0, indiceEmail);
        else
            return ("Erro!");
    }
    public static String valida_cpf() {

        if(cpf == null || cpf.trim().isEmpty()) System.out.println("Digite o numero do cpf");
        else cpf = cpf.replaceAll("[^\\d ]", "");
        if(cpf.length()<11) return ("CPF tem que ter pelo menos 11 digitos");
        if(cpf.equals(cpf)) return ("CPF já Existente");

        return(cpf);
    }
    public static String valida_valor() {

        if (valor>=1000 && valor<=40000) return (String.valueOf(valor));
        else return ("Valor não pode ser inferior a RS1000 nem superior a R$40.000");

    }
    public static String valida_parcelas() {

        if (parcelas>=2 || parcelas<=48) return (String.valueOf(parcelas));
        else return ("Valor não pode ser inferior a 2 nem superior a 48");


    }

}