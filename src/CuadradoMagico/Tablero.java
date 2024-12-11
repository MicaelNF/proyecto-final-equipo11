/**
 * Clase {@code Tablero} que contiene 5  posibles variantes 
 * del cuadrado magico resuelto, contiene el cuadrado magico "original", 
 * y algunas rotaciones y reflejos de el tablero original.
 * 
 * @author Romualdo Valera Seyin Xuxek
 * @author Nolasco Flores Micael
 * @date 6-12-2024
 * @version 1.0
 */
package src.CuadradoMagico;
import java.util.Random;

public class Tablero{
    //Tablero original.
    private final int[][] tableroMagico = {
        {1,15,14,4},
        {12,6,7,9},
        {8,10,11,5},
        {13,3,2,16}
    };

    /**
     * Método que devuelve de forma aleatoria una de las variantes 
     * del cuadrado magico original y el original.
     * 
     * @return una matriz de 4x4 con una de las posibles soluciones del cuadrado magico.
     */
    public int[][] posiblesCuadradosMagicos(){
        Random num = new Random();
        int eleccion = num.nextInt(5);

        switch (eleccion) {
            case 0:
                return tableroMagico;
            case 1:
                return variante1(tableroMagico);
            case 2: 
                return variante2(tableroMagico);
            case 3: 
                return variante3(tableroMagico);
            case 4:
                return variante4(tableroMagico);
            default:
                return tableroMagico;
        }
    }

    /**
     * Método que rota 90° el tablero original
     * @param tablero : el tablero original.
     * @return la variente1 del tablero original
     */
    private int[][] variante1(int[][] tablero){
        int[][] var1 = new int [4][4];
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                var1 [j][3-i]= tablero[i][j]; 
            }
        }
        return var1; 
    }

    /**
     * Método que invierte las columnas del tablero.
     * @param tablero: el tablero original.
     * @return la variante 2 del tablero original.
     */
    private int[][] variante2(int[][] tablero){
        int[][] var2 = new int[4][4];
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                var2 [i][3-j]= tablero[i][j]; 
            }
        }
        return var2;
    }

    /**
     * Método que rota 180° el tablero original.
     * @param tablero: el tablero original.
     * @return la variante 3 del tablero original.
     */
    private int[][] variante3(int[][] tablero){
        int[][] var3 = new int[4][4];
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                var3 [3-i][j]= tablero[i][j]; 
            }
        }
        return var3;
    }

    /**
     * Método que invierte las filas del tablero
     * @param tablero: el tablero original
     * @return la variente 4 del tablero original.
     */
    private int[][] variante4(int[][] tablero){
        int[][] var4 = new int[4][4];
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                var4 [i][j] = tablero[j][i];
            }
        }
        return var4;
    }
}