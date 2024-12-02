/**
 * Clase {@code LargoDelNombreExcepcion} que representa una excepción 
 * cuando el nombre proporcionado no cumple con la longitud establecida.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.3
 */
package src.Verificador.VerificadorExcepciones;

public class LargoDelNombreExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code LargoDelNombreException}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public LargoDelNombreExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code LargoDelNombreException}.
     * Inicializa la excepción con un mensaje descriptivo.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public LargoDelNombreExcepcion(String e) {
        super(e);
    }
}
