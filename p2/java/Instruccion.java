/*******************************************************************************
/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Instruccion.java>> con la definición de la clase
 *          <<Instruccion>> y sus métodos. 
 ******************************************************************************/

 import java.util.Stack;
/*-------------------CLASE BASE: Instruccion---------------*/
public abstract class Instruccion {
    protected String nombre;

    /* Pre: --
       Post: Construye una instancia de <<Instruccion>> con el nombre <nombre>.
    */
    public Instruccion(String nombre)
    {
        this.nombre = nombre;
    }
    // Pre:     -----
    // Post:    Devuelve una cadena con el nombre identificativo de  la instrucción <<this>>.
    // Nota:    Método abstracto, por lo que debe ser implementado por toda clase
    //          derivada de <<Instruccion>>.
    public String nombre(){
        return nombre;
    }

    // Pre:     -----
    // Post:    Ejecuta la instrucción <<this>>, modificando la pila <<stack>>
    //          acorde a su ejecución y actualizando el contador de programa <<PC[0]>>.
    // Nota:    Método abstracto, por lo que debe ser implementado por toda clase
    //          derivada de <<Instruccion>>.
    public abstract void ejecutar( Stack<Integer> stack, Integer[] PC);
}


