/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionNodoYaExistente.java>> para el manejo
 *          de errores al crear nodos con nombres duplicados.
 *          Esta excepción se lanza cuando se intenta crear un nuevo nodo 
 *          (directorio, fichero o enlace) con un nombre que ya existe en el 
 *          directorio actual, ya que no se permite tener nodos con el mismo 
 *          nombre dentro de un mismo directorio.
 ******************************************************************************/
public class ExcepcionNodoYaExistente extends ExcepcionArbolFicheros {
    public ExcepcionNodoYaExistente(String nombre) {
        super("Ya existe un nodo con el nombre '" + nombre + "' en el directorio actual. No se pueden crear nodos con el mismo nombre en el mismo directorio.");
    }
}
