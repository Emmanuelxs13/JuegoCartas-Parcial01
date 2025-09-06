// Enumeración que representa los diferentes grupos de cartas que pueden formarse en el juego.
// Cada valor indica la cantidad de cartas iguales encontradas (par, terna, cuarta, etc.).
public enum Grupo {
    VACIO,    // Sin grupo
    NON,     // Un solo ejemplar
    PAR,     // Dos cartas iguales
    TERNA,   // Tres cartas iguales
    CUARTA,  // Cuatro cartas iguales
    QUINTA,  // Cinco cartas iguales
    SEXTA,   // Seis cartas iguales
    SEPTIMA, // Siete cartas iguales
    OCTAVA,  // Ocho cartas iguales
    NOVENA,  // Nueve cartas iguales
    DECIMA;  // Diez cartas iguales

    // Constructor privado de la enumeración (no se utiliza directamente)
    private Grupo() {
    }
}
