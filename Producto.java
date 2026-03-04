
public class Producto {

    public int id;
    public String nombre;
    public String categoria;
    public long precio;
    public int stock;

    public Producto(int id, String nombre, String categoria, long precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String toCsv() {
        return id + "," + nombre + "," + categoria + "," + precio + "," + stock + ",";
    }

    public static Producto fromCsv(String line) {
        String[] d = line.split(",", -1);
        return new Producto(
                integer.parseInt(d[0].trim(),
                        d[1].trim(),
                        d[2].trim(),
                        Long.parseLong(d[3].trim),
                        d[4].trim()
                )
        );
    }

}
