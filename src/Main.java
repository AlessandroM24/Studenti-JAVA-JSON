// Macaluso Alessandro 4^C INF. 22/01/2024

public class Main {
    public static void main(String[] args) {
        Classi classi = new Classi();

        System.out.println("Classi: " + classi.getClassi()); // Stampa tutte le classi presenti in studenti.json


        System.out.println("Studenti della classe 3ainf: " + classi.getStudentiClasse("3ainf")); // Stampa tutti gli studenti presenti nella classe
        //passata come parametro.


        System.out.println("Voti dello studente Alessandro Macaluso: " + classi.getVotiStudente("salessandro.macaluso@itis.pr.it"));
        // Stampa i voti dello studente con l'email passata come parametro.


        System.out.println("\nMedia dello studente Alessandro Macaluso: " + classi.mediaStudente("salessandro.macaluso@itis.pr.it"));
        // Stampa la media dei voti dello studente con l'email passata come parametro.


        System.out.println("Studenti promossi classe 3ainf: " + classi.getStudentiConMediaMiglioreClasse("3ainf"));
        // La classe 3ainf è composta da LA ROCCA e BONAZZI.
        // Stampa solo BONAZZI perché è l'unico ad avere la media maggiore o uguale a 6.


        System.out.println("Studenti promossi intera scuola: " + classi.getStudentiConMediaMiglioreScuola());
        // Stampa solo BONAZZI E MACALUSO perché sono gli unici ad avere una media maggiore o uguale a 6
        // all'interno della scuola ("voti.json").
    }
}