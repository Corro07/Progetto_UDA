package registrofatture;

/**
 * Classe principale dell'applicazione Registro Fatture.
 * Avvia la finestra grafica MainGui.
 *
 * @author Davide
 */
public class RegistroFatture {

    /**
     * Punto di avvio del programma.
     * Apre la finestra grafica MainGui.
     * @param args argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainGui().setVisible(true);
        });
    }
}