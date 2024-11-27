package src.Verificador;
import src.Verificador.VerificadorExcepciones.*;

public class VerificadorDeNombres {
    private String nombreVerificado;

    public VerificadorDeNombres(String nombre) throws Exception {
        
        if (nombre == null) {
            throw new IllegalArgumentException("No puedes pasar como parámetro un objeto núlo");
        }

        nombreVerificado = nombre.toUpperCase();
        this.verificarNombre();
    }

    private void verificarNombre() throws LargoDelNombreException {
        String cadenaVerificada = "";
        String caracteresDisponibles = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for(int i = 0; i < nombreVerificado.length(); i++) {
            if(caracteresDisponibles.indexOf(nombreVerificado.charAt(i)) != -1) {
                cadenaVerificada += nombreVerificado.charAt(i);
            }
        }

        if (cadenaVerificada.length() < 5) {
            throw new LargoDelNombreException("Tu nombre debe de tener al menos 5 carácteres válidos.");
        }

        if (cadenaVerificada.length() > 10) {
            throw new LargoDelNombreException("Tu nombre debe de tener como máximo 10 carácteres válidos");
        }

        nombreVerificado = cadenaVerificada;
    }

    @Override
    public String toString() {
        return nombreVerificado;
    }

    public static void main(String[] args) {
        try {
            VerificadorDeNombres nombre = new VerificadorDeNombres("Micael1029");
            System.out.println(nombre);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
