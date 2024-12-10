/**
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 1.0
 */
package src.Usuarios;

import java.io.File;

import src.Verificador.VerificadorDeCuentaUnica;
import src.Verificador.VerificadorDeNombres;
import src.Verificador.VerificadorDeOpcionesInt;

public class ManejoDeUsuarios {
    
    public static User menuUsuarios() {

        // Variable encargada de inicializar un usuario.
        User usuarioEscogido = null;

        // Este bloque de código contiene las variables inicializadas que ocuparan los lectores y escritores de documento.
        String ruta = "src/Partidas/";
        File partidas = new File(ruta);
        File[] listaDePartidas = partidas.listFiles();

        // Variable que sirve como punto de separación entre las acciones del menú.
        StringBuilder guiones = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guiones.append("-");
        }

        System.out.println("Por favor indica si quieres crear una nueva cuenta o si quieres reanudar una sesión.");
        System.out.println("(1) Para crear una nueva cuenta      (2) Para reanudar una sesión      (0) Salir del programa");
        int opcion = 0;
        
        // Método que que asegura y verifica que la opción sea válida.
        opcion = VerificadorDeOpcionesInt.verificarOpcion(0, 2);
        
        // Marca el fin de la primera sección del menu
        System.out.println(guiones);

        /* 
         * Caso de que el usuario haya decidido reanudar una sesión pero que no haya sesiones existentes,
         * se le redirige a crear un nuevo usuario.
         * 
         */
        if(listaDePartidas.length == 0 && opcion == 2) {
            System.out.println("Aun no hay ninguna partida guardada. Así que tienes que crear una nueva cuenta.");
            opcion = 1;
            System.out.println(guiones);
        } 
    
        /* 
         * Este switch contiene las acciones que tiene que llevar a cabo la primera sección del menú. Encargada 
         * de gestionar la creación de usuarios y el uso de usuarios ya existentes. 
         */
        switch (opcion) {

            // Caso de que haya escogido crear un nuevo usuario
            case 1:
            System.out.println("Por favor introduce tu nombre (ten en cuenta que solo se aceptan números y carácteres no especiales):");
            
            // Método encargado de verificar los nombres de usuario.         
            String nombreVerificado = VerificadorDeNombres.verificarNombre();

            // Método que verifica si la cuenta ya existe o aún no ha sido creada.
            boolean esUnica = VerificadorDeCuentaUnica.esUnica(nombreVerificado, listaDePartidas);

            // Si la cuenta aún no existe se procede a crear el nuevo usuario.
            if(esUnica) {

                // Se manda un mensaje que verifica que efectivamente se creo el usuario con exito.
                System.out.println("\nSe ha creado con exito el usuario " + "\"" + nombreVerificado + "\":");
                usuarioEscogido = new User(nombreVerificado);
                System.out.println(usuarioEscogido + "\n");
    
                // Bloque de codigo encargado de guardar el usuario en un archivo .txt .
                try {
                    usuarioEscogido.guardarUsuario();
                } catch (Exception e) {
                    System.out.println(e);
                }

                // Mensaje que verifica que el usuario se ha guardado correctamente.
                System.out.println("El usuario se guardo correctamente en: " + usuarioEscogido.obtenerRutaString() + "\n");
                System.out.println("¡¡IMPORTANTE, recuerda que solo cuentas con 100 créditos para jugar durante los dos días!!");

                // Se marca el fin de la sección de creación de un usuario.
                System.out.println(guiones);

            // En caso de que exista un usuario ya creado con el mismo nombre.
            } else {

                // Se le notifica al usuario que ese nombre de usuario ya existe.
                System.out.println("\nEste usuario ya existe, así que iniciaras sesión como " + nombreVerificado + ", tus datos son:\n");
                
                // Se lee el archivo que contenga a este usuario ya existente.
                try {
                    usuarioEscogido = User.leerUsuario(User.generarRuta(nombreVerificado));
                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println(usuarioEscogido + "\n");

                // Se marca el fin de la sección de creación de un usuario.
                System.out.println(guiones);
            }
                break;
            
            case 2:
            // Se le pregunta al usuario que sesión desea reanudar.
            System.out.println("Por favor escoge que sesión quieres reanudar:");

            // Ciclo que se encarga de mostrar a todos los usuarios existentes
            for(int i = 0; i < listaDePartidas.length; i++) {
                User usuarioLeido = null;

                try {
                    usuarioLeido = User.leerUsuario(User.generarRuta(listaDePartidas[i].getName()));
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                System.out.println("\nUsuario " + (i + 1) + ":");
                System.out.println(usuarioLeido);
            }

            // Se le recuerda al usuario el escoger una opción.
            System.out.print("\nEscoge una opción: ");

            // Método que que asegura y verifica que la opción sea válida.
            opcion = VerificadorDeOpcionesInt.verificarOpcion(1, listaDePartidas.length);

            // Una vez que se escogió la opción se lee el archvio que contiene al usuario escogido.
            try {
                usuarioEscogido = User.leerUsuario(User.generarRuta(listaDePartidas[opcion - 1].getName()));
            } catch (Exception e) {
                System.out.println(e);
            }

            // Se manda un mensaje que le reafirma al usuario su elección
            System.out.println("\nHas escogido el usuario " + usuarioEscogido.obtenerId());

            // Se marca el fin de la sección del menu encargada de reanudar sesiones de usuarios ya existentes.
            System.out.println(guiones);
                break;
            
            // Caso de que se quiera salir del programa
            case 0:

            // Se le da la despedida al usuario y el programa se apaga.
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.print(guiones);
            System.exit(0);
                break;
        }

        return usuarioEscogido;
    }
}
