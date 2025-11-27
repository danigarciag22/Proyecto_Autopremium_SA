package autopremium.estructuras;

import autopremium.mundo.Motocicleta;
import autopremium.mundo.Nodo;
import autopremium.util.Validaciones;
import javax.swing.JOptionPane;

/**
 * Clase Arbol Binario.
 * Adaptada para manejar Motocicletas y usar la clase Nodo universal.
 * Contiene métodos recursivos exigidos en la rúbrica.
 */
public class ArbolBI {

    private Nodo raiz;
    
    // Auxiliares del código base proporcionado
    private String texto = "";
    
    public ArbolBI() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    // --- MÉTODOS DE CREACIÓN (Del material base, adaptados) ---

    public void crearRaiz() {
        Motocicleta moto = pedirDatosMoto("Ingrese datos de la RAÍZ del Árbol");
        if (moto != null) {
            raiz = new Nodo(moto);
            // Llamamos a crear recursivamente sus ramas
            crear(raiz);
        }
    }

    /**
     * Método recursivo principal para llenar el árbol.
     * Pregunta al usuario si quiere hijo izquierdo/derecho.
     */
    public void crear(Nodo padre) {
        if (padre == null) return;

        // 1. Intentar crear Hijo Izquierdo
        int resp = JOptionPane.showConfirmDialog(null, 
                "¿Desea agregar hijo IZQUIERDO a: " + padre.getDato().toString() + "?", 
                "Árbol Binario", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            Motocicleta moto = pedirDatosMoto("Hijo IZQUIERDO de " + ((Motocicleta)padre.getDato()).getPlaca());
            if (moto != null) {
                Nodo nuevo = new Nodo(moto);
                padre.setLigaIzquierda(nuevo); // Antes setAnterior
                crear(nuevo); // Recursividad
            }
        }

        // 2. Intentar crear Hijo Derecho
        resp = JOptionPane.showConfirmDialog(null, 
                "¿Desea agregar hijo DERECHO a: " + padre.getDato().toString() + "?", 
                "Árbol Binario", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            Motocicleta moto = pedirDatosMoto("Hijo DERECHO de " + ((Motocicleta)padre.getDato()).getPlaca());
            if (moto != null) {
                Nodo nuevo = new Nodo(moto);
                padre.setLigaDerecha(nuevo); // Antes setSiguiente
                crear(nuevo); // Recursividad
            }
        }
    }

    // --- MÉTODOS DE RECORRIDO (Recursivos) ---

    public void inicializarRecorrido() {
        texto = "";
    }
    
    public String getTextoRecorrido() {
        return texto;
    }

    public void inOrden(Nodo r) {
        if (r != null) {
            inOrden(r.getLigaIzquierda());
            texto += r.getDato().toString() + "\n";
            inOrden(r.getLigaDerecha());
        }
    }

    public void preOrden(Nodo r) {
        if (r != null) {
            texto += r.getDato().toString() + "\n";
            preOrden(r.getLigaIzquierda());
            preOrden(r.getLigaDerecha());
        }
    }

    public void postOrden(Nodo r) {
        if (r != null) {
            postOrden(r.getLigaIzquierda());
            postOrden(r.getLigaDerecha());
            texto += r.getDato().toString() + "\n";
        }
    }

    // --- MÉTODOS DE CÁLCULO (Opciones 4-9 del menú) ---

    // 4. Peso (Suma de precios de las motos en el árbol)
    public double calcularPeso(Nodo r) {
        if (r == null) return 0;
        Motocicleta m = (Motocicleta) r.getDato();
        return m.getPrecio() + calcularPeso(r.getLigaIzquierda()) + calcularPeso(r.getLigaDerecha());
    }
    
    // 6. Número de Nodos (Cantidad total)
    public int contarNodos(Nodo r) {
        if (r == null) return 0;
        return 1 + contarNodos(r.getLigaIzquierda()) + contarNodos(r.getLigaDerecha());
    }

