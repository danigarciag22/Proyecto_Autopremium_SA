package autopremium.main;

import autopremium.estructuras.ArbolBI;
import autopremium.mundo.Motocicleta;
import autopremium.util.Validaciones;
import javax.swing.JOptionPane;

public class ManejoArbol {

    public static void menu(ArbolBI arbol) {
        int opcion = 0;
        do {
            String menu = "MENU ÁRBOLES BINARIOS (MOTOCICLETAS)\n" +
                          "1. InOrden\n" +
                          "2. PostOrden\n" +
                          "3. PreOrden\n" +
                          "4. Peso (Suma de Precios)\n" +
                          "5. Hermanos\n" +
                          "6. Número de Nodos\n" +
                          "7. Hijos Izquierdos\n" +
                          "8. Ancestros de un nodo\n" +
                          "9. Altura\n" +
                          "10. Insertar Hoja (Buscando Padre)\n" +
                          "11. Crear / Reiniciar Árbol\n" + // Necesario para empezar
                          "15. Terminar\n" +
                          "--------------------------\n" +
                          "Seleccione una opción:";

            opcion = Validaciones.leerEntero(menu);

            // Validar que el árbol exista para opciones de visualización
            if (arbol.esVacio() && opcion != 11 && opcion != 15) {
                JOptionPane.showMessageDialog(null, "El árbol está vacío. Use la opción 11 para crearlo primero.");
                continue;
            }

            switch (opcion) {
                case 1: // InOrden
                    arbol.inicializarRecorrido();
                    arbol.inOrden(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "RECORRIDO IN-ORDEN:\n" + arbol.getTextoRecorrido());
                    break;

                case 2: // PostOrden
                    arbol.inicializarRecorrido();
                    arbol.postOrden(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "RECORRIDO POST-ORDEN:\n" + arbol.getTextoRecorrido());
                    break;

                case 3: // PreOrden
                    arbol.inicializarRecorrido();
                    arbol.preOrden(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "RECORRIDO PRE-ORDEN:\n" + arbol.getTextoRecorrido());
                    break;

                case 4: // Peso (Suma Precios)
                    double peso = arbol.calcularPeso(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "PESO DEL ÁRBOL (Suma Precios): $" + peso);
                    break;

                case 5: // Hermanos
                    arbol.inicializarRecorrido();
                    arbol.mostrarHermanos(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "PAREJAS DE HERMANOS:\n" + arbol.getTextoRecorrido());
                    break;

                case 6: // Número de Nodos
                    int cant = arbol.contarNodos(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "CANTIDAD DE NODOS: " + cant);
                    break;

                case 7: // Hijos Izquierdos
                    arbol.inicializarRecorrido();
                    arbol.mostrarHijosIzquierdos(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "HIJOS IZQUIERDOS:\n" + arbol.getTextoRecorrido());
                    break;

                case 8: // Ancestros
                    String placaBus = Validaciones.leerString("Ingrese PLACA de la moto para buscar ancestros:");
                    arbol.inicializarRecorrido();
                    boolean enc = arbol.buscarAncestros(arbol.getRaiz(), placaBus);
                    if (enc) JOptionPane.showMessageDialog(null, "ANCESTROS (De abajo hacia arriba):\n" + arbol.getTextoRecorrido());
                    else JOptionPane.showMessageDialog(null, "Nodo no encontrado.");
                    break;

                case 9: // Altura
                    int altura = arbol.calcularAltura(arbol.getRaiz());
                    JOptionPane.showMessageDialog(null, "ALTURA DEL ÁRBOL: " + altura);
                    break;

                case 10: // Insertar Hoja
                    String placaPadre = Validaciones.leerString("Ingrese PLACA del PADRE donde quiere insertar:");
                    // Pedir datos de la nueva moto
                    String p = Validaciones.leerString("Nueva Placa:");
                    String m = Validaciones.leerString("Marca:");
                    int mo = Validaciones.leerEntero("Modelo:");
                    double pr = Validaciones.leerReal("Precio:");
                    int cc = Validaciones.leerEntero("Cilindraje:");
                    Motocicleta nueva = new Motocicleta(p, m, mo, pr, cc);
                    
                    boolean inserto = arbol.insertarHoja(arbol.getRaiz(), placaPadre, nueva);
                    if (inserto) JOptionPane.showMessageDialog(null, "Hoja insertada con éxito.");
                    else JOptionPane.showMessageDialog(null, "No se pudo insertar (Padre no existe o está lleno).");
                    break;

                case 11: // Crear
                    arbol.crearRaiz();
                    break;

                case 15:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 15);
    }
}