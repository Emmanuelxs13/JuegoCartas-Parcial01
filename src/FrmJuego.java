import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

// Clase principal de la interfaz gráfica del juego de cartas.
// Permite repartir cartas, verificar grupos y escaleras, y visualizar puntajes.
// Incluye mejoras visuales y documentación clara para facilitar el aprendizaje.
public class FrmJuego extends JFrame {

    // Paneles para mostrar las cartas de cada jugador
    JPanel pnlJugador1, pnlJugador2;
    // Instancias de los jugadores
    Jugador jugador1, jugador2;
    // Componente para alternar entre los jugadores
    JTabbedPane tpJugadores;
    // Etiqueta de créditos
    JLabel lblCreditos;

    /**
     * Constructor: configura la interfaz gráfica y los componentes del juego.
     * Se definen colores suaves y contrastantes, fuentes legibles y bordes modernos.
     */
    public FrmJuego() {
        setTitle("Juguemos al apuntado"); // Título de la ventana
        setSize(700, 400);                // Tamaño de la ventana
        setLocationRelativeTo(null);      // Centra la ventana en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al salir
        setLayout(null);                  // Layout absoluto
        getContentPane().setBackground(new Color(245, 245, 250)); // Fondo general suave

        // Botón para repartir cartas a los jugadores
        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(20, 20, 120, 35);
        btnRepartir.setBackground(new Color(120, 180, 220)); // Azul claro
        btnRepartir.setForeground(Color.BLACK);
        btnRepartir.setFont(new Font("Arial", Font.BOLD, 16));
        btnRepartir.setFocusPainted(false);
        btnRepartir.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 160), 2));
        getContentPane().add(btnRepartir);

        // Botón para verificar los grupos y escaleras de cartas
        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(160, 20, 120, 35);
        btnVerificar.setBackground(new Color(180, 220, 120)); // Verde claro
        btnVerificar.setForeground(Color.BLACK);
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerificar.setFocusPainted(false);
        btnVerificar.setBorder(BorderFactory.createLineBorder(new Color(120, 160, 80), 2));
        getContentPane().add(btnVerificar);

        // Etiqueta de créditos al lado del botón de verificar
        lblCreditos = new JLabel("Creado por Juan Esteban Correa y Emmanuel Berrio");
        lblCreditos.setFont(new Font("Arial", Font.ITALIC, 14));
        lblCreditos.setForeground(new Color(60, 60, 60));
        lblCreditos.setBounds(300, 20, 350, 35);
        getContentPane().add(lblCreditos);

        // Panel para el jugador 1
        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(255, 255, 230)); // Amarillo muy suave
        pnlJugador1.setLayout(null);
        pnlJugador1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(120, 180, 220), 2), "Jugador 1", 0, 0, new Font("Arial", Font.BOLD, 14), new Color(120, 180, 220)));

        // Panel para el jugador 2
        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(230, 240, 255)); // Azul muy suave
        pnlJugador2.setLayout(null);
        pnlJugador2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 120), 2), "Jugador 2", 0, 0, new Font("Arial", Font.BOLD, 14), new Color(180, 220, 120)));

        // Pestañas para alternar entre los jugadores
        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);
        tpJugadores.setBounds(20, 70, 640, 260);
        tpJugadores.setFont(new Font("Arial", Font.BOLD, 15));
        tpJugadores.setBackground(new Color(220, 220, 230));
        tpJugadores.setForeground(new Color(60, 60, 60));
        getContentPane().add(tpJugadores);

        // Acción al presionar el botón "Repartir": reparte cartas a ambos jugadores
        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repartir(); // Llama al método para repartir cartas
            }
        });

        // Acción al presionar el botón "Verificar": muestra los grupos, escaleras y puntaje
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificar(); // Llama al método para verificar resultados
            }
        });

        // Instancia los jugadores
        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    /**
     * Reparte cartas a ambos jugadores y las muestra en pantalla.
     * Llama a los métodos de cada jugador para repartir y mostrar sus cartas.
     */
    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    /**
     * Verifica los grupos, escaleras y puntaje del jugador seleccionado.
     * Muestra los resultados en un cuadro de diálogo, incluyendo imágenes de las escaleras.
     * La documentación explica cada paso para facilitar el aprendizaje.
     */
    private void verificar() {
        int pestaña = tpJugadores.getSelectedIndex(); // Determina el jugador activo
        Jugador jugadorActual = pestaña == 0 ? jugador1 : jugador2;
        StringBuilder resultado = new StringBuilder();
        // Mostrar grupos encontrados
        resultado.append(jugadorActual.getGrupos()).append("\n\n");
        // Mostrar escaleras con imágenes
        java.util.List<Jugador.EscaleraInfo> escaleras = jugadorActual.getEscalerasPorPintaConImagen();
        if (!escaleras.isEmpty()) {
            for (Jugador.EscaleraInfo escalera : escaleras) {
                // Panel para mostrar imágenes y texto de la escalera
                JPanel panel = new JPanel();
                panel.setBackground(new Color(255, 255, 255));
                panel.add(new JLabel("<html><b>" + escalera.descripcion + "</b></html>"));
                for (javax.swing.ImageIcon icon : escalera.imagenes) {
                    JLabel imgLabel = new JLabel(icon);
                    imgLabel.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 220), 2));
                    panel.add(imgLabel);
                }
                JOptionPane.showMessageDialog(this, panel, "Escalera encontrada", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            resultado.append("No se encontraron escaleras de la misma pinta.\n");
        }
        // Mostrar puntaje final
        resultado.append("\nPuntaje de cartas sin figuras: ")
                 .append(jugadorActual.getPuntajeSinFiguras());
        JOptionPane.showMessageDialog(this, resultado.toString(), "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
}
