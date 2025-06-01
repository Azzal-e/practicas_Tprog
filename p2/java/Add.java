/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Add.java>> con la definición e implementación 
 * de la clase <<Add>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
 /*-------------------CLASE DERIVADA DE Instruccion: Add---------------*/

/*
Clase derivada de <<Instruccion>>, que representa una instrucción "Add",
la cual suma los dos elementos de la cima de una pila.
*/
public class Add extends Instruccion {

    // Pre:     -----
    // Post:    Crea una instancia de la instrucción "Add" con el nombre "add".
    public Add() {
        super("add");
    }
    // Pre:     <<stack>> tiene al menos 2 elementos apilados.
    // Post:    Devuelve el <<stack>> resultante de desapilar los 2 valores
    //          de la cima de la pila y apilar el valor resultante de la
    //          suma de éstos, avanza <<PC[0]>> en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC) {
        // Desapilamos los 2 valores de la cima de la pila
        int a = stack.pop();
        int b = stack.pop();
        // Apilamos el resultado de la suma de ambos valores
        stack.push(a + b);
        // Incrementamos el contador de programa en 1
        PC[0]++;
    }
}
