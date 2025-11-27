package autopremium.util;

import javax.swing.JOptionPane;

/**
 * Clase de utilidades para validar entradas del usuario.
 * Evita errores por tipos de datos incorrectos.
 */
public class Validaciones {

    // Leer un String (No puede ser vacío)
    public static String leerString(String mensaje) {
        String input = "";
        do {
            input = JOptionPane.showInputDialog(mensaje);
            if (input == null) return null; // Si cancela
            if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (input.trim().isEmpty());
        return input;
    }

    // Leer un Entero (Valida que sea número)
    public static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valido = false;
        do {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                if (input == null) return -999; // Código de cancelación
                valor = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        } while (!valido);
        return valor;
    }

    // Leer un Double (Valida precio)
    public static double leerReal(String mensaje) {
        double valor = 0;
        boolean valido = false;
        do {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                if (input == null) return -999;
                valor = Double.parseDouble(input);
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un valor numérico (puede usar punto decimal).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        } while (!valido);
        return valor;
    }
}