package autopremium.main;

import autopremium.estructuras.ListaDoble;
import autopremium.estructuras.Pila;
import autopremium.mundo.Automovil;
import autopremium.persistencia.CRUDAutomovil;
import autopremium.util.Validaciones;
import javax.swing.JOptionPane;

public class ManejoArchivos {

    public static void menu(Pila pila, ListaDoble lista) {
        int opcion = 0;
        do {
            String menu = "MENU GESTIÓN DE ARCHIVOS (Persistencia)\n" +
                          "1. Crear Registro en Archivo (Guardar)\n" +
                          "2. Leer Todos los Registros\n" +
                          "3. Buscar Auto por Placa en Archivo\n" +
                          "4. Actualizar Precio en Archivo\n" +
                          "5. Eliminar Auto del Archivo\n" +
                          "6. CARGAR Archivo -> A Lista Doble\n" +
                          "7. CARGAR Archivo -> A Pila\n" +
                          "8. Volver al Menú Principal\n" +
                          "--------------------------\n" +
                          "Seleccione una opción:";

            opcion = Validaciones.leerEntero(menu);
            String placa;

            switch (opcion) {
                case 1: // CREATE
                    // Pedimos datos usando Validaciones
                    String p = Validaciones.leerString("Placa:");
                    String m = Validaciones.leerString("Marca:");
                    int mo = Validaciones.leerEntero("Modelo:");
                    double pr = Validaciones.leerReal("Precio:");
                    int pu = Validaciones.leerEntero("Puertas:");
                    
                    Automovil nuevo = new Automovil(p, m, mo, pr, pu);
                    CRUDAutomovil.crearRegistro(nuevo);
                    break;

                case 2: // READ ALL
                    String datos = CRUDAutomovil.leerTodos();
                    JOptionPane.showMessageDialog(null, datos);
                    break;

                case 3: // READ ONE
                    placa = Validaciones.leerString("Ingrese Placa a buscar:");
                    Automovil buscado = CRUDAutomovil.buscarRegistro(placa);
                    if (buscado != null) JOptionPane.showMessageDialog(null, "ENCONTRADO:\n" + buscado.toString());
                    else JOptionPane.showMessageDialog(null, "No existe en el archivo.");
                    break;

                case 4: // UPDATE
                    placa = Validaciones.leerString("Ingrese Placa a actualizar:");
                    double nPrecio = Validaciones.leerReal("Nuevo Precio:");
                    CRUDAutomovil.actualizarRegistro(placa, nPrecio);
                    break;

                case 5: // DELETE
                    placa = Validaciones.leerString("Ingrese Placa a eliminar:");
                    CRUDAutomovil.eliminarRegistro(placa);
                    break;

                case 6: // CARGAR A LISTA
                    CRUDAutomovil.cargarAListaDoble(lista);
                    break;

                case 7: // CARGAR A PILA
                    CRUDAutomovil.cargarAPila(pila);
                    break;

                case 8:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 8);
    }
}