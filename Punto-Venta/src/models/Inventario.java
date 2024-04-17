package models;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos=new ArrayList<>();
    private ArrayList<Producto> registrarVentas = new ArrayList<>();

    public void registrarVentas (Producto producto1){
        registrarVentas.add(producto1);
    }

    public ArrayList<Producto> getRegistrarVentas() {
        return registrarVentas;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}