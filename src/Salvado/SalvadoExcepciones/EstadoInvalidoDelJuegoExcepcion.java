
/**
 * Excepción personalizada {@code EstadoInvalidoDelJuegoExcepcion} que se lanza cuando 
 * el estado del juego es inválido.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.0
 */
package  src.Salvado.SalvadoExcepciones;

public class EstadoInvalidoDelJuegoExcepcion extends RuntimeException {
    
    /**
     * Constructor por omisión de la clase {@code EstadoInválidoDelJuegoExcepcion}
     */
    public EstadoInvalidoDelJuegoExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code EstadoInválidoDelJuegoExcepcion}
     * @param e el mensaje que se manda cuando ocurre la excepción
     */
    public EstadoInvalidoDelJuegoExcepcion(String e) {
        super(e);
    }
}