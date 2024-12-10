/**
 * Clase {@code Conecta_4} que implementa la lógica principal del juego Conecta 4.
 * 
 * Esta clase incluye métodos para inicializar el tablero, insertar jugadas,
 * verificar si hay un ganador y representar en un formato de cadena el estado actual del juego.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @version 1.2
 * @date 10-12-2024
 */
package src.Conecta4;

import src.Conecta4.Conecta4Excepciones.ColumnaLlenaExcepcion;

public class Conecta_4 {
    private char[][] tablero = new char[6][7];
    
    /**
     * Constructor por omisión de la clase {@code Conecta_4}.
     *  
     * Inicializa un tablero vacío de 6 filas y 7 columnas, llenando todas las
     * posiciones con espacios en blanco (' ').
     */
    public Conecta_4() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    /**
     * Inserta una jugada en la columna especificada.
     * 
     * @param jugada el carácter que representa la ficha del jugador ('X' o 'O').
     * @param posicion la columna (0 a 6) donde se insertará la ficha.
     * @throws ColumnaLlenaExcepcion si la columna seleccionada ya está llena.
     */
    public void insertarJugada(char jugada, int posicion) throws Exception {

        // Caso de que la columna seleccionada ya este llena.
        if(espaciosEnBlancoColumna(posicion) == 0) {
            throw new ColumnaLlenaExcepcion("No puedes introducir fichas en una columna llena.");
        }

        tablero[espaciosEnBlancoColumna(posicion) - 1][posicion] = jugada;
    }

    /**
     * Verifica si hay un ganador en el juego.
     * 
     * @return 1 si hay un ganador, 0 si el tablero está lleno y no hay ganador vaya un empate y 
     * -1 si el juego puede continuar.
     */
    public int esGanador() {

        // Este bloque de codigo sirve para verificar si es que hay un empate.
        int contador = 0;

        // Se verifican todas las columnas y si ya no queda ningun espacio disponible se aumenta el contador.
        for (int i = 0; i < 7; i++) {
            if(espaciosEnBlancoColumna(i) == 0) {
                contador++;
            }
        }

        // Si el contador es igual a 7 quiere decir que el tablero se encuentra lleno por lo que es un empate.
        if (contador == 7) {
            return 0;
        }

        // Este bloque se encarga de verificar si hay ganadores hasta la posición 5.
        for(int i = 0; i < tablero.length; i++) {
            if(columnaGanadora(i) || filaGanadora(i) || diagonalGanadora(i)) {
                return 1;
            }
        }

        /* 
         * Ya que las columnas y las diagonales funcionan respecto a la cantidad de columnas que hay 
         * verificamos la columna 6 que no se verifico en lo anterior.
         */
        if(columnaGanadora(6) || diagonalGanadora(6)) {
            return 1;
        }

        // En caso de que no haya pasado ninguno de los anetriores casos quiere decir que el juego puede continuar.
        return -1;
    }

    /**
     * Calcula el número de espacios en blanco en una columna.
     * 
     * @param columna la columna a analizar (0 a 6).
     * @return el número de espacios vacíos en la columna.
     */    
    private int espaciosEnBlancoColumna(int columna) {
        int contador = 0;
        for (int i = 0; i < tablero.length; i++) {
            if(tablero[i][columna] == ' ') contador++;
        }

        return contador;
    }

    /**
     * Calcula el número de espacios en blanco en una fila.
     * 
     * @param fila la fila a analizar (0 a 5).
     * @return el número de espacios vacíos en la fila.
     */
    private int espaciosEnBlancoFila(int fila) {
        int contador = 0;
        for (int i = 0; i < tablero[0].length; i++) {
            if(tablero[fila][i] == ' ') contador++;
        }

        return contador;
    }

