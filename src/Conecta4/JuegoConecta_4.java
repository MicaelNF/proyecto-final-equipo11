/**
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 1.0
 */
package src.Conecta4;

import src.Usuarios.*;
import src.Verificador.VerificadorDeOpcionesInt;

public class JuegoConecta_4 {
    private static Conecta_4 juegoConecta4;

    public static void jugarConecta4(User usuarioEscogido) {
        juegoConecta4 = new Conecta_4();
        
        // Delimita el fin de cada jugada.
        StringBuilder guiones = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guiones.append("-");
        }

        // Se le da al usuario la opción de jugar solo o con alguien más.
        System.out.println("Por favor escoge si quieres jugar contra la máquina o en contra de otro jugador:");
        System.out.println("(1) Para jugar contra otro jugador        (2) Para poder jugar en contra de la máquina        (0) Salir del programa");
        int opcion = VerificadorDeOpcionesInt.verificarOpcion(0, 2);
        System.out.println(guiones);

        // Switch que contiene las opciones de jugar solo o con alguién más, además de salir del programa.
        switch (opcion) {
            case 1:

            // Variables que sirven para evitar problemas en la selección de otro jugador.
            boolean bucleTemporal = true;
            User usuarioEscogidoSecundario = null;

            // Bucle que asegura que los usuarios son diferentes.
            while (bucleTemporal) {
                usuarioEscogidoSecundario = ManejoDeUsuarios.menuUsuarios();

                // Si el usuario es diferente del escogido se apaga el bucle.
                if(!(usuarioEscogido.obtenerId().equals(usuarioEscogidoSecundario.obtenerId()))) {
                    bucleTemporal = false;
                
                // Si son iguales se le informa al usuario que no puede hacer eso.
                } else {
                    System.out.println("¡¡Debes de escoger o crear un usuario con un nombre diferente a tu sesión actual: \"" + usuarioEscogido.obtenerId() + "\" !!");
                    System.out.println(guiones);
                }
            }
            int variableParaLaFicha = (int) Math.random() * 2;

            char fichaJugador1 = (variableParaLaFicha == 1) ? 'X' : 'O';
            char fichaJugador2 = (fichaJugador1 == 'X') ? 'O' : 'X';

            System.out.println("El jugador 1 \"" + usuarioEscogido.obtenerId() + "\" jugara con las fichas " + fichaJugador1);
            System.out.println("El jugador 2 \"" + usuarioEscogidoSecundario.obtenerId() + "\" jugara con las fichas " + fichaJugador2);
            System.out.println(guiones);

            boolean continuarElJuego = true;

            while (continuarElJuego) {

                verificarJugada(fichaJugador1, usuarioEscogido);
                // Se verifica si ha habido una jugada ganadora por parte del jugador 1.
                if(!(juegoConecta4.esGanador())) {
                    verificarJugada(fichaJugador2, usuarioEscogidoSecundario);
                    if (juegoConecta4.esGanador()) {
                        System.out.println(juegoConecta4);
                        System.out.println("¡¡Perdiste el jugador " + usuarioEscogidoSecundario.obtenerId() + "  ha ganado. Por lo tanto has ganado 2 PUNTOS!!");
                        usuarioEscogido.sumarPuntos(2);
                        continuarElJuego = false; 
                    }
                
                // Caso de que el jugador 1 haya ganado
                } else {
                    System.out.println(juegoConecta4);
                    System.out.println("Felicidades " + usuarioEscogido.obtenerId() + " has ganado. Por lo tanto has ganado 10 PUNTOS!!");
                    usuarioEscogido.sumarPuntos(10);
                    continuarElJuego = false;
                }
            }
                break;

            case 2:
                
                break;

            case 0:
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.println(guiones);
            System.exit(0);
                break;
        }
    }

    public static void verificarJugada(char fichaEscogida, User usuarioEscogido) {
        
        // Delimita el fin de cada jugada.
        StringBuilder guiones = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guiones.append("-");
        }

        int elecciónDeColumna = 0;

        System.out.println("                                                                                                 PRESIONA (9) PARA SALIR\n");
        System.out.println("Turno del jugador \"" + usuarioEscogido.obtenerId() + "\"");
        System.out.println("\n" + juegoConecta4);
        System.out.print("Por favor indica la columna donde quieras colocar una ficha: ");
        elecciónDeColumna = VerificadorDeOpcionesInt.verificarOpcion(1, 7, 9);
        
        // Caso de que el usuario haya decidido salir del juego.
        if(elecciónDeColumna == 9) {
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.println(guiones);
            System.exit(0);
        }
        
        try {
            juegoConecta4.insertarJugada(fichaEscogida, elecciónDeColumna - 1);
        } catch (Exception e) {
            System.out.println("\n" + e);
            verificarJugada(fichaEscogida, usuarioEscogido);
        }

        System.out.println(guiones);
    }
}
