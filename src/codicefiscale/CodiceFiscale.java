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
        this.mese = mese;
        this.nome = nome;
        this.cognome = cognome;
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
        this.sesso = sesso;
    }

    public static String getConsonanti(String s) {
        return s.replaceAll("[aeiou]","").toUpperCase();
    }

    public static String getVocali(String s){
        return s.replaceAll("[^aeiou]","").toUpperCase();
    }


    private String getSiglaNome(String nome){
        String consonanti = getConsonanti(nome);
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
        String r="";
        switch(mese){
            case 1 : r="A"; break;
            case 2 : r="B"; break;
            case 3 : r="C"; break;
            case 4 : r="D"; break;
            case 5 : r="E"; break;
            case 6 : r="H"; break;
            case 7 : r="L"; break;
            case 8 : r="M"; break;
            case 9 : r="P"; break;
            case 10 : r="R"; break;
            case 11 : r="S"; break;
            case 12 : r="T"; break;
        }
        return r;
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
        return getSiglaNome(cognome)+getSiglaNome(nome)+getAnno()+getMese()+getGiorno();
    }

}
