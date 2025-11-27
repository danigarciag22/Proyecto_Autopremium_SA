package autopremium.persistencia;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase Genérica para manejo de Archivos de Texto.
 * Usa java.nio y try-with-resources para gestión eficiente de I/O.
 */
public class Archivo {

    // Método para LEER todas las líneas de un archivo
    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        Path path = Paths.get(ruta);

        // Si el archivo no existe, retornamos lista vacía
        if (!Files.exists(path)) return lineas;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) { // Ignorar líneas vacías
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error leyendo archivo: " + e.getMessage());
        }
        return lineas;
    }

    // Método para ESCRIBIR una lista de líneas en un archivo (Sobrescribe todo)
    public static void escribirArchivo(String ruta, List<String> lineas) {
        Path path = Paths.get(ruta);
        // Usamos un archivo temporal para atomicidad (Requisito del enunciado)
        Path tempPath = Paths.get(ruta + ".tmp");

        try (BufferedWriter bw = Files.newBufferedWriter(tempPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error escribiendo archivo temporal: " + e.getMessage());
            return;
        }

        // Si todo salió bien, reemplazamos el original con el temporal
        try {
            Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al renombrar archivo temporal: " + e.getMessage());
        }
    }

    // Método para ANEXAR una sola línea al final (Append)
    public static void anexarLinea(String ruta, String linea) {
        Path path = Paths.get(ruta);
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error anexando línea: " + e.getMessage());
        }
    }
}