/**
 * Clase {@code TorresDeHanoi} que representa el juego completo de las Torres de Hanoi.
 * Esta clase es heredada de la clase {@code Torre} y gestiona tres torres del juego.
 * Incluye métodos para inicializar el juego, mover discos entre torres, y generar una representación visual 
 * del estado actual del juego.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.3
 */
package src.TorresHanoi;
import src.TorresHanoi.TorresHanoiExcepciones.*;

public class TorresDeHanoi extends Torre {
    private Torre[] TorreDeHanoi = new Torre[3];
    static int jugadas = 0;

    /**
     * Constructor por omisión de la clase {@code JuegoTorresDeHanoi}.
     * Inicializa las tres torres del juego y llena la primera torre con 6 discos ordenados.
     */
    public TorresDeHanoi() {
        for (int i = 0; i < TorreDeHanoi.length; i++) {
            TorreDeHanoi[i] = new Torre();    
        }
        // Se llena la primera torre con discos en orden creciente.
        TorreDeHanoi[0].llenarTorre(); 
    }

    /**
     * Constructor por parámetros de la clase {@code JuegoTorresDeHanoi}.
     * Inicializa las tres torres del juego y llena la primera torre con la cantidad de discos 
     * especificada por el parámetro {@code numeroDeDiscos}.
     * 
     * @param numeroDeDiscos la cantidad de discos iniciales en la primera torre.
     * @throws Exception si ocurre un error durante la inicialización de las torres.
     */
    public TorresDeHanoi(int numeroDeDiscos) throws Exception {
            for (int i = 0; i < TorreDeHanoi.length; i++) {
                TorreDeHanoi[i] = new Torre(numeroDeDiscos);    
            }
            // Se llena la primera torre con discos en orden creciente.
            TorreDeHanoi[0].llenarTorre(); 
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
        TorreDeHanoi[torreDePartida].asignarDisco(TorreDeHanoi[torreDestino]);

        // Si se pasa todo lo anterior sin problema se suma 1 jugada.
        jugadas++;
    }

    /**
     * Verifica si el jugador ha ganado el juego.
     * La condición de victoria se cumple cuando todos los discos están en la tercera torre.
     * 
     * @return {@code true} si el jugador ha ganado; {@code false} en caso contrario.
     */
    public boolean esGanador() {
        return TorreDeHanoi[2].esGanador();
    }

    /**
     * Devuelve el número de jugadas hechas.
     * 
     * @return el número de jugadas que se han hecho.
     */
    public int obtenerNumeroDeJugadas() {
        return jugadas;
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
        for (int i = 0; i < TorreDeHanoi[0].length(); i++) {
            cadena += representacionDeDiscos(TorreDeHanoi[0].obtenerDisco(i)) + espacioBlanco +
                      representacionDeDiscos(TorreDeHanoi[1].obtenerDisco(i)) + espacioBlanco +
                      representacionDeDiscos(TorreDeHanoi[2].obtenerDisco(i)) + "\n";
            if (i == TorreDeHanoi[0].length() - 1) {
                cadena += "    Torre 1    " + espacioBlanco + "    Torre 2    " + espacioBlanco + "    Torre 3    ";
            }
        }
        return cadena;
    }
}

