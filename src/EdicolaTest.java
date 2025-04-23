import java.util.Scanner;

import bean.Quotidiano;

public class EdicolaTest {
    public static void main(String[] args) throws Exception {
        char termina = 'N';
        int select;
        boolean errore = false, aperto = true;

        Edicola miaEdicola = new Edicola();

        Scanner inputString = new Scanner(System.in);
        Scanner inputNum = new Scanner(System.in);

        while (aperto) {
            do {
                miaEdicola.printMenu();
                select = inputNum.nextInt(); 
                System.out.println();
            } while(select < 1 || select > 4);

            switch(select) {
                case 1:
                    do {
                        Quotidiano nuovaPubblicazione = new Quotidiano();
            
                        nuovaPubblicazione.setNome();
                        nuovaPubblicazione.setCopieRicevute();
                        nuovaPubblicazione.setPrezzo();
                        nuovaPubblicazione.setAggio();
                        nuovaPubblicazione.setCopieVendute();

                        miaEdicola.addPubblicazione(nuovaPubblicazione);
            
                        do {
                            if(errore) System.out.println("\nERRORE: i valori ammessi sono S/N");
                            System.out.print("\nVuoi inserire un'altra pubblicazione (S/N)? ");
                            termina = inputString.nextLine().charAt(0);
                            errore = true;
                        } while(termina != 'N' && termina != 'S');
                        errore = false;
                        System.err.println();
                    } while(termina != 'N');
                    System.out.println("RESOCONTO ATTUALE:");
                    miaEdicola.printResoconto();
                    break;
                case 2:
                    miaEdicola.modificaPubblicazione();
                    System.out.println("RESOCONTO ATTUALE:");
                    miaEdicola.printResoconto();
                    break;
                case 3:
                    miaEdicola.rimuoviTerminati();
                    System.out.println("RESOCONTO ATTUALE:");
                    miaEdicola.printResoconto();
                    break;
                case 4:
                    aperto = false;
                    break;
                default:
                    break;
            }
        }

        inputNum.close();
        inputString.close();
        System.exit(0);
    }
}