package registrofatture;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

/**
 * Finestra di dialogo per l'inserimento o la modifica di una fattura.
 * <p>
 * Questa classe permette all'utente di inserire una nuova fattura o modificare
 * una fattura esistente. Contiene campi di testo, combo box e bottoni per
 * salvare o annullare l'operazione. La validazione dei dati viene effettuata
 * prima del salvataggio.
 * </p>
 *
 * @author Davide
 */

public class DialogFattura extends javax.swing.JDialog {
    
    // Logger per registrare errori (utile per il look&feel e debugging)
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DialogFattura.class.getName());
    
// Riferimento al gestore dei dati (per aggiungere o modificare fatture)
    private Gestore gestore;
 // Fattura originale che si sta modificando (null se è un inserimento nuovo)   
    private Fattura fatturaOriginale;
    
    /**
     * Costruttore del dialogo. Crea la finestra modale, inizializza i componenti
     * grafici e, se si sta modificando una fattura esistente, precompila i campi.
     *
     * @param parent           finestra padre (solitamente la MainGui)
     * @param modal            true se il dialogo deve essere modale (blocca la finestra padre)
     * @param gestore          il gestore condiviso con la finestra principale
     * @param fatturaOriginale la fattura da modificare, oppure null per un nuovo inserimento
     */
    
    public DialogFattura(java.awt.Frame parent, boolean modal,
                        Gestore gestore, Fattura fatturaOriginale) {
       super(parent, modal);
       this.gestore = gestore;
       this.fatturaOriginale = fatturaOriginale;
       initComponents();
       setLocationRelativeTo(parent);

       // Se stiamo modificando una fattura esistente, carico i suoi dati nei campi
       if (fatturaOriginale != null) {
           popolaCampi(fatturaOriginale);
       }
   }
    /**
     * Metodo generato automaticamente dal costruttore di interfacce di NetBeans.
     * <p>
     * <strong>ATTENZIONE:</strong> Non modificare questo codice. Il contenuto di questo metodo
     * viene rigenerato ogni volta dall'editor di form.
     * </p>
     */
    
    // </editor-fold>
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRagioneSociale = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPartitaIva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDataEmissione = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDataScadenza = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotale = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cmbStato = new javax.swing.JComboBox<>();
        txtImponibile = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");

        txtId.setPreferredSize(new java.awt.Dimension(200, 22));
        txtId.addActionListener(this::txtIdActionPerformed);

        jLabel2.setText("Tipo:");

        jLabel3.setText("Ragione Sociale:");

        txtRagioneSociale.setPreferredSize(new java.awt.Dimension(200, 22));
        txtRagioneSociale.addActionListener(this::txtRagioneSocialeActionPerformed);

        jLabel4.setText("Partita IVA:");

        txtPartitaIva.setPreferredSize(new java.awt.Dimension(200, 22));
        txtPartitaIva.addActionListener(this::txtPartitaIvaActionPerformed);

        jLabel5.setText("Data Emissione:");

        txtDataEmissione.setPreferredSize(new java.awt.Dimension(200, 22));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "emessa", "ricevuta" }));
        cmbTipo.addPropertyChangeListener(this::cmbTipoPropertyChange);

        jLabel6.setText("Data Scadenza:");

        txtDataScadenza.setMinimumSize(new java.awt.Dimension(200, 22));
        txtDataScadenza.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel7.setText("IVA:");

        txtIVA.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel8.setText("Totale:");

        txtTotale.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel9.setText("Stato:");

        jButton1.setText("Salva");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Annulla");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        cmbStato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pagata", "in attesa", "scaduta" }));

        txtImponibile.setText("Imponibile:");

        jTextField1.setMinimumSize(new java.awt.Dimension(200, 22));
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPartitaIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRagioneSociale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDataEmissione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtDataScadenza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(62, 62, 62)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtImponibile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRagioneSociale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPartitaIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDataEmissione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtDataScadenza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImponibile)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbStato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ------------------------------------------------------------------------
    // GESTORI DEGLI EVENTI (azioni sui componenti)
    // ------------------------------------------------------------------------

    
    /**
     * Azione eseguita quando si preme "Invio" nel campo "Ragione Sociale".
     * Attualmente non utilizzata.
     *
     * @param evt evento dell'azione
     */
    private void txtRagioneSocialeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRagioneSocialeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRagioneSocialeActionPerformed
    /**
     * Azione eseguita quando si preme "Invio" nel campo "Partita IVA".
     * Attualmente non utilizzata.
     *
     * @param evt evento dell'azione
     */
    private void txtPartitaIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartitaIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartitaIvaActionPerformed
    /**
     * Azione eseguita quando si clicca sul bottone "Salva".
     * Richiama il metodo {@code salva()} che valida e salva la fattura.
     *
     * @param evt evento dell'azione
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salva();
    }//GEN-LAST:event_jButton1ActionPerformed
/**
     * Azione eseguita quando si clicca sul bottone "Annulla".
     * Chiude il dialogo senza salvare modifiche.
     *
     * @param evt evento dell'azione
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Evento generato quando cambia una proprietà della comboBox "Tipo".
     * Attualmente non utilizzata.
     *
     * @param evt evento di proprietà
     */
    private void cmbTipoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbTipoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoPropertyChange
