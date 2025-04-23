/*import java.util.ArrayList;
import java.util.Scanner;

import bean.Quotidiano;

public class Edicola {
    private ArrayList<Quotidiano> pubblicazioni;

    Scanner input = new Scanner(System.in);

    public Edicola() {
        pubblicazioni = new ArrayList<>();
    }

    public ArrayList<Quotidiano> getPubblicazioni() {
        return pubblicazioni;
    }

    public void addPubblicazione(Quotidiano pubblicazione) {
        pubblicazioni.add(pubblicazione);
    }

public void printResoconto() {
        double guadagnoTotale = 0;

        if(!pubblicazioni.isEmpty()) {
            for(Quotidiano pubblicazione : pubblicazioni) {
                System.out.println("Nome pubblicazione: " + pubblicazione.getNome());
                System.out.println("Numero copie vendute: " + pubblicazione.getCopieVendute());
                System.out.println("Numero copie da rendere: " + getCopieDaRendere(pubblicazione) + "\n");
                guadagnoTotale += getGuadagno(pubblicazione);
            }

            System.out.println("Guadagno netto complessivo: " + guadagnoTotale + " euro\n");
        }  
    }

    public void printMenu() {
        System.out.println("MENU:");
        System.out.println("1. Inserisci");
        System.out.println("2. Modifica");
        System.out.println("3. Rimuovi pubb. terminate");
        System.out.println("4. Chiudi\n");
        System.out.print("Scegli una voce dal menu': ");
    }

    public void rimuoviTerminati() {
        if(!pubblicazioni.isEmpty()) {
            ArrayList<Quotidiano> temp = new ArrayList<>(pubblicazioni);
            int i = 0;

            for(Quotidiano pubblicazione : temp) {
                if(getCopieDaRendere(pubblicazione) == 0) {
                    pubblicazioni.remove(pubblicazione);
                    i++;
                }
            }
            System.out.println("Sono state rimosse " + i + " pubblicazioni terminate\n");
        } else {
            System.out.println("Non sono presenti pubblicazioni in magazzino\n");
        }
    }

    public void modificaPubblicazione() {
        int select, i = 0;
        boolean errore = false;

        if(!pubblicazioni.isEmpty()) {
            System.out.println("PUBBLICAZIONI:");
            for(Quotidiano pubblicazione : pubblicazioni) {
                System.out.println(++i + ". " + pubblicazione.getNome());
            }

            do {
                if(errore) System.out.println("\nERRORE: scegliere un valore da 1 a " + pubblicazioni.size());
                System.out.print("\nScegli una pubblicazione da modificare: ");
                select = input.nextInt(); 
                System.out.println();
            } while(select < 1 || select > pubblicazioni.size());

            System.out.print("Qnt. ricevute memorizzate: " + pubblicazioni.get(select-1).getCopieRicevute() + " => " + "Qnt. attualmente ricevute: ");
            pubblicazioni.get(select-1).setCopieRicevute(input.nextLine());

            System.out.print("Prezzo memorizzato: " + pubblicazioni.get(select-1).getPrezzo() + " => " + "Prezzo attuale: ");
            pubblicazioni.get(select-1).setPrezzo(input.nextLine());

            System.out.print("Aggio memorizzato: " + pubblicazioni.get(select-1).getAggio() + " => " + "Aggio attuale: ");
            pubblicazioni.get(select-1).setAggio(input.nextLine());

            System.out.print("Qnt. vendute memorizzate: " + pubblicazioni.get(select-1).getCopieVendute() + " => " + "Qnt. attualemnte vendute: ");
            pubblicazioni.get(select-1).setCopieVendute(input.nextLine());
            System.out.println();
        } else {
            System.out.println("Non sono presenti pubblicazioni in magazzino\n");
        }
    }

    private int getCopieDaRendere(Quotidiano pubblicazione) {
        return pubblicazione.getCopieRicevute() - pubblicazione.getCopieVendute();
    }

    private double getGuadagno(Quotidiano pubblicazione) {
        return (pubblicazione.getPrezzo() * pubblicazione.getAggio() / 100) * pubblicazione.getCopieVendute();
    }
}*/