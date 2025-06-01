/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 24/05/2025
 * Resumen: Fichero de implementación <<Read.java>> con la definición e implementación 
 * de la clase <<Read>> y la implementación de su método abstracto 'ejecutar'.
 ******************************************************************************/
import java.util.Scanner;
import java.util.Stack;

/*-------------------CLASE DERIVADA DE Instruccion: Read---------------*/

/*
    Clase derivada de <<Instruccion>>, que representa una instrucción "Read",
    la cual lee un valor entero desde la entrada estándar y lo apila.
 */
public class Read extends Instruccion {

    // Pre:     -----
    // Post:    Construye una instancia de la instrucción "Read" con el nombre "read".
    public Read() {
        super("read");
    }

    // Pre:     Se debe de poder leer de entrada estándar.
    // Post:    Apila un entero en <<stack>> leído de
    //          la entrada estándar e incrementa <<PC[0]>> en 1.
    @Override
    public void ejecutar(Stack<Integer> stack , Integer[] PC) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("? ");
        int a = scanner.nextInt();
        stack.push(a);
        PC[0]++;
    }
}
