/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionNodoEsDirectorio.java>> para el manejo
 *          de errores cuando se intenta realizar operaciones no permitidas en
 *          directorios.
 *          Esta excepción se lanza cuando una operación esperaba encontrar un 
 *          fichero (o enlace a fichero) pero el nodo referenciado es un directorio,
 *          como por ejemplo al intentar modificar el tamaño de un directorio con
 *          el comando vi.
 ******************************************************************************/
public class ExcepcionNodoEsDirectorio extends ExcepcionArbolFicheros {
    public ExcepcionNodoEsDirectorio(String nombre) {
        super("El nodo '" + nombre + "' es un directorio. No se puede realizar la operación en un directorio.");
    }
}
