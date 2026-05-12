package registrofatture;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Gestisce l'insieme delle fatture dell'applicazione.
 * Si occupa di leggere e scrivere il file CSV, e fornisce
 * i metodi per aggiungere, eliminare e cercare le fatture.
 * È l'unica classe che comunica con la MainGui.
 *
 * @author Davide
 */
public class Gestore {

    // =========================================================
    // VARIABILI
    // =========================================================

    /** Il cassetto che contiene tutte le fatture caricate dal CSV */
    private ArrayList<Fattura> lista;

    /** Percorso del file CSV — si trova nella cartella radice del progetto */
    private String percorsoCSV = "fatture.csv";


    // =========================================================
    // COSTRUTTORE
    // =========================================================

    /**
     * Costruttore. Crea il cassetto vuoto.
     * Le fatture vengono caricate chiamando leggiCSV().
     */
    public Gestore() {
        lista = new ArrayList<>();
    }


    // =========================================================
    // LETTURA E SCRITTURA CSV
    // =========================================================

    /**
     * Legge il file CSV e riempie il cassetto con le fatture.
     * Svuota il cassetto prima di ricaricare per evitare doppioni.
     * In caso di errore stampa un messaggio nella console.
     */
    public void leggiCSV() {

        // svuota il cassetto prima di ricaricare
        lista.clear();

        try {
            // apre il file
            BufferedReader br = new BufferedReader(new FileReader(percorsoCSV));

            // salta la prima riga (intestazione)
            br.readLine();

            // legge tutte le righe una per una
            String riga;
            while ((riga = br.readLine()) != null) {

                // spezza la riga in pezzi separati dalla virgola
                String[] pezzi = riga.split(",");

                // crea una Fattura con i pezzi della riga
                Fattura f = new Fattura(
                    pezzi[0],                        // idFattura
                    pezzi[1],                        // tipo
                    pezzi[2],                        // ragioneSociale
                    pezzi[3],                        // partitaIva
                    pezzi[4],                        // dataEmissione
                    pezzi[5],                        // dataScadenza
                    Double.parseDouble(pezzi[6]),    // importoImponibile
                    Double.parseDouble(pezzi[7]),    // importoIVA
                    Double.parseDouble(pezzi[8]),    // importoTotale
                    pezzi[9]                         // statoPagamento
                );

                // aggiunge la fattura al cassetto
                lista.add(f);
            }

            // chiude il file
            br.close();

        } catch (Exception e) {
            System.out.println("Errore lettura CSV: " + e.getMessage());
        }
    }

    /**
     * Salva tutto il cassetto sul file CSV sovrascrivendo il contenuto precedente.
     * In caso di errore stampa un messaggio nella console.
     */
    public void scriviCSV() {

        try {
            // apre il file in scrittura
            BufferedWriter bw = new BufferedWriter(new FileWriter(percorsoCSV));

            // scrive la prima riga di intestazione
            bw.write("idFattura,tipo,ragioneSociale,partitaIva,dataEmissione," +
                     "dataScadenza,importoImponibile,importoIVA,importoTotale,statoPagamento");
            bw.newLine();

            // per ogni fattura nel cassetto costruisce e scrive la riga
            for (Fattura f : lista) {
                String riga = f.getIdFattura()        + "," +
                              f.getTipo()             + "," +
                              f.getRagioneSociale()   + "," +
                              f.getPartitaIva()       + "," +
                              f.getDataEmissione()    + "," +
                              f.getDataScadenza()     + "," +
                              f.getImportoImponibile()+ "," +
                              f.getImportoIVA()       + "," +
                              f.getImportoTotale()    + "," +
                              f.getStatoPagamento();
                bw.write(riga);
                bw.newLine();
            }

            // chiude il file
            bw.close();

        } catch (Exception e) {
            System.out.println("Errore scrittura CSV: " + e.getMessage());
        }
    }


    // =========================================================
    // AGGIUNGI / ELIMINA
    // =========================================================

    /**
     * Aggiunge una nuova fattura al cassetto.
     * @param f la fattura da aggiungere
     */
    public void aggiungiFattura(Fattura f) {
        lista.add(f);
    }

    /**
     * Elimina una fattura dal cassetto tramite la sua posizione.
     * La posizione corrisponde alla riga selezionata nella tabella della GUI.
     * @param indice posizione della fattura nella lista (parte da 0)
     */
    public void eliminaFattura(int indice) {
        lista.remove(indice);
    }


    // =========================================================
    // GET
    // =========================================================

    /**
     * Restituisce tutto il cassetto.
     * Usato dalla GUI per popolare la tabella con tutte le fatture.
     * @return lista di tutte le fatture
     */
    public ArrayList<Fattura> getLista() {
        return lista;
    }

