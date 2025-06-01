/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Mul.java>> con la definición e implementación 
 * de la clase <<Mul>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE Instruccion: Mul---------------*/

/*
    Clase derivada de <<Instruccion>>, que representa una instrucción "mul",
    la cual multiplica los dos elementos de la cima de una pila.
*/
public class Mul extends Instruccion {

    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "Mul" con el nombre "mul".
    public Mul(){
        super("mul");
    }

    // Pre:     <<stack>> tiene al menos 2 elementos apilados
    // Post:    Se desapilan los 2 primeros valores de <<stack>>, se
    //          multiplican y el valor resultante es apilado en <<stack>>
    //          el contador de programa <<PC[0]>> se incrementa en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC){
        Integer valor1 = stack.pop();
        Integer valor2 = stack.pop();
        stack.push(valor1*valor2);
        PC[0]++;
    }

}
