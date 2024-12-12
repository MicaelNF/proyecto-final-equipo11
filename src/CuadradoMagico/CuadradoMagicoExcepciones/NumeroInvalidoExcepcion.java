/**
 * Excepción personalizada {@code NumeroInvalidoExcepcion} que se lanza cuando 
 * un número no válido es ingresado.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 8-12-2024
 * @version 1.0
 */
package  src.CuadradoMagico.CuadradoMagicoExcepciones;

public class NumeroInvalidoExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code NumeroInvalidoExcepcion}
     */
    public NumeroInvalidoExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code NumeroInvalidoExcepcion}
     * @param e: el mensaje que se manda cuando ocurre la excepción
     */
    public NumeroInvalidoExcepcion(String e) {
        super(e);
    }
}