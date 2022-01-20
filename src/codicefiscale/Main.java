package codicefiscale;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        CodiceFiscale  c = new CodiceFiscale();
        System.out.println("> Cognome");
        c.setCognome(scanner.nextLine());
        System.out.println("> Nome");
        c.setNome(scanner.nextLine());
        int anno;
        do{
            System.out.println("> Anno di nascita");
            anno = scanner.nextInt();
        }while(anno > 3000 || anno < 0);
        c.setAnno(anno);
        int mese;
        do{
            System.out.println("> Mese di nascita");
            mese = scanner.nextInt();
        }while(mese > 12 || mese < 0);
        c.setMese(mese);
        int giorno;
        do {
            System.out.println("> Giorno di nascita");
            giorno = scanner.nextInt();
        }while(giorno<0 || giorno > 31);
        c.setGiorno(giorno);
        String sesso;
        do{
            System.out.println("> Sesso (M/F)");
            sesso = scanner.next().toUpperCase();
        }while(!sesso.equals("M") && !sesso.equals("F"));
        c.setSesso(sesso);
        scanner.nextLine();
        while(!c.checkComune()){
            System.out.println("> Comune di nascita");
            c.setComune(scanner.nextLine());
        }

        try {
            System.out.println(c.getCodice());
        } catch (Error e){
            System.out.println(e.getMessage());
        }
    }
}
