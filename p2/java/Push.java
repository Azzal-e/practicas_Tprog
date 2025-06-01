/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Push.java>> con la definición e implementación 
 * de la clase <<Push>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Stack;
/*-------------------CLASE DERIVADA DE InstruccionParam: Read---------------*/

/*
    Clase derivada de <<Instruccion>>, que representa una instrucción "push",
    la cual apila un valor <c>.
*/
public class Push extends Instruccion {
    
    private int param;
    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "push" con el nombre "push <param>".
    public Push(int param)
    {
        super("push " + param);
        this.param = param;
    }

    // Pre:     -----
    // Post:    Devuelve el <<stack>> resultante de apilar 'param' en <<stack>>
    //          y aumenta <<PC[0]>> en 1.
    @Override
    public void ejecutar( Stack<Integer> stack, Integer[] PC ) {
        stack.push(param);
        PC[0]++;
    }
}
