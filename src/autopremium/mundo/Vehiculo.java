package autopremium.mundo;

/**
 * Superclase Vehículo.
 * Contiene los atributos compartidos por Autos y Motos.
 * Aplica Herencia y Encapsulamiento.
 */
public class Vehiculo {
    // Atributos protegidos para que los hijos accedan, o privados con getters
    private String placa;
    private String marca;
    private int modelo;
    private double precio;

    public Vehiculo(String placa, String marca, int modelo, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    // Getters y Setters (Encapsulamiento)
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getModelo() { return modelo; }
    public void setModelo(int modelo) { this.modelo = modelo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Marca: " + marca + " | Precio: $" + precio;
    }
}