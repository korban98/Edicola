import java.util.Scanner;
import bean.Quotidiano;
import dao.QuotidianoDao;

public class EdicolaTest {

    public static void main(String[] args) throws Exception {
        char termina = 'N';
        int select, id;
        boolean errore = false, aperto = true;

        QuotidianoDao qDao = new QuotidianoDao();

        Scanner inputString = new Scanner(System.in);
        Scanner inputNum = new Scanner(System.in);

        while (aperto) {
            do {
                System.out.println("MENU:");
                System.out.println("1. Aggiungere una pubblicazione");
                System.out.println("2. Gestisci una pubblicazione");
                System.out.println("3. Ottenere rendiconto");
                System.out.println("4. Eliminare pubblicazione");
                System.out.println("5. Chiudi\n");
                System.out.print("Scegli una voce dal menu': ");
                select = inputNum.nextInt();
                System.out.println();
            } while (select < 1 || select > 4);

            switch (select) {
                case 1:
                    do {
                        Quotidiano nuovaPubblicazione = new Quotidiano();
            
                        nuovaPubblicazione.setNome();
                        nuovaPubblicazione.setPrezzo();
                        nuovaPubblicazione.setAggio();
            
                        qDao.insert(nuovaPubblicazione);
            
                        do {
                            if(errore) System.out.println("\nERRORE: i valori ammessi sono S/N");
                            System.out.print("\nVuoi inserire un'altra pubblicazione (S/N)? ");
                            termina = inputString.nextLine().charAt(0);
                            errore = true;
                        } while(termina != 'N' && termina != 'S');
                        errore = false;
                        System.err.println();
                    } while(termina != 'N');
                    break;
                case 2:
                    qDao.selectAll();
                    System.out.print("Inserisci id pubblicazione da gestire: ");
                    id = inputNum.nextInt();
                    do {
                        System.out.println("SUB-MENU:");
                        System.out.println("1. Inserire copie ricevute");
                        System.out.println("2. Incrementare copie vendute");
                        System.out.println("3. Modificare prezzo");
                        System.out.println("4. Modificare aggio");
                        System.out.println("5. Reset di inizio giornata");
                        System.out.println("6. Torna al MENU\n");
                        System.out.print("Scegli una voce dal sub-menu': ");
                        select = inputNum.nextInt();
                        System.out.println();
                    } while(select < 1 || select > 6);

                    switch (select) {
                        case 1:
                            System.out.print("Inserire copie ricevute: ");
                            int ricevute = inputNum.nextInt();
                            System.out.print("Inserire copie vendute: ");
                            int vendute = inputNum.nextInt();
                            qDao.updateRicevuteEVendute(id, ricevute, vendute);
                            break;
                        case 2:
                            qDao.incrementaCvendute(id);
                            break;
                        case 3:
                            System.out.print("Inserire prezzo: ");
                            double prezzo = inputNum.nextDouble();
                            qDao.modificaPrezzo(id, prezzo);
                            break;
                        case 4:
                            System.out.print("Inserire aggio: ");
                            int aggio = inputNum.nextInt();
                            qDao.modificaAggio(id, aggio);
                            break;
                        case 5:
                            qDao.reset();
                            break;
                        case 6:
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("RENDICONTO GIORNALIERO:");
                    qDao.selectRendiconto();
                    break;
                case 4:
                    qDao.selectAll();
                    break;
                case 5:
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