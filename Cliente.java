public class Cliente {
    private int id;
    private String nombre;
    private String email;

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + email;
    }

     public static Cliente fromCsv(String line) {
        String[] d = line.split(",", -1);
        return new Producto(
                integer.parseInt(d[0].trim(),
                        d[1].trim(),
                        d[2].trim(),
                        d[3].trim()
                        
                )
        );
    }
}