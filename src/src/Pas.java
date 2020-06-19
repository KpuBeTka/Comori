package src;

public class Pas {
    //variabila, care conține tipul pasului (1-8) și numărul de acești pași efectuați
    int tipPas, nrDePasi;

    //constructorul clasei
    Pas(int tipPas, int nrDePasi){
        this.tipPas = tipPas;
        this.nrDePasi = nrDePasi;
    }

    //funcția, pentru a afișa tipul pasului și numărul de pași (datele obiectului)
    void getAllData(){
        System.out.println(tipPas + ", " + nrDePasi);
    }

    //funcția, care returnează tipul pasului
    int getTipPas(){
        return tipPas;
    }
}