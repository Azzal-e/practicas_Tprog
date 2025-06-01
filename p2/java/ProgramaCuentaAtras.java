/*----------------------------PROGRAMA CUENTA ATRÁS------------------------------------*/

/* Clase que representa un programa "Cuenta atrás", que lee un entero positivo
 * y muestra por pantalla una cuenta atrás desde ese número hasta 0. <<ProgramaCuentaAtras>>
 * puede ejecutarse y listarse como cualquier otro programa.
 */
public class ProgramaCuentaAtras extends Programa {
    //Pre: --
    //Post: Instancia un programa "Cuenta atrás", que contiene las instrucciones necesarias
    //      para leer un entero positivo y mostrar por pantalla la cuenta atrás de ese número
    public ProgramaCuentaAtras() 
    {
        super(7);
        instrucciones[0] = new Read();
        instrucciones[1] = new Dup();
        instrucciones[2] = new Write();
        instrucciones[3] = new Push(-1);
        instrucciones[4] = new Add();
        instrucciones[5] = new Dup();
        instrucciones[6] = new Jumpif(1);
    }
}

