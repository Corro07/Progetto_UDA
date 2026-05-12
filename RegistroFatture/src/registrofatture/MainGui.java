package registrofatture;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * Finestra principale dell'applicazione "Registro Fatture".
 * 
 * Questa classe gestisce l'interfaccia grafica principale. Mostra una tabella con tutte le fatture,
 * permette di cercare, filtrare, inserire, modificare ed eliminare fatture tramite i menu e i bottoni.
 * Inoltre, visualizza un alert (messaggio di avviso) per segnalare fatture scadute o in scadenza.
 * 
 *
 * @author Davide
 */

public class MainGui extends javax.swing.JFrame {
    
    // ------------------------------------------------------------------------
    // ATTRIBUTI (campi della classe)
    // ------------------------------------------------------------------------
    
    /**
     * Oggetto Gestore che si occupa di leggere, scrivere e gestire la lista delle fatture.
     * È il "ponte" tra l'interfaccia grafica e i dati (file CSV e logica di business).
     */
    
    private Gestore gestore = new Gestore();
    
    /**
     * Logger per registrare eventuali errori (usato principalmente per il look&feel).
     */
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainGui.class.getName());
    
    /**
     * Logger per registrare eventuali errori (usato principalmente per il look&feel).
     */

    /**
     * Crea e inizializza la finestra principale.
     * Durante la costruzione vengono:
     *   caricate le fatture dal file CSV tramite il Gestore
     *   visualizzate le fatture nella tabella
     *   controllati gli alert (fatture scadute o in scadenza)
     *   impostato un ascoltatore per il doppio click sulla tabella (modifica fattura)
     *   disabilitata la modifica diretta delle celle della tabella
     */
    public MainGui() {
        // Chiama il metodo che genera automaticamente l'interfaccia 
        initComponents();
    // carica le fatture dal CSV
    gestore.leggiCSV();
        
    // mostra fatture nella tabella
    aggiornaTabellaFatture(gestore.getLista());
    
    // Controlla le scadenze e aggiorna il messaggio di alert
    aggiornaAlert();
     // Imposta una dimensione preferita per l'etichetta degli alert (per evitare problemi di layout)
    lblAlert.setPreferredSize(new java.awt.Dimension(500, 22)); // ← aggiungi qui

     // Aggiunge un "ascoltatore di eventi del mouse" alla tabella per intercettare i doppi click
    tblFatture.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getClickCount() == 2) { // Se l'utente fa doppio click su una riga...
                int riga = tblFatture.getSelectedRow(); // ottiene l'indice della riga selezion
                if (riga >= 0) { // se c'è una riga selezionata
                        // Recupera la fattura corrispondente dalla lista del Gestore
                    Fattura f = gestore.getFattura(riga);
                    // Apre il dialog di modifica, passando la fattura da modificare
                    DialogFattura dialog = new DialogFattura(MainGui.this, true, gestore, f);
                    dialog.setVisible(true); // mostra la finestra modale
                    aggiornaTabellaFatture(gestore.getLista());
                    // Dopo la chiusura del dialog, aggiorna la tabella e gli alert
                    aggiornaAlert();
                }
            }
        }
    });
    // Impedisce all'utente di modificare direttamente il testo nelle celle della tabella
    // (la modifica avviene solo tramite il dialog)
    tblFatture.setDefaultEditor(Object.class, null);
    }
    
    // ------------------------------------------------------------------------
    // METODI PRIVATI PER L'AGGIORNAMENTO DELL'INTERFACCIA
    // ------------------------------------------------------------------------
    
    /**
 * Popola la tabella con la lista di fatture ricevuta.
 * Viene chiamata ogni volta che la lista cambia.
 * @param lista lista di fatture da mostrare
 */
private void aggiornaTabellaFatture(java.util.ArrayList<Fattura> lista) {

    // Definisce i nomi delle colonne della tabella
    String[] colonne = {
        "ID", "Tipo", "Ragione Sociale", "P.IVA",
        "Emissione", "Scadenza", "Imponibile", "IVA", "Totale", "Stato"
    };

    // Crea una matrice (array bidimensionale) per contenere i dati di tutte le fatture
    // Ogni riga della matrice corrisponde a una fattura, ogni colonna a un attributo
    Object[][] dati = new Object[lista.size()][10];

    // Cicla su tutte le fatture e copia i valori nella matrice
    for (int i = 0; i < lista.size(); i++) {
        Fattura f = lista.get(i);
        dati[i][0] = f.getIdFattura();
        dati[i][1] = f.getTipo();
        dati[i][2] = f.getRagioneSociale();
        dati[i][3] = f.getPartitaIva();
        dati[i][4] = f.getDataEmissione();
        dati[i][5] = f.getDataScadenza();
        dati[i][6] = f.getImportoImponibile();
        dati[i][7] = f.getImportoIVA();
        dati[i][8] = f.getImportoTotale();
        dati[i][9] = f.getStatoPagamento();
    }

    // Imposta il nuovo modello della tabella, che ridisegna automaticamente la tabella
    tblFatture.setModel(new javax.swing.table.DefaultTableModel(dati, colonne));
}


