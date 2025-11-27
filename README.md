# 🚗 Proyecto Autopremium S.A. - Entrega 2

> **Estado del Proyecto:** Estructuras de Datos Lineales (Pilas y Colas)
> **Rama Actual:** \`entrega-2-pilas-colas\`

En esta segunda entrega, el sistema evoluciona de cálculos simples a **almacenamiento en memoria dinámica** utilizando punteros y nodos. Se implementan las estructuras solicitadas para la gestión eficiente de los vehículos.

## 🏗️ Nuevas Características

1.  **Clase Nodo Universal:**
    * Ubicación: \`autopremium.mundo.Nodo\`
    * Función: Contenedor genérico (\`Object\`) que permite almacenar tanto Automóviles como Motocicletas.

2.  **Estructuras de Datos Implementadas:**
    * **PILA (Stack):** Implementada para **Automóviles**.
        * *Comportamiento:* LIFO (Last In, First Out - Último en entrar, primero en salir).
        * *Métodos:* Push, Pop, Peek, IsEmpty.
    * **COLA (Queue):** Implementada para **Motocicletas**.
        * *Comportamiento:* FIFO (First In, First Out - Primero en entrar, primero en salir).
        * *Métodos:* Encolar, Desencolar, Peek.

3.  **Modularización de Menús:**
    * Se crearon controladores específicos (\`ManejoPila\`, \`ManejoCola\`) para no saturar la clase Main.
    * Se agregó la clase \`Validaciones\` para proteger el sistema de entradas incorrectas (letras en campos numéricos).

## 🧪 Guía de Pruebas (Test Drive)

El flujo sugerido para evaluar esta entrega es:

1.  **Ingresar Datos (Opción 1 -> Manejo de Estructuras):**
    * Vaya a **Pila** y registre 2 autos (Ej: Mazda, Renault).
    * Vaya a **Cola** y registre 2 motos (Ej: Yamaha, Ducati > 1000cc).
    
2.  **Verificar Lógica LIFO/FIFO:**
    * En la Pila, al dar "Mostrar", el último auto registrado debe salir primero.
    * En la Cola, al dar "Mostrar", el primer auto registrado debe salir primero.

3.  **Cálculos Generales (Opción 2 -> Requisitos Iniciales):**
    * Desde el menú principal, seleccione la opción 2.
    * El sistema recorrerá los nodos de la Pila y la Cola para calcular los promedios y generar el reporte de motos de alto cilindraje.

## 📂 Arquitectura Actualizada
* \`autopremium.mundo\`: Entidades + Clase Nodo.
* \`autopremium.estructuras\`: Lógica de Pila y Cola.
* \`autopremium.util\`: Validaciones de entrada.
* \`autopremium.main\`: Menús y control de flujo.