    /**
     * Verifica si una columna contiene una combinación ganadora.
     * 
     * @param columna la columna a analizar (0 a 6).
     * @return {@code true} si hay una combinación ganadora en la columna, {@code false} en caso contrario.
     */    
    private boolean columnaGanadora(int columna) {
        
        // Si la columna posee más de dos espacios disponibles entonces no hay forma posible de que haya un ganador en ella.
        if(espaciosEnBlancoColumna(columna) > 2) {
            return false;
        }

        // Bloque que se encarga de verificar en conjuntos de 4 si es que son iguales por ende si es un caso ganador.
        boolean encendido = true;
        int i = 0;

        while(encendido) {
            try {
                // Caso de un ganador
                if(tablero[i][columna] == tablero[i + 1][columna] && tablero[i][columna] == tablero[i + 2][columna] && tablero[i][columna] == tablero[i + 3][columna]) {
                    return true;
                }
                i++;
            
            // Se detiene justo cuando cacha una excepción que sería IndexOutOfBounds.
            } catch (Exception e) {
                encendido = false;
            }
        }

        // Si no hay ningun caso ganador.
        return false;
    }


    /**
     * Verifica si una fila contiene una combinación ganadora.
     * 
     * @param fila la fila a analizar (0 a 5).
     * @return {@code true} si hay una combinación ganadora en la fila, {@code false} en caso contrario.
     */
    private boolean filaGanadora(int fila) {

        // Si la fila posee más de tres espacios disponibles entonces no hay forma posible de que haya un ganador en ella.
        if(espaciosEnBlancoFila(fila) > 3) {
            return false;
        }

        // Bloque que se encarga de verificar en conjuntos de 4 si es que son iguales por ende si es un caso ganador.
        boolean encendido = true;
        int i = 0;

        while(encendido) {
            try {
                // Caso de un ganador
                if(tablero[fila][i] == tablero[fila][i + 1] && tablero[fila][i] == tablero[fila][i + 2] && tablero[fila][i] == tablero[fila][i + 3]) {
                    return true;
                }
                i++;

            // Se detiene justo cuando cacha una excepción que sería IndexOutOfBounds.
            } catch (Exception e) {
                encendido = false;
            }
        }

        // Si no hay ningun caso ganador.
        return false;
    }

    /**
     * Verifica si una diagonal contiene una combinación ganadora.
     * 
     * @param diagonal la diagonal a analizar (índice de fila o columna).
     * @return {@code true} si hay una combinación ganadora en la diagonal, {@code false} en caso contrario.
     */
    private boolean diagonalGanadora(int diagonal) {
        boolean encendido = true;
        int i = 0;

        /* 
         * Si el índice a verificar es mayor o igual a 3 quiere decir que las diagonales disponibles son negativas, 
         * esto quiere decir que conforme aumenta la el eje x que en este caso sería la fila la columna decrece.
         */
        if(diagonal >= 3) {
            while(encendido) {
                try {
                    if(tablero[i][diagonal] != ' ') {
                        if(tablero[i][diagonal] == tablero[i + 1][diagonal - 1] && tablero[i][diagonal] == tablero[i + 2][diagonal - 2] && tablero[i][diagonal] == tablero[i + 3][diagonal - 3]) {
                            return true;
                        }
                    }
                    i++;
                } catch (Exception e) {
                    encendido = false;
                }
            }    
        }
        /* 
         * Ahora bien si el indice es menor o igual a tres, quiere decir que los posibles casos ganadores 
         * tendran una pendiente positiva lo que quiere decir que conforme aumente el valor de las filas aumentara 
         * el valor de las columnas.
         */
        if(diagonal <= 3) {
            encendido = true;
            i = 0;
            while(encendido) {
                try {
                    if(tablero[i][diagonal] != ' ') {
                        if(tablero[i][diagonal] == tablero[i + 1][diagonal + 1] && tablero[i][diagonal] == tablero[i + 2][diagonal + 2] && tablero[i][diagonal] == tablero[i + 3][diagonal + 3]) {
                            return true;
                        }
                    }
                    i++;
                } catch (Exception e) {
                    encendido = false;
                }
            }
        }

        // Si es que no hay ningun caso ganador.
        return false;
    }

    /**
     * Genera una representación en cadena del estado actual del tablero.
     * 
     * @return una cadena que representa el tablero del juego.
     */
    @Override
    public String toString() {
    String guiones = "-----------------------------";
	String resultado = ("  1   2   3   4   5   6   7\n" + guiones + "\n");
        
	for (int i = 0; i < tablero.length; i++) {
	    for (int j = 0; j < tablero[i].length; j++) {
		resultado += "| " + tablero[i][j] + " ";
	    }
	    resultado += "|\n";
	    resultado += guiones + "\n";
	}
	return resultado;
    }
}
