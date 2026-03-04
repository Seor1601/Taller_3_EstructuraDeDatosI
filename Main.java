import java.util.Scanner;

public class Main {

    public static void main(String[] args){
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
                switch(op) {
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

        } while(op != 5);

        sc.close();
    }

    public static void ordenarProductos() throws Exception {
        System.out.println("Pendiente opcion 1");
    }

    public static void agregarCliente(Scanner sc) throws Exception {
        System.out.println("Pendiente opcion 2");
    }

    public static void totalVentas() throws Exception {
        System.out.println("Pendiente opcion 3");
    }

    public static void clientesCompraron() throws Exception {
        System.out.println("Pendiente opcion 4");
    }
}