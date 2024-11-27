/**
 * Clase {@code Torre} que representa una torre en el juego de las Torres de Hanoi.
 * Esta clase sirve para la asignar discos, verificación de posiciones disponibles, 
 * y generación de una representación en tipo String (ASCII).
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 26-11-2024
 * @version 1.0
 */
package src.TorresHanoi;
import src.TorresHanoi.TorresHanoiExcepciones.*;

public class Torre {
    
    private int[] torre;
    
    /**
     * Constructor por omisión de la clase {@code Torre}.
     * Inicializa una torre vacía con un espacio para 6 discos.
     */
    public Torre() {
        torre = new int[6];

        // Se inicializan todas las posiciones como vacías osea con un 0.
        for (int i = 0; i < torre.length; i++) {
            torre[i] = 0;
        }
    }

    /**
     * Constructor por parámetros de la clase {@code Torre}.
     * Inicializa una torre vacía con el espacio que se le pase como parámetro.
     * 
     * @param numeroDeDiscos la cantidad de discos que puede almacenar la torre.
     * @throws NumeroDeDiscosExcepcion si el número de discos es negativo.
     */
    public Torre(int numeroDeDiscos) throws NumeroDeDiscosExcepcion {
        
        // Caso de que el numero de discos sea negativo.
        if (numeroDeDiscos < 0) {
            throw new NumeroDeDiscosExcepcion("No puedes crear torres con números negativos.");
        }

        // Si no es negativo se da la capacidad que se paso como parámetro.
        torre = new int[numeroDeDiscos];

        // Se inicializan todas las posiciones como vacías osea con un 0.
        for (int i = 0; i < torre.length; i++) {
            torre[i] = 0; 
        }
    }

    /**
     * Llena la torre con discos en orden creciente desde la base.
     */
    public void llenarTorre() {
        for (int i = 0; i < torre.length; i++) {
            torre[i] = i + 1;
        }
    }

    /**
     * Obtiene el disco en una posición específica de la torre.
     * 
     * @param posición la posición del disco a obtener.
     * @return el tamaño del disco en la posición especificada.
     */
    public int obtenerDisco(int posición) {
        return torre[posición];
    }

    /**
     * Devuelve el tamaño de la torre.
     * 
     * @return el número de posiciones en la torre.
     */
    public int length() {
        return torre.length;
    }

    /**
     * Cuenta la cantidad de posiciones vacías en la torre.
     * 
     * @return el número de posiciones vacías en la torre.
     */
    public int posicionesSinNada() {
        int contadorDePosiciones = 0;
        for (int i = 0; i < torre.length; i++) {
            if (torre[i] == 0) {
                contadorDePosiciones++;
            }
        }
        return contadorDePosiciones;
    }

    /**
     * Asigna el disco que este hasta arriba de la torre con la que se le mande a llamar a la torre que
     * se le pasa como parámetro.
     * 
     * @param destino la torre a la que se quiere mover el disco.
     * @throws IllegalArgumentException si el destino es nulo.
     * @throws NumeroDeDiscosExcepcion si la torre origen no tiene discos.
     * @throws DiscoMasGrandeExcepcion si se intenta colocar un disco más grande sobre uno más pequeño.
     */
    public void asignarDisco(Torre destino) throws Exception {

        // Caso de que la torre destino sea nula.
        if (destino == null) {
            throw new IllegalArgumentException("No puedes asignar discos a objetos nulos (torres que no existen).");
        }

        // Caso de que la torre origen no tenga ningun disco.
        if (posicionesSinNada() == length()) {
            throw new NumeroDeDiscosExcepcion("No hay discos en la torre que escogiste.");
        }

        // Se verifica que la torre destino este vacía es decir que posea unicamente 0. 
        if (destino.posicionesSinNada() == destino.length()) {

            // Se verifica que la torre origen este llena es decir que no posea ningun 0.
            if (this.posicionesSinNada() == 0) {

                /* 
                 * Se asigna el disco de la primera posición de la torre origen a la última posición de 
                 * la torre destino.
                 */
                destino.asignarDisco(destino.length() - 1, obtenerDisco(0));
                asignarDisco(0, 0);
            
            // En caso de que la torre origen no este llena.
            } else {

                /* 
                 * El disco más cercano a la posicion 0 de la torre origen es asignado a la 
                 * última posición de la torre destino.
                 */
                destino.asignarDisco(destino.length() - 1, obtenerDisco(posicionesSinNada()));
                asignarDisco(posicionesSinNada(), 0);
            }
            
        // Caso de que la torre destino no este vacía es decir que cuente con al menos un disco.
        } else {
            
            /* 
             * Se comparan los discos más cercanos a la posición 0 de ambas torres y en caso de que
             * el disco de la torre destino sea más grande que el de la torre origen se mueve el disco
             * a la posición que le corresponde.
             */
            if (obtenerDisco(posicionesSinNada()) < destino.obtenerDisco(destino.posicionesSinNada())) {
                destino.asignarDisco(destino.posicionesSinNada() - 1, obtenerDisco(posicionesSinNada()));
                asignarDisco(posicionesSinNada(), 0);
            
            // Caso de que el disco de la torre origen sea mayor.
            } else {
                throw new DiscoMasGrandeExcepcion("Solo puedes poner discos más pequeños encima de otro.");
            }
        }
    }

    /**
     * Asigna un disco a una posición específica de la torre.
     * 
     * @param posición la posición en la que se colocará el disco.
     * @param disco el tamaño del disco a asignar.
     * @throws IndexOutOfBoundsException si la posición es negativa o mayor que el tamaño de la torre.
     * @throws DiscoMasGrandeExcepcion si el disco excede el tamaño permitido.
     */
    private void asignarDisco(int posición, int disco) throws Exception {
        
        // Caso de que la posición sea negativa.
        if (posición < 0) {
            throw new IndexOutOfBoundsException("No puedes asignar un disco a una posición negativa.");
        }

        // Caso de que la posición sea mayor a la cantidad de posiciones de la torre.
        if (posición > torre.length) {
            throw new IndexOutOfBoundsException("No puedes asignar un disco a una posición mayor al largo de la torre.");
        }

        // Caso de que el disco sea más grande que el disco máximo que puede aceptar la torre.
        if (disco > torre.length) {
            throw new DiscoMasGrandeExcepcion("No puedes asignar un disco más grande que el largo de la torre.");
        }
        torre[posición] = disco;
    }

    public boolean esGanador() {
        return (posicionesSinNada() == 0);
    }

    /**
     * Genera una representación visual de un disco (ASCII).
     * 
     * @param disco el tamaño del disco a representar.
     * @return una cadena que representa gráficamente el disco.
     */
    public String representacionDeDiscos(int disco) {
        String representacion = "";
        String espaciosBlancos = "";
        String asteriscos = "";
        if (disco == 0) {
            representacion = "       |       ";
        } else {
            int numDeEspaciosBlancos = 7 - disco;
            for (int i = 0; i < numDeEspaciosBlancos; i++) {
                espaciosBlancos += " ";
            }
            int numDeAsteriscos = (disco * 2) + 1;
            for (int i = 0; i < numDeAsteriscos; i++) {
                asteriscos += "*";
            }
            representacion += espaciosBlancos + asteriscos + espaciosBlancos;
        }
        return representacion;
    }
}