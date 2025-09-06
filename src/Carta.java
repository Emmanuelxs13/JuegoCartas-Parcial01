import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Clase que representa una carta en el juego de cartas.
// Cada carta tiene un nombre (valor), una pinta (figura) y una imagen asociada.
// Permite mostrar la carta en la interfaz gráfica y consultar sus propiedades.
public class Carta {

    // Índice de la carta (valor entre 1 y 52, representa la posición en la baraja)
    private int indice;

    /**
     * Constructor de la carta.
     * Recibe un objeto Random para generar el índice aleatorio de la carta.
     * @param r Generador de números aleatorios
     */
    public Carta(Random r) {
        indice = r.nextInt(52) + 1; // Asigna un valor aleatorio entre 1 y 52
    }

    /**
     * Muestra la carta en el panel indicado, en la posición (x, y).
     * Además, agrega un evento para mostrar el nombre y la pinta al hacer clic sobre la carta.
     * @param pnl Panel donde se mostrará la carta
     * @param x Posición horizontal
     * @param y Posición vertical
     */
    public void mostrar(JPanel pnl, int x, int y) {
        JLabel lblCarta = new JLabel(); // Componente visual para la carta
        String archivoCarta = "imagenes/CARTA" + indice + ".jpg"; // Ruta de la imagen
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(archivoCarta)); // Carga la imagen
        lblCarta.setIcon(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight()); // Posiciona la carta
        pnl.add(lblCarta);

        // Evento: al hacer clic en la carta, muestra un mensaje con el nombre y la pinta
        lblCarta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        getNombre() + " " + getPinta());
            }
        });
    }

    /**
     * Devuelve la pinta (figura) de la carta según su índice.
     * @return Pinta de la carta (TREBOL, PICA, CORAZON, DIAMANTE)
     */
    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    /**
     * Devuelve el nombre de la carta (AS, DOS, ..., KING) según su índice.
     * @return NombreCarta correspondiente
     */
    public NombreCarta getNombre() {
        int residuo = indice % 13;
        int posicion = residuo == 0 ? 12 : residuo - 1;
        return NombreCarta.values()[posicion];
    }
}
