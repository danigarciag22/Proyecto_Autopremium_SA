# 🚗 Proyecto Autopremium S.A. - Entrega Final

> **Estado del Proyecto:** FINALIZADO.
> **Rama Actual:** \`entrega-final-archivos\`

Se implementa la capa de persistencia para garantizar que los datos de los **Automóviles** no se pierdan al cerrar la aplicación. Se utiliza un archivo plano \`autos.txt\` como base de datos.

## 🏗️ Características Técnicas

1.  **Clase Genérica \`Archivo\`**:
    * Implementación robusta con \`java.nio\`.
    * Uso de \`try-with-resources\` para gestión de memoria.
    * Atomicidad en escritura: Usa archivos temporales (\`.tmp\`) para evitar corrupción de datos al actualizar.

2.  **Clase \`CRUDAutomovil\`**:
    * **Create:** Escribe nuevos autos al final del archivo.
    * **Read:** Lee y parsea (Deserializa) línea por línea.
    * **Update:** Modifica precio buscando por ID (Placa).
    * **Delete:** Elimina registros reescribiendo el archivo.
    * **Integración:** Métodos especiales \`cargarAListaDoble\` y \`cargarAPila\` para llenar las estructuras dinámicas desde el disco.

3.  **Formato de Datos**:
    * \`PLACA;MARCA;MODELO;PRECIO;PUERTAS\`

## 🧪 Pruebas Finales

1.  Vaya a **Opción 5 (Archivos)** -> **1. Crear Registro**. Guarde un auto.
2.  Cierre el programa completamente.
3.  Vuelva a abrirlo.
4.  Vaya a **Opción 5** -> **2. Leer Todos**. ¡El auto debe estar ahí!
5.  Use **Opción 6 (Cargar a Lista)**.
6.  Vuelva al menú principal -> **Opción 3 (Lista Doble)** -> **2. Mostrar**. El auto del archivo ahora debe estar en la lista en memoria.