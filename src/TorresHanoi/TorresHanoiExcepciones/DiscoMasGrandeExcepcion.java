/**
 * Clase {@code DiscoMasGrandeExcepcion} que representa una excepción lanzada
 * cuando se intenta colocar un disco más grande sobre uno más pequeño en el juego
 * de las Torres de Hanoi.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 26-11-2024
 * @version 1.0
 */
package src.TorresHanoi.TorresHanoiExcepciones;

public class DiscoMasGrandeExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code DiscoMasGrandeExcepcion}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public DiscoMasGrandeExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code DiscoMasGrandeExcepcion}.
     * Inicializa la excepción con un mensaje descriptivo.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public DiscoMasGrandeExcepcion(String e) {
        super(e);
    }
}