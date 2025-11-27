package autopremium.estructuras;

import autopremium.mundo.Nodo;
import javax.swing.JOptionPane;

public class Pila {
    
    private Nodo tope; // La cima de la pila

    public Pila() {
        this.tope = null;
    }

    // 1. IsEmpty (Verificar si está vacía)
    public boolean isEmpty() {
        return tope == null;
    }

    // 2. IsFull (En dinámica siempre es falso, salvo que se acabe la RAM)
    public boolean isFull() {
        return false;
    }

    // 3. Push (Apilar - Insertar al inicio)
    public void push(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (isEmpty()) {
            tope = nuevo;
        } else {
            nuevo.setLigaDerecha(tope); // El nuevo apunta al antiguo tope
            tope = nuevo; // El nuevo se convierte en el tope
        }
        // Mensaje opcional, a veces es mejor ponerlo en el ManejoPila
        // JOptionPane.showMessageDialog(null, "Dato apilado correctamente.");
    }

    // 4. Pop (Desapilar - Sacar el tope)
    public Object pop() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La Pila está vacía, no se puede desapilar.");
            return null;
        }
        Object datoAux = tope.getDato();
        tope = tope.getLigaDerecha(); // El tope baja al siguiente
        return datoAux;
    }

    // 5. Peek (Mirar el tope sin sacar)
    public Object peek() {
        if (isEmpty()) return null;
        return tope.getDato();
    }
    
    // Método auxiliar para imprimir (útil para la opción "Mostrar Pila")
    public String imprimirPila() {
        if (isEmpty()) return "La Pila está vacía.";
        
        String salida = "";
        Nodo actual = tope;
        while (actual != null) {
            salida += actual.getDato().toString() + "\n⬇\n"; // Flecha visual
            actual = actual.getLigaDerecha();
        }
        salida += "(Fin de la Pila)";
        return salida;
    }
    
    // Método extra para "Eliminar un dato específico" (Pedido en el menú)
    public boolean eliminarEspecifico(String placaBuscar) {
        if (isEmpty()) return false;
        
        // Caso especial: Es el tope
        // Nota: Como es Object, usaremos toString() para comparar simple por ahora,
        // o castearemos en una implementación más estricta.
        if (tope.getDato().toString().contains(placaBuscar)) {
            pop();
            return true;
        }
        
        Nodo anterior = tope;
        Nodo actual = tope.getLigaDerecha();
        
        while (actual != null) {
            if (actual.getDato().toString().contains(placaBuscar)) {
                anterior.setLigaDerecha(actual.getLigaDerecha()); // Saltamos el nodo
                return true;
            }
            anterior = actual;
            actual = actual.getLigaDerecha();
        }
        return false;
    }
    
    // Getter del tope (Para recorrer externamente si se necesita)
    public Nodo getTope() { return tope; }
}