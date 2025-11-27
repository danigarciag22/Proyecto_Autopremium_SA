package autopremium.estructuras;

import autopremium.mundo.Nodo;
import javax.swing.JOptionPane;

/**
 * Clase Lista Doblemente Enlazada.
 * Estructura dinámica que permite navegar en ambas direcciones.
 * - ligaDerecha: Siguiente nodo.
 * - ligaIzquierda: Anterior nodo.
 */
public class ListaDoble {

    private Nodo cabeza; // Apuntador al primer elemento

    public ListaDoble() {
        this.cabeza = null;
    }

    // Validación básica exigida por rúbrica
    public boolean isEmpty() {
        return cabeza == null;
    }

    // --- MÉTODOS DE INSERCIÓN ---

    /**
     * Inserta un dato al inicio de la lista.
     * Actualiza las ligas izquierda y derecha.
     */
    public void insertarInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (isEmpty()) {
            cabeza = nuevo;
        } else {
            nuevo.setLigaDerecha(cabeza);
            cabeza.setLigaIzquierda(nuevo);
            cabeza = nuevo;
        }
    }

    /**
     * Inserta un dato al final recorriendo la lista.
     */
    public void insertarFinal(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (isEmpty()) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getLigaDerecha() != null) {
                actual = actual.getLigaDerecha();
            }
            actual.setLigaDerecha(nuevo);
            nuevo.setLigaIzquierda(actual);
        }
    }

    /**
     * Inserta un nodo ANTES de un dato de referencia (por Placa/ID).
     */
    public boolean insertarAntesDe(Object dato, String referencia) {
        if (isEmpty()) return false;

        Nodo actual = cabeza;
        while (actual != null) {
            // Usamos toString para buscar la coincidencia
            if (actual.getDato().toString().contains(referencia)) {
                Nodo nuevo = new Nodo(dato);

                // Caso 1: Es la cabeza
                if (actual == cabeza) {
                    insertarInicio(dato);
                    return true;
                }

                // Caso 2: Está en el medio o final
                Nodo anterior = actual.getLigaIzquierda();
                
                anterior.setLigaDerecha(nuevo);
                nuevo.setLigaIzquierda(anterior);
                
                nuevo.setLigaDerecha(actual);
                actual.setLigaIzquierda(nuevo);
                
                return true;
            }
            actual = actual.getLigaDerecha();
        }
        return false;
    }

    /**
     * Inserta un nodo DESPUÉS de un dato de referencia.
     */
    public boolean insertarDespuesDe(Object dato, String referencia) {
        if (isEmpty()) return false;

        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().toString().contains(referencia)) {
                Nodo nuevo = new Nodo(dato);
                Nodo siguiente = actual.getLigaDerecha();

                actual.setLigaDerecha(nuevo);
                nuevo.setLigaIzquierda(actual);

                if (siguiente != null) {
                    nuevo.setLigaDerecha(siguiente);
                    siguiente.setLigaIzquierda(nuevo);
                }
                return true;
            }
            actual = actual.getLigaDerecha();
        }
        return false;
    }

    // --- MÉTODOS DE ELIMINACIÓN ---

    public void eliminarPrimero() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.");
            return;
        }
        if (cabeza.getLigaDerecha() == null) {
            cabeza = null;
        } else {
            cabeza = cabeza.getLigaDerecha();
            cabeza.setLigaIzquierda(null);
        }
        JOptionPane.showMessageDialog(null, "Primer elemento eliminado.");
    }

    public void eliminarUltimo() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.");
            return;
        }
        if (cabeza.getLigaDerecha() == null) {
            cabeza = null;
        } else {
            Nodo actual = cabeza;
            while (actual.getLigaDerecha() != null) {
                actual = actual.getLigaDerecha();
            }
            // Actual es el último
            Nodo anterior = actual.getLigaIzquierda();
            anterior.setLigaDerecha(null);
        }
        JOptionPane.showMessageDialog(null, "Último elemento eliminado.");
    }

    public boolean eliminarEspecifico(String referencia) {
        if (isEmpty()) return false;

        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().toString().contains(referencia)) {
                // Caso cabeza
                if (actual == cabeza) {
                    eliminarPrimero();
                    return true;
                }
                // Caso último
                if (actual.getLigaDerecha() == null) {
                    eliminarUltimo();
                    return true;
                }
                // Caso medio
                Nodo anterior = actual.getLigaIzquierda();
                Nodo siguiente = actual.getLigaDerecha();
                
                anterior.setLigaDerecha(siguiente);
                siguiente.setLigaIzquierda(anterior);
                return true;
            }
            actual = actual.getLigaDerecha();
        }
        return false;
    }

    // --- MÉTODOS DE VISUALIZACIÓN (Exigidos en la rúbrica) ---

    /**
     * Imprime la lista desde la cabeza hasta el final.
     */
    public String imprimirLista() {
        if (isEmpty()) return "Lista vacía.";
        String salida = "(Inicio) -> ";
        Nodo actual = cabeza;
        while (actual != null) {
            salida += "[" + actual.getDato().toString() + "] <-> ";
            actual = actual.getLigaDerecha();
        }
        salida += "(Fin)";
        return salida;
    }

    /**
     * Imprime la lista desde el final hasta la cabeza (Inverso).
     * Requisito explícito de la rúbrica.
     */
    public String imprimirListaInversa() {
        if (isEmpty()) return "Lista vacía.";
        
        // 1. Ir hasta el último nodo
        Nodo actual = cabeza;
        while (actual.getLigaDerecha() != null) {
            actual = actual.getLigaDerecha();
        }

        // 2. Recorrer hacia atrás usando ligaIzquierda
        String salida = "(Fin) -> ";
        while (actual != null) {
            salida += "[" + actual.getDato().toString() + "] <-> ";
            actual = actual.getLigaIzquierda();
        }
        salida += "(Inicio)";
        return salida;
    }

    // Método auxiliar para buscar nodo (Útil para actualizar)
    public Nodo buscarNodo(String referencia) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().toString().contains(referencia)) {
                return actual;
            }
            actual = actual.getLigaDerecha();
        }
        return null;
    }
}