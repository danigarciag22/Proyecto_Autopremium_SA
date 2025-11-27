package autopremium.main;

import autopremium.estructuras.Cola;
import autopremium.mundo.Motocicleta;
import autopremium.mundo.Nodo;
import autopremium.util.Validaciones;
import javax.swing.JOptionPane;

public class ManejoCola {

    public static void menu(Cola colaMotos) {
        int opcion = 0;
        do {
            String menu = "MENU MANEJO DE COLA (MOTOCICLETAS)\n" +
                          "1. Encolar datos (Registrar Moto)\n" +
                          "2. Mostrar datos de cola\n" +
                          "3. Desencolar un dato (Sacar el primero)\n" +
                          "4. Desencolar o eliminar un dato especifico\n" +
                          "5. Mostrar un dato especifico (Buscar)\n" +
                          "6. Actualizar un dato especifico\n" +
                          "8. Volver al menu principal\n" +
                          "--------------------------------\n" +
                          "Seleccione una opción:";
            
            opcion = Validaciones.leerEntero(menu);

            switch (opcion) {
                case 1: // ENCOLAR
                    String placa = Validaciones.leerString("Ingrese Placa de la Moto:");
                    String marca = Validaciones.leerString("Ingrese Marca:");
                    int modelo = Validaciones.leerEntero("Ingrese Modelo (Año):");
                    double precio = Validaciones.leerReal("Ingrese Precio:");
                    int cilindraje = Validaciones.leerEntero("Ingrese Cilindraje (cc):");

                    Motocicleta nuevaMoto = new Motocicleta(placa, marca, modelo, precio, cilindraje);
                    colaMotos.push(nuevaMoto);
                    JOptionPane.showMessageDialog(null, "Motocicleta encolada correctamente.");
                    break;

                case 2: // MOSTRAR
                    JOptionPane.showMessageDialog(null, colaMotos.imprimirCola());
                    break;

                case 3: // DESENCOLAR
                    Object eliminado = colaMotos.pop();
                    if (eliminado != null) {
                        JOptionPane.showMessageDialog(null, "Se atendió/sacó la moto:\n" + eliminado.toString());
                    }
                    break;

                case 4: // ELIMINAR ESPECÍFICO
                    String placaBorrar = Validaciones.leerString("Ingrese la PLACA de la moto a sacar:");
                    boolean borrado = colaMotos.eliminarEspecifico(placaBorrar);
                    if (borrado) JOptionPane.showMessageDialog(null, "Motocicleta eliminada de la fila.");
                    else JOptionPane.showMessageDialog(null, "No se encontró esa placa en la cola.");
                    break;

                case 5: // BUSCAR
                    String placaBuscar = Validaciones.leerString("Ingrese la PLACA a buscar:");
                    Nodo actual = colaMotos.getCabeza();
                    boolean encontrado = false;
                    
                    while (actual != null) {
                        Motocicleta moto = (Motocicleta) actual.getDato();
                        if (moto.getPlaca().equalsIgnoreCase(placaBuscar)) {
                            JOptionPane.showMessageDialog(null, "¡ENCONTRADA!\n" + moto.toString());
                            encontrado = true;
                            break;
                        }
                        actual = actual.getLigaDerecha();
                    }
                    if (!encontrado) JOptionPane.showMessageDialog(null, "Moto no encontrada.");
                    break;

                case 6: // ACTUALIZAR
                    String placaUpd = Validaciones.leerString("Ingrese la PLACA a actualizar:");
                    Nodo nodoUpd = colaMotos.getCabeza();
                    boolean existe = false;
                    
                    while (nodoUpd != null) {
                        Motocicleta moto = (Motocicleta) nodoUpd.getDato();
                        if (moto.getPlaca().equalsIgnoreCase(placaUpd)) {
                            double nuevoPrecio = Validaciones.leerReal("Moto: " + moto.getMarca() + "\nNuevo Precio:");
                            moto.setPrecio(nuevoPrecio);
                            JOptionPane.showMessageDialog(null, "Precio actualizado.");
                            existe = true;
                            break;
                        }
                        nodoUpd = nodoUpd.getLigaDerecha();
                    }
                    if (!existe) JOptionPane.showMessageDialog(null, "Placa no encontrada.");
                    break;

                case 8:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 8);
    }
}