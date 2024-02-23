// Macaluso Alessandro 4^C INF. 22/01/2024

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Classi {
    public Classi() {

    }


    /**
     * Ottenere un ArrayList con tutte le classi presenti nel file "studenti.json".
     *
     * @return ArrayList con tutte le classi.
     */
    public ArrayList<String> getClassi() {
        try {
            ArrayList<String> classi = new ArrayList<>();
            String file = new String(Files.readAllBytes(Paths.get("studenti.json")));
            JSONArray jsonArray = new JSONArray(file);

            for (int i = 0; i < jsonArray.length(); i++) {
                String classe = jsonArray.getJSONObject(i).getString("classe");
                classi.add(classe);
            }

            return classi;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Ottenere un ArrayList con tutti gli studenti di una classe.
     *
     * @param classe da cui ottenere la lista degli studenti.
     * @return ArrayList contenente gli studenti della classe.
     */
    public ArrayList<Studente> getStudentiClasse(String classe) {
        try {
            ArrayList<Studente> studenti = new ArrayList<>();
            String file = new String(Files.readAllBytes(Paths.get("studenti.json")));
            JSONArray jsonArray = new JSONArray(file);

            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getString("classe").equalsIgnoreCase(classe)) {
                    JSONArray array_studenti = jsonArray.getJSONObject(i).getJSONArray("studenti");
                    for (int j = 0; j < array_studenti.length(); j++) {
                        String utente = array_studenti.getJSONObject(j).getString("utente");
                        String cognome = array_studenti.getJSONObject(j).getString("cognome");
                        String nome = array_studenti.getJSONObject(j).getString("nome");
                        String email = array_studenti.getJSONObject(j).getString("email");
                        String classe_chiave = array_studenti.getJSONObject(j).getString("classe");

                        studenti.add(new Studente(utente, cognome, nome, email, classe_chiave));
                    }
                }
            }
            return studenti;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Ottenere ArrayList con tutti i voti dello studente.
     *
     * @param emailStudente da cui ottenere i voti.
     * @return ArrayList contenente i voti.
     */
    public ArrayList<Integer> getVotiStudente(String emailStudente) {
        try {
            ArrayList<Integer> voti = new ArrayList<>();
            String file = new String(Files.readAllBytes(Paths.get("voti.json")));
            JSONObject jsonObject = new JSONObject(file);
            JSONArray jsonArray = jsonObject.getJSONArray("studenti");

            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getString("email").equalsIgnoreCase(emailStudente)) {
                    JSONArray voti_studente = jsonArray.getJSONObject(i).getJSONArray("voti");
                    for (int j = 0; j < voti_studente.length(); j++) {
                        voti.add(voti_studente.getInt(j));
                    }
                }
            }
            return voti;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Ottenere la media dello studente.
     *
     * @param emailStudente da cui ottenere la media dei voti.
     * @return la media dei voti dello studente (float).
     */
    public float mediaStudente(String emailStudente) {
        int somma = 0;
        int divisione = getVotiStudente(emailStudente).size();

        for (Integer voto : getVotiStudente(emailStudente)) {
            somma += voto;
        }

        return (float) somma / divisione;
    }


    /**
     * Ottenere ArrayList con tutti gli studenti che hanno la media migliore della classe (maggiore o uguale a 6).
     *
     * @param classe da cui ottenere gli studenti con la media migliore.
     * @return ArrayList con tutti gli studenti con la media migliore della classe.
     */
    public ArrayList<Studente> getStudentiConMediaMiglioreClasse(String classe) {
        try {
            ArrayList<Studente> studentiConMediaMiglioreClasse = new ArrayList<>();
            String fileVoti = new String(Files.readAllBytes(Paths.get("voti.json")));
            JSONObject jsonObject = new JSONObject(fileVoti);
            JSONArray jsonArray = jsonObject.getJSONArray("studenti");

            for (int i = 0; i < jsonArray.length(); i++) {
                String classeStudenteFile = jsonArray.getJSONObject(i).getString("classe");
                if (classeStudenteFile.equalsIgnoreCase(classe)) {
                    int somma = 0;
                    String email = jsonArray.getJSONObject(i).getString("email");
                    JSONArray voti = jsonArray.getJSONObject(i).getJSONArray("voti");
                    for (int j = 0; j < voti.length(); j++) {
                        somma += voti.getInt(j);
                    }
                    float mediaVoti = (float) somma / voti.length();

                    if (mediaVoti >= 6) {
                        studentiConMediaMiglioreClasse.add(cercaStudente(email));
                    }
                }

            }
            return studentiConMediaMiglioreClasse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Ottenere ArrayList con tutti gli studenti che hanno la media migliore della scuola (maggiore o uguale a 6).
     *
     * @return ArrayList con tutti gli studenti con la media migliore della scuola.
     */
    public ArrayList<Studente> getStudentiConMediaMiglioreScuola() {
        try {
            ArrayList<Studente> studenti = new ArrayList<>();
            String fileVoti = new String(Files.readAllBytes(Paths.get("voti.json")));
            JSONObject jsonObject = new JSONObject(fileVoti);
            JSONArray jsonArray = jsonObject.getJSONArray("studenti");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray voti = jsonArray.getJSONObject(i).getJSONArray("voti");
                String email = jsonArray.getJSONObject(i).getString("email");
                int somma = 0;

                for (int j = 0; j < voti.length(); j++) {
                    somma += voti.getInt(j);
                }

                float mediaVoti = (float) somma / voti.length();

                if (mediaVoti >= 6.0) {
                    studenti.add(cercaStudente(email));
                }
            }
            return studenti;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Studente cercaStudente(String emailStudente) {
        try {
            String file = new String(Files.readAllBytes(Paths.get("studenti.json")));
            JSONArray jsonArray = new JSONArray(file);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray studenti = jsonArray.getJSONObject(i).getJSONArray("studenti");

                for (int j = 0; j < studenti.length(); j++) {
                    String email = studenti.getJSONObject(j).getString("email");
                    if (email.equalsIgnoreCase(emailStudente)) {
                        String nome = studenti.getJSONObject(j).getString("nome");
                        String cognome = studenti.getJSONObject(j).getString("cognome");
                        String utente = studenti.getJSONObject(j).getString("utente");
                        String classe = studenti.getJSONObject(j).getString("classe");
                        return new Studente(utente, cognome, nome, email, classe);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
