package autopremium.persistencia;

import autopremium.estructuras.ListaDoble;
import autopremium.estructuras.Pila;
import autopremium.mundo.Automovil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase CRUD específica para la entidad Automóvil.
 * Gestiona la persistencia en "autos.txt".
 * Formato: PLACA;MARCA;MODELO;PRECIO;PUERTAS
 */
public class CRUDAutomovil {

    private static final String RUTA_ARCHIVO = "autos.txt";
    private static final String SEPARADOR = ";";

    // --- MÉTODOS DE SERIALIZACIÓN (Objeto <-> Texto) ---

    // Convierte un Objeto Auto a String "AAA;Mazda;..."
    private static String serializar(Automovil auto) {
        return auto.getPlaca() + SEPARADOR +
               auto.getMarca() + SEPARADOR +
               auto.getModelo() + SEPARADOR +
               auto.getPrecio() + SEPARADOR +
               auto.getNumeroPuertas();
    }

    // Convierte un String "AAA;Mazda;..." a Objeto Auto
    private static Automovil deserializar(String linea) {
        try {
            String[] datos = linea.split(SEPARADOR);
            if (datos.length != 5) return null; // Validación de campos

            String placa = datos[0];
            String marca = datos[1];
            int modelo = Integer.parseInt(datos[2]);
            double precio = Double.parseDouble(datos[3]);
            int puertas = Integer.parseInt(datos[4]);

            return new Automovil(placa, marca, modelo, precio, puertas);
        } catch (Exception e) {
            System.err.println("Error parseando línea: " + linea); // Reporte interno
            return null;
        }
    }

    // --- OPERACIONES CRUD (Create, Read, Update, Delete) ---

    public static void crearRegistro(Automovil auto) {
        // Validar duplicados por ID (Placa) antes de escribir
        if (buscarRegistro(auto.getPlaca()) != null) {
            JOptionPane.showMessageDialog(null, "Error: Ya existe un auto con placa " + auto.getPlaca());
            return;
        }
        String linea = serializar(auto);
        Archivo.anexarLinea(RUTA_ARCHIVO, linea);
        JOptionPane.showMessageDialog(null, "Guardado en " + RUTA_ARCHIVO);
    }

    public static Automovil buscarRegistro(String placa) {
        List<String> lineas = Archivo.leerArchivo(RUTA_ARCHIVO);
        for (String linea : lineas) {
            Automovil auto = deserializar(linea);
            if (auto != null && auto.getPlaca().equalsIgnoreCase(placa)) {
                return auto;
            }
        }
        return null;
    }

    public static void actualizarRegistro(String placa, double nuevoPrecio) {
        List<String> lineas = Archivo.leerArchivo(RUTA_ARCHIVO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

        for (String linea : lineas) {
            Automovil auto = deserializar(linea);
            if (auto != null && auto.getPlaca().equalsIgnoreCase(placa)) {
                auto.setPrecio(nuevoPrecio); // Actualizamos
                nuevasLineas.add(serializar(auto)); // Guardamos el nuevo
                encontrado = true;
            } else {
                nuevasLineas.add(linea); // Mantenemos el viejo
            }
        }

        if (encontrado) {
            Archivo.escribirArchivo(RUTA_ARCHIVO, nuevasLineas);
            JOptionPane.showMessageDialog(null, "Precio actualizado en archivo.");
        } else {
            JOptionPane.showMessageDialog(null, "Placa no encontrada en archivo.");
        }
    }

    public static void eliminarRegistro(String placa) {
        List<String> lineas = Archivo.leerArchivo(RUTA_ARCHIVO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

        for (String linea : lineas) {
            Automovil auto = deserializar(linea);
            if (auto != null && auto.getPlaca().equalsIgnoreCase(placa)) {
                encontrado = true; // No lo agregamos a la nueva lista (se borra)
            } else {
                nuevasLineas.add(linea);
            }
        }

        if (encontrado) {
            Archivo.escribirArchivo(RUTA_ARCHIVO, nuevasLineas);
            JOptionPane.showMessageDialog(null, "Registro eliminado del archivo.");
        } else {
            JOptionPane.showMessageDialog(null, "Placa no encontrada.");
        }
    }

    public static String leerTodos() {
        List<String> lineas = Archivo.leerArchivo(RUTA_ARCHIVO);
        if (lineas.isEmpty()) return "El archivo está vacío.";
        
        StringBuilder sb = new StringBuilder("--- AUTOS EN ARCHIVO ---\n");
        for (String linea : lineas) {
            Automovil a = deserializar(linea);
            if (a != null) sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    // --- MÉTODOS "OJO" (Cargar a Estructuras) ---

    public static void cargarAListaDoble(ListaDoble lista) {
        List<String> lineas = Archivo.leerArchivo(RUTA_ARCHIVO);
        int cont = 0;
        // Reiniciamos la lista para no duplicar si se llama dos veces
        // (Opcional, depende de la lógica, aquí solo agregamos)
        
        for (String linea : lineas) {
            Automovil auto = deserializar(linea);
            if (auto != null) {
                // Validamos si ya está en la lista para no repetir (opcional)
                lista.insertarFinal(auto);
                cont++;
            }
        }
        JOptionPane.showMessageDialog(null, "Se cargaron " + cont + " autos a la Lista Doble.");
    }

    public static void cargarAPila(Pila pila) {
        List<String> lineas = Archivo.leerArchivo(RUTA_ARCHIVO);
        int cont = 0;
        for (String linea : lineas) {
            Automovil auto = deserializar(linea);
            if (auto != null) {
                pila.push(auto);
                cont++;
            }
        }
        JOptionPane.showMessageDialog(null, "Se cargaron " + cont + " autos a la Pila.");
    }
}