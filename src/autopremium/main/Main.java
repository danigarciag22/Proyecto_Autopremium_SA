package autopremium.main;

import autopremium.estructuras.ArbolBI;
import autopremium.estructuras.Cola;
import autopremium.estructuras.ListaDoble;
import autopremium.estructuras.Pila;
import autopremium.mundo.Automovil;
import autopremium.mundo.Motocicleta;
import autopremium.mundo.Nodo;
import autopremium.util.Validaciones;
// IMPORTANTE: Asegúrate de tener la clase ManejoArchivos creada en el paquete correcto
import autopremium.main.ManejoArchivos; 
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        // --- INSTANCIAS ÚNICAS DE LAS ESTRUCTURAS ---
        Pila pilaAutos = new Pila();
        Cola colaMotos = new Cola();
        ListaDoble listaAutos = new ListaDoble();
        ArbolBI arbolMotos = new ArbolBI(); 

        int opcion = 0;
        do {
            // Actualizamos el menú para incluir la opción 5 (Archivos) y 6 (Salir)
            String menu = "MENU PRINCIPAL - AUTOPREMIUM S.A.\n" +
                          "1. Manejo de estructuras (Pila y Cola)\n" +
                          "2. Requisitos iniciales de usuario (Estadísticas)\n" +
                          "3. Manejo de Lista Doble (Autos)\n" +
                          "4. Manejo de Árbol Binario (Motos)\n" +
                          "5. Gestión de Archivos (Persistencia)\n" + // ¡NUEVO!
                          "6. Terminar o salir\n" +                   // Ahora salir es la 6
                          "--------------------------\n" +
                          "Seleccione una opción:";
            
            opcion = Validaciones.leerEntero(menu);

            switch (opcion) {
                case 1: // SUB-MENÚ DE ESTRUCTURAS (Pila y Cola)
                    int subOpcion = 0;
                    do {
                        String subMenu = "MANEJO DE ESTRUCTURAS\n" +
                                         "1. Manejo de Pila (Autos)\n" +
                                         "2. Manejo de Cola (Motos)\n" +
                                         "6. Volver al menu principal";
                        subOpcion = Validaciones.leerEntero(subMenu);
                        
                        if (subOpcion == 1) {
                            ManejoPila.menu(pilaAutos);
                        } else if (subOpcion == 2) {
                            ManejoCola.menu(colaMotos);
                        }
                    } while (subOpcion != 6);
                    break;

                case 2: // REQUISITOS (Cálculos recorriendo las estructuras)
                    mostrarEstadisticas(pilaAutos, colaMotos);
                    break;
                    
                case 3: // LISTA DOBLE
                    ManejoListaDoble.menu(listaAutos);
                    break;

                case 4: // ÁRBOL BINARIO
                    ManejoArbol.menu(arbolMotos);
                    break;

                case 5: // ¡NUEVO CASO! GESTIÓN DE ARCHIVOS
                    // Se envían pilaAutos y listaAutos según tu requerimiento
                    ManejoArchivos.menu(pilaAutos, listaAutos);
                    break;

                case 6: // SALIR (Cambiamos de 5 a 6)
                    JOptionPane.showMessageDialog(null, "Finalizando Proyecto Autopremium.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 6); // El ciclo termina si elige 6
    }

    // Método auxiliar de estadísticas (se mantiene igual)
    private static void mostrarEstadisticas(Pila pila, Cola cola) {
        
        // 1. Promedio Autos
        double sumaAutos = 0;
        int cantAutos = 0;
        Nodo actualAuto = pila.getTope();
        while (actualAuto != null) {
            Automovil a = (Automovil) actualAuto.getDato();
            sumaAutos += a.getPrecio();
            cantAutos++;
            actualAuto = actualAuto.getLigaDerecha();
        }
        double promAutos = (cantAutos > 0) ? sumaAutos / cantAutos : 0;

        // 2. Promedio Motos y Listado > 1000cc
        double sumaMotos = 0;
        int cantMotos = 0;
        String motosPotentes = "";
        
        Nodo actualMoto = cola.getCabeza();
        while (actualMoto != null) {
            Motocicleta m = (Motocicleta) actualMoto.getDato();
            sumaMotos += m.getPrecio();
            cantMotos++;
            
            if (m.getCilindraje() > 1000) {
                motosPotentes += m.toString() + "\n";
            }
            actualMoto = actualMoto.getLigaDerecha();
        }
        double promMotos = (cantMotos > 0) ? sumaMotos / cantMotos : 0;

        // Mostrar Reporte
        String reporte = "--- REPORTE DE GESTIÓN ---\n\n" +
                         "AUTOMÓVILES (En Pila):\n" +
                         "Cantidad: " + cantAutos + "\n" +
                         "Precio Promedio: $" + promAutos + "\n\n" +
                         "MOTOCICLETAS (En Cola):\n" +
                         "Cantidad: " + cantMotos + "\n" +
                         "Precio Promedio: $" + promMotos + "\n\n" +
                         "MOTOS DE ALTO CILINDRAJE (>1000cc):\n" +
                         (motosPotentes.isEmpty() ? "Ninguna registrada." : motosPotentes);
                          
        JOptionPane.showMessageDialog(null, reporte);
    }
}