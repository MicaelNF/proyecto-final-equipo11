/**
 * Clase {@code JuegoTorresDeHanoi} que implementa la lógica del juego de las Torres de Hanoi.
 * Permite al usuario mover discos entre tres torres con el objetivo de completar el juego
 * en el menor número de movimientos posible.
 * 
 * El juego puede mover discos entre torres, y al mismo tiempo asegurar que los movimientos sean 
 * válidos, calcular los puntos obtenidos según el número de movimientos realizados, permitir al 
 * usuario salir del juego en cualquier momento, sin penalización de créditos y verificar si el usuario 
 * ha completado el juego exitosamente, para así sumarle sus puntos.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.0
 */
package src.TorresHanoi;

import src.Usuarios.User;
import src.Verificador.VerificadorDeOpcionesInt;

public class JuegoTorresDeHanoi {

    public static void jugarTorresDeHanoi(User usuarioEscogido) {

        // Delimita el fin de cada jugada.
        StringBuilder guiones = new StringBuilder();
        for(int i = 0; i < 120; i++) {
            guiones.append("-");
        }

        // Boolean que mantiene el juego encendido.
        boolean continuar = true;

        // Se inicializa una representación de este juego.
        TorresDeHanoi juegoTorreDeHanoi = new TorresDeHanoi();

        // Empieza el bucle principal de este juego.
        while(continuar) {
            try {
                
                // Se muestra el estado actual del juego. Además de que le recuerda al usuario que puede salir del juego.
                System.out.println("                                                                                                 PRESIONA (9) PARA SALIR");
                System.out.println("\n" + juegoTorreDeHanoi);

                // Se solicita al usuario introducir el número de torre desde donde va a mover el disco.
                System.out.println("\nPor favor indica la torre desde donde quieres mover el disco: ");

                // Método que que asegura y verifica que la opción sea válida.
                int torreDePartida = VerificadorDeOpcionesInt.verificarOpcion(1, 3, 9);
                
                // Caso de que el usuario escoga salir del juego.
                if(torreDePartida == 9) {
                    continuar = false;

                    // Se le notifica lo que ha hecho y que saldra del juego sin penalizaciones.
                    System.out.println(guiones);
                    System.out.println("¡¡Has insertado el 9 por lo que se saldrá del juego, se te regresaran tus créditos!!");
                }

                // Se verifica que el bucle siga activo lo que quiere decir que el usuario no haya decidido salir del juego.
                int torreDeDestino = 0;
                if (continuar) {
                    
                    // Se le solicita al usuario la torre a la que quiere mover su disco.
                    System.out.println("\nPor favor indica la torre a donde quieres mover el disco: ");

                    // Método que que asegura y verifica que la opción sea válida.  
                    torreDeDestino = VerificadorDeOpcionesInt.verificarOpcion(1, 3, 9);
                
                    // Caso de que el usuario escoga salir del juego.
                    if(torreDeDestino == 9) {
                        continuar = false;

                        // Se le notifica lo que ha hecho y que saldra del juego sin penalizaciones.
                        System.out.println(guiones);
                        System.out.println("¡¡Has insertado el 9 por lo que se saldrá del juego, se te regresaran tus créditos!!");
                    }     
                }
                
                // Si el bucle sigue activo se mueve la pieza.
                if(continuar == true) {
                    juegoTorreDeHanoi.moverPieza(torreDePartida - 1, torreDeDestino - 1);
                }                            

                // Caso de que se haya completado el juego.
                if(juegoTorreDeHanoi.esGanador()) {
                    
                    // Se imprime el juegocontinuar 
                    System.out.println("\n" + juegoTorreDeHanoi);

                    // Se inicializa la variable de los puntos.
                    int puntosObtenidos = 0;

                    // Se calculan los puntos obtenidos.
                    if(juegoTorreDeHanoi.obtenerNumeroDeJugadas() == 63) {
                        puntosObtenidos = 10;
                    }

                    if(juegoTorreDeHanoi.obtenerNumeroDeJugadas() <= 73) {
                        puntosObtenidos = 5;
                    }

                    if(juegoTorreDeHanoi.obtenerNumeroDeJugadas() > 73){
                        puntosObtenidos = 2;
                    }

                    // Se suman los puntos obtenidos al usuario.
                    usuarioEscogido.sumarPuntos(puntosObtenidos);

                    // Se le notifica al usuario en cuantos movimientos completo el juego y cuantos puntos gano.
                    System.out.println("\nHas completado el juego en " + juegoTorreDeHanoi.obtenerNumeroDeJugadas() + " jugadas");
                    System.out.println("¡¡FELICIDADES HAS GANADO " + puntosObtenidos + " PUNTOS!!");

                    // Se apaga el bucle del juego.
                    continuar = false;
                }

                // Caso de que el jugador haya decidido salir a mitad del juego.
                if(!(juegoTorreDeHanoi.esGanador()) && !(continuar)) {
                    
                    // Se le regresan sus créditos
                    usuarioEscogido.aumentarSaldo(15);
                }

                // Marca el fin de cada jugada, mientras no sea continuara o no se haya salido del juego.
                if(!(juegoTorreDeHanoi.esGanador()) && continuar) {
                    System.out.println(guiones);
                }
            
            } catch (Exception e) {
                System.out.println("\n" + guiones + "\n" + e.getMessage() + " Inténtalo de nuevo:");
            }                       
        }
    }
}
