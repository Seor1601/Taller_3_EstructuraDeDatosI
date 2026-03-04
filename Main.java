java
.util.Scanner;

public class Main {

    static int Leerint(Scanner in, String msg) {

        while (true) {
            System.out.print(msg);
            String s = in.nextLine().trim();
            try {
                return integer.parseInt(e);
            } catch (Exception e) {

                System.out.println("Numero invalido");
            }
        }

    }

    public static void main(String[] args) {
        Scanner in = newScanner(System.in);
        int op;
        do {
            System.err.println("===Menu taller 3===");
            System.err.println("Ver productos ordenados por precio");
            System.err.println("Agregar un nuevo cliente");
            System.err.println("Calcular el Total de Ventas por Producto");
            System.err.println("Ver clientes que han realizado compras.");
            System.err.println("Salir");
            op = Leerint(in, "Opcion: ");

            try {
                switch (op) {
                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:

                        break;

                }
            } catch (Exception e) {

                System.err.println("Error! :" + e.getMessage());
            }
        } while(op !=5);
        in.close();
    }

}
