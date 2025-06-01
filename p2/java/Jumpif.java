/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Jumpif.java>> con la definición e implementación 
 * de la clase <<Jumpif>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE InstruccionParam: Jumpif---------------*/

/*
    Clase derivada de <<InstruccionParam>>, que representa una instrucción "Jumpif",
    la cual realiza un salto condicional a una instrucción <l> si el valor en la cima de una pila es
    mayor o igual a 0.
*/
public class Jumpif extends Instruccion {
    
    private int param;
    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "Jumpif" con el nombre "Jumpif <param>".
    public Jumpif(int param) {
        super("jumpif " + param);
        this.param = param;
    }
    
    // Pre:     <<stack>> tiene al menos 1 elementos apilado.
    // Post:    Desapila el valor en la cima de <<stack>>.
    //          Si el valor es mayor o igual que 0, se salta <<PC[0]>> a la instrucción <this.param>, y 
    //          si no, se avanza el contador de programa <<PC[0]>> en 1.
    @Override
    public void ejecutar(Stack<Integer> stack, Integer[] PC) {
        if (stack.pop() >= 0) {
            PC[0] = param;
        } else {
            PC[0]++;
        }
    }

}
