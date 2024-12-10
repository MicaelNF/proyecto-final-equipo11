/**
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 1.1
 */
package src.Conecta4;

import src.Conecta4.Conecta4Excepciones.ColumnaLlenaExcepcion;

public class Conecta_4 {
    private char[][] tablero = new char[6][7];
    
    public Conecta_4() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public void insertarJugada(char jugada, int posicion) throws Exception {

        // Caso de que la columna seleccionada ya este llena.
        if(espaciosEnBlancoColumna(posicion) == 0) {
            throw new ColumnaLlenaExcepcion("No puedes introducir fichas en una columna llena.");
        }

        tablero[espaciosEnBlancoColumna(posicion) - 1][posicion] = jugada;
    }

    public boolean esGanador() {
        for(int i = 0; i < tablero.length; i++) {
            if(columnaGanadora(i) || filaGanadora(i) || diagonalGanadora(i)) {
                return true;
            }
        }

        if(columnaGanadora(6) || diagonalGanadora(6)) {
            return true;
        }

        return false;
    }

    private int espaciosEnBlancoColumna(int columna) {
        int contador = 0;
        for (int i = 0; i < tablero.length; i++) {
            if(tablero[i][columna] == ' ') contador++;
        }

        return contador;
    }

    private int espaciosEnBlancoFila(int fila) {
        int contador = 0;
        for (int i = 0; i < tablero[0].length; i++) {
            if(tablero[fila][i] == ' ') contador++;
        }

        return contador;
    }

    private boolean columnaGanadora(int columna) {
        if(espaciosEnBlancoColumna(columna) > 2) {
            return false;
        }

        boolean encendido = true;
        int i = 0;

        while(encendido) {
            try {
                if(tablero[i][columna] == tablero[i + 1][columna] && tablero[i][columna] == tablero[i + 2][columna] && tablero[i][columna] == tablero[i + 3][columna]) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                encendido = false;
            }
        }

        return false;
    }


    private boolean filaGanadora(int fila) {
        if(espaciosEnBlancoFila(fila) > 3) {
            return false;
        }

        boolean encendido = true;
        int i = 0;

        while(encendido) {
            try {
                if(tablero[fila][i] == tablero[fila][i + 1] && tablero[fila][i] == tablero[fila][i + 2] && tablero[fila][i] == tablero[fila][i + 3]) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                encendido = false;
            }
        }

        return false;
    }

    private boolean diagonalGanadora(int diagonal) {
        boolean encendido = true;
        int i = 0;

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

        return false;
    }

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

    public static void main(String[] args) {
        Conecta_4 prueba = new Conecta_4();

        System.out.println(prueba);
        try {
            prueba.insertarJugada('X', 0);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('O', 5);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }


        try {
            prueba.insertarJugada('O', 4);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }


        try {
            prueba.insertarJugada('O', 3);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('X', 5);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('O', 4);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('O', 1);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('X', 4);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('O', 3);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('O', 3);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            prueba.insertarJugada('X', 3);
            System.out.println(prueba);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(prueba.esGanador());
    }
}
