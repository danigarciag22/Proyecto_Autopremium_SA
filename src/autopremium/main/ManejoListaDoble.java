package autopremium.main;

import autopremium.estructuras.ListaDoble;
import autopremium.mundo.Automovil;
import autopremium.mundo.Nodo;
import autopremium.util.Validaciones;
import javax.swing.JOptionPane;

/**
 * Controlador para la gestión de la Lista Doble.
 * Interactúa con el usuario y gestiona objetos tipo Automovil.
 */
public class ManejoListaDoble {

    public static void menu(ListaDoble lista) {
        int opcion = 0;
        do {
            String menuTexto = "MENU MANEJO DE LISTA DOBLE (AUTOMÓVILES)\n" +
                    "1. Crear la lista (Reiniciar)\n" +
                    "2. Mostrar los datos de la lista (Inicio -> Fin)\n" +
                    "3. Insertar de primero\n" +
                    "4. Insertar de ultimo\n" +
                    "5. Insertar antes de un dato\n" +
                    "6. Insertar despues de un dato\n" +
                    "7. Liberar (Eliminar) el primero\n" +
                    "8. Liberar (Eliminar) el ultimo\n" +
                    "9. Liberar un dato específico\n" +
                    "10. Actualizar un dato específico\n" +
                    "11. Mostrar Inverso (Fin -> Inicio)\n" + // Agregado para cumplir rúbrica
                    "14. Volver al menu principal\n" +
                    "----------------------------\n" +
                    "Seleccione una opción:";

            opcion = Validaciones.leerEntero(menuTexto);

            // Variables locales para manejo de datos
            Automovil auto;
            String ref; 

            switch (opcion) {
                case 1: // Crear/Reiniciar
                    lista = new ListaDoble(); 
                    JOptionPane.showMessageDialog(null, "Lista creada exitosamente.");
                    break;

                case 2: // Mostrar Normal
                    JOptionPane.showMessageDialog(null, lista.imprimirLista());
                    break;

                case 3: // Insertar Inicio
                    auto = pedirDatosAuto();
                    if(auto != null) {
                        lista.insertarInicio(auto);
                        JOptionPane.showMessageDialog(null, "Automóvil insertado al inicio.");
                    }
                    break;

                case 4: // Insertar Final
                    auto = pedirDatosAuto();
                    if(auto != null) {
                        lista.insertarFinal(auto);
                        JOptionPane.showMessageDialog(null, "Automóvil insertado al final.");
                    }
                    break;

                case 5: // Insertar Antes
                    ref = Validaciones.leerString("Ingrese la PLACA del auto de referencia:");
                    auto = pedirDatosAuto();
                    if(auto != null) {
                        boolean res = lista.insertarAntesDe(auto, ref);
                        if(res) JOptionPane.showMessageDialog(null, "Insertado antes de " + ref);
                        else JOptionPane.showMessageDialog(null, "Referencia no encontrada.");
                    }
                    break;

                case 6: // Insertar Después
                    ref = Validaciones.leerString("Ingrese la PLACA del auto de referencia:");
                    auto = pedirDatosAuto();
                    if(auto != null) {
                        boolean res = lista.insertarDespuesDe(auto, ref);
                        if(res) JOptionPane.showMessageDialog(null, "Insertado después de " + ref);
                        else JOptionPane.showMessageDialog(null, "Referencia no encontrada.");
                    }
                    break;

                case 7: // Eliminar Primero
                    lista.eliminarPrimero();
                    break;

                case 8: // Eliminar Último
                    lista.eliminarUltimo();
                    break;

                case 9: // Eliminar Específico
                    ref = Validaciones.leerString("Ingrese la PLACA a eliminar:");
                    boolean borrado = lista.eliminarEspecifico(ref);
                    if(borrado) JOptionPane.showMessageDialog(null, "Automóvil eliminado.");
                    else JOptionPane.showMessageDialog(null, "Placa no encontrada.");
                    break;

                case 10: // Actualizar
                    ref = Validaciones.leerString("Ingrese la PLACA a actualizar:");
                    Nodo nodo = lista.buscarNodo(ref);
                    if (nodo != null) {
                        Automovil a = (Automovil) nodo.getDato();
                        // Ejemplo: Actualizar el precio
                        double nuevoPrecio = Validaciones.leerReal("Auto: " + a.getMarca() + "\nIngrese Nuevo Precio:");
                        a.setPrecio(nuevoPrecio);
                        JOptionPane.showMessageDialog(null, "Dato actualizado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Placa no encontrada.");
                    }
                    break;
                    
                case 11: // Mostrar Inverso (Cumpliendo instrucción de imprimir desde el final)
                    JOptionPane.showMessageDialog(null, lista.imprimirListaInversa());
                    break;

                case 14:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (opcion != 14);
    }

    // Método privado para solicitar datos usando Validaciones
    private static Automovil pedirDatosAuto() {
        try {
            String p = Validaciones.leerString("Placa:");
            String m = Validaciones.leerString("Marca:");
            int mo = Validaciones.leerEntero("Modelo:");
            double pr = Validaciones.leerReal("Precio:");
            int pu = Validaciones.leerEntero("Puertas:");
            return new Automovil(p, m, mo, pr, pu);
        } catch (Exception e) {
            return null;
        }
    }
}