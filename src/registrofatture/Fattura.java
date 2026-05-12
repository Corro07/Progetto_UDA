package registrofatture;

/**
 * Rappresenta una singola fattura aziendale.
 * Contiene tutti i dati di una fattura (emessa o ricevuta)
 * e i controlli di validità sui campi tramite i setter.
 *
 * @author Davide
 */
public class Fattura {

    // =========================================================
    // VARIABILI - i campi della scheda fattura
    // =========================================================

    /** Identificativo univoco della fattura (es: "F001") */
    private String idFattura;

    /** Tipo di fattura: "emessa" o "ricevuta" */
    private String tipo;

    /** Nome o ragione sociale del cliente o fornitore */
    private String ragioneSociale;

    /** Partita IVA del cliente o fornitore (11 cifre) */
    private String partitaIva;

    /** Data di emissione nel formato AAAA-MM-GG (es: "2026-01-10") */
    private String dataEmissione;

    /** Data di scadenza nel formato AAAA-MM-GG (es: "2026-02-10") */
    private String dataScadenza;

    /** Importo imponibile senza IVA */
    private double importoImponibile;

    /** Importo dell'IVA (22%) */
    private double importoIVA;

    /** Importo totale comprensivo di IVA */
    private double importoTotale;

    /** Stato del pagamento: "pagata", "in attesa" o "scaduta" */
    private String statoPagamento;


    // =========================================================
    // COSTRUTTORI
    // =========================================================

    /**
     * Costruttore completo. Crea una fattura con tutti i campi valorizzati.
     * Usa i setter internamente per applicare i controlli di validità.
     *
     * @param idFattura         identificativo univoco della fattura
     * @param tipo              tipo fattura: "emessa" o "ricevuta"
     * @param ragioneSociale    nome o ragione sociale
     * @param partitaIva        partita IVA (11 cifre)
     * @param dataEmissione     data emissione nel formato AAAA-MM-GG
     * @param dataScadenza      data scadenza nel formato AAAA-MM-GG
     * @param importoImponibile importo senza IVA
     * @param importoIVA        importo dell'IVA
     * @param importoTotale     importo totale con IVA
     * @param statoPagamento    stato: "pagata", "in attesa" o "scaduta"
     */
    public Fattura(String idFattura, String tipo, String ragioneSociale,
                   String partitaIva, String dataEmissione, String dataScadenza,
                   double importoImponibile, double importoIVA,
                   double importoTotale, String statoPagamento) {
        setIdFattura(idFattura);
        setTipo(tipo);
        setRagioneSociale(ragioneSociale);
        setPartitaIva(partitaIva);
        setDataEmissione(dataEmissione);
        setDataScadenza(dataScadenza);
        setImportoImponibile(importoImponibile);
        setImportoIVA(importoIVA);
        setImportoTotale(importoTotale);
        setStatoPagamento(statoPagamento);
    }

    /**
     * Costruttore vuoto. Crea una fattura con tutti i campi vuoti o a zero.
     * Usato dalla GUI per creare una fattura vuota prima di riempirla
     * con i dati inseriti dall'utente nel modulo.
     */
    public Fattura() {
        this.tipo = "";
        this.ragioneSociale = "";
        this.partitaIva = "";
        this.dataEmissione = "";
        this.dataScadenza = "";
        this.importoImponibile = 0;
        this.importoIVA = 0;
        this.importoTotale = 0;
        this.statoPagamento = "";
    }

    /**
     * Costruttore di copia. Crea una nuova fattura copiando tutti i campi
     * da una fattura esistente. Usato dalla GUI quando l'utente vuole
     * modificare una fattura — si lavora sulla copia, non sull'originale.
     *
     * @param f la fattura da copiare
     */
    public Fattura(Fattura f) {
        this.idFattura = f.idFattura;
        this.tipo = f.tipo;
        this.ragioneSociale = f.ragioneSociale;
        this.partitaIva = f.partitaIva;
        this.dataEmissione = f.dataEmissione;
        this.dataScadenza = f.dataScadenza;
        this.importoImponibile = f.importoImponibile;
        this.importoIVA = f.importoIVA;
        this.importoTotale = f.importoTotale;
        this.statoPagamento = f.statoPagamento;
    }


    // =========================================================
    // GETTER e SETTER con controlli
    // =========================================================

    /**
     * Restituisce l'ID della fattura.
     * @return idFattura
     */
    public String getIdFattura() {
        return idFattura;
    }

    /**
     * Imposta l'ID della fattura.
     * @param idFattura identificativo univoco, non può essere vuoto
     * @throws IllegalArgumentException se il valore è null o vuoto
     */
    public void setIdFattura(String idFattura) {
        if (idFattura == null || idFattura.isEmpty())
            throw new IllegalArgumentException("ID fattura non può essere vuoto");
        this.idFattura = idFattura;
    }

    /**
     * Restituisce il tipo della fattura.
     * @return "emessa" o "ricevuta"
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Imposta il tipo della fattura.
     * @param tipo deve essere "emessa" o "ricevuta"
     * @throws IllegalArgumentException se il valore non è valido
     */
    public void setTipo(String tipo) {
        if (!tipo.equals("emessa") && !tipo.equals("ricevuta"))
            throw new IllegalArgumentException("Tipo deve essere 'emessa' o 'ricevuta'");
        this.tipo = tipo;
    }

