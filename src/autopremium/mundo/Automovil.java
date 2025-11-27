package autopremium.mundo;

public class Automovil extends Vehiculo {

    private int numeroPuertas;

    public Automovil(String placa, String marca, int modelo, double precio, int numeroPuertas) {
        // Envia los datos comunes al padre (Vehiculo)
        super(placa, marca, modelo, precio);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() { return numeroPuertas; }
    public void setNumeroPuertas(int numeroPuertas) { this.numeroPuertas = numeroPuertas; }

    @Override
    public String toString() {
        return super.toString() + " | Puertas: " + numeroPuertas;
    }
}