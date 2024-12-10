/**
 * Clase {@code OrdenamientoDeUsuarios} que sirve como un sistema para ordenar
 * y gestionar una lista de archivos que contienen objetos de tipo {@code User}.
 * Esta clase permite convertir archivos en usuarios, ordenarlos en base a sus 
 * puntuaciones.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 10-12-2024
 * @version 1.1
 */
package src.Ordenamiento;

import java.io.*;
import src.Ordenamiento.OrdenamientoExcepciones.*;
import src.Usuarios.*;

public class OrdenamientoDeUsuarios {
    private File[] listaDeArchivos;

    /**
     * Constructor por parámetros de la clase {@code OrdenamientoDeUsuarios}.
     * Inicializa la lista de archivos con los usuarios proporcionados y verifica
     * que no haya elementos nulos en la lista.
     * 
     * @param listaDeArchivos un arreglo de archivos que contienen objetos serializados de tipo {@code User}.
     * @throws IllegalArgumentException si la lista de archivos es nula.
     * @throws ListaConElementosVaciosExcepcion si algún elemento de la lista es nulo.
     */
    public OrdenamientoDeUsuarios(File[] listaDeArchivos) throws Exception {

        // Se verifica que la lista no sea nula
        if (listaDeArchivos == null) {
            throw new IllegalArgumentException("No puedes ingresar una lista vacía o nula.");
        }

        // Se verifica que el arreglo no contenga ningún elemento vacío.
        for (int i = 0; i < listaDeArchivos.length; i++) {
            if (listaDeArchivos[i] == null) {
                throw new ListaConElementosVaciosExcepcion("No puedes tener una lista con elementos vacíos");
            }
        }

        this.listaDeArchivos = listaDeArchivos;
    }

    /**
     * Convierte un archivo en un objeto de tipo {@code User}.
     * 
     * @param posicion la posición del archivo dentro de la lista de archivos.
     * @return un objeto {@code User} representado por el archivo en la posición indicada.
     * @throws Exception si ocurre un error al leer el archivo.
     */
    private User convertirAUsuario(int posicion) throws Exception {
        String ruta = "src/Partidas/";
        ruta += listaDeArchivos[posicion].getName();
        File archivoParaAbrir = new File(ruta);

        ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivoParaAbrir));
        User objetoGenerico = (User) lector.readObject();
        lector.close();

        return objetoGenerico;
    }

    /**
     * Ordena la lista de archivos que contienen usuarios en función de sus puntuaciones,
     * utilizando el algoritmo de insertion sort.
     * 
     * @throws Exception si ocurre un error al leer alguno de los archivos.
     */
    public void ordenarLista() throws Exception {
        File valorAuxFile = null;
        User valorAux = null;

        for (int i = 0; i < listaDeArchivos.length; i++) {
            valorAuxFile = listaDeArchivos[i];
            valorAux = convertirAUsuario(i);
            int j = i - 1;

            while (j >= 0 && convertirAUsuario(j).compareTo(valorAux) == -1) {
                listaDeArchivos[j + 1] = listaDeArchivos[j];
                j--;
            }
            listaDeArchivos[j + 1] = valorAuxFile;
        }
    }

    /**
     * Regresa a el usuario correspondiente a la posición especificada dentro de la lista.
     * 
     * @param posicion la posición del usuario dentro de la lista.
     * @return el objeto {@code User} en la posición indicada.
     * @throws Exception si ocurre un error al leer el archivo.
     */
    public User obtenerUsuarioEnPosicion(int posicion) throws Exception {
        return convertirAUsuario(posicion);
    }

    /**
     * Regresa la posición de un usuario específico dentro de la lista de archivos.
     * 
     * @param usuarioABuscar el nombre de usuario a buscar.
     * @return la posición del usuario dentro de la lista.
     * @throws UsuarioNoExistenteExcepcion si el usuario no existe en la lista.
     * @throws Exception si ocurre un error al leer algún archivo.
     */
    public int obtenerPosicionDeUsuario(String usuarioABuscar) throws Exception {
        boolean condicion = false;
        int posicion = 0;
        for (int i = 0; i < length(); i++) {
            String usuarioAComparar = convertirAUsuario(i).obtenerId();
            if (usuarioABuscar.equals(usuarioAComparar)) {
                condicion = true;
                posicion = i;
            }
        }
        if (condicion) {
            return posicion;
        } else {
            throw new UsuarioNoExistenteExcepcion("No existe dicho usuario.");
        }
    }

    /**
     * Muestra el podio de los tres primeros lugares en el ranking de usuarios, 
     * así como la posición de un usuario específico dentro de la lista.
     * 
     * @param usuarioEscogido el objeto {@code User} cuya posición en el ranking será mostrada.
     * @throws Exception si ocurre un error al leer los archivos o al procesar
     *         la lista de usuarios.
     */
    public static void mostrarPodio(User usuarioEscogido) {
        
        // Este bloque de código contiene las variables inicializadas que ocuparan los lectores y escritores de documento.
        String ruta = "src/Partidas/";
        File partidas = new File(ruta);
        File[] listaDePartidas = partidas.listFiles();

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

        // Caso de que no haya suficientes usuarios para un top 3 de usuarios.
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
    }

    /**
     * Regresa la longitud de la lista de archivos que contiene usuarios.
     * 
     * @return la longitud de la lista de archivos.
     */
    public int length() {
        return listaDeArchivos.length;
    }
}