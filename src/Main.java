package src;

import java.io.*;
import java.util.Scanner;
import src.Verificador.*;
import src.Verificador.VerificadorExcepciones.*;
import src.Usuarios.*;
import src.TorresHanoi.*;
public class Main {
    public static void main(String[] args) {
        
        boolean buclePrincipal = true;
        boolean bucleSecundario = true; 
        Scanner inTexto = new Scanner(System.in);
        User usuarioEscogido = null;

        String guiones = "";
        for(int i = 0; i < 120; i++) {
            guiones += "-";
        }

        System.out.println("                                    Bienvenido a la Feria de Ciencias                              ");
        System.out.println(guiones);

        while(buclePrincipal) {
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
                //Hace falta verficar que no exista otra cuenta con el mismo nombre.

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
                    break;
                
                case 2:
                    System.out.println("Por favor escoge que sesión quieres reanudar:");
    
                    opcion = 0;
                    ObjectInputStream lector = null;
        
                    for(int i = 0; i < listaDePartidas.length; i++) {
        
                        ruta = "src/Partidas/";
                        ruta += listaDePartidas[i].getName();
                        File archivoParaAbrir = new File(ruta);
        
                        try {
                            lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
                            User usuarioLeido = (User) lector.readObject();
                            System.out.print("\nUsuario " + (i + 1) + ":\n");
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
                        System.out.print(guiones);
                        System.exit(0);
                            break;
                    }
                }
                    break;

                case 2:

                bucleSecundario = true;
                while(bucleSecundario) {
                    System.out.println("¿Que juego deseas jugar? ( para salir mientras estas en el juego teclea (9) )");
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
                        System.out.println("Implementar el código que falta");
                            break;
                        
                        case 2:
                        boolean ganador = true;

                        try {
                            usuarioEscogido.retirarSaldo(15);
                        } catch (Exception e) {
                            System.out.println("Lo siento no tienes saldo suficiente para jugar.");
                            System.out.print(guiones);
                            ganador = false;
                        }

                        JuegoTorresDeHanoi juegoTorreDeHanoi = null;
                        try {
                            juegoTorreDeHanoi = new JuegoTorresDeHanoi(3);                            
                        } catch (Exception e) {
                            System.out.println(e);
                        }


                        while(ganador) {
                            try {
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

                                if(!(juegoTorreDeHanoi.esGanador())) {
                                    inTexto.nextLine();
                                    System.out.println(guiones);
                                }


                            } catch (Exception e) {
                                System.out.println("\n" + e);
                            }
                        }
                        System.out.println("\n" + usuarioEscogido);
                        //Hace falta mostrar los tres primeros jugadores Y la posicion del jugador.

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
                                    System.out.print(guiones);
                                    System.out.println("Gracias por haber usado este programa nos vemos luego.");
                                    System.out.print(guiones);
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
                            break;
                    
                        case 0:
                        System.out.println("Gracias por haber usado este programa nos vemos luego.");
                        System.out.print(guiones);
                        System.exit(0);
                            break;
                    }
                }
                    break;
            
                case 0:
                System.out.println("Gracias por haber usado este programa nos vemos luego.");
                System.out.print(guiones);
                System.exit(0);
                    break;
            }
        }
        inTexto.close();
    }
}
