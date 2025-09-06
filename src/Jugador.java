import java.util.Random;

import javax.swing.JPanel;

// Clase que representa a un jugador en el juego.
// Cada jugador tiene un conjunto de cartas y puede repartirlas y mostrarlas en pantalla.
public class Jugador {

    private final int TOTAL_CARTAS = 10; // Número total de cartas por jugador
    private final int SEPARACION = 40;   // Espacio entre cartas al mostrar
    private final int MARGEN = 10;       // Margen inicial para mostrar cartas
    private Carta[] cartas = new Carta[TOTAL_CARTAS]; // Arreglo de cartas del jugador
    private Random r = new Random();     // Generador de números aleatorios

    // Reparte cartas aleatorias al jugador
    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    // Muestra las cartas del jugador en el panel indicado
    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN + TOTAL_CARTAS * SEPARACION;
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i].mostrar(pnl, posicion, MARGEN);
            posicion -= SEPARACION;
        }
        pnl.repaint();
    }

    // Devuelve una cadena con los grupos encontrados entre las cartas del jugador
    public String getGrupos() {
        String resultado = "No se encontaron grupos";
        // iniciar los contadores
        int[] contadores = new int[13];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        // obtener los resultados
        boolean hayGrupos = false;
        for (int c : contadores) {
            if (c >= 2) {
                hayGrupos = true;
            }
        }
        if (hayGrupos) {
            resultado = "Se encontraron los siguientes grupos:\n";
            int p = 0;
            for (int c : contadores) {
                if (c >= 2) {
                    resultado += Grupo.values()[c] +
                            " de " + NombreCarta.values()[p] + "\n";
                }
                p++;
            }
        }

        return resultado;
    }
}
