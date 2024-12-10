/**
 * Clase que implementa el juego Conecta 4, donde los usuarios pueden jugar
 * en contra de otro jugador o contra la máquina. Además, maneja la verificación
 * de jugadas, el control del turno de los jugadores, y las puntuaciones.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 1.2
 */
package src.Conecta4;

import src.Usuarios.*;
import src.Verificador.VerificadorDeOpcionesInt;

public class JuegoConecta_4 {
    
    // Variables que ocupan varios métodos.
    private static Conecta_4 juegoConecta4;
    private static boolean continuar;

    /**
     * Método que inicia al juego. Permite al usuario elegir si jugar contra otro jugador 
     * o contra la máquina. También maneja la lógica de turnos y verifica las condiciones
     * de victoria o empate.
     * 
     * @param usuarioEscogido El usuario que ha iniciado el juego.
     */
    public static void jugarConecta4(User usuarioEscogido) {

        // Se inicializa la lógica del juego.
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

        boolean continuarElJuego = true;

        // Fichas que usaran los usuarios o la máquina. Se determinan de forma al azar.
        int variableParaLaFicha = (int) Math.random() * 2;
        char fichaJugador1 = (variableParaLaFicha == 1) ? 'X' : 'O';
        char fichaJugador2 = (fichaJugador1 == 'X') ? 'O' : 'X';

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

            // Se le notifica a ambos usuarios cuales son las fichas que les tocan.
            System.out.println("El jugador 1 \"" + usuarioEscogido.obtenerId() + "\" jugara con las fichas " + fichaJugador1);
            System.out.println("El jugador 2 \"" + usuarioEscogidoSecundario.obtenerId() + "\" jugara con las fichas " + fichaJugador2);
            System.out.println(guiones);

            // Bucle que mantiene el juego activo
            continuarElJuego = true;
            while (continuarElJuego) {

                // Método que se encarga de verificar que la jugada sea válida.
                verificarJugada(fichaJugador1, usuarioEscogido);

                // Caso de que no se haya decidido salir del juego.
                if (continuar) {

                    // Si no ha habido jugadora ganadora se le da paso al jugador 2
                    if(juegoConecta4.esGanador() == -1) {

                        // Método que se encarga de verificar que la jugada sea válida.
                        verificarJugada(fichaJugador2, usuarioEscogidoSecundario);

                        // Se verifica si el jugador 2 ha ganado.
                        if (juegoConecta4.esGanador() == 1) {
                            System.out.println(juegoConecta4);
                            System.out.println("¡¡Perdiste el jugador " + usuarioEscogidoSecundario.obtenerId() + "  ha ganado. Por lo tanto has ganado 2 PUNTOS!!");
                            usuarioEscogido.sumarPuntos(2);
                            continuarElJuego = false; 
                        }
                    } 

                    // Caso de que el jugador 1 haya ganado.
                    if (juegoConecta4.esGanador() == 1){
                        System.out.println(juegoConecta4);
                        System.out.println("¡¡Felicidades " + usuarioEscogido.obtenerId() + " has ganado. Por lo tanto has ganado 10 PUNTOS!!");
                        usuarioEscogido.sumarPuntos(10);
                        continuarElJuego = false;
                    } 
                    
                    // Caso de que haya un empate.
                    if (juegoConecta4.esGanador() == 0) {
                        System.out.println(juegoConecta4);
                        System.out.println("¡¡Ha habido un empate. Por lo tanto has ganado 5 PUNTOS!!");
                        usuarioEscogido.sumarPuntos(5);
                        continuarElJuego = false;     
                    }
                
                // Se apaga el bucle que mantiene al juego activo si es que se ha decidido salir del juego.
                } else {
                    continuarElJuego = false;
                }
            }
                break;

            case 2:

            // Se le notifica al usuarios cual es la ficha que le toca.
            System.out.println("El jugador \"" + usuarioEscogido.obtenerId() + "\" jugara con las fichas " + fichaJugador1);
            System.out.println("La computadora jugara con las fichas " + fichaJugador2);
            System.out.println(guiones);

            // Bucle que mantiene el juego activo
            continuarElJuego = true;
            while (continuarElJuego) {
                
                // Método que se encarga de verificar que la jugada sea válida.
                verificarJugada(fichaJugador1, usuarioEscogido);

                // Caso de que no se haya decidido salir del juego.
                if(continuar) {

                    // Caso de que aun no haya un ganador.
                    if(juegoConecta4.esGanador() == -1) {

                        // Método que se encarga de verificar que la jugada sea válida, por parte de la computadora.
                        verificarJugada(fichaJugador2, "Computadora");
    
                        // Se verifica si es que la maquina gano.
                        if (juegoConecta4.esGanador() == 1) {
                            System.out.println(juegoConecta4);
                            System.out.println("¡¡Perdiste la computadora ha ganado. Por lo tanto has ganado 2 PUNTOS!!");
                            usuarioEscogido.sumarPuntos(2);
                            continuarElJuego = false; 
                        }
                    } 
                    
                    // Caso de que el jugador haya ganado.
                    if (juegoConecta4.esGanador() == 1) {
                        System.out.println(juegoConecta4);
                        System.out.println("Felicidades " + usuarioEscogido.obtenerId() + " has ganado. Por lo tanto has ganado 10 PUNTOS!!");
                        usuarioEscogido.sumarPuntos(10);
                        continuarElJuego = false;
                    } 
                    
                    // Caso de que haya habido un empate.
                    if (juegoConecta4.esGanador() == 0) {
                        System.out.println(juegoConecta4);
                        System.out.println("¡¡Ha habido un empate. Por lo tanto has ganado 5 PUNTOS!!");
                        usuarioEscogido.sumarPuntos(5);
                        continuarElJuego = false;     
                    }
                
                // Caso de que se haya decidido salir del juego.
                } else {
                    continuarElJuego = false;
                }
            }
                break;

            // Caso de que se quiera salir del programa.
            case 0:
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.println(guiones);
            System.exit(0);
                break;
        }
    }

    /**
     * Verifica si la jugada del jugador es válida y la ejecuta.
     * 
     * @param fichaEscogida La ficha que el jugador este usando.
     * @param usuarioEscogido El usuario que está realizando la jugada.
     */
    private static void verificarJugada(char fichaEscogida, User usuarioEscogido) {

        // Delimita el fin de cada jugada.
        StringBuilder guiones = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guiones.append("-");
        }        

        int elecciónDeColumna = 0;

        // Se le despliega al usuario una representación del juego actual y se le dan indicaciones de que hacer.
        System.out.println("                                                                                                 PRESIONA (9) PARA SALIR\n");
        System.out.println("Turno del jugador \"" + usuarioEscogido.obtenerId() + "\"");
        System.out.println("\n" + juegoConecta4);
        System.out.print("Por favor indica la columna donde quieras colocar una ficha: ");
        elecciónDeColumna = VerificadorDeOpcionesInt.verificarOpcion(1, 7, 9);
        
        // Caso de que el usuario haya decidido salir del juego.
        continuar = true;
        if(elecciónDeColumna == 9) {
            continuar = false;
            
            // Se le notifica lo que ha hecho y que saldra del juego sin penalizaciones.
            System.out.println(guiones);
            System.out.println("¡¡Has insertado el 9 por lo que se saldrá del juego, se te regresaran tus créditos!!");
            usuarioEscogido.aumentarSaldo(15);
            }     

        // Caso de que no haya decidido salir del juego.
        if(continuar) {
            // Se intenta insertar la ficha en la columna indicada.
            try {
                juegoConecta4.insertarJugada(fichaEscogida, elecciónDeColumna - 1);
                System.out.println(guiones);
            
            // Se indica la excepción y de forma recursiva se vuelve a ejecutar el método.
            } catch (Exception e) {
                System.out.println("\n" + guiones + "\n" + e.getMessage() + " Inténtalo de nuevo:");
                verificarJugada(fichaEscogida, usuarioEscogido);
            }
        }
    }

    /**
     * Verifica y realiza la jugada de la computadora, eligiendo una columna al azar.
     * 
     * @param fichaEscogida La ficha que la computadora está utilizando.
     * @param maquina El nombre de la computadora.
     */
    private static void verificarJugada(char fichaEscogida, String maquina) {

        // Se verifica que no se haya decidido salir del juego.
        if(continuar) {

            // Se selecciona una columna al azar
            int elecciónDeColumna = (int) (Math.random() * 7);

            // Se inserta en la posición dada.
            try {
                juegoConecta4.insertarJugada(fichaEscogida, elecciónDeColumna);
            
            // Si no se puede insertar en dicha columna se vuelve a intentar llamadno al mismo método.
            } catch (Exception e) {
                verificarJugada(fichaEscogida, maquina);
            }
        }
    }
}