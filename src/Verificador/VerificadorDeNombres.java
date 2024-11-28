/**
 * Clase {@code VerificadorDeNombres} que permite verificar y validar nombres según 
 * ciertos criterios de longitud y caracteres permitidos. El nombre se transforma a mayúsculas 
 * y se eliminan los caracteres no deseados.
 * 
 * Requisitos del nombre:
 * - Debe contener entre 5 y 10 caracteres válidos.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.1
 */
package src.Verificador;
import src.Verificador.VerificadorExcepciones.*;

public class VerificadorDeNombres {

    private String nombreVerificado;

    /**
     * Constructor de la clase {@code VerificadorDeNombres}.
     * Verifica y valida el nombre proporcionado. El nombre se convierte a mayúsculas y se eliminan 
     * los carácteres no válidos.
     * 
     * @param nombre el nombre que se desea verificar.
     * @throws IllegalArgumentException si el nombre proporcionado es nulo.
     * @throws LargoDelNombreException si el nombre no cumple con los requisitos de longitud.
     */
    public VerificadorDeNombres(String nombre) throws Exception {
        
        // Caso de que el nombre sea un objeto nulo.
        if (nombre == null) {
            throw new IllegalArgumentException("No puedes pasar como parámetro un objeto nulo.");
        }

        // Se convierte el nombre a mayúsculas.
        nombreVerificado = nombre.toUpperCase();

        // Se convierte el nombre en uno válido.
        this.verificarNombre();
    }

    /**
     * Método privado que verifica la validez del nombre. Filtra los caracteres no alfabéticos
     * y valida la longitud resultante. Si el nombre tiene menos de 5 caracteres o más de 10,
     * se lanza una excepción.
     * 
     * @throws LargoDelNombreException si la longitud del nombre verificado es menor a 5 
     *         o mayor a 10 caracteres alfabéticos válidos.
     */
    private void verificarNombre() throws LargoDelNombreException {
        String cadenaVerificada = "";
        String caracteresDisponibles = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Filtra los caracteres permitidos.
        for (int i = 0; i < nombreVerificado.length(); i++) {
            if (caracteresDisponibles.indexOf(nombreVerificado.charAt(i)) != -1) {
                cadenaVerificada += nombreVerificado.charAt(i);
            }
        }

        // Verifica si la longitud es válida.
        if (cadenaVerificada.length() < 3) {
            throw new LargoDelNombreException("Tu nombre debe de tener al menos 3 caracteres válidos.");
        }

        if (cadenaVerificada.length() > 15) {
            throw new LargoDelNombreException("Tu nombre debe de tener como máximo 15 caracteres válidos.");
        }

        // Asigna el nombre validado.
        nombreVerificado = cadenaVerificada;
    }

    /**
     * Método que devuelve la representación en cadena del nombre validado.
     * 
     * @return el nombre validado en formato de cadena.
     */
    @Override
    public String toString() {
        return nombreVerificado;
    }

    /**
     * Método principal que ejecuta una prueba del verificador de nombres.
     * 
     * @param args los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        try {
            VerificadorDeNombres nombre = new VerificadorDeNombres("Micael1029");
            System.out.println(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
