/**
 * La clase {@code Main} modela una "Feria de Ciencias". Este programa permite a los usuarios crear una cuenta, 
 * reanudar una sesión existente y jugar a diferentes juegos, dependiendo del día.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.2
 */
package src;

import java.io.*;
import java.util.Scanner;
import src.Verificador.*;
import src.Verificador.VerificadorExcepciones.*;
import src.Usuarios.*;
import src.TorresHanoi.*;
import src.Salvado.*;
public class Main {

    /**
     * El método principal que se encarga de llevar a cabo todo la lógica del programa.
     * 
     * @param args Parámetros de línea de comandos. En este caso no se utilizan.
     */
    public static void main(String[] args) {


        // Variables que se usan a lo largo del programa multiples veces.
        boolean bucleSecundario = true; 
        Scanner inTexto = new Scanner(System.in);
        User usuarioEscogido = null;

        // Variable que sirve como punto de separación entre las acciones del menú.
        String guiones = "";
        for(int i = 0; i < 120; i++) {
            guiones += "-";
        }

        //---------------------------------------Se inicia el programa---------------------------------------

        // Se le da la bienvenida al usuario.
        System.out.println("                                    Bienvenido a la Feria de Ciencias                              ");
        System.out.println(guiones);
        
        // Se le pregunta al usuario que es lo que desea hacer.
        System.out.println("Por favor indica si quieres crear una nueva cuenta o si quieres reanudar tu sesión.");
        System.out.println("(1) Para crear una nueva cuenta      (2) Para reanudar una sesión      (0) Salir del programa");
        int opcion = 0;
        
        
        boolean temporalOpciones = true;
        while(temporalOpciones) {
            try {
                opcion = inTexto.nextInt();
                if(opcion >= 0 && opcion <= 2) {
                    temporalOpciones = false;
                } else {
                    System.out.println("\nOpción inválida intentalo de nuevo:");
                    inTexto.nextLine();
                }
            } catch (Exception e) {
                System.out.println("\nSolo puedes introducir números intentalo de nuevo:");
                inTexto.nextLine();
            }
        }
        System.out.println(guiones);
        inTexto.nextLine();

        String ruta = "src/Partidas/";
        File partidas = new File(ruta);
        File[] listaDePartidas = partidas.listFiles();
        ObjectInputStream lector = null;

        if(listaDePartidas.length == 0) {
            System.out.println("Aun no hay ninguna partida guardada. Así que tienes que crear una nueva cuenta.");
            opcion = 1;
            System.out.println(guiones);
        } 
    
        switch (opcion) {
            case 1:
            System.out.println("Por favor introduce tu nombre (ten en cuenta que solo se aceptan números y carácteres no especiales):");
            String nombre = "";            
            VerificadorDeNombres nombreVerificado = null;

            boolean temporalNombreUsuario = true;
            while(temporalNombreUsuario) {
                try {
                    nombre = inTexto.nextLine();
                    nombreVerificado = new VerificadorDeNombres(nombre);
                    temporalNombreUsuario = false;    
                } catch (LargoDelNombreException e) {
                    System.out.println("\n" + e);
                } catch (Exception e) {
                    System.out.println("\n" + e);
                }
            }

            lector = null;
            boolean estarepetido = true;
            for (int i = 0; i < listaDePartidas.length; i++) {
                ruta = "src/Partidas/";
                ruta += listaDePartidas[i].getName();
                File archivoParaAbrir = new File(ruta);

                try {
                    lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
                    User usuarioLeido = (User) lector.readObject();

                    if(usuarioLeido.obtenerId().equals(nombreVerificado.toString())) {
                        estarepetido = false;
                    }

                    lector.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            if(estarepetido) {
                System.out.println("\nSe ha creado con exito el usuario " + "\"" + nombreVerificado + "\":");
                usuarioEscogido = new User(nombreVerificado.toString());
                System.out.println(usuarioEscogido + "\n");
    
                try {
                    usuarioEscogido.guardarUsuario();
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("El usuario se guardo correctamente en: " + usuarioEscogido.obtenerRutaString() + "\n");
                System.out.println("¡¡IMPORTANTE, recuerda que solo cuentas con 100 créditos para jugar durante los dos días!!");

                System.out.println(guiones);

            } else {
                System.out.println("\nEste usuario ya existe, así que iniciaras sesión como " + nombreVerificado + ", tus datos son:\n");
                ruta = "src/Partidas/";
                ruta += nombreVerificado.toString() + ".txt"; 
                File archivoParaAbrir = new File(ruta);
                try {
                    lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
                    usuarioEscogido = (User) lector.readObject();
                    System.out.println(usuarioEscogido + "\n");
                    lector.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println(guiones);
            }
                break;
            
            case 2:
                System.out.println("Por favor escoge que sesión quieres reanudar:");

                opcion = 0;
                lector = null;
    
                for(int i = 0; i < listaDePartidas.length; i++) {
    
                    ruta = "src/Partidas/";
                    ruta += listaDePartidas[i].getName();
                    File archivoParaAbrir = new File(ruta);
    
                    try {
                        lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
                        User usuarioLeido = (User) lector.readObject();
                        System.out.println("\nUsuario " + (i + 1) + ":");
                        System.out.println(usuarioLeido);
                        lector.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
    
                System.out.print("\nEscoge una opción: ");
                temporalOpciones = true;
                usuarioEscogido = null;
                while(temporalOpciones) {
                    try {
                        opcion = inTexto.nextInt();
    
                        if (opcion >= 0 && opcion - 1  < listaDePartidas.length) {
                            temporalOpciones = false;
                        } else {
                            System.out.println("\nLa opcion que esocgiste es invalida, vuelvelo a intentar.");
                        }
    
                    } catch (Exception e) {
                        System.out.println("\nSolo puedes escoger números, intentalo de nuevo:");
                        inTexto.nextLine();
                    }
                }
                
                ruta = "src/Partidas/";
                ruta += listaDePartidas[opcion - 1].getName();
                File archivoParaAbrir = new File(ruta);
                try {
                    lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
                    usuarioEscogido = (User) lector.readObject();
                    lector.close();
                    temporalOpciones = false;
                
                } catch (Exception e) {
                    System.out.println(e);
                }
    
                System.out.println("\nHas escogido el usuario " + usuarioEscogido.obtenerId());
    
                System.out.println(guiones);
                inTexto.nextLine();
                break;
            
            case 0:
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.print(guiones);
            System.exit(0);
                break;
        }
        System.out.println("¿Que día quieres jugar?");
        System.out.println("(1) Día 1          (2) Día 2          (0) Salir del programa");
        
        opcion = 0;
        temporalOpciones = true;
        while (temporalOpciones) {
            try {
                opcion = inTexto.nextInt();
                if (opcion >= 0 && opcion < 3) {
                    temporalOpciones = false;
                } else {
                    System.out.println("\nOpción no válida");  
                    inTexto.nextLine();   
                }
            } catch (Exception e) {
                System.out.println("\nSolo puedes escoger números.");
                inTexto.nextLine();
            }
        }
        inTexto.nextLine();
        System.out.println(guiones);

        switch (opcion) {
            case 1:

            bucleSecundario = true;
            while(bucleSecundario) {
                System.out.println("¿Que juego deseas jugar?");
                System.out.println("(1) Cuadrado mágico          (2) Conecta 4           (0) Salir del programa");
                
                opcion = 0;
                temporalOpciones = true;
                while (temporalOpciones) {
                    try {
                        opcion = inTexto.nextInt();
                        if (opcion >= 0 && opcion < 3) {
                            temporalOpciones = false;
                        } else {
                            System.out.println("\nOpción no válida");  
                            inTexto.nextLine();   
                        }
                    } catch (Exception e) {
                        System.out.println("\nSolo puedes escoger números.");
                        inTexto.nextLine();
                    }
                }
                inTexto.nextLine();
                System.out.println(guiones);

                switch (opcion) {
                    case 1:
                    System.out.println("Implementar el código que falta");
                        break;
                    
                    case 2:
                    System.out.println("Implementar el código que falta");
                        break;
                
                    case 0:
                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                    System.out.println(guiones);
                    System.exit(0);
                        break;
                }
            }
                break;

            case 2:

            bucleSecundario = true;
            while(bucleSecundario) {
            System.out.println("¿Que juego deseas jugar?");
                System.out.println("(1) Salvado          (2) Torres de Hanoi           (0) Salir del programa");
                
                opcion = 0;
                temporalOpciones = true;
                while (temporalOpciones) {
                    try {
                        opcion = inTexto.nextInt();
                        if (opcion >= 0 && opcion < 3) {
                            temporalOpciones = false;
                        } else {
                            System.out.println("\nOpción no válida");  
                            inTexto.nextLine();   
                        }
                    } catch (Exception e) {
                        System.out.println("\nSolo puedes escoger números.");
                        inTexto.nextLine();
                    }
                }
                inTexto.nextLine();
                System.out.println(guiones);

                switch (opcion) {
                    case 1:
                        try {
                            usuarioEscogido.retirarSaldo(10);
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
                    
                    case 2:
                    boolean ganador = true;

                    try {
                        usuarioEscogido.retirarSaldo(15);
                    } catch (Exception e) {
                        System.out.println("Lo siento no tienes saldo suficiente.");
                        ganador = false;
                    }

                    JuegoTorresDeHanoi juegoTorreDeHanoi = new JuegoTorresDeHanoi();                                                        
                    while(ganador) {
                        try {
                            System.out.println("                                                                                                 PRESIONA (9) PARA SALIR");
                            System.out.println("\n" + juegoTorreDeHanoi);
                            System.out.println("\nPor favor indica la torre desde donde quieres mover el disco: ");
                            int torreDePartida = 0;
                            temporalOpciones = true;
                            while (temporalOpciones) {
                                try {
                                    torreDePartida = inTexto.nextInt();
                                    if (torreDePartida >= 1 && torreDePartida <= 3) {
                                        temporalOpciones = false;
                                    } else if(torreDePartida == 9) {
                                        temporalOpciones = false;
                                        ganador = false;
                                        System.out.println("¡¡Has insertado el 9 por lo que al acabar esta ronda se saldrá del juego, se te regresaran tus créditos!!");
                                    } else {
                                        System.out.println("\nOpción no válida");  
                                        inTexto.nextLine();   
                                    }
                                } catch (Exception e) {
                                    System.out.println("\nSolo puedes escoger números.");
                                    inTexto.nextLine();
                                }
                            }
                            inTexto.nextLine();

                            System.out.println("\nPor favor indica la torre a donde quieres mover el disco: ");
                            int torreDeDestino = 0;
                            temporalOpciones = true;
                            while (temporalOpciones) {
                                try {
                                    torreDeDestino = inTexto.nextInt();
                                    if (torreDeDestino >= 1 && torreDeDestino <= 3) {
                                        temporalOpciones = false;
                                    } else if(torreDePartida == 9) {
                                        temporalOpciones = false;
                                        ganador = false;
                                        System.out.println("¡¡Has insertado el 9 por lo que al acabar esta ronda se saldrá del juego, se te regresaran tus créditos!!");
                                    } else {
                                        System.out.println("\nOpción no válida");  
                                        inTexto.nextLine();   
                                    }
                                } catch (Exception e) {
                                    System.out.println("\nSolo puedes escoger números.");
                                    inTexto.nextLine();
                                }
                            }
                            if(ganador == true) {
                                juegoTorreDeHanoi.moverPieza(torreDePartida - 1, torreDeDestino - 1);
                            }


                            if(juegoTorreDeHanoi.esGanador()) {
                                System.out.println("\n" + juegoTorreDeHanoi);
                                int puntosObtenidos = 0;

                                if(juegoTorreDeHanoi.obtenerNumeroDeJugadas() == 63) {
                                    puntosObtenidos = 10;
                                }

                                if(juegoTorreDeHanoi.obtenerNumeroDeJugadas() <= 73) {
                                    puntosObtenidos = 5;
                                }

                                if(juegoTorreDeHanoi.obtenerNumeroDeJugadas() > 73){
                                    puntosObtenidos = 2;
                                }

                                usuarioEscogido.sumarPuntos(puntosObtenidos);
                                System.out.println("\n¡¡FELICIDADES HAS GANADO " + puntosObtenidos + " PUNTOS!!, se mostrara tu nueva información: ");
                                ganador = false;
                            }

                            if(!(juegoTorreDeHanoi.esGanador()) && !(ganador)) {
                                usuarioEscogido.aumentarSaldo(15);
                            }

                            if(!(juegoTorreDeHanoi.esGanador()) && ganador) {
                                inTexto.nextLine();
                                System.out.println(guiones);
                            }


                        } catch (Exception e) {
                            System.out.println("\n" + e);
                        }
                    }
                        break;
                
                    case 0:
                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                    System.out.println(guiones);
                    System.exit(0);
                        break;
                }
                     //Hace falta mostrar los tres primeros jugadores cada que se acabe un juego Y la posicion del jugador actual.
                     
                     try {
                        usuarioEscogido.guardarUsuario();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println(guiones);

                    System.out.println("¿Deseas seguir jugando?");
                    System.out.println("(1) Sí              (0) No");
                    opcion = 0;
                    temporalOpciones = true;
                    while (temporalOpciones) {
                        try {
                            opcion = inTexto.nextInt();
                            if (opcion == 0) {
                                temporalOpciones = false;
                                System.out.println(guiones);
                                System.out.println("Gracias por haber usado este programa nos vemos luego.");
                                System.out.println(guiones);
                                System.exit(0);
                            } else if (opcion == 1) {
                                temporalOpciones = false;
                            }else {
                                System.out.println("\nOpción no válida");  
                                inTexto.nextLine();   
                            }
                        } catch (Exception e) {
                            System.out.println("\nSolo puedes escoger números.");
                            inTexto.nextLine();
                        }
                    }
                    inTexto.nextLine();
                    System.out.println(guiones);
            }
                break;
        
            case 0:
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.println(guiones);
            System.exit(0);
                break;
        }
        inTexto.close();
    }
}
