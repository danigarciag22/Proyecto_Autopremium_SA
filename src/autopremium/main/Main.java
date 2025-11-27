package autopremium.main;

import autopremium.mundo.Automovil;
import autopremium.mundo.Motocicleta;
import autopremium.servicios.ServicioAutopremium;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        // 1. Instanciamos el servicio (La lógica)
        ServicioAutopremium servicio = new ServicioAutopremium();
        int opcion = 0;

        do {
            try {
                String input = JOptionPane.showInputDialog(null,
                        "AUTOPREMIUM S.A. (Versión POO con Herencia)\n\n" +
                                "1. Registrar Automóvil\n" +
                                "2. Registrar Motocicleta\n" +
                                "3. Ver Reportes\n" +
                                "4. Salir\n\nElija una opción:");

                if (input == null) break;
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        // Captura de datos (Vista)
                        String plA = JOptionPane.showInputDialog("Placa:");
                        String maA = JOptionPane.showInputDialog("Marca:");
                        int moA = Integer.parseInt(JOptionPane.showInputDialog("Modelo (Año):"));
                        double prA = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
                        int puA = Integer.parseInt(JOptionPane.showInputDialog("Puertas:"));

                        // Creación del objeto (Mundo)
                        Automovil auto = new Automovil(plA, maA, moA, prA, puA);

                        // Delegación a la lógica (Servicio)
                        servicio.procesarAutomovil(auto);
                        JOptionPane.showMessageDialog(null, "Automóvil procesado.");
                        break;

                    case 2:
                        String plM = JOptionPane.showInputDialog("Placa:");
                        String maM = JOptionPane.showInputDialog("Marca:");
                        int moM = Integer.parseInt(JOptionPane.showInputDialog("Modelo (Año):"));
                        double prM = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
                        int ccM = Integer.parseInt(JOptionPane.showInputDialog("Cilindraje:"));

                        Motocicleta moto = new Motocicleta(plM, maM, moM, prM, ccM);

                        servicio.procesarMotocicleta(moto);
                        JOptionPane.showMessageDialog(null, "Motocicleta procesada.");
                        break;

                    case 3:
                        // Obtener datos del servicio para mostrar
                        String reporte = "--- ESTADÍSTICAS ---\n" +
                                "Promedio Autos: $" + servicio.calcularPromedioAutos() + "\n" +
                                "Promedio Motos: $" + servicio.calcularPromedioMotos() + "\n\n" +
                                servicio.obtenerReporteMotosPotentes();

                        JOptionPane.showMessageDialog(null, reporte);
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en datos: " + e.getMessage());
            }
        } while (opcion != 4);
    }
}