import java.util.Stack;

 /*Clase que representa un programa, compuesto de un vector de instrucciones.
      El programa puede ejecutar o listar sus instrucciones.
*/
public class Programa {
    protected Instruccion[] instrucciones;

    // Pre: -----
    // Post: Instancia un programa vacío, sin instrucciones.
    public Programa(){}

    //Pre: -----
    // Post: Instancia un programa con un número de instrucciones <<numIns>>, pero
    // con instrucciones sin definir
    public Programa(int nIns){
        instrucciones = new Instruccion[nIns];
    }


    // Pre: ------
    // Post: Ejecuta <<this>>, es decir, comienza a ejecutar desde la primera instrucción hasta que
    //       el contador de programa sobrepase la última.
    public void ejecutar() {
        Integer[] PC = new Integer[]{0};
        Stack<Integer> stack = new Stack<Integer>();
        while (PC[0] < instrucciones.length && PC[0] >= 0) {
            instrucciones[PC[0]].ejecutar(stack, PC);
        }
    }
    // Pre: --
    // Post: Muestra por salida estándar todas las instrucciones de <<this>>, línea por
    //       línea por línea, junto al contador de programa que les corresponde.
    public void listarInstrucciones(){
        Integer i = 0;
        for(Instruccion instruccion : instrucciones){
            System.out.println(i + "\t" + instruccion.nombre());
            i++;
        }
    }
}
