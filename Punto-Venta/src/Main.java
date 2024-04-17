import models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Venta venta = new Venta();
        Scanner teclado = new Scanner(System.in);
        Inventario inventario = tienda.getInventario();
        double total = 0;
        Caja caja = tienda.getCaja();
        boolean cerrarPrograma = true;
        boolean banderaAddProducto = true;

        do {
            System.out.println(" ");
            System.out.println("--------- MENU PRINCIPAL -----------");
            System.out.println("------------------------------------");
            System.out.println("--- ✅ 1) Menu del Administrador ---");
            System.out.println("--- ✅ 2) Menu del Empleado      ---");
            System.out.println("--- ❌ 3) Cerrar Programa        ---");
            System.out.println("------------------------------------");
            System.out.println(" ");
            System.out.print("Elige una opcion: ");
            String opcion = teclado.nextLine();
            switch (opcion) {
                case "1":
                    boolean cerrarAdmin = true;
                    do {
                        System.out.println(" ");
                        System.out.println("----- Menu del Administrador ----");
                        System.out.println("---------------------------------");
                        System.out.println("--- ✅ 1) Apertura de Caja    ---");
                        System.out.println("--- ✅ 2) Arqueo de Caja      ---");
                        System.out.println("--- ✅ 3) Historial Ventas    ---");
                        System.out.println("--- ❌ 4) Regresar al menu    ---");
                        System.out.println("---------------------------------");
                        System.out.println(" ");
                        System.out.print("Elige una opcion: ");
                        String admin = teclado.nextLine();
                        System.out.println(" ");
                        switch (admin){
                            case "1":
                                System.out.println("------ Apertura de Caja ------");
                                System.out.print("Ingrese el Monto Inicial: ");
                                double aperturaCaja = teclado.nextDouble();
                                teclado.nextLine();
                                caja.setApertura(aperturaCaja);
                                total += caja.getApertura();
                                System.out.println("Caja aperturada correctamente con $" + caja.getApertura() + "0 pesos");
                                break;
                            case "2":
                                System.out.println("------ Arqueo de Caja ------");
                                double finalizarDia = caja.getApertura() + venta.getTotalDia();
                                System.out.println(finalizarDia);
                                caja.setSaldoTotal(total);
                                System.out.println("Total: " + tienda.getCaja().getSaldoTotal());
                                tienda.getCaja().setSaldoTotal(0);
                                break;
                            case "3":
                                System.out.println("---Historial de ventas----");
                                System.out.println(" ");
                                for (Producto ventaRealizada : inventario.getRegistrarVentas()){
                                    System.out.println("Producto: " + ventaRealizada.getNombre());
                                    System.out.println("Cantidad vendida: " + ventaRealizada.getCantidad());
                                    System.out.println("Ganancia total: $" + (ventaRealizada.getPrecio() * ventaRealizada.getCantidad()));
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
                        System.out.println(" ");
                        System.out.println("------- Menu del Empleado -----");
                        System.out.println("-------------------------------");
                        System.out.println("--- ✅ 1) Alta de Productos ---");
                        System.out.println("--- ✅ 2) Realizar Venta    ---");
                        System.out.println("--- ✅ 3) Ver productos     ---");
                        System.out.println("--- ❌ 4) Regresar al Menu  ---");
                        System.out.println("-------------------------------");
                        System.out.println(" ");
                        System.out.print("Elige una opcion: ");
                        String opcionEmpleado = teclado.nextLine();
                        switch (opcionEmpleado) {
                            case "1":
                                do {
                                    System.out.println("------ Alta de Productos ------");
                                    System.out.print("Ingrese el nombre del producto: ");
                                    String nombreProducto = teclado.nextLine();
                                    System.out.print("Ingrese el precio individual del producto: ");
                                    double precioProducto = teclado.nextFloat();
                                    System.out.print("Ingrese la cantidad del productos: ");
                                    int cantidadProducto = teclado.nextInt();
                                    teclado.nextLine();
                                    Producto producto = new Producto(nombreProducto, precioProducto, cantidadProducto);
                                    inventario.agregarProducto(producto);
                                    System.out.println("---------------------------------------------------------");
                                    System.out.println("------ Producto Agregado con exito ------");
                                    System.out.println(" ");
                                    System.out.println("¿Desea agregar otro producto? S) Si / N) No.");
                                    System.out.println(" ");
                                    String decisionAddProducto = teclado.nextLine();
                                    if (!decisionAddProducto.equalsIgnoreCase("s")) {
                                        banderaAddProducto = false;
                                    }
                                } while (banderaAddProducto);
                                break;
                            case "2":
                                System.out.println("------ Realizar Venta ------");
                                System.out.println("Ingrese el nombre del producto a vender: ");
                                String nproductoVender = teclado.nextLine();
                                boolean encontrado = false;
                                for (Producto inven : inventario.getProductos()) {
                                    if (inven.getNombre().equalsIgnoreCase(nproductoVender)) {
                                        System.out.println(" ");
                                        System.out.println("Verificando que exista el producto... ⚙️");
                                        System.out.println("********************************************");
                                        System.out.println("********************************************");
                                        System.out.println("Producto encontrado, puedes continuar...");
                                        System.out.println(" ");
                                        System.out.println("Ingrese la cantidad a vender: ");
                                        int cantidadVender = teclado.nextInt();
                                        teclado.nextLine();
                                        if (inven.getCantidad() >= cantidadVender) {
                                            boolean reduccionExitosa = venta.reducirStock(inventario, nproductoVender, cantidadVender);
                                            if (reduccionExitosa) {
                                                Producto producto = new Producto(nproductoVender, inven.getPrecio(),cantidadVender);
                                                Double gananciaTotal = cantidadVender * inven.getPrecio();
                                                venta.hacerVenta(gananciaTotal);
                                                inventario.registrarVentas(producto);
                                                System.out.println("la venta se ha realizado correctamente.");
                                                System.out.println("Total vendido $" + venta.getTotalDia());

                                            }
                                        } else {
                                            System.out.println("No hay suficiente stock disponible para vender esa cantidad.");
                                        }
                                        encontrado = true;
                                        break;
                                    }
                                }
                                if (!encontrado) {
                                    System.out.println("---------------------------------------------------");
                                    System.out.println("El producto no se encuentra en el inventario.");
                                }
                                break;
                            case "3":
                                int k = 0;
                                System.out.println("------ Productos disponibles ------");
                                for (Producto listaProductos: inventario.getProductos()){
                                    System.out.println((k+1)+".- "+ listaProductos.getNombre());
                                    k++;
                                }
                                break;

                            default:
                                System.out.println("Regresando al Menu de Usuarios.");
                                cerrarEmpleado = false;
                        }
                    } while (cerrarEmpleado);
                    break;
                default:
                    cerrarPrograma = false;
            }
        } while (cerrarPrograma);
    }
}