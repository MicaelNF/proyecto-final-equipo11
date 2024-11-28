/**
 * Clase {@code User} que representa a un usuario dentro del sistema.
 * Un usuario tiene un nombre único, un saldo, y una puntuación.
 * Además, se puede serializar para guardarse en un archivo.
 * 
 * @author Nolasco Flores Micael
 * @author Romualdo Valera Seyin Xuxek
 * @date 27-11-2024
 * @version 1.3
 */
package src.Usuarios;

import java.io.*;
import java.util.Comparator;
import src.Usuarios.UsuariosExcepciones.*;

public class User implements Serializable, Comparable<User>, Comparator<User>{

    private int saldo;
    private String id;
    private int puntos;

    /**
     * Constructor de la clase {@code User}.
     * Inicializa un usuario con un saldo inicial de 100 y el nombre proporcionado.
     * 
     * @param id El nombre del usuario.
     * @throws IllegalArgumentException si el nombre es nulo.
     */
    public User(String id) {

        // Caso de que el nombre / id sea un objeto nulo.
        if (id == null) {
            throw new IllegalArgumentException("No puedes crear un usuario nulo.");
        }

        // Si no pasa lo anterior se inicializa con el nombre que se le paso y con un saldo de 100.
        this.id = id;
        saldo = 100;
    }

    /**
     * Regresa el saldo actual del usuario.
     * 
     * @return el saldo actual del usuario.
     */
    public int obtenerSaldo() {
        return saldo;
    }

    /**
     * Disminuye el saldo del usuario en la cantidad que se le pasa como parámetro.
     * 
     * @param saldoARetirar La cantidad de saldo a retirar.
     * @throws SinSaldoSuficienteExcepcion si el saldo actual es menor que la cantidad a retirar.
     */
    public void retirarSaldo(int saldoARetirar) throws SinSaldoSuficienteExcepcion {

        // Caso de que se quiera retirar más dinero del que se posee.
        if (saldo < saldoARetirar) {
            throw new SinSaldoSuficienteExcepcion("No cuentas con el saldo suficiente.");
        }

        // Se retira el dinero si no pasa lo anterior.
        saldo -= saldoARetirar;
    }

    /**
     * Aumenta el saldo del usuario en la cantidad especificada.
     * 
     * @param saldoASumar La cantidad de saldo a añadir.
     */
    public void aumentarSaldo(int saldoASumar) {
        saldo += saldoASumar;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return el nombre del usuario.
     */
    public String obtenerId() {
        return id;
    }

    /**
     * Obtiene los puntos actuales del usuario.
     * 
     * @return la puntuación actual del usuario.
     */
    public int obtenerPuntos() {
        return puntos;
    }

    /**
     * Suma la cantidad de puntos especificada a la puntuación actual del usuario.
     * 
     * @param puntosAñadir La cantidad de puntos a añadir.
     */
    public void sumarPuntos(int puntosAñadir) {
        puntos += puntosAñadir;
    }

    /**
     * Obtiene la ruta del archivo donde se guardará el usuario.
     * 
     * @return un objeto {@code File} con la ruta del archivo.
     */
    public File obtenerRuta() {

        // Ya se sabe donde esta guardado.
        String ruta = "src/Partidas/";

        // Se añade a la ruta principal y el nombre y formato del archivo.
        ruta += id + ".txt";
        
        // Se genera un objeto tipo File.
        return new File(ruta);
    }

    /**
     * Obtiene la ruta del archivo donde se guardará el usuario en formato de cadena.
     * 
     * @return la ruta del archivo en formato {@code String}.
     */
    public String obtenerRutaString() {

        // Ya se sabe donde esta guardado.
        String ruta = "src/Partidas/";

        // Se añade a la ruta principal y el nombre y formato del archivo.     
        ruta += id + ".txt";

        // Se regresa la ruta en una cadena.
        return ruta;
    }

    /**
     * Guarda a el usuario en un archivo.
     * El archivo se guarda en la ruta especificada por el método {@code obtenerRuta()}.
     * 
     * @throws Exception si ocurre un error al guardar el archivo.
     */
    public void guardarUsuario() throws Exception {
        
        // Se crea un objeto que va a guardar el archivo en la ruta establecida.
        ObjectOutputStream grabador = new ObjectOutputStream(new FileOutputStream(this.obtenerRuta()));

        // Se guarda el archivo.
        grabador.writeObject(this);
        grabador.close();
    }

    /**
     * Compara dos {@code User} de acuerdo a su puntuación.
     * Devuelve un valor negativo si el usuario que se esta comparando es menor, positivo si la puntuación es mayor 
     * y 0 si ambos son iguales respecto a la puntuación.
     * 
     * @param usuarioComparado el primer usuario a comparar.
     * @param usuarioAComparar el segundo usuario a comparar.
     * @return un valor negativo si es menor, positivo es mayor y 0 si su puntuación es la misma.
     */
    @Override
    public int compare(User usuarioComparado, User usuarioAComparar) {
        return Integer.compare(usuarioComparado.obtenerPuntos(), usuarioAComparar.obtenerPuntos());
    }

    /**
     * Compara al usuario con el que se manda a llamr al método con otro usuario de acuerdo a su puntuación.
     * 
     * @param usuarioAComparar el usuario con el que se va a comparar.
     * @return un valor negativo si es menor, positivo es mayor y 0 si su puntuación es la misma.
     */
    @Override
    public int compareTo(User usuarioAComparar) {
        return compare(this, usuarioAComparar);
    }

    /**
     * Devuelve una representación en cadena del usuario.
     * Incluye el nombre de usuario, el saldo actual y la puntuación.
     * 
     * @return una cadena con la información del usuario.
     */
    @Override
    public String toString() {
        String cadena = "";
        cadena += "\t*Nombre de usuario: " + id + "\n" +
                "\t*Saldo actual: " + saldo + "\n" +
                "\t*Puntuación: " + puntos;
        return cadena;
    }
}
