/**
 * Excepción personalizada {@code EntradaInvalidaExcepcion} que se lanza cuando 
 * el usuario no teclea un número.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 11-11-2024
 * @version 1.0
 */
package src.Salvado.SalvadoExcepciones;

public class EntradaInvalidaExcepcion extends Exception {
    
    /**
     * Constructor por omisión de la clase {@code EntradaInvalidaExcepcion}
     */
    public EntradaInvalidaExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code EntradaInvalidaExcepcion}
     * @param e el mensaje que se manda cuando ocurre la excepción
     */
    public EntradaInvalidaExcepcion(String e) {
        super(e);
    }
}