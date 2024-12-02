/**
 * Clase {@code ListaConElementosVaciosExcepcion} que representa una excepción que ocurre
 * cuando una lista contiene uno o más elementos vacíos.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.0
 */
package src.Ordenamiento.OrdenamientoExcepciones;

public class ListaConElementosVaciosExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code ListaConElementosVaciosExcepcion}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public ListaConElementosVaciosExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code ListaConElementosVaciosExcepcion}.
     * Inicializa la excepción con un mensaje descriptivo que indica la causa del error.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public ListaConElementosVaciosExcepcion(String e) {
        super(e);
    }
}