    // 9. Altura del Árbol
    public int calcularAltura(Nodo r) {
        if (r == null) return 0;
        int altIzq = calcularAltura(r.getLigaIzquierda());
        int altDer = calcularAltura(r.getLigaDerecha());
        return 1 + Math.max(altIzq, altDer);
    }

    // 7. Hijos Izquierdos (Mostrar solo los que son hijos izquierdos)
    public void mostrarHijosIzquierdos(Nodo r) {
        if (r != null) {
            if (r.getLigaIzquierda() != null) {
                texto += "H.Izq: " + r.getLigaIzquierda().getDato().toString() + "\n";
            }
            mostrarHijosIzquierdos(r.getLigaIzquierda());
            mostrarHijosIzquierdos(r.getLigaDerecha());
        }
    }
    
    // 5. Hermanos (Buscar nodos que tengan el mismo padre) - Lógica compleja recursiva
    // Simplificación: Mostrar nodos que tienen hermanos (es decir, el padre tiene ambos hijos)
    public void mostrarHermanos(Nodo r) {
        if (r != null) {
            if (r.getLigaIzquierda() != null && r.getLigaDerecha() != null) {
                texto += "[" + r.getLigaIzquierda().getDato() + "] Y [" + r.getLigaDerecha().getDato() + "] son hermanos.\n";
            }
            mostrarHermanos(r.getLigaIzquierda());
            mostrarHermanos(r.getLigaDerecha());
        }
    }

    // 8. Ancestros de un nodo (Buscar placa y retornar true al volver)
    public boolean buscarAncestros(Nodo r, String placaBuscada) {
        if (r == null) return false;
        
        Motocicleta m = (Motocicleta) r.getDato();
        if (m.getPlaca().equalsIgnoreCase(placaBuscada)) {
            return true; // Encontrado
        }
        
        // Si lo encuentra por la izquierda o por la derecha, este nodo es ancestro
        if (buscarAncestros(r.getLigaIzquierda(), placaBuscada) || 
            buscarAncestros(r.getLigaDerecha(), placaBuscada)) {
            texto += m.toString() + " (Es ancestro)\n";
            return true;
        }
        return false;
    }
    
    // 10. Insertar Hoja (Buscar un padre por placa y agregarle hijo si tiene espacio)
    public boolean insertarHoja(Nodo r, String placaPadre, Motocicleta nuevaMoto) {
        if (r == null) return false;
        
        Motocicleta m = (Motocicleta) r.getDato();
        if (m.getPlaca().equalsIgnoreCase(placaPadre)) {
            // Encontramos al padre, intentamos insertar
            if (r.getLigaIzquierda() == null) {
                r.setLigaIzquierda(new Nodo(nuevaMoto));
                return true;
            } else if (r.getLigaDerecha() == null) {
                r.setLigaDerecha(new Nodo(nuevaMoto));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El nodo padre ya tiene 2 hijos. No se puede insertar hoja.");
                return false;
            }
        }
        
        // Buscar en hijos
        if (insertarHoja(r.getLigaIzquierda(), placaPadre, nuevaMoto)) return true;
        return insertarHoja(r.getLigaDerecha(), placaPadre, nuevaMoto);
    }
    
    // Auxiliares EsHoja / EsRama
    public boolean esHoja(Nodo r) {
        return r != null && r.getLigaIzquierda() == null && r.getLigaDerecha() == null;
    }

    // --- UTILIDAD PRIVADA PARA PEDIR DATOS (Para que Crear funcione) ---
    private Motocicleta pedirDatosMoto(String titulo) {
        try {
            String p = Validaciones.leerString(titulo + "\nIngrese Placa:");
            String m = Validaciones.leerString("Marca:");
            int mo = Validaciones.leerEntero("Modelo:");
            double pr = Validaciones.leerReal("Precio:");
            int cc = Validaciones.leerEntero("Cilindraje:");
            return new Motocicleta(p, m, mo, pr, cc);
        } catch (Exception e) {
            return null; // Si cancela
        }
    }
}