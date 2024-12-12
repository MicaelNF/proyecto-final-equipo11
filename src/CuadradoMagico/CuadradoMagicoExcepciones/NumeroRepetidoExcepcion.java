/**
 * Excepción personalizada {@code NumeroRepetidoExcepcion} que se lanza cuando 
 * el usuario intenta ingresar un número que ya ha sido utilizado previamente.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 8-12-2024
 * @version 1.0
 */
package  src.CuadradoMagico.CuadradoMagicoExcepciones;

public class NumeroRepetidoExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code NumeroRepetidoExcepcion}
     */
    public NumeroRepetidoExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code NumeroRepetidoExcepcion}
     * @param e: el mensaje que se manda cuando ocurre la excepción
     */
    public NumeroRepetidoExcepcion(String e) {
        super(e);
    }
}