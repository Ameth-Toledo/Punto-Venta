import models.*;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Scanner entrance = new Scanner(System.in);
        boolean cerrarPrograma = true;
        Inventario inventario = tienda.getInventario();
        double total = 0;
        byte auxVariable = 1;

        Caja caja = tienda.getCaja();
        Venta venta = new Venta();

        boolean banderaAddProducto = true;

        do {
            System.out.println("-------- MENU PRINCIPAL ---------");
            System.out.println("---------------------------------");
            System.out.println("--- 1) Menu del Administrador ---");
            System.out.println("--- 2) Menu del Empleado.");
            System.out.println("X) Cerrar Programa.");
            System.out.print("Elige una opcion: ");
            String eleccionMenu = entrance.nextLine();
            switch (eleccionMenu) {
                case "1":
                    boolean cerrarAdmin = true;
                    do {
                        System.out.println("Menu del Administrador.");
                        System.out.println(" ");
                        System.out.println("1) Apertura de Caja.");
                        System.out.println("2) Arqueo de Caja.");
                        System.out.println("3) Historial Ventas");
                        System.out.println("X) Regresar al menu de Usuarios.");
                        System.out.print("Elige una opcion: ");
                        String opcionAdmin = entrance.nextLine();
                        System.out.println(" ");
                        switch (opcionAdmin){
                            case "1":
                                if (auxVariable == 1) {
                                    System.out.println("Desea realizar la apertura de caja? Ingrese s para realizar la apertura");
                                    String eleccionApertura = entrance.nextLine();
                                    if (Objects.equals(eleccionApertura, "s")) {
                                        System.out.println("Apertura de Caja.");
                                        System.out.print("Ingresa el Monto Inicial: ");
                                        double aperturaCaja = entrance.nextDouble();
                                        entrance.nextLine();
                                        caja.setApertura(aperturaCaja);
                                        total += caja.getApertura();
                                        // aperturaCaja;
                                        //caja.setSaldoTotal(total);
                                        System.out.println("Caja aperturada con $" + caja.getApertura() + ".");
                                        //System.out.println("Apertura total " + caja.getSaldoTotal());
                                        auxVariable--;

                                    }else {
                                        System.out.println("Regresando al menu.");
                                    }
                                }else {
                                    System.out.println(" ");
                                    System.out.println("Apertura de Caja iniciada, para volverla a abrir necesita realizar el arqueo de caja.");
                                }
                                break;
                            case "2":
                                System.out.println("Arqueo de Caja.");
                                double finalizarDia = caja.getApertura() + venta.getTotalDia();
                                System.out.println(finalizarDia);
                                caja.setSaldoTotal(total);
                                System.out.println("Total: " + tienda.getCaja().getSaldoTotal());
                                tienda.getCaja().setSaldoTotal(0);
                                auxVariable = 1 ;
                                break;
                            case "3":
                                System.out.println("---Historial de ventas----");
                                System.out.println(" ");
                                for (Producto ventaRealizada : inventario.getRegistrarVentas()){
                                    System.out.println("Producto: " + ventaRealizada.getNombre());
                                    System.out.println("Cantidad vendida: " + ventaRealizada.getCantidad());
                                    System.out.println("Ganancia total: " + (ventaRealizada.getPrecio() * ventaRealizada.getCantidad()));
                                    System.out.println("---------------------------------------");
                                }

                                break;
                            default:
                                System.out.println("Regresando al Menu.");
                                cerrarAdmin = false;
                        }
                    }while (cerrarAdmin);
                    break;
                case "2":
                    boolean cerrarEmpleado = true;
                    do {
                        System.out.println("Menu del Empleado.");
                        System.out.println(" ");
                        System.out.println("1) Alta de Productos.");
                        System.out.println("2) Realizar Venta.");
                        System.out.println("3) Ver productos ");

                        System.out.println("X) Regresar al Menu de Usuarios.");
                        System.out.print("Elige una opcion: ");
                        String opcionEmpleado = entrance.nextLine();
                        switch (opcionEmpleado) {
                            case "1":
                                do {
                                    System.out.println("Alta de Productos.");
                                    System.out.print("Ingrese el nombre del producto: ");
                                    String nombreProducto = entrance.nextLine();
                                    System.out.print("Ingrese el precio individual del producto: ");
                                    double precioProducto = entrance.nextFloat();
                                    System.out.print("Ingrese la cantidad del productos: ");
                                    int cantidadProducto = entrance.nextInt();
                                    entrance.nextLine();
                                    Producto producto = new Producto(nombreProducto, precioProducto, cantidadProducto);
                                    inventario.agregarProducto(producto);
                                    System.out.println("Producto Agregado con exito.");
                                    System.out.println(" ");
                                    System.out.println("¿Desea agregar otro producto?");
                                    System.out.println("S) Si / X) No.");
                                    String decisionAddProducto = entrance.nextLine();
                                    if (!decisionAddProducto.equalsIgnoreCase("s")) {
                                        banderaAddProducto = false;
                                    }
                                } while (banderaAddProducto);
                                break;
                            case "2":
                                System.out.println("--- Realizar Venta ---");
                                System.out.println("Ingrese el nombre del producto a vender");
                                String nproductoVender = entrance.nextLine();
                                boolean encontrado = false;
                                for (Producto inven : inventario.getProductos()) {
                                    if (inven.getNombre().equalsIgnoreCase(nproductoVender)) {
                                        System.out.println("El producto está en el inventario. Puedes proceder con la venta.");
                                        System.out.println(" ");
                                        System.out.println("Ingrese la cantidad a vender");
                                        int cantidadVender = entrance.nextInt();
                                        entrance.nextLine();
                                        if (inven.getCantidad() >= cantidadVender) {
                                            boolean reduccionExitosa = venta.reducirStock(inventario, nproductoVender, cantidadVender);
                                            if (reduccionExitosa) {
                                                Producto producto = new Producto(nproductoVender, inven.getPrecio(),cantidadVender);
                                                Double gananciaTotal = cantidadVender * inven.getPrecio();
                                                venta.hacerVenta(gananciaTotal);
                                                inventario.registrarVentas(producto);
                                                System.out.println("Se ha reducido el stock correctamente.");
                                                System.out.println(venta.getTotalDia());

                                            }
                                        } else {
                                            System.out.println("No hay suficiente stock disponible para vender esa cantidad.");
                                        }
                                        encontrado = true;
                                        break;
                                    }
                                }
                                if (!encontrado) {
                                    System.out.println("El producto no se encuentra en el inventario.");
                                }
                                break;
                            case "3":
                                int k = 0;
                                System.out.println("La lista de productos disponibles es: ");
                                for (Producto listaProductos: inventario.getProductos()){
                                    System.out.println((k+1)+".- "+ listaProductos.getNombre());
                                    k++;
                                }
                                break;

                            default:
                                System.out.println("Regresando al Menu de Usuarios.");
                                cerrarEmpleado = false;
                        }
                    }while (cerrarEmpleado);
                    break;
                default:
                    cerrarPrograma = false;
            }
        } while (cerrarPrograma);
    }
}