/**
     * Controlla le fatture scadute e in scadenza (entro 7 giorni) tramite il Gestore,
     * e aggiorna il testo e il colore dell'etichetta {@code lblAlert} in basso.
     * Il messaggio cambia a seconda della situazione:
      * Rosso: ci sono fatture scadute (e opzionalmente anche in scadenza)
     * Arancione: solo fatture in scadenza
     *   Verde: nessun alert
     
     */
private void aggiornaAlert() {
    // Chiede al Gestore quante fatture sono scadute e quante in scadenza
    int scadute    = gestore.getFattureScadute().size();
    int inScadenza = gestore.getFattureInScadenza().size();

    // Decide il messaggio e il colore in base ai conteggi
    if (scadute > 0 && inScadenza > 0) {
        lblAlert.setText("⚠ " + scadute + " fatture scadute   |   ⚠ " + inScadenza + " fatture in scadenza entro 7 giorni");
        lblAlert.setForeground(java.awt.Color.RED);
    } else if (scadute > 0) {
        lblAlert.setText("⚠ " + scadute + " fatture scadute");
        lblAlert.setForeground(java.awt.Color.RED);
    } else if (inScadenza > 0) {
        lblAlert.setText("⚠ " + inScadenza + " fatture in scadenza entro 7 giorni");
        lblAlert.setForeground(java.awt.Color.ORANGE);
    } else {
        lblAlert.setText("✅ Nessun alert");
        lblAlert.setForeground(java.awt.Color.GREEN);
    }
    // Forza il ricalcolo del layout e il ridisegno dell'etichetta (per sicurezza)
    lblAlert.revalidate();
    lblAlert.repaint();
}
    // ------------------------------------------------------------------------
    // METODI GENERATI AUTOMATICAMENTE DAL COSTRUTTORE DI INTERFACCE DI NetBeans
    // ------------------------------------------------------------------------

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRicerca = new javax.swing.JTextField();
        btnCerca = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbStato = new javax.swing.JComboBox<>();
        btnFiltra = new javax.swing.JButton();
        lblAlert = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFatture = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        f = new javax.swing.JMenu();
        menuApri = new javax.swing.JMenuItem();
        menuSalva = new javax.swing.JMenuItem();
        menuSalvaConNome = new javax.swing.JMenuItem();
        menuEsci = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuInserisci = new javax.swing.JMenuItem();
        menuVisualizzaLista = new javax.swing.JMenuItem();
        menuElimina = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuAbout = new javax.swing.JMenuItem();
        menuCredits = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cerca:");

        txtRicerca.addActionListener(this::txtRicercaActionPerformed);

        btnCerca.setText("Cerca");
        btnCerca.addActionListener(this::btnCercaActionPerformed);

        jLabel2.setText("Stato:");

        cmbStato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tutti", "pagata", "in attesa", "scaduta" }));
        cmbStato.addPropertyChangeListener(this::cmbStatoPropertyChange);

        btnFiltra.setText("Filtra");
        btnFiltra.addActionListener(this::btnFiltraActionPerformed);

        lblAlert.setText("Nessun alert");

        tblFatture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFatture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFattureMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFatture);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbStato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCerca)
                            .addComponent(btnFiltra)))
                    .addComponent(lblAlert)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerca))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbStato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAlert)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        f.setText("File");

        menuApri.setText("Apri");
        menuApri.addActionListener(this::menuApriActionPerformed);
        f.add(menuApri);

        menuSalva.setText("Salva");
        menuSalva.addActionListener(this::menuSalvaActionPerformed);
        f.add(menuSalva);

        menuSalvaConNome.setText("Salva con Nome");
        menuSalvaConNome.addActionListener(this::menuSalvaConNomeActionPerformed);
        f.add(menuSalvaConNome);

        menuEsci.setText("Esci");
        menuEsci.addActionListener(this::menuEsciActionPerformed);
        f.add(menuEsci);

        jMenuBar1.add(f);

        jMenu2.setText("Modifica");

        menuInserisci.setText("Inserisci");
        menuInserisci.addActionListener(this::menuInserisciActionPerformed);
        jMenu2.add(menuInserisci);

        menuVisualizzaLista.setText("Visualizza Lista");
        menuVisualizzaLista.addActionListener(this::menuVisualizzaListaActionPerformed);
        jMenu2.add(menuVisualizzaLista);

        menuElimina.setText("Elimina");
        menuElimina.addActionListener(this::menuEliminaActionPerformed);
        jMenu2.add(menuElimina);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Info");

        menuAbout.setText("About");
        menuAbout.addActionListener(this::menuAboutActionPerformed);
        jMenu3.add(menuAbout);

        menuCredits.setText("Credits");
        menuCredits.addActionListener(this::menuCreditsActionPerformed);
        jMenu3.add(menuCredits);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // ------------------------------------------------------------------------
    // GESTORI DEGLI EVENTI (ACTION LISTENER)
    // ------------------------------------------------------------------------

    /**
     * Apre un file CSV scelto dall'utente e carica le fatture da quel file.
     * Viene chiamato quando si clicca su "File → Apri".
     * @param evt evento dell'azione
     */
    
    private void menuApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuApriActionPerformed
        // TODO add your handling code here:
        // Crea un selettore di file (dialog per scegliere un file)
    javax.swing.JFileChooser fc = new javax.swing.JFileChooser();

    // Filtra in modo che vengano mostrati solo i file con estensione .csv
    fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("File CSV", "csv"));

    // Mostra la finestra di dialogo "Apri" e ottiene il risultato (APPROVE_OPTION = ha scelto un file)
    int risultato = fc.showOpenDialog(null);

    // se l'utente ha scelto un file
    if (risultato == javax.swing.JFileChooser.APPROVE_OPTION) {

        // Prende il percorso assoluto del file selezionato
        String percorso = fc.getSelectedFile().getAbsolutePath();

        // Chiede al Gestore di leggere le fatture da quel file (sostituisce la lista corrente)
        gestore.leggiCSV(percorso);

        // Aggiorna la tabella con le nuove fatture
        aggiornaTabellaFatture(gestore.getLista());

        // Ricalcola gli alert
        aggiornaAlert();
    }
    }//GEN-LAST:event_menuApriActionPerformed

    private void txtRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRicercaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtRicercaActionPerformed

    private void cmbStatoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbStatoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatoPropertyChange
    /**
     * Chiede conferma all'utente e chiude l'applicazione.
     * Viene chiamato quando si clicca su "File → Esci".
     *
     * @param evt evento dell'azione
     */
    private void menuEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEsciActionPerformed
        // TODO add your handling code here:
         // Mostra un messaggio di conferma (Sì/No)
        int scelta = javax.swing.JOptionPane.showConfirmDialog(
        null,
        "Sei sicuro di voler uscire?",
        "Esci",
        javax.swing.JOptionPane.YES_NO_OPTION
    );
    if (scelta == javax.swing.JOptionPane.YES_OPTION) {
        System.exit(0);
    }
    }//GEN-LAST:event_menuEsciActionPerformed

    /**
     * Salva la lista corrente delle fatture sul file CSV predefinito (quello da cui è stato caricato).
     * Viene chiamato quando si clicca su "File → Salva".
     *
     * @param evt evento dell'azione
     */
    private void menuSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvaActionPerformed
        // TODO add your handling code here:
         // salva direttamente sul file CSV corrente
    gestore.scriviCSV();

    // mostra messaggio di conferma
    javax.swing.JOptionPane.showMessageDialog(
        null,
        "File salvato con successo!",
        "Salva",
        javax.swing.JOptionPane.INFORMATION_MESSAGE
    );
    }//GEN-LAST:event_menuSalvaActionPerformed
