/**
 * Clase {@code VerificadorDeCuentaUnica} que verifica si un nombre de usuario ya existe 
 * en una lista de archivos de usuarios guardados.
 * Se utiliza para garantizar que los nombres de usuario sean únicos dentro del sistema.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 02-12-2024
 * @version 1.0
 */
package src.Verificador;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import src.Usuarios.User;

public class VerificadorDeCuentaUnica {

    /**
     * Método estático que verifica si un nombre de usuario es único dentro de una lista de archivos de usuarios.
     * 
     * @param idDeLaCuenta El nombre del usuario que se desea verificar.
     * @param listaDePartidas Un arreglo de archivos que contiene las partidas guardadas de los usuarios.
     * @return {@code true} si el nombre de usuario es único, {@code false} si ya existe.
     */
    public static boolean esUnica(String idDeLaCuenta, File[] listaDePartidas) {
        
        ObjectInputStream lector = null;
        boolean esUnica = true;
        String ruta = "";

        // Ciclo que genera la ruta para todos los archivos que están guardados.
        for (int i = 0; i < listaDePartidas.length; i++) {
            ruta = "src/Partidas/";
            ruta += listaDePartidas[i].getName();
            File archivoParaAbrir = new File(ruta);

            // Se compara el usuario que se pasó como parámetro con cada usuario de la lista.
            try {
                lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
                User usuarioLeido = (User) lector.readObject();

                // Caso en que ya exista un usuario con ese nombre.
                if (usuarioLeido.obtenerId().equals(idDeLaCuenta)) {
                    esUnica = false;
                }

                lector.close();
            // Caso de que surja alguna excepción.
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return esUnica;        
    }
}
