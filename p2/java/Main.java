/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 2
 * Autores: Athanasios Usero (NIP: 839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 20/05/2025
 * Resumen: Fichero con el programa principal, el cual crea los programas correspondientes
 *          a las clases derivadas en los respectivos ficheros .java, 
 *          los lista, y los ejecuta (cada uno por separado).
 ******************************************************************************/


 /* Pre: --
    Post: Crea un vector de punteros a <<Programa>> con los tres tipos de programas
          derivados "suma", "cuenta atras" y "factorial"; y para cada uno, lo lista y
          ejecuta
*/
 public class Main {
    public static void main(String[] args) {
        // Construir vector de punteros a programas.
        Programa[] programas = new Programa[3];
        programas[0] = new ProgramaSuma();
        programas[1] = new ProgramaCuentaAtras();
        programas[2] = new ProgramaFactorial();

        // Listar y ejecutar cada programa
        for (Programa programa : programas) {
            System.out.println("Programa: ");
            programa.listarInstrucciones();
            System.out.println();
            System.out.println("Ejecucion: ");
            programa.ejecutar();
            System.out.println();
            System.out.println();
        }
    }
}