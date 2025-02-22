/**
 * Clase {@code NumeroDeDiscosExcepcion} que representa una excepción lanzada
 * cuando el número de discos para una torre es inválido.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 26-11-2024
 * @version 1.0
 */
package src.TorresHanoi.TorresHanoiExcepciones;

public class NumeroDeDiscosExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code NumeroDeDiscosExcepcion}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public NumeroDeDiscosExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code NumeroDeDiscosExcepcion}.
     * Inicializa la excepción con un mensaje descriptivo.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public NumeroDeDiscosExcepcion(String e) {
        super(e);
    }
}