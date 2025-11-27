# 🚗 Proyecto Autopremium S.A. - Entrega 3 (Lista Doble)

> **Estado del Proyecto:** Implementación de Lista Doblemente Enlazada.
> **Rama Actual:** \`entrega-3-lista-doble\`

En este evento evaluativo, se incorpora la gestión avanzada de **Automóviles** mediante una lista que permite navegación en ambos sentidos (Inicio a Fin y Fin a Inicio).

## 📋 Características Técnicas

1.  **Clase \`ListaDoble\`**:
    * Implementada desde cero (sin \`LinkedList\` de Java).
    * Uso de punteros \`ligaIzquierda\` (Anterior) y \`ligaDerecha\` (Siguiente) de la clase \`Nodo\`.
    * Métodos implementados: Insertar (Inicio, Fin, Antes, Después), Eliminar (Inicio, Fin, Específico), Mostrar (Normal e Inverso).

2.  **Validaciones**:
    * Se utiliza la clase \`Validaciones\` para garantizar que los precios y modelos sean numéricos.
    * Se valida que la lista no esté vacía antes de eliminar o imprimir.

3.  **Tipo de Dato**:
    * Se cumple estrictamente el requisito de usar **Automóviles** (mismo tipo que la Pila).

## 🧪 Pruebas Sugeridas

1.  Ingrese al Menú Principal -> Opción 3.
2.  Use "Insertar de primero" y agregue un Mazda.
3.  Use "Insertar de último" y agregue un Renault.
4.  Use "Insertar en medio" (Antes de Renault) y agregue un Ford.
5.  Use la opción **"Mostrar Inverso"** para verificar que los punteros hacia atrás funcionan (Debe salir: Renault -> Ford -> Mazda).