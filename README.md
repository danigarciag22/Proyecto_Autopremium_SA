# 🚗 Proyecto Autopremium S.A. - Entrega 1

> **Estado del Proyecto:** Fase Inicial (POO + Herencia)  
> **Rama:** `entrega-1-base-poo`

Este proyecto implementa la solución inicial para la gestión de vehículos de la empresa **Autopremium S.A.**, cumpliendo estrictamente con los principios de Programación Orientada a Objetos (Encapsulamiento, Herencia y Modularidad).

## 📋 Características de esta Entrega

1.  **Arquitectura en Capas:**
    * `autopremium.mundo`: Contiene las entidades (`Vehiculo`, `Automovil`, `Motocicleta`).
    * `autopremium.servicios`: Lógica de negocio separada (`ServicioAutopremium`).
    * `autopremium.main`: Interfaz de usuario (JOptionPane).
2.  **Uso de Herencia:** Implementación de una superclase `Vehiculo` para evitar redundancia de código.
3.  **Sin Almacenamiento Persistente:** Siguiendo la restricción de la entrega, **no se utilizan arreglos ni listas**. Los cálculos (promedios) se realizan "al vuelo" mediante acumuladores en la capa de servicio.

## 🧪 Datos de Prueba (Escenario Colombiano)

Para probar la funcionalidad, utilice los siguientes formatos de placa estándar en Colombia:

### 🚘 Caso 1: Automóvil
* **Formato:** Tres letras y tres números (`AAA-123`).
* **Ejemplo:** `MZR-987` (Mazda 2, Modelo 2022).
* **Resultado esperado:** El sistema debe sumar el precio al promedio de autos.

### 🏍️ Caso 2: Motocicleta (Cilindraje Bajo)
* **Formato:** Tres letras, dos números y una letra (`XXX-11A`).
* **Ejemplo:** `QWE-45C` (Yamaha NMax, 155cc).
* **Resultado esperado:** Suma al promedio de motos. No aparece en el reporte especial.

### 🏍️ Caso 3: Motocicleta (Alto Cilindraje)
* **Ejemplo:** `DUC-88F` (Ducati Multistrada, 1200cc).
* **Resultado esperado:** Suma al promedio y **DEBE** aparecer en el reporte de "Motos > 1000cc".
