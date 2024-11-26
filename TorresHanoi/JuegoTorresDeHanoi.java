/**
 * Clase {@code JuegoTorresDeHanoi} que representa el juego completo de las Torres de Hanoi.
 * Esta clase extiende la funcionalidad de la clase {@code Torre} y gestiona las tres torres del juego.
 * Incluye métodos para inicializar el juego, mover discos entre torres, y generar una representación visual del estado actual.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 26-11-2024
 * @version 1.0
 */
package TorresHanoi;
import TorresHanoi.TorresHanoiExcepciones.*;

public class JuegoTorresDeHanoi extends Torre {
    private Torre[] juegoTorreDeHanoi = new Torre[3];
    static int jugadas = 0;

    /**
     * Constructor por omisión de la clase {@code JuegoTorresDeHanoi}.
     * Inicializa las tres torres del juego y llena la primera torre con discos ordenados.
     */
    public JuegoTorresDeHanoi() {
        for (int i = 0; i < juegoTorreDeHanoi.length; i++) {
            juegoTorreDeHanoi[i] = new Torre();    
        }
        // Se llena la primera torre con discos en orden creciente.
        juegoTorreDeHanoi[0].llenarTorre(); 
    }

    /**
     * Mueve el disco superior de una torre de origen a una torre de destino.
     * 
     * @param torreDePartida la torre desde la que se desea mover el disco.
     * @param torreDestino la torre a la que se desea mover el disco.
     * @throws NumeroDeTorreExcepcion si la torre de partida o de destino no es válida (fuera del rango 0-2).
     * @throws IllegalArgumentException si los índices de las torres son negativos.
     * @throws NumeroDeDiscosExcepcion si no hay discos en la torre de partida.
     * @throws DiscoMasGrandeExcepcion si se intenta colocar un disco más grande sobre uno más pequeño.
     */
    public void moverPieza(int torreDePartida, int torreDestino) throws Exception {
        
        // Caso de que la torre de partida o la destino tengan una posición negativa
        if (torreDePartida < 0 || torreDestino < 0) {
            throw new NumeroDeTorreExcepcion("No puedes escoger una torre con un número negativo.");
        }

        // Caso de que se escoga una torre con una posición mayor a 2
        if (torreDePartida > 2 || torreDestino > 2) {
            // Se muestra 3 ya que es lo que ve el usuario.
            throw new NumeroDeTorreExcepcion("No puedes escoger una torre mayor a 3"); 
        }
        
        // Se usa un método de la super clase para mover la pieza, este ya tiene sus excepciones.
        juegoTorreDeHanoi[torreDePartida].asignarDisco(juegoTorreDeHanoi[torreDestino]);

        // Si se pasa todo lo anterior sin problema se suma 1 jugada.
        jugadas++;
    }

    /**
     * Genera una representación visual del estado actual del juego.
     * Muestra los discos en las tres torres en formato ASCII.
     * 
     * @return una cadena que representa gráficamente el estado actual del juego.
     */
    @Override
    public String toString() {
        String cadena = "";
        String espacioBlanco = "      ";
        for (int i = 0; i < juegoTorreDeHanoi[0].length(); i++) {
            cadena += (i + 1) + ")" + representacionDeDiscos(juegoTorreDeHanoi[0].obtenerDisco(i)) + espacioBlanco +
                      (i + 1) + ")" + representacionDeDiscos(juegoTorreDeHanoi[1].obtenerDisco(i)) + espacioBlanco +
                      (i + 1) + ")" + representacionDeDiscos(juegoTorreDeHanoi[2].obtenerDisco(i)) + "\n";
            if (i == juegoTorreDeHanoi[0].length() - 1) {
                cadena += "      Torre 1    " + espacioBlanco + "      Torre 2    " + espacioBlanco + "      Torre 3    ";
            }
        }
        return cadena;
    }

    /**
     * Método principal para ejecutar una prueba del juego de Torres de Hanoi. Aun no se solicitan creditos ni el día
     * ya que el encargado de realizar dichas verificaciones sera otra clase.
     * 
     * @param args no se utilizo la línea de comandos.
     */
    public static void main(String[] args) {
        JuegoTorresDeHanoi prueba = new JuegoTorresDeHanoi();
        String guiones = "--------------------------------------------------------------------------------";
        try {
            System.out.println(prueba);
            System.out.println(guiones);
            
            prueba.moverPieza(0, 2);
            System.out.println(prueba);
            System.out.println(guiones);

            prueba.moverPieza(0, 1);
            System.out.println(prueba);
            System.out.println(guiones);


            prueba.moverPieza(2, 1);
            System.out.println(prueba);
            System.out.println(guiones);

            prueba.moverPieza(0, 2);
            System.out.println(prueba);
            System.out.println(guiones);

            prueba.moverPieza(1, 2);
            System.out.println(prueba);
            System.out.println(guiones);

            prueba.moverPieza(1, 0);
            System.out.println(prueba);
            System.out.println(guiones);

            prueba.moverPieza(0, 1);
            System.out.println(prueba);
            System.out.println(guiones);

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

