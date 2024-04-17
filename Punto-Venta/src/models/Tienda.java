package models;

public class Tienda {

    private Inventario inventario = new Inventario();
    private Caja caja = new Caja();

    public Inventario getInventario() {
        return inventario;
    }

    public Caja getCaja() {
        return caja;
    }
}