    /**
     * Restituisce una singola fattura tramite la sua posizione.
     * Usato dalla GUI quando l'utente clicca su una riga per vedere il dettaglio.
     * @param indice posizione della fattura nella lista (parte da 0)
     * @return la fattura in quella posizione
     */
    public Fattura getFattura(int indice) {
        return lista.get(indice);
    }


    // =========================================================
    // RICERCA
    // =========================================================

    /**
     * Cerca le fatture che contengono il testo nella ragione sociale.
     * La ricerca non è case-sensitive: "rossi" trova anche "Rossi" e "ROSSI".
     * @param testo testo da cercare nella ragione sociale
     * @return lista di fatture che corrispondono alla ricerca
     */
    public ArrayList<Fattura> cercaPerRagioneSociale(String testo) {
        ArrayList<Fattura> risultati = new ArrayList<>();
        for (Fattura f : lista) {
            boolean trovato = f.getRagioneSociale().toLowerCase()
                               .contains(testo.toLowerCase());
            if (trovato) {
                risultati.add(f);
            }
        }
        return risultati;
    }

    /**
     * Cerca le fatture con un determinato stato di pagamento.
     * La ricerca non è case-sensitive: "pagata" trova anche "Pagata".
     * @param stato stato da cercare: "pagata", "in attesa" o "scaduta"
     * @return lista di fatture con quello stato
     */
    public ArrayList<Fattura> cercaPerStato(String stato) {
        ArrayList<Fattura> risultati = new ArrayList<>();
        for (Fattura f : lista) {
            boolean trovato = f.getStatoPagamento().toLowerCase()
                               .equals(stato.toLowerCase());
            if (trovato) {
                risultati.add(f);
            }
        }
        return risultati;
    }
    /**
 * Cerca le fatture che contengono il testo nella partita IVA.
 * La ricerca è parziale: "123" trova anche "12345678901".
 * @param testo testo da cercare nella partita IVA
 * @return lista di fatture che corrispondono alla ricerca
 */
public ArrayList<Fattura> cercaPerPartitaIva(String testo) {
    ArrayList<Fattura> risultati = new ArrayList<>();
    for (Fattura f : lista) {
        if (f.getPartitaIva().contains(testo)) {
            risultati.add(f);
        }
    }
    return risultati;
}

    // =========================================================
    // ALERT SCADENZE
    // =========================================================

    /**
     * Restituisce le fatture con data di scadenza già passata
     * e stato diverso da "pagata".
     * Usato dalla GUI per mostrare gli alert rossi.
     * @return lista di fatture scadute
     */
    public ArrayList<Fattura> getFattureScadute() {
        ArrayList<Fattura> risultati = new ArrayList<>();
        String oggi = java.time.LocalDate.now().toString();
        for (Fattura f : lista) {
            if (f.getStatoPagamento().equals("pagata")) {
                continue;
            }
            boolean scaduta = f.getDataScadenza().compareTo(oggi) < 0;
            if (scaduta) {
                risultati.add(f);
            }
        }
        return risultati;
    }

    /**
     * Restituisce le fatture che scadono entro 7 giorni
     * e stato diverso da "pagata".
     * Usato dalla GUI per mostrare gli alert gialli.
     * @return lista di fatture in scadenza entro 7 giorni
     */
    public ArrayList<Fattura> getFattureInScadenza() {
        ArrayList<Fattura> risultati = new ArrayList<>();
        String oggi = java.time.LocalDate.now().toString();
        String fra7giorni = java.time.LocalDate.now().plusDays(7).toString();
        for (Fattura f : lista) {
            if (f.getStatoPagamento().equals("pagata")) {
                continue;
            }
            boolean dopoOggi       = f.getDataScadenza().compareTo(oggi) >= 0;
            boolean primaDi7giorni = f.getDataScadenza().compareTo(fra7giorni) <= 0;
            if (dopoOggi && primaDi7giorni) {
                risultati.add(f);
            }
        }
        return risultati;
    }
    
    /**
 * Legge un file CSV da un percorso esterno scelto dall'utente.
 * Usato quando l'utente clicca File → Apri e sceglie un file diverso.
 * @param percorso percorso completo del file CSV scelto dall'utente
 */
public void leggiCSV(String percorso) {
    
    // aggiorna il percorso
    this.percorsoCSV = percorso;
    
    // ricarica il file
    leggiCSV();
}
/**
 * Salva il CSV su un percorso esterno scelto dall'utente.
 * Usato quando l'utente clicca File → Salva con Nome.
 * @param percorso percorso completo del file CSV dove salvare
 */
public void scriviCSV(String percorso) {

    // aggiorna il percorso
    this.percorsoCSV = percorso;

    // salva
    scriviCSV();
}
}