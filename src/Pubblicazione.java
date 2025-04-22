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
                } else if(nome.length() < 3) {
                    System.out.print("Reinserire nome di almeno 3 caratteri: ");
                    nome = input.nextLine();
                }
                contieneNumeri(nome);
                this.nome = nome;
                flag = true;
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
                input.nextLine();
            }
        } while(!flag);
    }

    public int getCopieRicevute() {
        return copieRicevute;
    }

    public void setCopieRicevute(String copieRicevute) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire numero copie ricevute valido: ");
                    copieRicevute = input.nextLine();
                } else if(Integer.parseInt(copieRicevute) <= 0) {
                    System.out.print("Reinserire numero copie ricevute positivo: ");
                    copieRicevute = input.nextLine();
                }
                this.copieRicevute = Integer.parseInt(copieRicevute);
                flag = true;
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
            }
        } while(!flag);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire prezzo: ");
                    prezzo = input.nextLine();
                } else if(Double.parseDouble(prezzo) <= 0) {
                    System.out.print("Reinserire un prezzo positivo: ");
                    prezzo = input.nextLine();
                }
                this.prezzo = Double.parseDouble(prezzo);
                flag = true;
            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
            }
        } while(!flag);
    }

    public int getAggio() {
        return aggio;
    }

    public void setAggio(String aggio) {
        boolean flag = false, errore = false;
        
        do {
            try {
                if(errore) {
                    System.out.print("Reinserire aggio da 5% a 20%: ");
                    aggio = input.nextLine();
                } else if(Integer.parseInt(aggio) <= 0) {
                    System.out.print("Reinserire un aggio positivo: ");
                    aggio = input.nextLine();
                }
                this.aggio = Integer.parseInt(aggio);
                flag = true;

            } catch(Exception e) {
                errore = true;
                System.out.println("ERRORE: " + e.getMessage());
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
            } catch(Exception e) {
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