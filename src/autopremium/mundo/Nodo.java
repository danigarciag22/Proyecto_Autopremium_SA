package autopremium.mundo;

/**
 * Clase Nodo Universal.
 * Sirve para estructuras simples, dobles y árboles.
 * Para Pila/Cola usaremos solo ligaDerecha.
 */
public class Nodo {
    
    private Object dato;          // Puede guardar Automovil o Motocicleta
    private Nodo ligaIzquierda;   // Futuro: "Anterior" o "Hijo Izquierdo"
    private Nodo ligaDerecha;     // Futuro: "Siguiente" o "Hijo Derecho"

    // Constructor
    public Nodo(Object dato) {
        this.dato = dato;
        this.ligaIzquierda = null;
        this.ligaDerecha = null;
    }

    // Getters y Setters
    public Object getDato() { return dato; }
    public void setDato(Object dato) { this.dato = dato; }

    public Nodo getLigaIzquierda() { return ligaIzquierda; }
    public void setLigaIzquierda(Nodo ligaIzquierda) { this.ligaIzquierda = ligaIzquierda; }

    public Nodo getLigaDerecha() { return ligaDerecha; }
    public void setLigaDerecha(Nodo ligaDerecha) { this.ligaDerecha = ligaDerecha; }
}