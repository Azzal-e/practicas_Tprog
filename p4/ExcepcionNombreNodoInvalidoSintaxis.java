/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero Samaras (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionNombreNodoInvalidoSintaxis.java>> 
 *          para errores de sintaxis en nombres de nodos.
 *          Esta excepción se lanza cuando se intenta crear un nodo con un nombre
 *          que no cumple con las reglas de sintaxis: que sea vacío, contenga 
 *          espacios o incluya el carácter separador '/' en el nombre, lo cual
 *          no está permitido en el sistema de ficheros implementado.
 ******************************************************************************/
public class ExcepcionNombreNodoInvalidoSintaxis extends ExcepcionNombreInvalido {

    public ExcepcionNombreNodoInvalidoSintaxis(String nombre) {
        super("El nombre del nodo '" + nombre + "' no es válido. Debe ser una cadena no vacía, sin espacios, y que no contenga el carácter especial '/'.");
    }
}
