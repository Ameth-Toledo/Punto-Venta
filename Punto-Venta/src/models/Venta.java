package models;

public class Venta {
    private double totalDia =0;

    public Venta() {
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

    public void hacerVenta(Double cantidadGanada) {
        totalDia = totalDia + cantidadGanada;

    }

    public double getTotalDia() {
        return totalDia;
    }
}