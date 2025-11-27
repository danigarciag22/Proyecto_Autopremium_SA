package autopremium.estructuras;

import autopremium.mundo.Nodo;
import javax.swing.JOptionPane;

public class Cola {
    
    private Nodo cabeza; // Primer elemento (El que sale)
    private Nodo cola;   // Último elemento (El que entra)

    public Cola() {
        this.cabeza = null;
        this.cola = null;
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    public boolean isFull() {
        return false;
    }

    // Push / Encolar (Insertar al final)
    public void push(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (isEmpty()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setLigaDerecha(nuevo); // El último actual apunta al nuevo
            cola = nuevo; // El nuevo se vuelve el último
        }
    }

    // Pop / Desencolar (Sacar del inicio)
    public Object pop() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La Cola está vacía.");
            return null;
        }
        Object datoAux = cabeza.getDato();
        cabeza = cabeza.getLigaDerecha(); // La cabeza avanza
        
        if (cabeza == null) { // Si quedó vacía
            cola = null;
        }
        return datoAux;
    }

    public Object peek() {
        if (isEmpty()) return null;
        return cabeza.getDato();
    }
    
    public String imprimirCola() {
        if (isEmpty()) return "La Cola está vacía.";
        String salida = "(Inicio) -> ";
        Nodo actual = cabeza;
        while (actual != null) {
            salida += actual.getDato().toString() + " -> ";
            actual = actual.getLigaDerecha();
        }
        salida += "(Fin)";
        return salida;
    }
    
    // Método para buscar y eliminar específico
    public boolean eliminarEspecifico(String placaBuscar) {
        if (isEmpty()) return false;

        if (cabeza.getDato().toString().contains(placaBuscar)) {
            pop();
            return true;
        }
        
        Nodo anterior = cabeza;
        Nodo actual = cabeza.getLigaDerecha();
        
        while (actual != null) {
            if (actual.getDato().toString().contains(placaBuscar)) {
                anterior.setLigaDerecha(actual.getLigaDerecha());
                if (actual == cola) { // Si borramos el último
                    cola = anterior;
                }
                return true;
            }
            anterior = actual;
            actual = actual.getLigaDerecha();
        }
        return false;
    }
    
    public Nodo getCabeza() { return cabeza; }
}