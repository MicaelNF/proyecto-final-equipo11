/**
 * Clase {@code JugarSalvado} que representa el juego de las sillas.
 * La clase nos configurar y simular el juego mediante la eliminación de las personas
 * con un número de saltos aleatorio.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.0
 */
package src.Salvado;
import src.Salvado.SalvadoExcepciones.*;
import java.util.Random;

public class JugarSalvado{
    private int [] circulo;
    private int saltos;
    private Random numAlea;

    /**
     * Constructor por omisión, que crea un arreglo que representa un circulo con 100 personas,
     * inicializa los saltos con un número aleatorio.
     * 
    */
    public JugarSalvado(){
        this.circulo = new int[100];
        this.numAlea = new Random();
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
    public int jugar(){
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

}