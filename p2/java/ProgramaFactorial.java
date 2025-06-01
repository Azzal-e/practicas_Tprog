/*----------------------------PROGRAMA FACTORIAL------------------------------------*/

/*    Clase que representa un programa "Factorial", que calcula el factorial de un número
 *    y muestra por pantalla el resultado. <<ProgramaFactorial>> puede ejecutarse y listarse
 *    como cualquier otro programa.
 */
public class ProgramaFactorial extends Programa {
    public ProgramaFactorial() {
        // Pre: --
        // Post: Instancia un programa "Factorial", que contiene las instrucciones necesarias
        //       para leer un número y calcular su factorial
        super(14);
        instrucciones[0] = new Push(1);
        instrucciones[1] = new Read();
        instrucciones[2] = new Swap();
        instrucciones[3] = new Over();
        instrucciones[4] = new Mul();
        instrucciones[5] = new Swap();
        instrucciones[6] = new Push(-1);
        instrucciones[7] = new Add();
        instrucciones[8] = new Dup();
        instrucciones[9] = new Push(-2);
        instrucciones[10] = new Add();
        instrucciones[11] = new Jumpif(2);
        instrucciones[12] = new Swap();
        instrucciones[13] = new Write();
    }
}