package src.Usuarios;
import java.io.*;
import src.Usuarios.UsuariosExcepciones.*;

public class User implements Serializable {

    private int saldo;
    private String id;
    private int puntos;

    public User(String id) {
        
        // Se verifica que no se pueda pasar como parámetro un id nulo.
        if (id == null) {
            throw new IllegalArgumentException("No puedes crear un usuario nulo.");
        }

        // Se inicializa el usuario con el id que se paso como parámetro y con un saldo inicial de 100.
        this.id = id;
        saldo = 100;
    }
    
    // Método que se encarga de regresar el saldo actual.
    public int obtenerSaldo() {
        return saldo;
    }

    // Método que disminuye el saldo de acuerdo al parámetro.
    public void retirarSaldo(int saldoARetirar) throws SinSaldoSuficienteExcepcion {
        if (saldo < saldoARetirar) {
            throw new SinSaldoSuficienteExcepcion(" No cuentas con el saldo suficiente.");
        }
        saldo = saldo - saldoARetirar;
    }

    public void aumentarSaldo(int saldoASumar) {
        saldo = saldo + saldoASumar;
    }

    // Metodo que regresa el ID.
    public String obtenerId() {
        return id;
    }

    // Metodo que regresa los puntos actuales.
    public int obtenerPuntos() {
        return puntos;
    }

    // Metodo que suma puntos a los actuales.
    public void sumarPuntos(int puntosAñadir) {
        puntos = puntos + puntosAñadir;
    }
    // Método que crea la ruta para guardar el achivo.
    public File obtenerRuta() {
        String ruta = "src/Partidas/";
        ruta += id + ".txt";
        
        return new File(ruta);
    }

    public String obtenerRutaString() {
        String ruta = "src/Partidas/";
        ruta += id + ".txt";
        
        return ruta;
    }

    public void guardarUsuario() throws Exception {
        ObjectOutputStream grabador = new ObjectOutputStream(new FileOutputStream(this.obtenerRuta()));
        grabador.writeObject(this);
        grabador.close();
    }

    @Override
    public String toString(){
        String cadena = "";
        cadena += "\t*Nombre de usuario: " + id + "\n" +
                "\t*Saldo actual: " + saldo + "\n" +
                "\t*Puntuación: " + puntos;
        
        return cadena;
    }

    public static void main(String[] args) {
        User usuario = new User("MICAEL");
        System.out.println(usuario);
    }
}
