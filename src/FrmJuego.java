import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    JPanel pnlJugador1, pnlJugador2;
    Jugador jugador1, jugador2;
    JTabbedPane tpJugadores;

    public FrmJuego() {
        setTitle("Juguemos al apuntado");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(150, 255, 50));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);

        tpJugadores.setBounds(10, 40, 550, 200);
        getContentPane().add(tpJugadores);

        btnRepartir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repartir();
            }

        });

        btnVerificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }

        });

        // instanciar losjugadores
        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

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
