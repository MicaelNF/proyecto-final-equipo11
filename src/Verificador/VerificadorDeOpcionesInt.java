/**
 * Clase {@code VerificadorDeOpcionesInt} que permite verificar si una opción 
 * ingresada por el usuario es válida dentro de un rango específico de números enteros. 
 * La clase incluye métodos para manejar excepciones de entrada y garantizar 
 * que el valor ingresado sea un número entero dentro del rango esperado.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.0
 */
package src.Verificador;

import java.util.Scanner;

public class VerificadorDeOpcionesInt {

    /**
     * Solicita al usuario que ingrese un número entero dentro de un rango especificado.
     * Repite la solicitud hasta que el usuario proporcione una opción válida.
     * 
     * @param inicioDeRango El límite inferior del rango permitido.
     * @param finalDelRango El límite superior del rango permitido.
     * @return El número ingresado por el usuario, validado y dentro del rango permitido.
     */
    @SuppressWarnings("resource") // Se añadió aunque realmente no es necesario.
    public static int verificarOpcion(int inicioDeRango, int finalDelRango) {
        Scanner in = new Scanner(System.in);
        int numeroElegido = 0;
        boolean opcionInvalida = true;

        // Bucle que asegura que el número ingresado esté dentro del rango.
        while (opcionInvalida) {
            try {
                numeroElegido = in.nextInt();
                if (numeroElegido >= inicioDeRango && numeroElegido <= finalDelRango) {
                    opcionInvalida = false;
                    in.nextLine(); // Limpia el buffer del scanner.
                } else {
                    System.out.println("\nOpción inválida, intenta de nuevo:");
                    in.nextLine(); // Limpia el buffer del scanner.
                }
            } catch (Exception e) {
                System.out.println("\nSolo puedes introducir números, intenta de nuevo:");
                in.nextLine(); // Limpia el buffer del scanner.
            }
        }

        // Regresa el número válido.
        return numeroElegido;
    }

    /**
     * Solicita al usuario que ingrese un número entero dentro de un rango especificado, 
     * permitiendo una excepción a la regla de rango.
     * Repite la solicitud hasta que el usuario proporcione una opción válida.
     * 
     * @param inicioDeRango El límite inferior del rango permitido (inclusive).
     * @param finalDelRango El límite superior del rango permitido (inclusive).
     * @param excepcionDeNúmero Un número que se permite fuera del rango establecido.
     * @return El número ingresado por el usuario, validado dentro del rango o coincidiendo con la excepción.
     */
    @SuppressWarnings("resource") // Se añadió aunque realmente no es necesario.
    public static int verificarOpcion(int inicioDeRango, int finalDelRango, int excepcionDeNúmero) {
        Scanner in = new Scanner(System.in);
        int numeroElegido = 0;
        boolean opcionInvalida = true;

        // Bucle que asegura que el número ingresado esté dentro del rango o sea la excepción permitida.
        while (opcionInvalida) {
            try {
                numeroElegido = in.nextInt();
                if ((numeroElegido >= inicioDeRango && numeroElegido <= finalDelRango) || numeroElegido == excepcionDeNúmero) {
                    opcionInvalida = false;
                    in.nextLine(); // Limpia el buffer del scanner.
                } else {
                    System.out.println("\nOpción inválida, intenta de nuevo:");
                    in.nextLine(); // Limpia el buffer del scanner.
                }
            } catch (Exception e) {
                System.out.println("\nSolo puedes introducir números, intenta de nuevo:");
                in.nextLine(); // Limpia el buffer del scanner.
            }
        }

        // Regresa el número válido.
        return numeroElegido;
    }
}