/**
     * Azione eseguita quando si preme "Invio" nel campo "ID".
     * Attualmente non utilizzata.
     *
     * @param evt evento dell'azione
     */
    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    /**
     * Punto di ingresso per testare il dialogo in modo indipendente.
     * Non viene usato nell'applicazione reale, ma può essere utile per fare prove.
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DialogFattura dialog = new DialogFattura(new javax.swing.JFrame(), true, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
        /**
     * Precompila i campi del form con i dati della fattura da modificare.
     * L'ID viene reso non modificabile perché è la chiave della fattura.
     *
     * @param f la fattura da mostrare nel form
     */
private void popolaCampi(Fattura f) {
    txtId.setText(f.getIdFattura());
    txtId.setEditable(false); // in modifica l'ID non si cambia
    cmbTipo.setSelectedItem(f.getTipo());
    txtRagioneSociale.setText(f.getRagioneSociale());
    txtPartitaIva.setText(f.getPartitaIva());
    txtDataEmissione.setText(f.getDataEmissione());
    txtDataScadenza.setText(f.getDataScadenza());
    jTextField1.setText(String.valueOf(f.getImportoImponibile())); // jTextField1 = imponibile
    txtIVA.setText(String.valueOf(f.getImportoIVA()));
    txtTotale.setText(String.valueOf(f.getImportoTotale()));
    cmbStato.setSelectedItem(f.getStatoPagamento());
}

/**
     * Legge i campi, valida i dati e salva la fattura nel gestore.
     * <p>
     * Se {@code fatturaOriginale} è {@code null} → si tratta di un nuovo inserimento.
     * Se {@code fatturaOriginale} non è {@code null} → modifica della fattura esistente.
     * In caso di errori (formato numerico non valido o dati errati) mostra un messaggio di errore.
     * </p>
     */
private void salva() {
    try {
        // Legge tutti i campi dalla grafica
        String id        = txtId.getText().trim();
        String tipo      = (String) cmbTipo.getSelectedItem();
        String ragSoc    = txtRagioneSociale.getText().trim();
        String piva      = txtPartitaIva.getText().trim();
        String dataEmiss = txtDataEmissione.getText().trim();
        String dataScad  = txtDataScadenza.getText().trim();
        double imponibile = Double.parseDouble(jTextField1.getText().trim()); // jTextField1 = imponibile
        double iva        = Double.parseDouble(txtIVA.getText().trim());
        double totale     = Double.parseDouble(txtTotale.getText().trim());
        String stato     = (String) cmbStato.getSelectedItem();

        // Crea l'oggetto Fattura (il costruttore e i setter lanciano IllegalArgumentException se i dati non sono validi)
        Fattura nuova = new Fattura(id, tipo, ragSoc, piva,
                                    dataEmiss, dataScad,
                                    imponibile, iva, totale, stato);

        if (fatturaOriginale == null) {
            // INSERIMENTO: aggiunge la nuova fattura alla lista del gestore
            gestore.aggiungiFattura(nuova);
        } else {
            // MODIFICA: trova e sostituisce
            java.util.ArrayList<Fattura> lista = gestore.getLista();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getIdFattura().equals(fatturaOriginale.getIdFattura())) {
                    lista.set(i, nuova);
                    break;
                }
            }
        }

        dispose(); // chiude il dialog

    } catch (NumberFormatException e) {
        // Errore se i campi numerici non sono numeri validi (es. virgola invece di punto)
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Importi non validi.\nUsa il punto come separatore decimale (es: 100.00)",
            "Errore nei dati",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    } catch (IllegalArgumentException e) {
        // Errore lanciato dal costruttore di Fattura o dai setter (es. partita IVA errata, date sbagliate)
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Dati non validi:\n" + e.getMessage(),
            "Errore nei dati",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbStato;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtDataEmissione;
    private javax.swing.JTextField txtDataScadenza;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel txtImponibile;
    private javax.swing.JTextField txtPartitaIva;
    private javax.swing.JTextField txtRagioneSociale;
    private javax.swing.JTextField txtTotale;
    // End of variables declaration//GEN-END:variables
}
