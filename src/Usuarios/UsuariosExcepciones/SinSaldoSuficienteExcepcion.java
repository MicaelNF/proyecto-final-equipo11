/**
 * Clase {@code SinSaldoSuficienteExcepcion} que representa una excepción lanzada
 * cuando un usuario intenta realizar una operación que requiere más saldo del
 * disponible en su cuenta.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.0
 */
package src.Usuarios.UsuariosExcepciones;

public class SinSaldoSuficienteExcepcion extends Exception {

    /**
     * Constructor por omisión de la clase {@code SinSaldoSuficienteExcepcion}.
     * Inicializa la excepción sin un mensaje específico.
     */
    public SinSaldoSuficienteExcepcion() {
        super();
    }

    /**
     * Constructor por parámetros de la clase {@code SinSaldoSuficienteExcepcion}.
     * Inicializa la excepción con un mensaje descriptivo.
     * 
     * @param e el mensaje que describe el motivo de la excepción.
     */
    public SinSaldoSuficienteExcepcion(String e) {
        super(e);
    }
}
