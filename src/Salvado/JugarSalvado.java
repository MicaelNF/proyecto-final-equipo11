/**
 * Clase {@code JugarSalvado} que representa el juego de las sillas.
 * La clase nos configurar y simular el juego mediante la eliminación de las personas
 * con un número de saltos aleatorio.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.2
 */
package src.Salvado;
import java.util.Random;
import java.util.Scanner;
import src.Salvado.SalvadoExcepciones.*;
import src.Usuarios.*;

public class JugarSalvado{
    private int [] circulo;
    private int saltos;
    private Random numAlea;
    private User usuario;
    private Scanner teclado = new Scanner(System.in);
    private int puntosGanados;

    /**
     * Constructor por omisión, que crea un arreglo que representa un circulo con 100 personas,
     * inicializa los saltos con un número aleatorio.
     * 
     * @param usuario: el usuario que esta jugando.
    */
    public JugarSalvado(User usuario){
        this.circulo = new int[100];
        this.numAlea = new Random();
        this.usuario = usuario;
        this.puntosGanados = 0;
        iniciarJuego();
    }

    /**
     * Método para inicializar el juego.
     * Llena el arreglo con números del 1 al 100 (consecutivos) que representan a los participantes
     * y asigna un número aleatorio de saltos.
     */
    private void iniciarJuego(){
        for(int i=0; i< circulo.length; i++){
            circulo[i]=i+1;
        }

        this.saltos = numAlea.nextInt(circulo.length) + 1;
    }

    /**
     *
     * Método que simula el juego
     * Elimina participantes del círculo siguiendo el número de saltos hasta que queda uno.
     * 
     * @return el número que del participante ganador.
     * @throws EstadoInvalidoDelJuegoExcepcion si ocurre un error inesperado en el juego.
     * 
     */
    public int jugar() throws Exception{
        //Participantes restantes
        int sobrevivientes = circulo.length;
        int indiceActual = 0;

        // Ciclo que se ejecuta hasta que solo queda un participante.
        while (sobrevivientes > 1){
            //Contador de saltos
            int conteo = 0;

            // Avanza en el círculo hasta completar el número de saltos.
            while(conteo < saltos){
                if(circulo[indiceActual] != -1){
                // Ignora las posiciones eliminadas.
                    conteo ++;
                }
                if(conteo < saltos){
                // Avanza al siguiente participante.
                    indiceActual= (indiceActual + 1) % circulo.length;
                }
            }

            // Elimina al participante actual.
            circulo[indiceActual] = -1;
            sobrevivientes--;

            // Avanza al siguiente participante válido.
            do { 
                indiceActual = (indiceActual + 1) % circulo.length;
            } while (circulo[indiceActual] == -1);
        }

        for(int i=0; i<circulo.length; i++){
            if(circulo[i] !=-1){
                return circulo[i];
            }
        }
        // Si no se encuentra un ganador, lanza una excepción.
        throw new EstadoInvalidoDelJuegoExcepcion("Algo salió mal: no se econtró un ganador.");
    }

    /**
     * Método para devolver el número de saltos
     * @return número de saltos
     */
    public int obtenerSaltos(){
        return saltos;
    }

    /**
     * Método para interactuar con el usuario y permitirle jugar al juego de salvado.
     * @throws EntradaInvalidaExcepcion si el usuario ingresa un valor que no es un número entero. 
     * @throws EstadoInvalidoDelJuegoExcepcion si ocurre un error inesperado en el juego. 
     * @throws IndexOutOfBoundsException si el índice está fuera del rango válido. 
     * @throws Exception para manejar cualquier otra excepción no específica que pueda ocurrir.
     */
    public void jugarUser() throws Exception{
        System.out.println("INSTRUCCIONES: ");
        System.out.println("En un circulo formado por sillas hay 100 personas.");
        System.out.println("Dependiendo el número de saltos que de, se irán eliminando hasta que quede un ganador.");
        System.out.println("Tu tarea es adivinar que posición quedará hasta el final.");
        System.out.println("----------------------------------------------------------------------------------");
        try {
            System.out.println("El número de saltos de esta ronda es: " + obtenerSaltos());
            System.out.println("Ingresa el número de jugador que crees que sobrevivirá hasta el final, solo números entre 1 y 100: ");

            try{
                int prediccion = teclado.nextInt();
                int sobrevivio = jugar();
                if (prediccion== sobrevivio) {
                    System.out.println("¡Has Adivinado!. El jugador que sobrevivio hasta el final fue: " + sobrevivio);
                    puntosGanados = 12;
                } else{
                    System.out.println("¡Has fallado!. El jugador que sobrevivio al final fue: " + sobrevivio);
                    puntosGanados = 2;
                }
                usuario.sumarPuntos(puntosGanados);
                System.out.println("\n¡¡HAS GANADO " + puntosGanados + " PUNTOS!!, se mostrara tu nueva información: ");
            }catch(Exception e){
                throw new EntradaInvalidaExcepcion("Solo puedes escribir números");
            }
        } catch (EstadoInvalidoDelJuegoExcepcion e) {
            System.out.println(e.getMessage());
            teclado.nextLine();
        } catch (IndexOutOfBoundsException e){
            System.out.println("El indice está fuera del rango válido");
        } catch (Exception e){
            System.out.println("Ocurrió un error");
            teclado.nextLine();
        } 
    }

}