package bean;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quotidiano {
    private int id;
    private String nome;
    private int copieRicevute;
    private double prezzo;
    private int aggio;
    private int copieVendute;
    
    Scanner input = new Scanner(System.in);

    public Quotidiano() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome() {
        boolean flag = false, errore = false;
        String nome;
        
        do {
            try {
                if(errore) {
                    System.out.print("ERRORE: ");
                }
                System.out.print("Inserire nome pubblicazione: ");
                nome = input.nextLine();
                contieneNumeri(nome);
                this.nome = nome;
                flag = true;
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
            }
        } while(!flag);
    }

    public int getCopieRicevute() {
        return copieRicevute;
    }

    public void setCopieRicevute() {
        boolean flag = false, errore = false;
        int copieRicevute;

        do {
            try {
                if(errore) {
                    System.out.print("ERRORE: ");
                } 
                System.out.print("Inserire quantità di copie ricevute: ");
                copieRicevute = input.nextInt();
                if(copieRicevute > 0) {
                    this.copieRicevute = copieRicevute;
                    flag = true;
                } else {
                    errore = true;
                }
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
            }
        } while(!flag);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo() {
        boolean flag = false, errore = false;
        double prezzo;
        
        do {
            try {
                if(errore) {
                    System.out.print("ERRORE: ");
                } 
                System.out.print("Inserire prezzo di copertina: ");
                prezzo = input.nextDouble();
                if(prezzo > 0) {
                    this.prezzo = prezzo;
                    flag = true;
                } else {
                    errore = true;
                }
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
            }
        } while(!flag);
    }

    public int getAggio() {
        return aggio;
    }

    public void setAggio() {
        boolean flag = false, errore = false;
        int aggio;
        
        do {
            try {
                if(errore) {
                    System.out.print("ERRORE: ");
                } 
                System.out.print("Inserire percentuale di aggio (da 5% a 20%): ");
                aggio = input.nextInt();
                if(aggio > 0) {
                    this.aggio = aggio;
                    flag = true;
                } else {
                    errore = true; 
                }
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
            }
        } while(!flag);
    }

    public int getCopieVendute() {
        return copieVendute;
    }

    public void setCopieVendute() {
        boolean flag = false, errore = false;
        int copieVendute;
        
        do {
            try {
                if(errore) {
                    System.out.print("ERRORE: ");
                }
                System.out.print("Inserire quantità di copie vendute: ");
                copieVendute = input.nextInt();
                if(copieVendute > 0 && copieVendute <= copieRicevute) {
                    this.copieVendute = copieVendute;
                    flag = true;
                } else {
                    errore = true;
                }
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE" + e.getMessage());
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