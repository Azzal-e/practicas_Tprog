/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionDirNoExiste.java>> para el manejo
 *          de errores cuando se intenta acceder a un directorio inexistente.
 *          Esta excepción se lanza cuando se intenta navegar a un directorio 
 *          que no existe en la ruta especificada, o cuando una ruta esperaba
 *          encontrar un directorio pero el nodo referenciado no lo es.
 ******************************************************************************/
public class ExcepcionDirNoExiste extends ExcepcionArbolFicheros {
    public ExcepcionDirNoExiste(String nombre) {
        super("El directorio '" + nombre + "' no existe en el ámbito de la ruta actual.");
    }
    
}