    /**
     * Restituisce la ragione sociale.
     * @return ragioneSociale
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Imposta la ragione sociale.
     * @param ragioneSociale non può essere vuota o null
     * @throws IllegalArgumentException se il valore è null o vuoto
     */
    public void setRagioneSociale(String ragioneSociale) {
        if (ragioneSociale == null || ragioneSociale.isEmpty())
            throw new IllegalArgumentException("Ragione sociale non può essere vuota");
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Restituisce la partita IVA.
     * @return partitaIva
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Imposta la partita IVA.
     * @param partitaIva deve avere esattamente 11 caratteri
     * @throws IllegalArgumentException se la lunghezza non è 11
     */
    public void setPartitaIva(String partitaIva) {
        if (partitaIva == null || partitaIva.length() != 11)
            throw new IllegalArgumentException("Partita IVA deve avere 11 cifre");
        this.partitaIva = partitaIva;
    }

    /**
     * Restituisce la data di emissione.
     * @return dataEmissione nel formato AAAA-MM-GG
     */
    public String getDataEmissione() {
        return dataEmissione;
    }

    /**
     * Imposta la data di emissione.
     * @param dataEmissione nel formato AAAA-MM-GG, non può essere vuota
     * @throws IllegalArgumentException se il valore è null o non nel formato corretto
     */
    public void setDataEmissione(String dataEmissione) {
        if (dataEmissione == null || !dataEmissione.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new IllegalArgumentException("Data emissione deve essere nel formato AAAA-MM-GG");
        this.dataEmissione = dataEmissione;
    }

    /**
     * Restituisce la data di scadenza.
     * @return dataScadenza nel formato AAAA-MM-GG
     */
    public String getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta la data di scadenza.
     * @param dataScadenza nel formato AAAA-MM-GG, non può essere vuota
     * @throws IllegalArgumentException se il valore è null o non nel formato corretto
     */
    public void setDataScadenza(String dataScadenza) {
        if (dataScadenza == null || !dataScadenza.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new IllegalArgumentException("Data scadenza deve essere nel formato AAAA-MM-GG");
        this.dataScadenza = dataScadenza;
    }

    /**
     * Restituisce l'importo imponibile.
     * @return importoImponibile
     */
    public double getImportoImponibile() {
        return importoImponibile;
    }

    /**
     * Imposta l'importo imponibile.
     * @param importoImponibile non può essere negativo
     * @throws IllegalArgumentException se il valore è negativo
     */
    public void setImportoImponibile(double importoImponibile) {
        if (importoImponibile < 0)
            throw new IllegalArgumentException("Importo imponibile non può essere negativo");
        this.importoImponibile = importoImponibile;
    }

    /**
     * Restituisce l'importo IVA.
     * @return importoIVA
     */
    public double getImportoIVA() {
        return importoIVA;
    }

    /**
     * Imposta l'importo IVA.
     * @param importoIVA non può essere negativo
     * @throws IllegalArgumentException se il valore è negativo
     */
    public void setImportoIVA(double importoIVA) {
        if (importoIVA < 0)
            throw new IllegalArgumentException("Importo IVA non può essere negativo");
        this.importoIVA = importoIVA;
    }

    /**
     * Restituisce l'importo totale.
     * @return importoTotale
     */
    public double getImportoTotale() {
        return importoTotale;
    }

    /**
     * Imposta l'importo totale.
     * @param importoTotale non può essere negativo
     * @throws IllegalArgumentException se il valore è negativo
     */
    public void setImportoTotale(double importoTotale) {
        if (importoTotale < 0)
            throw new IllegalArgumentException("Importo totale non può essere negativo");
        this.importoTotale = importoTotale;
    }

    /**
     * Restituisce lo stato del pagamento.
     * @return "pagata", "in attesa" o "scaduta"
     */
    public String getStatoPagamento() {
        return statoPagamento;
    }

    /**
     * Imposta lo stato del pagamento.
     * @param statoPagamento deve essere "pagata", "in attesa" o "scaduta"
     * @throws IllegalArgumentException se il valore non è valido
     */
    public void setStatoPagamento(String statoPagamento) {
        if (!statoPagamento.equals("pagata") &&
            !statoPagamento.equals("in attesa") &&
            !statoPagamento.equals("scaduta"))
            throw new IllegalArgumentException("Stato deve essere 'pagata', 'in attesa' o 'scaduta'");
        this.statoPagamento = statoPagamento;
    }

    /**
     * Restituisce una rappresentazione testuale della fattura.
     * Utile per il debug e per stampare la fattura nella console.
     *
     * @return stringa con tutti i campi della fattura
     */
    @Override
    public String toString() {
        return "Fattura{" + "idFattura=" + idFattura + ", tipo=" + tipo +
               ", ragioneSociale=" + ragioneSociale + ", partitaIva=" + partitaIva +
               ", dataEmissione=" + dataEmissione + ", dataScadenza=" + dataScadenza +
               ", importoImponibile=" + importoImponibile + ", importoIVA=" + importoIVA +
               ", importoTotale=" + importoTotale + ", statoPagamento=" + statoPagamento + '}';
    }
}