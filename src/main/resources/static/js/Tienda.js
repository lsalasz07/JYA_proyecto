function filtrarProductos() {
    const categoriaSeleccionada = document.getElementById("categoria").value;
    const productos = document.querySelectorAll(".producto");

    productos.forEach(producto => {
        const categoria = producto.getAttribute("data-categoria");

        if (categoriaSeleccionada === "todos" || categoria === categoriaSeleccionada) {
            producto.style.display = "inline-block";
        } else {
            producto.style.display = "none";
        }
    });
}
