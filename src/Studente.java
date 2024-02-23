// Macaluso Alessandro 4^C INF. 22/01/2024

public class Studente {
    private String utente;
    private String cognome;
    private String nome;
    private String email;
    private String classe;

    //COSTRUTTORE
    public Studente(String utente, String cognome, String nome, String email, String classe) {
        this.utente = utente;
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.classe = classe;
    }

    //GETTERS
    public String getUtente() {
        return utente;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getClasse() {
        return classe;
    }

    //toString
    @Override
    public String toString() {
        return "Studente{" +
                "utente='" + utente + '\'' +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}
