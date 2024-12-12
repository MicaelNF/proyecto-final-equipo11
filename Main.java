/**
 * La clase {@code Main} modela una "Feria de Ciencias". Este programa permite a los usuarios crear una cuenta, 
 * reanudar una sesión existente y jugar a diferentes juegos, dependiendo del día.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 2.2
 */
import java.util.Scanner;
import src.Conecta4.JuegoConecta_4;
import src.CuadradoMagico.*;
import src.Ordenamiento.*;
import src.Salvado.JugarSalvado;
import src.TorresHanoi.*;
import src.Usuarios.*;
import src.Verificador.*;

public class Main {
    // Variable que delimita el fin de una sección del menú.
    private static String guiones = "";

    /**
     * El método principal que se encarga de llevar a cabo todo la lógica del programa.
     * 
     * @param args Parámetros de línea de comandos. En este caso no se utilizan.
     */
    public static void main(String[] args) {

        // Variables que se usan a lo largo del programa multiples veces.
        Scanner inTexto = new Scanner(System.in);
        boolean bucleSecundario = true;
        User usuarioEscogido = null;
        int opcion = 0;

        // Se marca el largo del delimitante del menú
        StringBuilder guionesBuilder = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guionesBuilder.append("-");
        }
        guiones = guionesBuilder.toString();

        //---------------------------------------Se inicia el programa---------------------------------------

        // Se le da la bienvenida al usuario.
        System.out.println("                                    Bienvenido a la Feria de Ciencias                              ");
        System.out.println(guiones);
        
        // Empieza la primera sección del menú encargada de gestionar la creación y uso de usuarios ya existentes.
        usuarioEscogido = ManejoDeUsuarios.menuUsuarios();

        /* 
         * Empieza la segunda sección del menú. Encargada de gestionar en que día de la feria se encuentra el usuario 
         * para así poder darle a escoger los juegos disponibles de ese día y negarle el acceso a los juegos de otro día.
         */
        System.out.println("¿Que día quieres jugar?");
        System.out.println("(1) Día 1          (2) Día 2          (0) Salir del programa");
        
        // Método que que asegura y verifica que la opción sea válida.
        opcion = VerificadorDeOpcionesInt.verificarOpcion(0, 2);
        
        // Se marca el fin de esta parte del menú. 
        System.out.println(guiones);

