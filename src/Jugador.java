import java.util.Random;

import javax.swing.JPanel;

// Clase que representa a un jugador en el juego de cartas.
// Un jugador tiene un conjunto de cartas y puede realizar acciones como repartir, mostrar sus cartas,
// identificar grupos (pares, ternas, etc.), encontrar escaleras y calcular su puntaje.
public class Jugador {

    // Constantes para la configuración del juego
    private final int TOTAL_CARTAS = 10; // Número total de cartas que recibe cada jugador
    private final int SEPARACION = 40;   // Espacio horizontal entre cartas al mostrarlas
    private final int MARGEN_X = 10;     // Margen horizontal inicial para mostrar las cartas
    private final int MARGEN_Y = 40;     // Margen vertical inicial para mostrar las cartas (ajustado para no tapar el título)

    // Arreglo que almacena las cartas del jugador
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    // Generador de números aleatorios para repartir cartas
    private Random r = new Random();

    /**
     * Reparte cartas aleatorias al jugador.
     * Cada carta se crea con un valor aleatorio y se almacena en el arreglo 'cartas'.
     */
    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r); // Se crea una nueva carta aleatoria
        }
    }

    /**
     * Muestra las cartas del jugador en el panel indicado.
     * Las cartas se colocan en el panel con una separación definida y se actualiza la vista.
     * El margen vertical (MARGEN_Y) se ha aumentado para evitar que las cartas tapen el texto del panel.
     * Si deseas ajustar la posición vertical, modifica el valor de MARGEN_Y.
     * @param pnl Panel donde se mostrarán las cartas
     */
    public void mostrar(JPanel pnl) {
        pnl.removeAll(); // Limpia el panel antes de mostrar las cartas
        int posicion = MARGEN_X + TOTAL_CARTAS * SEPARACION; // Calcula la posición inicial horizontal
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i].mostrar(pnl, posicion, MARGEN_Y); // Muestra cada carta en el panel, más abajo
            posicion -= SEPARACION; // Actualiza la posición para la siguiente carta
        }
        pnl.repaint(); // Refresca el panel para mostrar los cambios
    }

    /**
     * Identifica los grupos de cartas (pares, ternas, etc.) que tiene el jugador.
     * Un grupo es cuando hay dos o más cartas con el mismo nombre (valor).
     * @return Cadena con la descripción de los grupos encontrados
     */
    public String getGrupos() {
        String resultado = "No se encontaron grupos";
        // Contador para cada tipo de carta (por nombre)
        int[] contadores = new int[13];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        // Verifica si hay grupos
        boolean hayGrupos = false;
        for (int c : contadores) {
            if (c >= 2) {
                hayGrupos = true;
            }
        }
        // Si hay grupos, los describe
        if (hayGrupos) {
            resultado = "Se encontraron los siguientes grupos:\n";
            int p = 0;
            for (int c : contadores) {
                if (c >= 2) {
                    // Usa la enumeración Grupo para describir el tipo de grupo
                    resultado += Grupo.values()[c] +
                            " de " + NombreCarta.values()[p] + "\n";
                }
                p++;
            }
        }
        return resultado;
    }

    /**
     * Busca todas las escaleras (secuencias) de cartas consecutivas de la misma pinta.
     * Una escalera es una secuencia de 3 o más cartas con valores consecutivos y la misma pinta.
     * Por cada escalera encontrada, se guarda la descripción textual y las imágenes de las cartas que la conforman.
     * Esto permite mostrar tanto el texto como las imágenes en la interfaz gráfica.
     *
     * Proceso:
     * 1. Agrupa las cartas del jugador por pinta (figura).
     * 2. Ordena las cartas de cada pinta por su valor (ordinal).
     * 3. Busca secuencias consecutivas de 3 o más cartas dentro de cada grupo de pinta.
     * 4. Por cada secuencia encontrada, crea un objeto EscaleraInfo con la descripción y las imágenes.
     *
     * @return Lista de objetos EscaleraInfo, cada uno con la descripción y las imágenes de la escalera.
     */
    public java.util.List<EscaleraInfo> getEscalerasPorPintaConImagen() {
        java.util.List<EscaleraInfo> escaleras = new java.util.ArrayList<>();
        java.util.Map<Pinta, java.util.List<Carta>> cartasPorPinta = new java.util.HashMap<>();
        // Agrupa las cartas por pinta
        for (Carta carta : cartas) {
            cartasPorPinta.computeIfAbsent(carta.getPinta(), k -> new java.util.ArrayList<>()).add(carta);
        }
        // Para cada pinta, busca secuencias consecutivas
        for (Pinta pinta : cartasPorPinta.keySet()) {
            java.util.List<Carta> cartasPinta = cartasPorPinta.get(pinta);
            cartasPinta.sort(java.util.Comparator.comparingInt(c -> c.getNombre().ordinal()));
            int inicio = 0;
            while (inicio < cartasPinta.size()) {
                int fin = inicio;
                // Busca secuencia consecutiva de valores
                while (fin + 1 < cartasPinta.size() && cartasPinta.get(fin + 1).getNombre().ordinal() == cartasPinta.get(fin).getNombre().ordinal() + 1) {
                    fin++;
                }
                // Si la secuencia tiene 3 o más cartas, es una escalera
                if (fin - inicio >= 2) {
                    StringBuilder sb = new StringBuilder();
                    java.util.List<javax.swing.ImageIcon> imagenes = new java.util.ArrayList<>();
                    sb.append("Escalera de ").append(pinta).append(": ");
                    for (int i = inicio; i <= fin; i++) {
                        sb.append(cartasPinta.get(i).getNombre()); // Agrega el nombre de la carta
                        imagenes.add(cartasPinta.get(i).getImageIcon()); // Agrega la imagen de la carta
                        if (i < fin) sb.append(", ");
                    }
                    // Guarda la escalera encontrada
                    escaleras.add(new EscaleraInfo(sb.toString(), imagenes));
                }
                inicio = fin + 1; // Continúa buscando más escaleras
            }
        }
        return escaleras;
    }

    /**
     * Clase interna para encapsular la información de una escalera.
     * Contiene la descripción textual y la lista de imágenes de las cartas que forman la escalera.
     * Esto facilita mostrar la información completa en la interfaz gráfica.
     */
    public static class EscaleraInfo {
        public final String descripcion; // Texto descriptivo de la escalera
        public final java.util.List<javax.swing.ImageIcon> imagenes; // Imágenes de las cartas
        public EscaleraInfo(String descripcion, java.util.List<javax.swing.ImageIcon> imagenes) {
            this.descripcion = descripcion;
            this.imagenes = imagenes;
        }
    }

    /**
     * Busca todas las escaleras (secuencias) de cartas consecutivas de la misma pinta.
     * Una escalera es una secuencia de 3 o más cartas con valores consecutivos y la misma pinta.
     * @return Lista de cadenas con la descripción de cada escalera encontrada
     */
    public java.util.List<String> getEscalerasPorPinta() {
        java.util.List<String> escaleras = new java.util.ArrayList<>();
        // Agrupa las cartas por pinta (figura)
        java.util.Map<Pinta, java.util.List<NombreCarta>> cartasPorPinta = new java.util.HashMap<>();
        for (Carta carta : cartas) {
            cartasPorPinta.computeIfAbsent(carta.getPinta(), k -> new java.util.ArrayList<>()).add(carta.getNombre());
        }
        // Busca secuencias consecutivas en cada grupo de pinta
        for (Pinta pinta : cartasPorPinta.keySet()) {
            java.util.List<NombreCarta> nombres = cartasPorPinta.get(pinta);
            // Ordena los nombres de las cartas por su valor
            nombres.sort(java.util.Comparator.comparingInt(NombreCarta::ordinal));
            // Busca secuencias de 3 o más cartas consecutivas
            int inicio = 0;
            while (inicio < nombres.size()) {
                int fin = inicio;
                while (fin + 1 < nombres.size() && nombres.get(fin + 1).ordinal() == nombres.get(fin).ordinal() + 1) {
                    fin++;
                }
                if (fin - inicio >= 2) { // Secuencia de 3 o más
                    StringBuilder sb = new StringBuilder();
                    sb.append("Escalera de ").append(pinta).append(": ");
                    for (int i = inicio; i <= fin; i++) {
                        sb.append(nombres.get(i));
                        if (i < fin) sb.append(", ");
                    }
                    escaleras.add(sb.toString());
                }
                inicio = fin + 1;
            }
        }
        return escaleras;
    }

    /**
     * Calcula el puntaje de las cartas que no forman parte de ninguna figura (par, terna, etc.).
     * Las cartas Ace, Jack, Queen y King valen 10 puntos; el resto su número (ordinal + 1).
     * @return Puntaje total de cartas sin figuras
     */
    public int getPuntajeSinFiguras() {
        int[] contadores = new int[13];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        int puntaje = 0;
        for (int i = 0; i < cartas.length; i++) {
            NombreCarta nombre = cartas[i].getNombre();
            // Solo suma el puntaje de cartas que aparecen una sola vez (no forman grupo)
            if (contadores[nombre.ordinal()] == 1) {
                switch (nombre) {
                    case AS:
                    case JACK:
                    case QUEEN:
                    case KING:
                        puntaje += 10; // Figuras valen 10
                        break;
                    default:
                        puntaje += nombre.ordinal() + 1; // El resto su número
                        break;
                }
            }
        }
        return puntaje;
    }
}
