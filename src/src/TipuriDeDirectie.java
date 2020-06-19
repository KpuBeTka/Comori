package src;

public class TipuriDeDirectie{
    int tipulPasului, numarulDeAparitii;

    TipuriDeDirectie(int tipulPasului){
        this.tipulPasului = tipulPasului;
    }

    TipuriDeDirectie(int tipulPasului, int numarulDeAparitii){
        this.tipulPasului = tipulPasului;
        this.numarulDeAparitii = numarulDeAparitii;
    }

    void getAllData(){
        System.out.println(tipulPasului + ", " + numarulDeAparitii);
    }

    int getTipulPasului(){
        return tipulPasului;
    }

    int getNumarulDeAparitii(){
        return numarulDeAparitii;
    }

}
