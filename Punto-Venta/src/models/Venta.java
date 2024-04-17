package models;


import java.util.ArrayList;

public class Venta {

    private ArrayList<Producto> productosVender = new ArrayList<>();
    private double totalDia =0;

    public Venta() {
    }

    public double calcularTotal() {
        double totalVenta = 0;
        for (Producto producto : productosVender) {
            totalVenta += producto.getPrecio();
        }
        return totalVenta;
    }

    public boolean reducirStock(Inventario inventario, String nombreProducto, int cantidad) {
        for (Producto producto : inventario.getProductos()) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                int stockActual = producto.getCantidad();
                if (stockActual >= cantidad) {
                    producto.setCantidad(stockActual - cantidad);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public Producto agregarVenta(Inventario inventario, String nombre, int cantidad) {
        Producto producto = null;
        for (Producto productoInventario : inventario.getProductos()) {
            if (nombre.equalsIgnoreCase(productoInventario.getNombre())) {
                productosVender.add(productoInventario);
                producto = productoInventario;
                break;
            }
        }
        return producto;
    }

    public void hacerVenta(Double cantidadGanada) {
        totalDia = totalDia + cantidadGanada;

    }

    public double getTotalDia() {
        return totalDia;
    }
}