package autopremium.servicios;

import autopremium.mundo.Automovil;
import autopremium.mundo.Motocicleta;

/**
 * Clase de Lógica de Negocio.
 * Se encarga de los cálculos y acumuladores.
 * NO INTERACTÚA CON EL USUARIO (Eso lo hace el Main).
 */
public class ServicioAutopremium {

    // Atributos para mantener el estado (Persistencia en memoria volátil)
    private double sumaPrecioAutos;
    private int cantAutos;

    private double sumaPrecioMotos;
    private int cantMotos;

    // String acumulador para el reporte de motos potentes
    private String reporteMotosPotentes;

    public ServicioAutopremium() {
        this.sumaPrecioAutos = 0;
        this.cantAutos = 0;
        this.sumaPrecioMotos = 0;
        this.cantMotos = 0;
        this.reporteMotosPotentes = "--- MOTOS > 1000cc ---\n";
    }

    // Método limpio para procesar un automóvil
    public void procesarAutomovil(Automovil auto) {
        this.sumaPrecioAutos += auto.getPrecio();
        this.cantAutos++;
    }

    // Método limpio para procesar una motocicleta
    public void procesarMotocicleta(Motocicleta moto) {
        this.sumaPrecioMotos += moto.getPrecio();
        this.cantMotos++;

        if (moto.getCilindraje() > 1000) {
            this.reporteMotosPotentes += moto.toString() + "\n";
        }
    }

    // Métodos para entregar resultados al Main
    public double calcularPromedioAutos() {
        if (cantAutos == 0) return 0;
        return sumaPrecioAutos / cantAutos;
    }

    public double calcularPromedioMotos() {
        if (cantMotos == 0) return 0;
        return sumaPrecioMotos / cantMotos;
    }

    public String obtenerReporteMotosPotentes() {
        return reporteMotosPotentes;
    }

    public int getCantAutos() { return cantAutos; }
    public int getCantMotos() { return cantMotos; }
}