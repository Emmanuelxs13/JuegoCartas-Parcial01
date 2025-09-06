import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

// Clase que representa la ventana principal del juego de cartas.
// Permite repartir cartas y verificar los grupos formados por cada jugador.
public class FrmJuego extends JFrame {

    // Paneles para mostrar las cartas de cada jugador
    JPanel pnlJugador1, pnlJugador2;
    // Instancias de los jugadores
    Jugador jugador1, jugador2;
    // Componente para alternar entre los jugadores
    JTabbedPane tpJugadores;

    // Constructor: configura la interfaz gráfica y los componentes
    public FrmJuego() {
        setTitle("Juguemos al apuntado"); // Título de la ventana
        setSize(600, 300);                // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al salir
        setLayout(null);                  // Layout absoluto

        // Botón para repartir cartas
        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        // Botón para verificar los grupos de cartas
        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        // Panel para el jugador 1
        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(150, 255, 50));
        pnlJugador1.setLayout(null);

        // Panel para el jugador 2
        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        // Pestañas para alternar entre los jugadores
        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);

        tpJugadores.setBounds(10, 40, 550, 200);
        getContentPane().add(tpJugadores);

        // Acción al presionar el botón "Repartir": reparte cartas a ambos jugadores
        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repartir();
            }
        });

        // Acción al presionar el botón "Verificar": muestra los grupos encontrados
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }
        });

        // Instancia los jugadores
        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    // Reparte cartas a ambos jugadores y las muestra en pantalla
    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    // Verifica los grupos formados por el jugador seleccionado y muestra el resultado
    private void verificar() {
        int pestaña = tpJugadores.getSelectedIndex();
        switch (pestaña) {
            case 0:
                JOptionPane.showMessageDialog(null,
                        jugador1.getGrupos());
                break;
            case 1:
                JOptionPane.showMessageDialog(null,
                        jugador2.getGrupos());
                break;
        }
    }

}