/**
     * Salva le fatture su un nuovo file CSV scelto dall'utente.
     * Viene chiamato quando si clicca su "File → Salva con Nome".
     *
     * @param evt evento dell'azione
     */
    private void menuSalvaConNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvaConNomeActionPerformed
        // TODO add your handling code here:
        // apre la finestra di esplora file in modalità salvataggio
    javax.swing.JFileChooser fc = new javax.swing.JFileChooser();

    // mostra solo file CSV
    fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("File CSV", "csv"));

    int risultato = fc.showSaveDialog(null);

    // se l'utente ha scelto dove salvare
    if (risultato == javax.swing.JFileChooser.APPROVE_OPTION) {

        // prende il percorso scelto
        String percorso = fc.getSelectedFile().getAbsolutePath();

        // aggiunge .csv se l'utente non lo ha scritto
        if (!percorso.endsWith(".csv")) {
            percorso = percorso + ".csv";
        }

        // salva sul nuovo file
        gestore.scriviCSV(percorso);

        // mostra messaggio di conferma
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "File salvato in: " + percorso,
            "Salva con Nome",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    }//GEN-LAST:event_menuSalvaConNomeActionPerformed
    /**
     * Mostra una finestra con i crediti degli sviluppatori.
     * Viene chiamato quando si clicca su "Info → Credits".
     *
     * @param evt evento dell'azione
     */
    private void menuCreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCreditsActionPerformed
        // TODO add your handling code here:
        javax.swing.JOptionPane.showMessageDialog(
        null,
        "Sviluppato da:\n" +
        "Corrone, Ly, \n\n" +
        "Classe 4°E \n" +
        "I.I.S. Blaise Pascal - Reggio Emilia\n" +
        "Anno scolastico 2025/2026",
        "Credits",
        javax.swing.JOptionPane.INFORMATION_MESSAGE
    );
    }//GEN-LAST:event_menuCreditsActionPerformed
