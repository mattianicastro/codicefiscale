package codicefiscale;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CodiceFiscale  c = new CodiceFiscale();
        System.out.println("> Cognome");
        c.setCognome(scanner.nextLine());
        System.out.println("> Nome");
        c.setNome(scanner.nextLine());
        System.out.println("> Cognome");
        c.setCognome();

        c.getCodice();
    }
}
