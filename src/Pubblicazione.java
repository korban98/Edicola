import java.util.InputMismatchException;
import java.util.Scanner;

public class Pubblicazione {
    private String nome;
    private int copieRicevute;
    private double prezzo;
    private int aggio;
    private int copieVendute;

    Scanner input = new Scanner(System.in);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire nome: ");
                    nome = input.nextLine();
                } 
                contieneNumeri(nome);
                this.nome = nome;
                flag = true;
            } catch(InputMismatchException e) {
                errore = true;
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while(!flag);
    }

    public int getCopieRicevute() {
        return copieRicevute;
    }

    public void setCopieRicevute(int copieRicevute) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire numero copie ricevute valido: ");
                    copieRicevute = input.nextInt();
                } else if(copieRicevute <= 0) {
                    System.out.print("Reinserire numero copie ricevute positivo: ");
                    copieRicevute = input.nextInt();
                }
                this.copieRicevute = copieRicevute;
                flag = true;
            } catch(InputMismatchException e) {
                errore = true;
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while(!flag);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire prezzo: ");
                    prezzo = input.nextInt();
                } else if(prezzo <= 0) {
                    System.out.print("Reinserire un prezzo positivo: ");
                    prezzo = input.nextInt();
                }
                this.prezzo = prezzo;
                flag = true;
            } catch(InputMismatchException e) {
                errore = true;
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while(!flag);
    }

    public int getAggio() {
        return aggio;
    }

    public void setAggio(int aggio) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire aggio: ");
                    aggio = input.nextInt();
                } else if(aggio <= 0) {
                    System.out.print("Reinserire un aggio positivo: ");
                    aggio = input.nextInt();
                }
                this.aggio = aggio;
                flag = true;
            } catch(InputMismatchException e) {
                errore = true;
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while(!flag);
    }

    public int getCopieVendute() {
        return copieVendute;
    }

    public void setCopieVendute(int copieVendute) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire copieVendute: ");
                    copieVendute = input.nextInt();
                } else if(copieVendute <= 0 || copieVendute > copieRicevute) {
                    System.out.print("Reinserire numero copie vendute positivo e maggiore delle copie ricevute (" + copieRicevute + "): ");
                    copieVendute = input.nextInt();
                }
                this.copieVendute = copieVendute;
                flag = true;
            } catch(InputMismatchException e) {
                errore = true;
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while(!flag);
    }

    private void contieneNumeri(String str) throws InputMismatchException {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new InputMismatchException("Il nome e cognome non possono contenere numeri.");
            }
        }
    }
}