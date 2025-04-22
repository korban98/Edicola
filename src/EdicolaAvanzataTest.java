import java.util.Scanner;

public class EdicolaAvanzataTest {
    public static void main(String[] args) throws Exception {
        char termina = 'N';
        int select;
        boolean errore = false, aperto = true;

        EdicolaAvanzata miaEdicola = new EdicolaAvanzata();

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
                        Pubblicazione nuovaPubblicazione = new Pubblicazione();
            
                        System.out.print("Inserire nome pubblicazione (almeno 3 caratteri): ");
                        nuovaPubblicazione.setNome(inputString.nextLine());
                        System.out.print("Inserire quantità di copie ricevute: ");
                        nuovaPubblicazione.setCopieRicevute(inputString.nextLine());
                        System.out.print("Inserire prezzo di copertina: ");
                        nuovaPubblicazione.setPrezzo(inputString.nextLine());
                        System.out.print("Inserire percentuale di aggio (da 5% a 20%): ");
                        nuovaPubblicazione.setAggio(inputString.nextLine());
                        System.out.print("Inserire quantità di copie vendute: ");
                        nuovaPubblicazione.setCopieVendute(inputString.nextLine());
            
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