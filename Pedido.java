public class Pedido {
   public int id;
    public int clienteId;
    public int productoId;
    public int cantidad;
    public String fecha;

    public Pedido(int id, int clienteId, int productoId, int cantidad, String fecha) {
        this.id = id;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
}