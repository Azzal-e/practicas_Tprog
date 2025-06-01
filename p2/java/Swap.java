/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Swap.java>> con la definición e implementación 
 * de la clase <<Swap>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE Instruccion: Swap---------------*/

/*
    Clase derivada de <<Instruccion>>, que representa una instrucción "Swap",
    la cual intercambia los dos elementos de la cima de una pila.
*/
public class Swap extends Instruccion{

    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "Swap" con el nombre "swap".
    public Swap() {
        super("swap");
    }

    // Pre:     <<stack>> tiene al menos 2 elementos apilados.
    // Post:    Se desapilan los dos valores de la cima de
    //          <<stack>>, se reapilan en orden inverso y se incrementa
    //          <<PC[0]>> en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC) {
        int valor1 = stack.pop();
        int valor2 = stack.pop();
        stack.push(valor1);
        stack.push(valor2);
        PC[0]++;
    }
}
