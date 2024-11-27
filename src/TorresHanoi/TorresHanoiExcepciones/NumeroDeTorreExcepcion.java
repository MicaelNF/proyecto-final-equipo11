/**
 * Clase {@code NumeroDeTorreExcepcion} que representa una excepción lanzada
 * cuando se intenta acceder a una torre con un número inválido.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 26-11-2024
 * @version 1.0
 */
package src.TorresHanoi.TorresHanoiExcepciones;

public class NumeroDeTorreExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code NumeroDeTorreExcepcion}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public NumeroDeTorreExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code NumeroDeTorreExcepcion}.
     * Inicializa la excepción con un mensaje descriptivo.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public NumeroDeTorreExcepcion(String e) {
        super(e);
    }
}