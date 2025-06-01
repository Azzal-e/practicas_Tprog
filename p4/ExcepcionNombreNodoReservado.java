public class ExcepcionNombreNodoReservado extends ExcepcionNombreInvalido {
    public ExcepcionNombreNodoReservado(String nombre) {
        super("El nombre del nodo '" + nombre + "' es un nombre reservado. No se pueden usar nombres con significado especial '..' (directorio padre) o '.' (directorio actual).");

    }
}