/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Over.java>> con la definición e implementación 
 * de la clase <<Over>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE Instruccion: Over---------------*/

/*  Clase derivada de <<Instruccion>>, que representa una instrucción "Over",
    la cual duplica el elemento que está antes de la cima de una pila.  
*/
public class Over extends Instruccion{
    
    // Pre:     ------
    // Post:    Construye una instancia de <<Over>> con el nombre "over".
    public Over() {
        super("over");
    }

    // Pre: <<stack>> tiene al menos 2 elementos apilados.
    // Post: Se apila el elemento que está antes de la cima de <<stack>>
    //       y se avanza el contador de programa <<PC[0]>> en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC) {
        int valor1 = stack.pop(); // Cima
        int valor2 = stack.pop(); // Antes de la cima
        stack.push(valor2);
        stack.push(valor1);
        stack.push(valor2);
        PC[0]++;
    }
}
