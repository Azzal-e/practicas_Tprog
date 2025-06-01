/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionNodoNoExiste.java>> para el manejo
 *          de errores cuando se intenta acceder a un nodo inexistente.
 *          Esta excepción se lanza cuando se intenta acceder o manipular un nodo
 *          (ya sea fichero, directorio o enlace) que no existe en la ruta 
 *          especificada.
 ******************************************************************************/
public class ExcepcionNodoNoExiste extends ExcepcionArbolFicheros {
    public ExcepcionNodoNoExiste(String nombre_nodo){
        super("No existe el nodo " + nombre_nodo + " en el directorio final de la ruta especificada");
    }
}
