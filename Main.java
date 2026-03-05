import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Ver productos ordenados por precio");
            System.out.println("2. Agregar cliente");
            System.out.println("3. Total ventas por producto");
            System.out.println("4. Ver clientes que compraron");
            System.out.println("5. Salir");

            op = sc.nextInt();
            sc.nextLine();

            try {
                switch (op) {
                    case 1:
                        ordenarProductos();
                        break;

                    case 2:
                        agregarCliente(sc);
                        break;

                    case 3:
                        totalVentas();
                        break;

                    case 4:
                        clientesCompraron();
                        break;

                    case 5:
                        System.out.println("Listo.");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (op != 5);

        sc.close();
    }

    public static void ordenarProductos() throws Exception {
        ArrayList<Producto> lista = new ArrayList<>();

        Scanner sc = new Scanner(new File("productos.csv"));
        if (sc.hasNextLine())
            sc.nextLine();

        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] d = linea.split(",", -1);

            int id = Integer.parseInt(d[0].trim());
            String nombre = d[1].trim();
            String categoria = d[2].trim();
            long precio = Long.parseLong(d[3].trim());
            int stock = Integer.parseInt(d[4].trim());

            lista.add(new Producto(id, nombre, categoria, precio, stock));
        }
        sc.close();

        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - 1 - i; j++) {
                if (lista.get(j).precio > lista.get(j + 1).precio) {
                    Producto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter("productos.csv"));
        pw.println("id,nombre,categoria,precio,stock");
        for (Producto p : lista) {
            pw.println(p.id + "," + p.nombre + "," + p.categoria + "," + p.precio + "," + p.stock);
        }
        pw.close();

        System.out.println("Productos ordenados por precio (asc):");
        for (Producto p : lista) {
            System.out.println(p.nombre + " | $" + p.precio + " | stock=" + p.stock);
        }
    }

    public static void agregarCliente(Scanner sc) throws Exception {
        File f = new File("clientes.csv");
        if (!f.exists()) {
            PrintWriter pw = new PrintWriter(new FileWriter("clientes.csv"));
            pw.println("id,nombre,email");
            pw.close();
        }

        int maxId = 0;

        Scanner scFile = new Scanner(new File("clientes.csv"));
        if (scFile.hasNextLine())
            scFile.nextLine();

        while (scFile.hasNextLine()) {
            String linea = scFile.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] d = linea.split(",", -1);
            int id = Integer.parseInt(d[0].trim());
            if (id > maxId)
                maxId = id;
        }
        scFile.close();

        int nuevoId = maxId + 1;

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        ArrayList<String> lineas = new ArrayList<>();
        Scanner scAll = new Scanner(new File("clientes.csv"));
        while (scAll.hasNextLine()) {
            lineas.add(scAll.nextLine());
        }
        scAll.close();

        PrintWriter pw = new PrintWriter(new FileWriter("clientes.csv"));
        for (String l : lineas)
            pw.println(l);
        pw.println(nuevoId + "," + nombre + "," + email);
        pw.close();

        System.out.println("Cliente agregado con ID: " + nuevoId);
    }

    public static void totalVentas() throws Exception {
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();

        Scanner sc1 = new Scanner(new File("productos.csv"));
        if (sc1.hasNextLine())
            sc1.nextLine();

        while (sc1.hasNextLine()) {
            String linea = sc1.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] d = linea.split(",", -1);

            productos.add(new Producto(
                    Integer.parseInt(d[0].trim()),
                    d[1].trim(),
                    d[2].trim(),
                    Long.parseLong(d[3].trim()),
                    Integer.parseInt(d[4].trim())));
        }
        sc1.close();

        Scanner sc2 = new Scanner(new File("pedidos.csv"));
        if (sc2.hasNextLine())
            sc2.nextLine();

        while (sc2.hasNextLine()) {
            String linea = sc2.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] d = linea.split(",", -1);

            pedidos.add(new Pedido(
                    Integer.parseInt(d[0].trim()),
                    Integer.parseInt(d[1].trim()),
                    Integer.parseInt(d[2].trim()),
                    Integer.parseInt(d[3].trim()),
                    d[4].trim()));
        }
        sc2.close();

        for (Producto p : productos) {
            long total = 0;

            for (Pedido ped : pedidos) {
                if (ped.productoId == p.id) {
                    total += (long) ped.cantidad * p.precio;
                }
            }

            ventas.add(new Venta(p.id, p.nombre, total));
        }

        for (int i = 0; i < ventas.size() - 1; i++) {
            for (int j = 0; j < ventas.size() - 1 - i; j++) {
                if (ventas.get(j).total < ventas.get(j + 1).total) {
                    Venta tmp = ventas.get(j);
                    ventas.set(j, ventas.get(j + 1));
                    ventas.set(j + 1, tmp);
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter("total_ventas.csv"));
        pw.println("producto_id,nombre_producto,total");
        for (Venta v : ventas) {
            pw.println(v.productoId + "," + v.nombreProducto + "," + v.total);
        }
        pw.close();

        System.out.println("Total ventas por producto (desc):");
        for (Venta v : ventas) {
            System.out.println(v.nombreProducto + " | total=" + v.total);
        }
    }

    public static void clientesCompraron() throws Exception {
        ArrayList<Integer> idsCompradores = new ArrayList<>();
        ArrayList<Cliente> compradores = new ArrayList<>();

        Scanner scP = new Scanner(new File("pedidos.csv"));
        if (scP.hasNextLine())
            scP.nextLine();

        while (scP.hasNextLine()) {
            String linea = scP.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] d = linea.split(",", -1);
            int idCliente = Integer.parseInt(d[1].trim());

            boolean ya = false;
            for (int x : idsCompradores) {
                if (x == idCliente) {
                    ya = true;
                    break;
                }
            }
            if (!ya)
                idsCompradores.add(idCliente);
        }
        scP.close();

        Scanner scC = new Scanner(new File("clientes.csv"));
        if (scC.hasNextLine())
            scC.nextLine();

        while (scC.hasNextLine()) {
            String linea = scC.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] d = linea.split(",", -1);
            int id = Integer.parseInt(d[0].trim());
            String nombre = d[1].trim();
            String email = d[2].trim();

            for (int x : idsCompradores) {
                if (x == id) {
                    compradores.add(new Cliente(id, nombre, email));
                    break;
                }
            }
        }
        scC.close();

        for (int i = 0; i < compradores.size() - 1; i++) {
            for (int j = 0; j < compradores.size() - 1 - i; j++) {
                String n1 = compradores.get(j).nombre.toLowerCase();
                String n2 = compradores.get(j + 1).nombre.toLowerCase();

                if (n1.compareTo(n2) > 0) {
                    Cliente tmp = compradores.get(j);
                    compradores.set(j, compradores.get(j + 1));
                    compradores.set(j + 1, tmp);
                }
            }
        }

        System.out.println("Clientes que han comprado (ordenados):");
        for (Cliente c : compradores) {
            System.out.println(c.nombre + " | " + c.email);
        }
    }
}