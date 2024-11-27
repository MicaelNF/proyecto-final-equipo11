package src.Usuarios.UsuariosExcepciones;

public class SinSaldoSuficienteExcepcion extends Exception {
    public SinSaldoSuficienteExcepcion() {
        super();
    }

    public SinSaldoSuficienteExcepcion(String e) {
        super(e);
    }
}
