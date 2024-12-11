package src.CuadradoMagico;
import java.util.Random;
import java.util.Scanner;
import src.CuadroMagico.CuadradoMagicoExcepciones.*;
import src.Usuarios.*;


public class JugarCuadradoMagico{
    Tablero tab;
    CuadradoMagico cuadM;
    Random num = new Random();
    Scanner teclado = new Scanner(System.in);
    boolean seguir = true;
    int direccion, linea;
    private User usuario;

    public JugarCuadradoMagico(User usuario){
        this.tab = new Tablero();
        this.cuadM = new CuadradoMagico();
        this.usuario = usuario;

        this.linea = num.nextInt(4);
        this.direccion = num.nextInt(3);

        if(direccion == 2){
            this.linea= num.nextInt(2);
        }
    }

    private void sumarPuntos(int puntos){
        usuario.sumarPuntos(puntos);
    }

    public void jugar(){
        int[][] cuadradoMagico = tab.posiblesCuadradosMagicos();
        
        for(int i=0; i < 4; i++){
            if(direccion == 0){
                    int nums = cuadradoMagico[linea][i];
                    cuadM.controlNumeros(nums,linea,i);
            } else if(direccion == 1){
                    int nums = cuadradoMagico[i][linea];
                    cuadM.controlNumeros(nums,i,linea);  
            } else {
                if(linea == 0){
                        int nums = cuadradoMagico[i][i];
                        cuadM.controlNumeros(nums,i,i);
                } else{
                        int nums = cuadradoMagico[i][3-i];
                        cuadM.controlNumeros(nums, i, 3-i);
                    
                }
            }
        }

        System.out.println("Has elegido jugar Cuadrado Magico");
        System.out.println("INSTRUCCIONES \n" + 
                "A continuacion se mostrará un tablero, el cual debes de llenar considerando números del 1 al 16. \n" +
                "Asegúrate de que la suma de cada fila, columna y las diagonales sean:" + cuadM.sumaObjetivo + " (debes lograrlo sin repetir números).\n");
        System.out.println(cuadM.toString());
        System.out.println("¡Comienza el juego!");

        
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                if(cuadM.cuadrado[i][j] ==0){
                    boolean posValida = false;
                    while( !posValida && seguir){
                        try {
                            System.out.println("Teclea un número del 1 al 16 en la posición: ("+ i+","+j+"): ");
                            int numpos = teclado.nextInt();

                            if(numpos < 1 || numpos > 16){
                                throw new NumeroInvalidoExcepcion("Solo puedes poner números entre 1 y 16");
                            } else if(cuadM.numUsados[numpos]){
                                throw new NumeroRepetidoExcepcion("El número " + numpos + " ya ha sido usado");
                            }

                            cuadM.controlNumeros(numpos, i, j);
                            posValida = true;

                            if(!cuadM.sePuedeContinuar()){
                                System.out.println("¡Has perdido! Ya no es posible completar un cuadrado mágico.");
                                seguir = false;
                                break;
                            }

                            if(cuadM.esCuadradoMagico()){
                                System.out.println("¡Lo lograste!, has completado un cuadrado mágico");
                                seguir = false;

                                sumarPuntos(10);
                                break;
                            }

                        } catch (Exception e) {
                            System.out.println("Error: "+ e.getMessage());
                        }
                    }
                }
            }
        }

        System.out.println("El tablero quedó así: ");
        System.out.println(cuadM.toString());

        if(cuadM.esCuadradoMagico()){
            System.out.println("¡Has Ganado!, completaste un cuadrado mágico.");
        } else{
            System.out.println("¡Has fallado!, no lograste completar un cuadrado mágico.");
        }

        System.out.println("Tus puntos finales: "+ usuario.obtenerPuntos());
    }
}