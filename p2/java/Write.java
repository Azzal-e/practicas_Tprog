/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Write.java>> con la definición e implementación 
 * de la clase <<Write>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE Instruccion: Write---------------*/

/*
    Clase derivada de <<Instruccion>>, que representa una instrucción "Write",
    la cual muestra un valor y lo elimina.
*/
public class Write extends Instruccion {

    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "Write" con el nombre "write".
    public Write() {
        super("write");
    }

    // Pre:     <<stack>> tiene al menos 1 elemento apilado.
    // Post:    Desapila el valor en la cima de <<stack>> y lo
    //          escribe en la salida estándar. Aumenta <<PC[0]>> en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC) {
        System.out.println(stack.pop());
        PC[0]++;
    }
}
