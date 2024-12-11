package src.CuadradoMagico;
public class CuadradoMagico{
    int [][]cuadrado;
    int sumaObjetivo;
    boolean[]numUsados;

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

    public void controlNumeros(int num, int i, int j){
        cuadrado[i][j] = num;
        numUsados[num]= true;
    }

    private boolean validarLinea(int suma, int vacios) {
        return vacios == 0 ? suma == sumaObjetivo : suma <= sumaObjetivo;
    }

    public boolean sePuedeContinuar(){
        int sumaDiagonal1 =0;
        int sumaDiagonal2 =0;
        int espaciosVaciosD1 =0;
        int espaciosVaciosD2 =0;
        
        for(int i=0; i < 4; i++){
            int sumaFila = 0;
            int sumaColumna =0;
            int espaciosVaciosF=0;
            int espaciosVaciosC=0;
            
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
            if (!validarLinea(sumaFila, espaciosVaciosF) || !validarLinea(sumaColumna, espaciosVaciosC)) {
                return false;
            }

            
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
        return validarLinea(sumaDiagonal1, espaciosVaciosD1) && validarLinea(sumaDiagonal2, espaciosVaciosD2);
    }


    public boolean esCuadradoMagico(){
        int sumaDiagonal1 = 0, sumaDiagonal2 = 0;

        for (int i = 0; i < 4; i++) {
            int sumaFila = 0, sumaColumna = 0;

            for (int j = 0; j < 4; j++) {
                sumaFila += cuadrado[i][j];
                sumaColumna += cuadrado[j][i];
            }

            if (sumaFila != sumaObjetivo || sumaColumna != sumaObjetivo) {
                return false;
            }

            sumaDiagonal1 += cuadrado[i][i];
            sumaDiagonal2 += cuadrado[i][3 - i];
        }

        return sumaDiagonal1 == sumaObjetivo && sumaDiagonal2 == sumaObjetivo; 
    }

    public String toString() {
        String resultado = "    0   1   2   3  \n" + "  -----------------\n";

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