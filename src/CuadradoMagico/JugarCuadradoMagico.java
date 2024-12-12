/**
 * Clase {@code JugarCuadradoMagico} que implementa la lógica del juego del cuadrado mágico.
 * El usuario debe llenar un tablero 4x4 con números del 1 al 16 de manera que las filas, columnas 
 * y diagonales sumen un valor objetivo sin repetir números
 * 
 * @author Romualdo Valera Seyin Xuxek.
 * @author Nolasco Flores Micael.
 * @date 8-11-2024
 * @version 1.2
 */
package src.CuadradoMagico;
import java.util.Random;
import java.util.Scanner;
import src.CuadradoMagico.CuadradoMagicoExcepciones.*;
import src.Usuarios.*;


public class JugarCuadradoMagico{
    //Atributos de la clase.
    Tablero tab;
    CuadradoMagico cuadM;
    Random num = new Random();
    Scanner teclado = new Scanner(System.in);
    boolean seguir = true;
    int direccion, linea;
    private User usuario;

    /**
     * Constructor que inicializa el juego.
     * Recibe un objeto {@code User} que representa al jugador y configura el tablero
     * y el cuadrado mágico con una dirección y línea aleatoria.
     * 
     * @param usuario: el usuario que esta jugando.
     */
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

    /**
     * Método privado que suma puntos al usuario.
     * @param puntos: los puntos que se le van a sumar al usuario. 
     */
    private void sumarPuntos(int puntos){
        usuario.sumarPuntos(puntos);
    }
    
    /**
     *  Método principal del juego. Controla la lógica de interacción con el usuario
     * y la validación de los movimientos realizados en el cuadrado mágico.
     */
    public void jugar() throws Exception{
        // Obtiene una variante aleatoria del cuadrado mágico.
        int[][] cuadradoMagico = tab.posiblesCuadradosMagicos();

        //Se elige una linea aleatoria para el llenado inicial del cuadrado mágico con los números correspondientes
        for(int i=0; i < 4; i++){
            if(direccion == 0){ //si es fila.
                    int nums = cuadradoMagico[linea][i];
                    cuadM.controlNumeros(nums,linea,i);
            } else if(direccion == 1){  //si es columna.
                    int nums = cuadradoMagico[i][linea];
                    cuadM.controlNumeros(nums,i,linea);  
            } else {  //si es diagonal.
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

        //Ciclo principal donde el usuario ingresa los números para llenar el cuadrado.
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                if(cuadM.cuadrado[i][j] ==0){
                    boolean posValida = false;
                    while( !posValida && seguir){
                        try {
                            System.out.println("Teclea un número del 1 al 16 en la posición: ("+ i+","+j+"): ");
                            int numpos = teclado.nextInt();

                            //Revisa si el número es valido
                            if(numpos < 1 || numpos > 16){
                                throw new NumeroInvalidoExcepcion("Solo puedes poner números entre 1 y 16");
                            } else if(cuadM.numUsados[numpos]){
                                throw new NumeroRepetidoExcepcion("El número " + numpos + " ya ha sido usado");
                            }

                            //registra el número en usado
                            cuadM.controlNumeros(numpos, i, j);
                            posValida = true;

                            //Revisa si se puede continuar jugando 
                            if(!cuadM.sePuedeContinuar()){
                                System.out.println("¡Has perdido! Ya no es posible completar un cuadrado mágico.");
                                seguir = false;
                                break;
                            }

                            //Revisa si el cuadrado completado es un cuadrado magico
                            if(cuadM.esCuadradoMagico()){
                                System.out.println("¡Lo lograste!, has completado un cuadrado mágico");
                                seguir = false;

                                //suma los puntos ganados si el usuario logro completar el cuadrado
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
        //Muestra los puntos finales del usuario
        System.out.println("Tus puntos finales: "+ usuario.obtenerPuntos());
    }
}