/**
     * Esegue la ricerca delle fatture in base al testo inserito nel campo {@code txtRicerca}.
     * <p>
     * Se il testo è vuoto mostra tutte le fatture.<br>
     * Se il testo è composto solo da numeri, cerca per partita IVA.<br>
     * Altrimenti cerca per ragione sociale (ricerca testuale parziale).
     * </p>
     * <p>
     * Viene chiamato quando si clicca sul bottone "Cerca".
     * </p>
     *
     * @param evt evento dell'azione
     */
    private void btnCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCercaActionPerformed
        // TODO add your handling code here:
        String testo = txtRicerca.getText().trim();
        if (testo.isEmpty()) {
            // Se non c'è testo, mostra tutte le fatture
            aggiornaTabellaFatture(gestore.getLista());
        } else if (testo.matches("\\d+")) {
            // se l'utente ha scritto solo numeri → cerca per partita IVA
            aggiornaTabellaFatture(gestore.cercaPerPartitaIva(testo));
        } else {
            // altrimenti → cerca per ragione sociale
            aggiornaTabellaFatture(gestore.cercaPerRagioneSociale(testo));
        }
    }//GEN-LAST:event_btnCercaActionPerformed
    /**
     * Filtra le fatture in base allo stato selezionato nella comboBox {@code cmbStato}.
     * <p>
     * Se si sceglie "Tutti", mostra tutte le fatture; altrimenti mostra solo quelle con lo stato selezionato.
     * </p>
     * <p>
     * Viene chiamato quando si clicca sul bottone "Filtra".
     * </p>
     *
     * @param evt evento dell'azione
     */
    private void btnFiltraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltraActionPerformed
        // TODO add your handling code here:
        String stato = (String) cmbStato.getSelectedItem();
    if (stato.equals("Tutti")) {
        aggiornaTabellaFatture(gestore.getLista());
    } else {
        aggiornaTabellaFatture(gestore.cercaPerStato(stato));
    }

    }//GEN-LAST:event_btnFiltraActionPerformed
     /**
     * Apre il dialog per inserire una nuova fattura (passando {@code null} come fattura).
     * <p>
     * Viene chiamato quando si clicca su "Modifica → Inserisci".
     * </p>
     *
     * @param evt evento dell'azione
     */
    private void menuInserisciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInserisciActionPerformed
        // TODO add your handling code here:
        // apre il dialog vuoto per inserire una nuova fattura
    DialogFattura dialog = new DialogFattura(this, true, gestore, null);
    dialog.setVisible(true);
    // Dopo la chiusura del dialog, aggiorna la tabella e gli alert
    aggiornaTabellaFatture(gestore.getLista());
    aggiornaAlert();
    }//GEN-LAST:event_menuInserisciActionPerformed
    /**
     * Mostra una finestra informativa sull'applicazione.
     * <p>
     * Viene chiamato quando si clicca su "Info → About".
     * </p>
     *
     * @param evt evento dell'azione
     */
    private void menuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAboutActionPerformed
        // TODO add your handling code here:
        javax.swing.JOptionPane.showMessageDialog(
    this,
    "Registro Fatture - progetto UDA \n" +
    "Applicazione per la gestione delle fatture aziendali.\n" +
    "Permette di inserire, modificare, eliminare e cercare fatture\n" +
    "con riconoscimento di scadenze imminenti.",
    "About",
    javax.swing.JOptionPane.INFORMATION_MESSAGE
);

    }//GEN-LAST:event_menuAboutActionPerformed
 /**
     * Ripristina la visualizzazione della lista completa: svuota il campo di ricerca,
     * imposta il filtro stato su "Tutti" e mostra tutte le fatture.
     * <p>
     * Viene chiamato quando si clicca su "Modifica → Visualizza Lista".
     * </p>
     *
     * @param evt evento dell'azione
     */
    private void menuVisualizzaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVisualizzaListaActionPerformed
        // TODO add your handling code here:
        // svuota il campo di ricerca
        txtRicerca.setText(""); 

        // reimposta il filtro stato su "Tutti"
        cmbStato.setSelectedIndex(0);

        // ricarica tutta la lista nella tabella
        aggiornaTabellaFatture(gestore.getLista());
    }//GEN-LAST:event_menuVisualizzaListaActionPerformed
