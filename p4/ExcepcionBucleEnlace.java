/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción <<ExcepcionBucleEnlace.java>> para el manejo 
 *          de errores relacionados con bucles en enlaces simbólicos.
 *          Esta excepción se lanza cuando se detecta un bucle infinito al seguir
 *          una cadena de enlaces simbólicos, específicamente cuando se sobrepasa
 *          el límite de 100 enlaces al calcular el tamaño de un nodo.
 ******************************************************************************/

public class ExcepcionBucleEnlace extends ExcepcionArbolFicheros {
    public ExcepcionBucleEnlace() {
        super("Se ha detectado un posible bucle. Se ha alcanzado el límite de enlaces (50).");
    }
}
