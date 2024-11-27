package src;

import java.io.*;
import java.util.Scanner;
import src.Verificador.*;
import src.Verificador.VerificadorExcepciones.*;
import src.Usuarios.*;

public class Main {
    public static void main(String[] args) {

        Scanner inTexto = new Scanner(System.in);

        String guiones = "";
        for(int i = 0; i < 120; i++) {
            guiones += "-";
        }

        System.out.println("                                    Bienvenido a la Feria de Ciencias                              ");
        System.out.println(guiones);

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
            System.out.println("\nSe ha creado con exito el usuario " + "\"" + nombreVerificado + "\":");
            User nuevoUser = new User(nombreVerificado.toString());
            System.out.println(nuevoUser + "\n");
            ObjectOutputStream grabador = null;

            try {
                grabador = new ObjectOutputStream(new FileOutputStream(nuevoUser.obtenerRuta()));
                grabador.writeObject(nuevoUser);
                grabador.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("El usuario se guardo correctamente en: " + nuevoUser.obtenerRutaString());
            System.out.println(guiones);
                break;
            
            case 2:
            String ruta = "src/Partidas/";
            File partidas = new File(ruta);
            File[] listaDePartidas = partidas.listFiles();

            if(listaDePartidas.length == 0) {
                System.out.println("Aun no hay ninguna partida guardada.");
                System.out.println(guiones);
            } else {
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
                User usuarioEscogido = null;
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
            }
                break;
            
            case 0:
            System.out.println("Gracias por haber usado este programa nos vemos luego.");
            System.out.print(guiones);
            System.exit(0);
                break;
        }
        inTexto.close();
    }
}