/**
     * Elimina la fattura attualmente selezionata nella tabella, dopo aver chiesto conferma.
     * <p>
     * Viene chiamato quando si clicca su "Modifica → Elimina".
     * </p>
     *
     * @param evt evento dell'azione
     */
    private void menuEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEliminaActionPerformed
        // TODO add your handling code here:
        int riga = tblFatture.getSelectedRow();
    if (riga < 0) {
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Seleziona una fattura da eliminare.",
            "Nessuna selezione",
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
        return;
    }
    int scelta = javax.swing.JOptionPane.showConfirmDialog(
        null,
        "Sei sicuro di voler eliminare questa fattura?",
        "Elimina",
        javax.swing.JOptionPane.YES_NO_OPTION
    );
    if (scelta == javax.swing.JOptionPane.YES_OPTION) {
        gestore.eliminaFattura(riga);
        aggiornaTabellaFatture(gestore.getLista());
        aggiornaAlert();
        pack(); // ridimensiona la finestra per adattare il contenuto 
    }
    }//GEN-LAST:event_menuEliminaActionPerformed
    /**
     * Gestisce il doppio click sulla tabella per modificare una fattura.
     * <p>
     * Nota: il doppio click è già gestito anche nell'ascoltatore aggiunto nel costruttore.
     * Questo metodo è stato generato automaticamente e richiama la stessa logica.
     * </p>
     *
     * @param evt evento del mouse
     */
    private void tblFattureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFattureMouseClicked
        // TODO add your handling code here:
        // apre il dialog solo se l'utente fa doppio clic
    if (evt.getClickCount() == 2) {
        int riga = tblFatture.getSelectedRow();
        if (riga >= 0) {
            // passa la fattura selezionata al dialog
            DialogFattura dialog = new DialogFattura(this, true, gestore, gestore.getFattura(riga));
            dialog.setVisible(true);
            // aggiorna la tabella dopo la modifica
            aggiornaTabellaFatture(gestore.getLista());
            aggiornaAlert();
        }
    }
    }//GEN-LAST:event_tblFattureMouseClicked
    // ------------------------------------------------------------------------
    // MAIN (PUNTO DI INGRESSO DELL'APPLICAZIONE)
    // ------------------------------------------------------------------------
    
    /**
     * Punto di ingresso dell'applicazione.
     * <p>
     * Imposta il look&feel "Nimbus" (se disponibile) e avvia la finestra principale
     * nell'event dispatch thread di Swing.
     * </p>
     *
     * @param args argomenti della riga di comando (non utilizzati)
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainGui().setVisible(true));
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerca;
    private javax.swing.JButton btnFiltra;
    private javax.swing.JComboBox<String> cmbStato;
    private javax.swing.JMenu f;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAlert;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuApri;
    private javax.swing.JMenuItem menuCredits;
    private javax.swing.JMenuItem menuElimina;
    private javax.swing.JMenuItem menuEsci;
    private javax.swing.JMenuItem menuInserisci;
    private javax.swing.JMenuItem menuSalva;
    private javax.swing.JMenuItem menuSalvaConNome;
    private javax.swing.JMenuItem menuVisualizzaLista;
    private javax.swing.JTable tblFatture;
    private javax.swing.JTextField txtRicerca;
    // End of variables declaration//GEN-END:variables
}
