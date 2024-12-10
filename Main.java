/**
 * La clase {@code Main} modela una "Feria de Ciencias". Este programa permite a los usuarios crear una cuenta, 
 * reanudar una sesión existente y jugar a diferentes juegos, dependiendo del día.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 2.1
 */
import java.io.*;
import java.util.Scanner;
import src.Verificador.*;
import src.Usuarios.*;
import src.TorresHanoi.*;
import src.Salvado.*;
import src.Salvado.SalvadoExcepciones.*;
import src.Conecta4.JuegoConecta_4;
import src.Ordenamiento.*;
public class Main {

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

        // Este bloque de código contiene las variables inicializadas que ocuparan los lectores y escritores de documento.
        String ruta = "src/Partidas/";
        File partidas = new File(ruta);
        File[] listaDePartidas = partidas.listFiles();

        // Variable que sirve como punto de separación entre las acciones del menú.
        StringBuilder guiones = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guiones.append("-");
        }

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
                System.out.println("(1) Cuadrado mágico          (2) Conecta 4           (0) Salir del programa");
                
                // Método que que asegura y verifica que la opción sea válida.
                opcion = VerificadorDeOpcionesInt.verificarOpcion(0, 2);

                // Se marca el fin de esta parte del menú.
                System.out.println(guiones);

                // Este switch contiene los juegos que se puden jugar en el día uno.
                switch (opcion) {

                    // Caso de que el usuario haya escogido jugar "Cuadrado mágico".
                    case 1:
                    System.out.println("Implementar el código que falta");
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
                
                    // Caso de que el usuario quiera salir del programa.    
                    case 0:
                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                    System.out.println(guiones);
                    System.exit(0);
                        break;
                }

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
                opcion = VerificadorDeOpcionesInt.verificarOpcion(0,1);

                // Caso de que se decida salir de programa.
                if(opcion == 0) {
                    // Se le da la despedida y se apaga el programa 
                    System.out.println(guiones);
                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                    System.out.println(guiones);
                    System.exit(0);
                }

                // Se marca el fin de esta sección del menú.
                System.out.println(guiones);
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
                        try {
                            usuarioEscogido.retirarSaldo(15);
                        } catch (Exception e) {
                            System.out.println("Lo siento no tienes saldo suficiente.");
                        }
                        
                        JugarSalvado nuevoJuego = new JugarSalvado();
                        int puntosGanados = 0;
                        System.out.println("INSTRUCCIONES: ");
                        System.out.println("En un circulo formado por sillas hay 100 personas.");
                        System.out.println("Dependiendo el número de saltos que de, se irán eliminando hasta que quede un ganador.");
                        System.out.println("Tu tarea es adivinar que posición quedará hasta el final.");
                        System.out.println("----------------------------------------------------------------------------------");
                        try {
                            System.out.println("El número de saltos de esta ronda es: " + nuevoJuego.obtenerSaltos());
                            System.out.println("Ingresa el número de jugador que crees que sobrevivirá hasta el final, solo números entre 1 y 100: ");
                            int prediccion = inTexto.nextInt();
                            int sobrevivio = nuevoJuego.jugar();
                            if (prediccion== sobrevivio) {
                                System.out.println("¡Has Adivinado!. El jugador que sobrevivio hasta el final fue: " + sobrevivio);
                                puntosGanados = 12;
                            }
                            else{
                                System.out.println("¡Has fallado!. El jugador que sobrevivio al final fue: " + sobrevivio);
                                puntosGanados = 2;
                            }
                            usuarioEscogido.sumarPuntos(puntosGanados);
                            System.out.println("\n¡¡HAS GANADO " + puntosGanados + " PUNTOS!!, se mostrara tu nueva información: ");

                        } catch (EstadoInvalidoDelJuegoExcepcion e) {
                            System.out.println(e);
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("El indice está fuera del rango válido");
                        } catch (Exception e){
                            System.out.println("Solo puedes escribir números.");
                            inTexto.nextLine();
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

                    // Se inicializan las variables que se van a usar para evitar problemas.
                    int i = 0;
                    OrdenamientoDeUsuarios listaOrdenada = null;
                    try {
                        // Se ordena la lista de usuarios.
                        listaOrdenada = new OrdenamientoDeUsuarios(listaDePartidas);
                        listaOrdenada.ordenarLista();

                        // Se imprimen los tres primeros usuarios.
                        System.out.println("\n------Podio de los tres primeros lugares------");
                        for (i = 0; i < 3; i++) {
                            System.out.println("\nTop " + (i + 1) + ":");
                            System.out.println(listaOrdenada.obtenerUsuarioEnPosicion(i));
                        }
                    } catch (Exception e) {
                        System.out.println("Aún no hay suficientes usuarios existentes para un top " + (i + 1));
                    }

                    // Se imprime en que posición se encuentra la sesión actual.
                    try {
                        System.out.println("\n------Posición de la sesión actual------");
                        System.out.println("Top " + (listaOrdenada.obtenerPosicionDeUsuario(usuarioEscogido.obtenerId()) + 1) + ":");
                        System.out.println(usuarioEscogido); 
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                        break;
                
                    // Caso de que quieran salir del programa    
                    case 0:
                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                    System.out.println(guiones);
                    System.exit(0);
                        break;
                }                     
                
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
                opcion = VerificadorDeOpcionesInt.verificarOpcion(0,1);

                // Caso de que se decida salir de programa.
                if(opcion == 0) {
                    // Se le da la despedida y se apaga el programa 
                    System.out.println(guiones);
                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                    System.out.println(guiones);
                    System.exit(0);
                }

                // Se marca el fin de esta sección del menú.
                System.out.println(guiones);
            }
                break;
            
            // Caso de que quiera salir del programa.    
            case 0:
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.println(guiones);
            System.exit(0);
                break;
        }

        // Se cierra el Scanner.
        inTexto.close();
    }
}
