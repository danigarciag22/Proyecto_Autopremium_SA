package autopremium.main;

import autopremium.estructuras.Pila;
import autopremium.mundo.Automovil;
import autopremium.mundo.Nodo;
import autopremium.util.Validaciones;
import javax.swing.JOptionPane;

public class ManejoPila {

    public static void menu(Pila pilaAutos) {
        int opcion = 0;
        do {
            String menu = "MENU MANEJO DE PILA (AUTOMÓVILES)\n" +
                          "1. Apilar datos (Registrar Auto)\n" +
                          "2. Mostrar pila\n" +
                          "3. Desapilar un dato (Eliminar Tope)\n" +
                          "4. Desapilar o eliminar un dato especifico\n" +
                          "5. Mostrar un dato especifico (Buscar)\n" +
                          "6. Actualizar un dato especifico\n" +
                          "8. Volver al menu principal\n" +
                          "--------------------------------\n" +
                          "Seleccione una opción:";
            
            opcion = Validaciones.leerEntero(menu);

            switch (opcion) {
                case 1: // APILAR
                    String placa = Validaciones.leerString("Ingrese Placa del Automóvil:");
                    String marca = Validaciones.leerString("Ingrese Marca:");
                    int modelo = Validaciones.leerEntero("Ingrese Modelo (Año):");
                    double precio = Validaciones.leerReal("Ingrese Precio:");
                    int puertas = Validaciones.leerEntero("Ingrese Número de Puertas:");

                    Automovil nuevoAuto = new Automovil(placa, marca, modelo, precio, puertas);
                    pilaAutos.push(nuevoAuto);
                    JOptionPane.showMessageDialog(null, "Automóvil apilado correctamente.");
                    break;

                case 2: // MOSTRAR TODO
                    JOptionPane.showMessageDialog(null, pilaAutos.imprimirPila());
                    break;

                case 3: // DESAPILAR
                    Object eliminado = pilaAutos.pop();
                    if (eliminado != null) {
                        JOptionPane.showMessageDialog(null, "Se eliminó el auto:\n" + eliminado.toString());
                    }
                    break;

                case 4: // ELIMINAR ESPECÍFICO
                    String placaBorrar = Validaciones.leerString("Ingrese la PLACA del auto a eliminar:");
                    boolean borrado = pilaAutos.eliminarEspecifico(placaBorrar);
                    if (borrado) JOptionPane.showMessageDialog(null, "Automóvil eliminado con éxito.");
                    else JOptionPane.showMessageDialog(null, "No se encontró un auto con esa placa.");
                    break;

                case 5: // BUSCAR
                    String placaBuscar = Validaciones.leerString("Ingrese la PLACA a buscar:");
                    Nodo actual = pilaAutos.getTope();
                    boolean encontrado = false;
                    
                    while (actual != null) {
                        // Casteamos a Automovil para poder acceder a getPlaca()
                        Automovil auto = (Automovil) actual.getDato();
                        if (auto.getPlaca().equalsIgnoreCase(placaBuscar)) {
                            JOptionPane.showMessageDialog(null, "¡ENCONTRADO!\n" + auto.toString());
                            encontrado = true;
                            break;
                        }
                        actual = actual.getLigaDerecha();
                    }
                    if (!encontrado) JOptionPane.showMessageDialog(null, "Auto no encontrado.");
                    break;

                case 6: // ACTUALIZAR
                    String placaUpd = Validaciones.leerString("Ingrese la PLACA del auto a actualizar:");
                    Nodo nodoUpd = pilaAutos.getTope();
                    boolean existe = false;
                    
                    while (nodoUpd != null) {
                        Automovil auto = (Automovil) nodoUpd.getDato();
                        if (auto.getPlaca().equalsIgnoreCase(placaUpd)) {
                            // Pedimos nuevo precio (ejemplo de actualización)
                            double nuevoPrecio = Validaciones.leerReal("Auto encontrado: " + auto.getMarca() + "\nIngrese el NUEVO precio:");
                            auto.setPrecio(nuevoPrecio);
                            JOptionPane.showMessageDialog(null, "Precio actualizado correctamente.");
                            existe = true;
                            break;
                        }
                        nodoUpd = nodoUpd.getLigaDerecha();
                    }
                    if (!existe) JOptionPane.showMessageDialog(null, "No se puede actualizar: Placa no existe.");
                    break;

                case 8: // SALIR
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 8);
    }
}