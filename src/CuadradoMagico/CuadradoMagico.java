/**
 * Clase {@code CuadradoMagico} que representa un cuadrado mágico de 4x4,
 * en el cual se gestionan los números a colocar y se valida si las condiciones 
 * de un cuadrado mágico se cumplen.
 * 
 * @author Romualdo Valera Seyin Xuxek
 * @author Nolasco Flores Micael
 * @date 7-12-2024
 * @version 2.2
 */

package src.CuadradoMagico;
public class CuadradoMagico{
    //se inicializan las variables que usaremos
    int [][]cuadrado;
    int sumaObjetivo;
    boolean[]numUsados;

    /**
     * Constructor por omisión de la clase {@code CuadradoMagico}
     * Inicializa un cuadrado mágico de 4x4 con valores en cero y se declara 
     * que la suma objetivo sea 34. Además, inicializa u arreglo {@code numUsados}
     * para llevar un control de los números ya utilizados.
     */
    public CuadradoMagico(){
        this.cuadrado = new int[4][4];
        this.numUsados = new boolean[17];
        this.sumaObjetivo = 34;

        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                cuadrado[i][j]=0;
            }
        }
    }

    /**
     * Método que asigna un número a una posición específica en el cuadrado mágico 
     * y marca ese número como utilizado.
     * @param num: número que se esta usando. 
     * @param i : la fila donde se ubica el numero dentro del cuadro.
     * @param j : la columna donde se ubica el numero dentro del cuadro.
     */
    public void controlNumeros(int num, int i, int j){
        cuadrado[i][j] = num;
        numUsados[num]= true;
    }

    /**
     * Método privado que valida si la suma de una línea (fila, columna o diagonal)
     * es válida según la cantidad de celdas vacías y la suma objetivo.
     * 
     * @param suma: la suma actual de la linea.
     * @param vacios : cuantos espacios vacios hay en la linea.
     * @return {@code true} si la linea cumple con la linea objetivo, y {@code false} si no cumple.
     */
    private boolean validarLinea(int suma, int vacios) {
        return vacios == 0 ? suma == sumaObjetivo : suma <= sumaObjetivo;
    }

    /**
     * Método que valida si se puede continuar llenando el cuadrado mágico. 
     * Verifica que las sumas parciales de filas, columnas y diagonales no 
     * excedan la suma objetivo.
     * 
     * @return {@code true} si se puede continuar llenando el cuadrado mágico, 
     *         {@code false} si alguna línea no cumple con las condiciones. 
     */
    public boolean sePuedeContinuar(){
        int sumaDiagonal1 =0;
        int sumaDiagonal2 =0;
        int espaciosVaciosD1 =0;
        int espaciosVaciosD2 =0;

        // Recorre cada fila y columna para calcular las sumas parciales
        for(int i=0; i < 4; i++){
            int sumaFila = 0;
            int sumaColumna =0;
            int espaciosVaciosF=0;
            int espaciosVaciosC=0;
            
            //Recorre las columnas de cada fila
            for(int j=0; j < 4; j++){
                if(cuadrado[i][j] == 0){
                    espaciosVaciosF ++;
                }else{
                    sumaFila += cuadrado[i][j];
                }
                if(cuadrado[j][i] == 0){
                    espaciosVaciosC ++;
                }else{
                    sumaColumna += cuadrado[j][i];
                }
            }

            // Verifica si la suma de la fila y la columna cumplen con las condiciones.
            if (!validarLinea(sumaFila, espaciosVaciosF) || !validarLinea(sumaColumna, espaciosVaciosC)) {
                return false;
            }

            // Verifica las diagonales
            if(cuadrado[i][i] == 0){
                espaciosVaciosD1 ++;
            }else{
                sumaDiagonal1 += cuadrado[i][i];
            }
            if(cuadrado[i][3-i] == 0){
                espaciosVaciosD2 ++;
            }else{
                sumaDiagonal2 += cuadrado[i][3-i];
            }
        }

        // Verifica si las diagonales cumplen con las condiciones.
        return validarLinea(sumaDiagonal1, espaciosVaciosD1) && validarLinea(sumaDiagonal2, espaciosVaciosD2);
    }

    /**
     * Método que valida si el cuadrado mágico es válido. Verifica que todas las filas,
     * columnas y diagonales den 34 (la suma objetivo).
     * 
     * @return {@code true} si el cuadrado mágico es válido, {@code false} si no lo es.
     */
    public boolean esCuadradoMagico(){
        int sumaDiagonal1 = 0;
        int sumaDiagonal2 = 0;

        //Recorre cada fila y columna para verificar las sumas.
        for (int i = 0; i < 4; i++) {
            int sumaFila = 0, sumaColumna = 0;

            // Suma los elementos de cada fila y columna
            for (int j = 0; j < 4; j++) {
                sumaFila += cuadrado[i][j];
                sumaColumna += cuadrado[j][i];
            }

            // Verifica que la suma de la fila y columna sea igual a la suma objetivo
            if (sumaFila != sumaObjetivo || sumaColumna != sumaObjetivo) {
                return false;
            }

            // Suma los elementos de las diagonales
            sumaDiagonal1 += cuadrado[i][i];
            sumaDiagonal2 += cuadrado[i][3 - i];
        }
        // Verifica que las diagonales tengan la suma objetivo
        return sumaDiagonal1 == sumaObjetivo && sumaDiagonal2 == sumaObjetivo; 
    }

    /**
     * Método que imprime el cuadrado mágico,
     * @return la representación en cadena del cuadrado magico.
     */
    public String toString() {
        String resultado = "    0   1   2   3  \n" + "  -----------------\n";

        // Recorre cada fila y columna para generar la representación en cadena
        for (int i = 0; i < 4; i++) {
            resultado += i + " ";
            for (int j = 0; j < 4; j++) {
                resultado += "| " + (cuadrado[i][j]==0 ? " ": cuadrado[i][j]) + " ";
            }
            resultado += "|\n" + "  -----------------\n";
        }
        return resultado;
    }
}