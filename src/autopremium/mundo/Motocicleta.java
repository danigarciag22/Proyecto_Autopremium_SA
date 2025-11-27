package autopremium.mundo;

public class Motocicleta extends Vehiculo {
    
    private int cilindraje;

    public Motocicleta(String placa, String marca, int modelo, double precio, int cilindraje) {
        super(placa, marca, modelo, precio);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() { return cilindraje; }
    public void setCilindraje(int cilindraje) { this.cilindraje = cilindraje; }

    @Override
    public String toString() {
        return super.toString() + " | Cilindraje: " + cilindraje + "cc";
    }
}