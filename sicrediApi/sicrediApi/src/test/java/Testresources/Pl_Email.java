package Testresources;

public class Pl_Email {

    public static String email;
    public static String Nome;
    public static int indiceEmail;

    public Pl_Email (String email){
          this.email = email;
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
}