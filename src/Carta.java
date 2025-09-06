import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Clase que representa una carta de la baraja española utilizada en el juego.
// Cada carta tiene un índice que determina su imagen, nombre y pinta.
public class Carta {

    // Índice de la carta (valor entre 1 y 52, representa la posición en la baraja)
    private int indice;

    // Constructor: genera una carta aleatoria usando un objeto Random
    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }

    // Muestra la carta en el panel indicado, en la posición (x, y)
    // También agrega un evento para mostrar el nombre y la pinta al hacer clic sobre la carta
    public void mostrar(JPanel pnl, int x, int y) {
        JLabel lblCarta = new JLabel();
        String archivoCarta = "imagenes/CARTA" + indice + ".jpg"; // Ruta de la imagen de la carta
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

    // Devuelve la pinta (figura) de la carta según su índice
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

    // Devuelve el nombre de la carta (AS, DOS, TRES, ..., KING) según su índice
    public NombreCarta getNombre() {
        int residuo = indice % 13;
        int posicion = residuo == 0 ? 12 : residuo - 1;
        return NombreCarta.values()[posicion];
    }

}
