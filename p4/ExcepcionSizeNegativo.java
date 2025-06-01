/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionSizeNegativo.java>> para el manejo
 *          de errores cuando se especifica un tamaño negativo para un fichero.
 *          Esta excepción hereda de ExcepcionArbolFicheros y se lanza cuando
 *          se intenta crear un fichero con un tamaño negativo o modificar el
 *          tamaño de un fichero existente asignándole un valor negativo, lo
 *          cual no está permitido en el sistema de ficheros implementado.
 ******************************************************************************/

public class ExcepcionSizeNegativo extends ExcepcionArbolFicheros {
    //Pre: size < 0
    //Post: Se crea una nueva excepción con un mensaje explicativo que incluye el valor negativo
    public ExcepcionSizeNegativo(int size) {
        super("El tamaño del fichero no puede ser negativo. Se ha recibido un tamaño de: " + size + " bytes.");
    }
}
