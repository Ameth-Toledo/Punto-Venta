package models;

import java.util.ArrayList;

public class Inventario {
    Producto producto = new Producto();

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

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }


}