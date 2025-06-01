/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción base <<ExcepcionArbolFicheros.java>> para el
 *          sistema de manejo de errores de la aplicación.
 *          Define la clase base de todas las excepciones del árbol de ficheros,
 *          que hereda de Exception. Todas las excepciones específicas del sistema
 *          de ficheros extenderán de esta clase para permitir un manejo unificado
 *          de errores en la aplicación.
 ******************************************************************************/
public class ExcepcionArbolFicheros extends Exception{
    public ExcepcionArbolFicheros(String message){
        super(message);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

