/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Dup.java>> con la definición e implementación 
 * de la clase <<Dup>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE Instruccion: Dup---------------
Clase derivada de <<Instruccion>>, que representa una instrucción "Dup",
la cual duplica la cima de una pila.
*/
public class Dup extends Instruccion {

    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "Dup" con el nombre "dup".
    public Dup() {
        super("dup");
    }
    // Pre:     <<stack>> tiene al menos 1 elementos apilado.
    // Post:    Devuelve un <<stack>> resultante de apilar el elemento
    //          que se encuentra en la cima 2 veces e incrementa <<PC[0]>>
    //          en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC) {
        int valor = stack.pop();
        stack.push(valor);
        stack.push(valor);
        PC[0]++;
    }
}
