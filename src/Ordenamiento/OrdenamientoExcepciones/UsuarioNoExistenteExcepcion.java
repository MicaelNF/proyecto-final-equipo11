/**
 * Clase {@code UsuarioNoExistenteExcepcion} que representa una excepción que se lanza
 * cuando no se encuentra un usuario dentro de la lista ordenada.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.0
 */
package src.Ordenamiento.OrdenamientoExcepciones;

public class UsuarioNoExistenteExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code UsuarioNoExistenteExcepcion}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public UsuarioNoExistenteExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code UsuarioNoExistenteExcepcion}.
     * Inicializa la excepción con un mensaje que describe el motivo del error.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public UsuarioNoExistenteExcepcion(String e) {
        super(e);
    }
}