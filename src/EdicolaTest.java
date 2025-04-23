import java.util.Scanner;
import bean.Quotidiano;
import dao.QuotidianoDao;
import java.util.ArrayList;
import java.util.List;

public class EdicolaTest {

    public static void main(String[] args) throws Exception {
        char termina = 'N';
        int select;
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
                        nuovaPubblicazione.setCopieRicevute();
                        nuovaPubblicazione.setPrezzo();
                        nuovaPubblicazione.setAggio();
                        nuovaPubblicazione.setCopieVendute();
            
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
                    List<Quotidiano> quotidiani = new ArrayList<>();
                    int index = 1;

                    qDao.selectAll();

                    if (!quotidiani.isEmpty()) {
                        System.out.println("RESOCONTO ATTUALE:");
                        for (Quotidiano q : quotidiani) {
                            System.out.println(index + ") " + q);
                            index++;
                        }
                       // gestionePubblicazione(quotidiani);
                    } else {
                        System.out.println("Non sono presenti pubblicazioni nel Database");
                    }
                    break;
                case 3:
                    System.out.println("RENDICONTO GIORNALIERO:");
                    System.out.println(qDao.selectRendiconto() + " euro"); 
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