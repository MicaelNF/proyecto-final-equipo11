/**
 * Clase {@code VerificadorDeNombres} que permite verificar y validar nombres 
 * según ciertos criterios de longitud y caracteres permitidos. El nombre ingresado 
 * se transforma a mayúsculas y se filtran los caracteres que si estan permitidos.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 2.0
 */
package src.Verificador;

import java.util.Scanner;
import src.Verificador.VerificadorExcepciones.LargoDelNombreExcepcion;

public class VerificadorDeNombres {

    private static final String CARACTERES_DISPONIBLES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Método estático que solicita al usuario un nombre por consola y lo verifica.
     * Si el nombre ingresado no cumple con los requisitos de longitud, se solicita nuevamente
     * hasta que se introduzca un nombre válido.
     * 
     * @return El nombre verificado y filtrado que cumple con los requisitos de longitud y caracteres permitidos.
     */
    @SuppressWarnings("resource") // Se añadió aunque realmente no es necesario.
    public static String verificarNombre() {
        Scanner in = new Scanner(System.in);
        String nombre;
        String nombreVerificado = null;
        boolean nombreInvalido = true;

        // Bucle que asegura y verifica que el nombre de usuario sea válido.
        while (nombreInvalido) {
            try {
                System.out.print("Ingrese un nombre: ");
                nombre = in.nextLine();
                nombreVerificado = VerificadorDeNombres.verificarNombre(nombre);
                nombreInvalido = false;
            } catch (LargoDelNombreExcepcion e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nError inesperado: " + e.getMessage());
            }
        }
        
        // Se regresa el nombre verificado.
        return nombreVerificado;
    }

    /**
     * Método auxiliar que verifica la validez de un nombre ingresado. Convierte el nombre a mayúsculas,
     * filtra los caracteres no alfabéticos y valida la longitud resultante.
     * 
     * @param nombre El nombre ingresado por el usuario.
     * @return El nombre ya válido.
     * @throws LargoDelNombreExcepcion si la longitud del nombre verificado es menor a 3 
     * o mayor a 15 caracteres válidos.
     */
    private static String verificarNombre(String nombre) throws LargoDelNombreExcepcion {

        // Se crea un StringBuilder para almacenar el nombre filtrado.
        StringBuilder cadenaVerificada = new StringBuilder();

        // Se convierte el nombre a mayúsculas.
        String nombreEnMayusculas = nombre.toUpperCase();

        // Se filtran los caracteres permitidos.
        for (int i = 0; i < nombreEnMayusculas.length(); i++) {
            char caracterActual = nombreEnMayusculas.charAt(i);
            if (CARACTERES_DISPONIBLES.indexOf(caracterActual) != -1) {
                cadenaVerificada.append(caracterActual);
            }
        }

        // Caso en que la longitud del nombre sea menor a 3 caracteres válidos.
        if (cadenaVerificada.length() < 3) {
            throw new LargoDelNombreExcepcion("El nombre debe tener al menos 3 caracteres válidos.");
        }

        // Caso en que la longitud del nombre sea mayor a 15 caracteres válidos.
        if (cadenaVerificada.length() > 15) {
            throw new LargoDelNombreExcepcion("El nombre debe tener como máximo 15 caracteres válidos.");
        }

        // Regresa el nombre verificado.
        return cadenaVerificada.toString();
    }
}
