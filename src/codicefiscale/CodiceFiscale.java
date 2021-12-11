package codicefiscale;


public class CodiceFiscale {
    private String nome;
    private String cognome;
    private int anno;
    private int mese;
    private int giorno;
    private String sesso;

    public CodiceFiscale() {}

    public CodiceFiscale(String nome, String cognome, int anno, int mese, int giorno, String sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
        this.sesso = sesso;
    }

    public static String getConsonanti(String s) {
        return s.toLowerCase().replaceAll("[aeiou]","").toUpperCase();
    }

    public static String getVocali(String s){
        return s.toLowerCase().replaceAll("[^aeiou]","").toUpperCase();
    }


    private String getSiglaNome(String nome,String mode){
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
        return new String(res);
    }

    public String getAnno() {
        String a = Integer.toString(anno);
        return a.substring(a.length()-2);
    }

    public String getMese(){
        return switch (mese) {
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
            default -> "";
        };
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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

    public String getGiorno() {
        int r = giorno;
        if (sesso.equals("F")) {
            r = giorno + 40;
        }
        return String.format("%02d", r);
    }

    public String getCodice() {
        return getSiglaNome(cognome,"cognome")+getSiglaNome(nome,"nome")+getAnno()+getMese()+getGiorno();
    }

}
