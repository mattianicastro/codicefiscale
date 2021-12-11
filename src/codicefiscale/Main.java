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
        System.out.println("> Anno di nascita");
        c.setAnno(scanner.nextInt());
        System.out.println("> Mese di nascita");
        c.setMese(scanner.nextInt());
        System.out.println("> Giorno di nascita");
        c.setGiorno(scanner.nextInt());
        System.out.println("> Sesso (M/F)");
        String sesso;
        do{
            sesso = scanner.next().toUpperCase();
        }while(!sesso.equals("M") && !sesso.equals("F"));
        c.setSesso(sesso);
        System.out.println(c.getCodice());
    }
}
