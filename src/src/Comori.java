package src;

import java.util.*;
import java.io.*;

//clasa, în care se efectuează punctele problemei
public class Comori {
    //inițializarea scannerului, care va fi vizibil în totate funțiile clasei
    static Scanner in = new Scanner(System.in);
    //inițializarea ArrayList-ului, care conține obiecte (tipul pasului și numărul de pași)
    static ArrayList<Pas> pas = new ArrayList<Pas>();


    //funcția, care citește datele din fișierul ”Comori.in”
    static void getPasuri() throws IOException, FileNotFoundException{
        //inițializarea scannerului, pentru a citi datele din fișier
        Scanner filescan = new Scanner(new FileReader("E:\\Admin\\Practica anul II\\src\\src\\Comori.in"));

        //citirea fișierului
        while(filescan.hasNext()){
            //înscrierea unei linii într-un string
            String line = filescan.nextLine();
            //împărțirea string-ului acolo, unde este virgula, urmată de spațiu
            String[] lineArray = line.split(", ");

            //introducerea datelor în ArrayList-ului cu pași
            pas.add(new Pas(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1])));
        }

        //închiderea scannerului
        filescan.close();
    }

    //funția, care afișează datele despre pași
    static void showPasii() throws  IOException{
        //recitirea listei
        refreshArray();

        //parcurgerea ArrayList-ului
        for(Pas i: pas){
            //utilizarea funției, pentru a afișa datele obiectului din clasa Pas
            i.getAllData();
        }

        //recitirea listei
        refreshArray();
    }

    //funcția pentru a reciti, a reîncărca, a rescrie ArrayList-ul, care conține obiectele
    static void refreshArray() throws IOException{
        //ștergerea datelor din ArrayList
        pas.clear();

        //introducerea datelor din fișier în ArraList
        getPasuri();
    }

    //funcția, pentru a adăuga un pas nou
    static void addPas() throws IOException, FileNotFoundException{
        //recitirea fișierului în ArrayList-ului cu obiecte
        refreshArray();

        //inițilazrea scrierii în fișier
        BufferedWriter fw = new BufferedWriter(new FileWriter("E:\\Admin\\Practica anul II\\src\\src\\Comori.in"));

        System.out.println("\n\n=======================================< INTRODUCEREA UNUI NOU PAS >========================================");

        try{
            //citirea tipului de pas
            System.out.print("Tipul pasului: ");
            int tipPas = in.nextInt();

            if(tipPas < 1 && tipPas > 8){
                System.out.println("Așa pas nu există. Introduceți pasul inclus între 1 si 8. Introduceți încă o dată: ");
                tipPas = in.nextInt();
            }

            //citirea numărului de pași
            System.out.print("Numărul de pași: ");
            int nrDePasi = in.nextInt();

            if(nrDePasi < 1 && nrDePasi > 1000){
                System.out.println("Numărul de pași trebuie să fie mai mare ca 1 și mai mic ca 1000. Introduceți încă o dată: ");
                nrDePasi = in.nextInt();
            }

            //citirea rândului în carea va fi pasul
            System.out.print("Rândul în care va fi noul pas: ");
            int rand = in.nextInt();

            if(rand < 1 && rand > pas.size()){
                System.out.println("Introduceți un număr mai mare ca 1 si mai mic ca " + pas.size() + ". Introduceți înca o data: ");
                rand = in.nextInt();
            }

            //variabila va stoca în care linie se află acum ciclul de mai jos
            int randAux = 1;

            //parcurgerea ArrayList-ului
            for(Pas i: pas){
                //verificarea dacă am ajuns la rândul de care avem nevoie
                if (rand == randAux) {
                    //dacă s-a ajuns la rândul ales de utilizator, se vor scrie
                    fw.write(tipPas + ", " + nrDePasi + "\n");
                }
                //înscrierea datelor despre pas în fișier
                fw.write(i.tipPas + ", " + i.nrDePasi + "\n");
                //incrementarea variabilei, pentru a fi la curent în ce linie se află ciclul
                randAux++;
            }
        } catch(InputMismatchException ex){
            System.out.println("Introduceti datele corect.");
        }

        //închiderea scriitorului în fișier
        fw.close();

        //recitirea datelor din fișier, pentru a avea și pasul nou-introdus
        refreshArray();

        System.out.println("Rândul a fost adăugat în fișier.");

        System.out.println("============================================================================================================\n\n");

        //reafișarea meniului
        meniu();
    }

    //funcția, pentru a șterge un pas
    static void deletePas() throws  IOException, FileNotFoundException{
        //recitirea datelor din fișier
        refreshArray();

        //inițializarea scriitorului în fișier
        BufferedWriter fw = new BufferedWriter(new FileWriter("E:\\Admin\\Practica anul II\\src\\src\\Comori.in"));

        try{
            //citirea liniei, care va fi ștearsă
            System.out.println("\n\n========================================< ȘTERGERGEREA UNEI LINII >=========================================");
            System.out.print("Linia care va fi ștearsă: ");
            int rand = in.nextInt();

            //ștergerea liniei alese din ArrayList
            pas.remove(rand-1);

            //parcurgerea ArrayList-ului
            for(Pas i: pas){
                //înscrierea datelor în fișier
                fw.write(i.tipPas + ", " + i.nrDePasi + "\n");
            }
        } catch(InputMismatchException ex){
            System.out.println("Introduceți datele corect.");
        }

        //închiderea scriitorului din fișier
        fw.close();

        //recitirea datelor din fișier
        refreshArray();

        System.out.println("Linia a fost ștearsă.");

        System.out.println("============================================================================================================\n\n");

        //reafișarea meniului
        meniu();
    }

    //funcția, pentru a calcula numărul total de pași
    static void totalPasi() throws  IOException{
        //recitirea ArrayList-ului
        refreshArray();

        //variabila care va stoca numărul total de pași
        int nrTotalDePasi = 0;

        //parcurgerea liste
        for(Pas i: pas){
            //incrementarea numărlului total de pași cu numărul de pași efectuați la un punct anumit
            nrTotalDePasi += i.nrDePasi;
        }

        System.out.println("\n\n=========================================< NUMĂRUL TOTAL DE PAȘI >==========================================");

        //afișarea pe ecran numărul total de pași
        System.out.println("Numărul de pași necesari pentru a găsi comoara este " + nrTotalDePasi + ".");

        System.out.println("============================================================================================================\n\n");

        //recitirea listei
        refreshArray();

        //reafișarea meniului
        meniu();
    }

    //funcția, pentru a calcula numărul total de schimbări în direcție
    static void totalSchimbariDirectie() throws IOException{
        //recitirea listei
        refreshArray();

        //variabila, în care se va stoca numărul total de schimbări în direcție
        int nrSchimbariDirectie = 0;

        //parcurgerea listei
        for(int i = 0; i < pas.size(); i++){
            //verifiacrea, dacă nu s-a ajuns la ultimul element
            if(i+1 != pas.size())
                //verificarea, dacă tipul pasului curent nu este același ca și următorul
                if(pas.get(i).tipPas != pas.get(i+1).tipPas)
                    //incrementarea variabilei, care stochează numărul total de schimbări a direcției
                    nrSchimbariDirectie++;
        }

        /*
        Notă nr. 1:
        Se verifică dacă s-a ajuns la pen-ultimul element, pentru a preveni încercarea de a compara cu un element, care nu există,
        care se află după ultimul elemnt.

        Notă nr. 2:
        Dacă doi pași sunt identici, nu se v-a schimba direcția de deplasare spre comoară.
        */

        System.out.println("\n\n=================================< NUMĂRUL TOTAL DE SCHIMBĂRI ÎN DIRECȚIE >=================================");

        //afișarea numărului total de schimbări în direcție
        System.out.println("Numărul total de schimbări în direcție necesare pentru a ajunge la țintă este " + nrSchimbariDirectie + ".");

        System.out.println("============================================================================================================\n\n");

        //recitirea liste
        refreshArray();

        //reafișarea meniului
        meniu();
    }

    //funcția, care transcrie în fișier toți pașii, efectuați o dată
    static void numarulDePasiEste1() throws IOException{
        //recitirea listei
        refreshArray();

        //inițializarea scriitorului în fișiere
        BufferedWriter fw = new BufferedWriter(new FileWriter("E:\\Admin\\Practica anul II\\src\\src\\UnPas.txt"));

        System.out.println("\n\n================================< NUMĂRUL DE PAȘI CARE S-AU EFECTUAT O DATĂ >===============================");

        //parcurgerea liste
        for(Pas i: pas){
            //verificarea dacă numărul de pași este egal cu unu
            if(i.nrDePasi == 1){
                //transcrierea liniei găsite în fișier
                fw.write(i.tipPas + ", " + i.nrDePasi + "\n");

                System.out.println(i.tipPas + ", " + i.nrDePasi);
            }
        }

        //anunțarea că procesul s-a finisat
        System.out.println("\nLista de pași a fost adăugata în fișier.");

        System.out.println("============================================================================================================\n\n");

        //închiderea scriitorului
        fw.close();

        //recitirea listei
        refreshArray();

        //reafișarea meniului
        meniu();
    }

    //funcția pentru afișarea listei de direcții sortate
    static void listaDirectiilorSortate() throws IOException{
        //recitirea listei
        refreshArray();

        //inițializarea listei, care va stoca tipurile pasurilor și numărul lor de apariții
        ArrayList<TipuriDeDirectie> tipuriDeDirectie = new ArrayList<TipuriDeDirectie>();

        //transcrierea tipurilor de pasuri din lista principală în cea, creată mai sus
        for(Pas i: pas){
            tipuriDeDirectie.add(new TipuriDeDirectie(i.tipPas));
        }

        //calcularea numărului de apariție a anumitui pas
        for(TipuriDeDirectie i: tipuriDeDirectie){
            //variabila, care va stoca numărul de apariții a anumitui pas
            int nrDeTipuri = 0;
            //parcurgerea listei și căutarea aceleași tipurilor de pas
            for(TipuriDeDirectie j: tipuriDeDirectie){
                if(i.tipulPasului == j.tipulPasului)
                    //incrementarea variabilei, dacă s-a găsit un tip de pas potrivit
                    nrDeTipuri++;
            }
            //atribuirea variabilei din clasă, care conține numărul de apariții a unui tip de pas, valoarea calculată în ciclu
            i.numarulDeAparitii = nrDeTipuri;
        }

        //sortarea listei prin metoda bulelor
        for(int i = 0; i < tipuriDeDirectie.size(); i++){
            for(int j = 1; j < tipuriDeDirectie.size()-i; j++){
                if(tipuriDeDirectie.get(j-1).numarulDeAparitii > tipuriDeDirectie.get(j).numarulDeAparitii){
                    //schimbarea cu lorcul obiectele din listă
                    Collections.swap(tipuriDeDirectie, j-1, j);
                }
            }
        }

        System.out.println("\n\n============================================< LISTA DE DIRECȚII >===========================================");

        //afișarea listei de pasuri, în ordine crescătoare a numărului lor de apariție
        for(TipuriDeDirectie i: tipuriDeDirectie){
            System.out.println(i.getTipulPasului() + ", de " + i.getNumarulDeAparitii() + " ori.");
        }

        System.out.println("============================================================================================================\n\n");

        //recitirea listei
        refreshArray();

        //reafișarea meniului
        meniu();
    }

    //funcția care găsește dreptunghiul în care se află comoara
    static void dreptunghiulComorii() throws  IOException{
        //recitirea listei
        refreshArray();

        //lista care conține toate coordontale pe axa Ox a traseului
        ArrayList<Integer> Xs = new ArrayList<>();
        Xs.add(0);
        //lista care conține totate coordonatele pe axa Oy a traseului
        ArrayList<Integer> Ys = new ArrayList<>();
        Ys.add(0);

        //variabilele, care vor stoca punctele dreptunghiului
        int maxX, maxY, minX, minY;

        //parcurgerea listei cu pași
        for(Pas i: pas){
            //adăugarea în listă unui nou element, egal cu precedent, dar schimbat în dependență de tipul pasului, și numărului lor
            switch(i.tipPas){
                case 1:
                    Ys.add(Ys.get(Ys.size()-1) + i.nrDePasi);
                    break;

                case 2:
                    Xs.add(Xs.get(Xs.size()-1) + i.nrDePasi);
                    Ys.add(Ys.get(Ys.size()-1) + i.nrDePasi);
                    break;

                case 3:
                    Xs.add(Xs.get(Xs.size()-1) + i.nrDePasi);
                    break;

                case 4:
                    Xs.add(Xs.get(Xs.size()-1) + i.nrDePasi);
                    Ys.add(Ys.get(Ys.size()-1) - i.nrDePasi);
                    break;

                case 5:
                    Ys.add(Ys.get(Ys.size()-1) - i.nrDePasi);
                    break;

                case 6:
                    Xs.add(Xs.get(Xs.size()-1) - i.nrDePasi);
                    Ys.add(Ys.get(Ys.size()-1) - i.nrDePasi);
                    break;

                case 7:
                    Xs.add(Xs.get(Xs.size()-1) - i.nrDePasi);
                    break;

                case 8:
                    Xs.add(Xs.get(Xs.size()-1) - i.nrDePasi);
                    Ys.add(Ys.get(Ys.size()-1) + i.nrDePasi);
                    break;

                default:
                    break;
            }
        }

        //variabila care are valoarea celui mai mare element din lista cu coordonatele pe axa Ox
        maxX = Collections.max(Xs);
        //variabila care are valoarea celui mai mare element din lista cu coordonatele pe axa Oy
        maxY = Collections.max(Ys);

        //variabila care are valoarea celui mai mic element din lista cu coordonatele pe axa Ox
        minX = Collections.min(Xs);
        //variabila care are valoarea celui mai mic element din lista cu coordonatele pe axa Oy
        minY = Collections.min(Ys);

        System.out.println("\n\n============================< DREPTUNGHIUL ÎN CARE SE AFLĂ DRUMUL SPRE COMOARĂ >============================");

        //afișarea punctelor și ariei dreptunghiului
        System.out.println("Punctul de dreapta-sus a dreptunghiului: D(" + maxX + "; " + maxY + ");");
        System.out.println("Punctul de stânga-jos a dreptunghiului: S(" + minX + "; " + minY + ");");
        System.out.println("Aria dreptunghiului: " +  ( maxX + Math.abs(minX) ) * ( maxY + Math.abs(minY) ) + ".");

        System.out.println("============================================================================================================\n\n");

        //recitirea listei cu pasuri
        refreshArray();

        //reafișarea meniului
        meniu();
    }

    //funcția care găsește coordinata comorii
    static void coordinataComorii() throws IOException{
        //recitirea listei
        refreshArray();

        //obiectul, care va stoca coordonatele comorii
        CoordonataComorii coordonataComorii = new CoordonataComorii(0, 0);

        //parcurgerea listei și schimbarea coordonatelor în dependență de tipul pasului
        for(Pas i: pas){
            if(i.tipPas == 1){
                coordonataComorii.coordonataX += i.nrDePasi;
            } else if(i.tipPas == 2){
                coordonataComorii.coordonataX += i.nrDePasi;
                coordonataComorii.coordonataY += i.nrDePasi;
            } else if(i.tipPas == 3){
                coordonataComorii.coordonataY += i.nrDePasi;
            } else if(i.tipPas == 4){
                coordonataComorii.coordonataX += i.nrDePasi;
                coordonataComorii.coordonataY -= i.nrDePasi;
            } else if(i.tipPas == 5){
                coordonataComorii.coordonataY -= i.nrDePasi;
            } else if(i.tipPas == 6){
                coordonataComorii.coordonataX -= i.nrDePasi;
                coordonataComorii.coordonataY -= i.nrDePasi;
            } else if(i.tipPas == 7){
                coordonataComorii.coordonataX -= i.nrDePasi;
            } else if(i.tipPas == 8){
                coordonataComorii.coordonataX -= i.nrDePasi;
                coordonataComorii.coordonataY += i.nrDePasi;
            }
        }

        //inițializarea FileWriter-ului, care va înscrie în fișier datele
        FileWriter fw = new FileWriter("E:\\Admin\\Practica anul II\\src\\src\\Comori.out");

        //înscrierea în fișier coordonatele comorii
        fw.write(coordonataComorii.coordonataX + ", " + coordonataComorii.coordonataY);

        System.out.println("\n\n===========================================< COORDONATELE COMORII >=========================================");

        System.out.println("Comoara se află în punctul C(" + coordonataComorii.coordonataX + ", " + coordonataComorii.coordonataY + ").");

        //afișarea la ecran faptului că s-a realizat punctul
        System.out.println("Cordonatale comorii au fost adaugate în fișier.");

        System.out.println("============================================================================================================\n\n");

        //închiderea FileWriter-ului
        fw.close();

        //recitirea listei
        refreshArray();

        //reafișarea meniului
        meniu();
    }

    //funcția pentru afișarea meniului pe ecran
    static void afisareMeniu() throws IOException{
        //meniul
        System.out.println("=================================================< MENIUL >=================================================");
        System.out.println("|  0. Terminarea programului.                                                                              |");
        System.out.println("|  1. Adăugare în fișier un rând nou.                                                                      |");
        System.out.println("|  2. Excluderea din fișier a unui rând.                                                                   |");
        System.out.println("|  3. Determinarea numarului de pași necesari pentru a ajunge la comoară.                                  |");
        System.out.println("|  4. Determinarea numarului necesar de schimbări in direcție pentru a ajunge la comoară.                  |");
        System.out.println("|  5. Transcrierea in fișier pașilor, numarul cărora este egal cu 1.                                       |");
        System.out.println("|  6. Afișarea listei de direcții, sortată ascendent in dependență de numărul lor de apariții pe hartă.    |");
        System.out.println("|  7. Identificarea dreptunghiului de dimensiuni minimale în care se încadrează drumul spre comoară.       |");
        System.out.println("|  8. Identificarea si transcrierea in fișier coordonatele comorii.                                        |");
        System.out.println("============================================================================================================");
    }

    //funcția, care efectuează punctele din meniu
    static void switchMeniu(int punct) throws IOException {
        //efectuarea funcțiilor
        switch (punct) {
            case 0:
                System.exit(0);
                break;
            //efectuarea punctului 1
            case 1:
                addPas();
                break;
            //efectuarea punctului 2
            case 2:
                deletePas();
                break;
            //ș.a.m.d.
            case 3:
                totalPasi();
                break;
            case 4:
                totalSchimbariDirectie();
                break;
            case 5:
                numarulDePasiEste1();
                break;
            case 6:
                listaDirectiilorSortate();
                break;
            case 7:
                dreptunghiulComorii();
                break;
            case 8:
                coordinataComorii();
                break;
            default:
                System.out.println("Introduceți un punct din meniu. ");
        }
    }

    //funcția, care citește punctul dorit și îl efectuează
    static void efectuarePunctMeniu() throws IOException{
        System.out.print("Punctul dorit: ");

        //tratarea excepțiilor
        try{
            //citirea punctului, care va fi efectuat
            switchMeniu(in.nextInt());
        } catch(InputMismatchException ex){
            //în caz că ceea, ce a fost citit nu este un număr întreg, se va afișa următoarea:
            System.out.println("Introduceți datele corect.");
        }
    }

    //meniul
    static void meniu() throws IOException{
        //afișarea meniului
        afisareMeniu();

        //efectuarea punctului ales de utilizator
        efectuarePunctMeniu();
    }

    //funcția main
    public static void main(String [] args) throws IOException {
        meniu();
    }
}