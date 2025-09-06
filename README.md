# Juego de Cartas - Proyecto Educativo

**Institución:** I.U Pascual Bravo  
**Autores:** Juan Esteban Correa y Emmanuel Berrio  
**Apoyo:** Documentación y comprensión asistida por IA (GitHub Copilot)

---

## Descripción

Este proyecto es una aplicación educativa desarrollada en Java, que simula un juego de cartas tipo baraja inglesa. El objetivo principal es aprender conceptos de programación orientada a objetos, manejo de interfaces gráficas (Swing), y lógica de agrupamiento y secuencias de cartas.

La aplicación permite:

- Repartir cartas aleatorias a dos jugadores.
- Visualizar las cartas en una interfaz moderna y amigable.
- Detectar grupos de cartas (pares, ternas, etc.).
- Identificar escaleras (secuencias de cartas consecutivas de la misma pinta).
- Calcular el puntaje de cartas no agrupadas, siguiendo reglas específicas para cartas especiales (Ace, Jack, Queen, King).
- Mostrar resultados y cartas con imágenes en cuadros de diálogo.

---

## Objetivo Educativo

Este proyecto fue realizado como parte del proceso de aprendizaje en la I.U Pascual Bravo, con el fin de fortalecer habilidades en:

- Programación orientada a objetos (POO).
- Diseño de interfaces gráficas en Java.
- Lógica de juegos y algoritmos de agrupamiento.
- Documentación clara y escalable para principiantes.

La documentación y explicación del código fue enriquecida con el apoyo de la inteligencia artificial, facilitando la comprensión y el desarrollo colaborativo.

---

## Estructura del Proyecto

- `src/`: Código fuente principal (clases, lógica del juego, interfaz gráfica).
- `bin/`: Archivos compilados.
- `imagenes/`: Imágenes de las cartas utilizadas en la interfaz.
- `README.md`: Documentación y guía del proyecto.

---

## Explicación de Clases y Métodos

A continuación se describen las clases principales y sus métodos más importantes:

### App.java

- **main(String[] args):** Punto de entrada. Inicia la interfaz gráfica del juego.

### FrmJuego.java

- **FrmJuego():** Constructor. Configura la ventana, colores, botones y paneles.
- **repartir():** Reparte cartas a ambos jugadores y las muestra en pantalla.
- **verificar():** Muestra los resultados del jugador activo: grupos, escaleras y puntaje, incluyendo imágenes.

### Jugador.java

- **repartir():** Asigna 10 cartas aleatorias al jugador.
- **mostrar(JPanel pnl):** Dibuja las cartas en el panel, ajustando posición y separación.
- **getGrupos():** Detecta grupos de cartas iguales (pares, ternas, etc.) y los describe.
- **getEscalerasPorPintaConImagen():** Busca secuencias de cartas consecutivas de la misma pinta y devuelve descripción e imágenes.
- **getEscalerasPorPinta():** Devuelve solo la descripción textual de las escaleras encontradas.
- **getPuntajeSinFiguras():** Calcula el puntaje de cartas que no forman grupos, siguiendo reglas para cartas especiales.
- **EscaleraInfo:** Clase interna que encapsula la descripción y las imágenes de una escalera encontrada.

### Carta.java

- **Carta(Random r):** Constructor. Crea una carta con valor aleatorio.
- **mostrar(JPanel pnl, int x, int y):** Dibuja la carta en el panel y muestra su imagen y datos al hacer clic.
- **getPinta():** Devuelve la pinta (figura) de la carta.
- **getNombre():** Devuelve el nombre (valor) de la carta.
- **getImageIcon():** Devuelve el icono de la imagen de la carta.

### Grupo.java

- **Enum Grupo:** Representa los posibles grupos de cartas (par, terna, cuarta, etc.).

### NombreCarta.java

- **Enum NombreCarta:** Enumera los nombres posibles de las cartas (AS, DOS, ..., KING).

### Pinta.java

- **Enum Pinta:** Enumera las pintas posibles (TREBOL, PICA, CORAZON, DIAMANTE).

---

Cada método está documentado en el código fuente para facilitar el aprendizaje y la consulta. El diseño modular permite entender y modificar el juego fácilmente, ideal para quienes se inician en Java y programación orientada a objetos.

---

## Cómo Ejecutar

1. Abre el proyecto en Visual Studio Code o tu IDE favorito.
2. Compila el código fuente ubicado en la carpeta `src`.
3. Ejecuta la clase principal `App.java` para iniciar la aplicación.
4. Utiliza los botones para repartir cartas y verificar resultados.

---

## Créditos y Agradecimientos

- **Juan Esteban Correa y Emmanuel Berrio:** Desarrollo y lógica del juego.
- **I.U Pascual Bravo:** Formación y guía académica.
- **GitHub Copilot:** Asistencia en documentación y comprensión del código.

Este proyecto es un ejemplo de cómo la tecnología y la colaboración pueden potenciar el aprendizaje y la creatividad en el desarrollo de software.

---

## Contacto

Para dudas, sugerencias o aportes, puedes contactar a los autores a través de la institución.

---

¡Gracias por revisar este proyecto educativo y esperamos que te ayude a aprender y disfrutar la programación en Java!