        // Este switch es el encargado de dirigir a el usuario a las actividades del día correspondiente.
        switch (opcion) {

            // Caso de que haya escogido el primer día.
            case 1:

            // Bucle que le permite al usuario jugar varias veces los juegos de este día.
            bucleSecundario = true;
            while(bucleSecundario) {
                
                // Se le pregunta al usuario que juego desea jugar.
                System.out.println("¿Que juego deseas jugar?");
                System.out.println("(1) Cuadrado mágico          (2) Conecta 4          (3) Ver tu posición en el top           (0) Salir del programa");
                
                // Método que que asegura y verifica que la opción sea válida.
                opcion = VerificadorDeOpcionesInt.verificarOpcion(0, 3);

                // Se marca el fin de esta parte del menú.
                System.out.println(guiones);

                // Este switch contiene los juegos que se puden jugar en el día uno.
                switch (opcion) {

                    // Caso de que el usuario haya escogido jugar "Cuadrado mágico".
                    case 1:
                        //se intenta retirar el saldo de la cuenta del usuario.
                        try {
                            usuarioEscogido.retirarSaldo(15);
                            JugarCuadradoMagico juego = new JugarCuadradoMagico(usuarioEscogido);
                            juego.jugar();
                            
                            // Caso de que no cuente con los fondos suficientes se le notifica y no se le da acceso al juego. 
                        } catch (Exception e) {
                            System.out.println("Lo siento no tienes saldo suficiente.");
                        }
                        break;
                    
                    // Caso de que el usuario haya escogido jugar "Conecta 4".
                    case 2:

                    // Se intenta retirar el saldo de la cuenta del usuario.
                    try {
                        usuarioEscogido.retirarSaldo(15);
                        JuegoConecta_4.jugarConecta4(usuarioEscogido);

                    // Caso de que no cuente con los fondos suficientes se le notifica y no se le da acceso al juego. 
                    } catch (Exception e) {
                        System.out.println("Lo siento no tienes saldo suficiente.");
                    }
                        break;

                    // Caso de que quieran ver el top de jugadores y su posición    
                    case 3:
                    OrdenamientoDeUsuarios.mostrarPodio(usuarioEscogido);
                        break;
                
                    // Caso de que el usuario quiera salir del programa.    
                    case 0:
                    finalizarPrograma();
                        break;
                }
                // Método encargado de marcar el fin de un juego y desplegar un menú de opciones a realizar.                     
                finDelJuego(usuarioEscogido);
            }
                break;

            
            case 2:

            // Bucle que se le permite al usuario jugar varias veces los juegos de este día.
            bucleSecundario = true;
            while(bucleSecundario) {

                // Se le pregunta al usuario que juego quiere hacer.
                System.out.println("¿Que es lo que deseas jugar o hacer?");
                System.out.println("(1) Salvado          (2) Torres de Hanoi           (3) Ver tu posición en el top           (0) Salir del programa");
                
                // Método que que asegura y verifica que la opción sea válida.
                opcion = VerificadorDeOpcionesInt.verificarOpcion(0, 3);

                // Se marca el fin de esta parte del menú
                System.out.println(guiones);

                // Este switch contiene los juegos disponibles para este día.
                switch (opcion) {

                    // Caso de que el usuario haya escogido jugar "Salvado".
                    case 1:
                        //Se intenta retirar el saldo
                        try {
                            usuarioEscogido.retirarSaldo(15);
                            JugarSalvado juego = new JugarSalvado(usuarioEscogido);
                            juego.jugarUser();

                        // Caso de que no cuente con los fondos suficientes se le notifica y no se le da acceso al juego.
                        } catch (Exception e) {
                            System.out.println("Lo siento no tienes saldo suficiente.");
                        }
                        
                        break;
                    
                    // Caso de que el usuario haya escogido jugar "Torres de Hanoi".
                    case 2:

                    // Se intenta retirar el saldo de la cuenta del usuario.
                    try {
                        usuarioEscogido.retirarSaldo(15);
                        JuegoTorresDeHanoi.jugarTorresDeHanoi(usuarioEscogido);
                    
                    // Caso de que no cuente con los fondos suficientes se le notifica y no se le da acceso al juego. 
                    } catch (Exception e) {
                        System.out.println("Lo siento no tienes saldo suficiente.");
                    }
                        break;
                    
                    // Caso de que quieran ver el top de jugadores y su posición    
                    case 3:
                    OrdenamientoDeUsuarios.mostrarPodio(usuarioEscogido);
                        break;
                
                    // Caso de que se quiera salir del programa    
                    case 0:
                    finalizarPrograma();
                        break;
                }

                // Método encargado de marcar el fin de un juego y desplegar un menú de opciones a realizar.                     
                finDelJuego(usuarioEscogido);
            }
                break;
            
            // Caso de que se quiera salir del programa.    
            case 0:
            finalizarPrograma();
                break;
        }

        // Se cierra el Scanner.
        inTexto.close();
    }

    /**
     * Método que finaliza la ejecución del programa mostrando un mensaje de despedida al usuario.
     */
    public static void finalizarPrograma() {
        System.out.println("Gracias por haber usado este programa nos vemos luego.");
        System.out.println(guiones);
        System.exit(0);
    }

    /**
     * Método que finaliza el juego para un usuario y ofrece la opción de continuar explorando la feria o 
     * salir del programa.
     * @param usuarioEscogido el usuario actual cuyo progreso se debe guardar.
     */
    public static void finDelJuego(User usuarioEscogido) {

        // Al finalizar los juegos se guarda al usuario con sus nuevos datos.
        try {
            usuarioEscogido.guardarUsuario();
        } catch (Exception e) {
            System.out.println(e);
        }                        
        
        // Se marca el fin de el juego.
        System.out.println(guiones);

        // Se le pregunta al usuario si quiere seguir explorando la feria o no.
        System.out.println("¿Deseas seguir explorando la feria?");
        System.out.println("(1) Sí              (0) No");

        // Método que que asegura y verifica que la opción sea válida.
        int opcion = VerificadorDeOpcionesInt.verificarOpcion(0,1);

        // Caso de que se decida salir de programa.
        if(opcion == 0) {
            System.out.println(guiones);
            finalizarPrograma();
        }

        // Se marca el fin de la sección.
        System.out.println(guiones);
    }
}