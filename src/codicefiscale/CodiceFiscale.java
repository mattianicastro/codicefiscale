package codicefiscale;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodiceFiscale {
    private String nome;
    private String cognome;
    private int anno;
    private int mese;
    private int giorno;
    private String sesso;
    private String comune;
    private final Map<String, Map<String, String>> values = new HashMap<>();
    private final StringBuilder result = new StringBuilder();

    public CodiceFiscale() throws IOException {
        loadResources();
    }

    public static String getConsonanti(String s) {
        return s.toLowerCase().replaceAll("[aeiou]","").toUpperCase();
    }

    public static String getVocali(String s){
        return s.toLowerCase().replaceAll("[^aeiou]","").toUpperCase();
    }


    private void buildSigla(String nome, String mode){
        StringBuilder consonantiBuilder = new StringBuilder(getConsonanti(nome));
        if(mode.equals("nome")&&consonantiBuilder.length()>3){
            consonantiBuilder.deleteCharAt(1);
        }
        String consonanti = consonantiBuilder.toString();
        String vocali = getVocali(nome);
        char[] res = new char[3];
        int vocali_usate = 0;
        for(int i=0;i<3;i++){
            if(consonanti.length()>i){
                res[i] = consonanti.charAt(i);
            }else if(vocali.length()>vocali_usate){
                res[i] = vocali.charAt(vocali_usate);
                vocali_usate++;
            }else{
                res[i] = 'X';
            }

        }
        result.append(res);
    }

    private void buildAnno() {
        String a = Integer.toString(anno);
        result.append(a.substring(a.length()-2));

    }

    private void buildMese(){
        String r = switch (mese) {
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "C";
            case 4 -> "D";
            case 5 -> "E";
            case 6 -> "H";
            case 7 -> "L";
            case 8 -> "M";
            case 9 -> "P";
            case 10 -> "R";
            case 11 -> "S";
            case 12 -> "T";
            default -> throw new Error("Mese non trovato");
        };
        result.append(r);
    }
    public void buildGiorno() {
        int r = giorno;
        if (sesso.equals("F")) {
            r = giorno + 40;
        }
        result.append(String.format("%02d", r));
    }

    public boolean checkComune(){
        if(comune==null){
            return false;
        }
        Map<String, String> comuni = values.get("lista-codici.txt");
        String value = comuni.get(comune.toUpperCase());
        return value != null;
    }

    private void loadResources() throws IOException {
        String[] files = new String[]{"lista-codici.txt","controllo-dispari.txt","controllo-pari.txt","controllo-resto.txt"};
        for (String file : files) {
            Map<String, String> tmp = new HashMap<>();
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            boolean eof = false;
            while (!eof) {
                String c = reader.readLine();
                if (c == null) {
                    values.put(file, tmp);
                    eof = true;
                } else {
                    String[] parti = c.split(";");
                    tmp.put(parti[0], parti[1]);
                }
            }

        }

    }
    private void buildComune() {
        Map<String, String> comuni = values.get("lista-codici.txt");
        String code = comuni.get(comune.toUpperCase());
        result.append(code);
    }

    private void buildCodiceControllo() {
        Map<String, String> dispari = values.get("controllo-dispari.txt");
        Map<String, String> pari = values.get("controllo-pari.txt");
        Map<String, String> resto = values.get("controllo-resto.txt");
        int somma = 0;
        for(int i=1; i<=result.length(); i++){
            if(i%2==0){
                somma += Integer.parseInt(pari.get(String.valueOf(result.charAt(i-1))));
            }else{
                somma += Integer.parseInt(dispari.get(String.valueOf(result.charAt(i-1))));
            }
        }
        result.append(resto.get(Integer.toString(somma%26)));
    }

    public void setNome(String nome) {
        this.nome = nome.replace(" ","");
    }

    public void setCognome(String cognome) {
        this.cognome = cognome.replace(" ","");
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getCodice() {
        buildSigla(cognome, "cognome");
        buildSigla(nome, "nome");
        buildAnno();
        buildMese();
        buildGiorno();
        buildComune();
        buildCodiceControllo();
        return new String(result);
    }

}
