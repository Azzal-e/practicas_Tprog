 /*----------------------------PROGRAMA SUMA------------------------------------*/
 
 /*    Clase que representa un programa "Suma", que lee dos números
  *    y muestra su suma, por salida estándar. <<ProgramaSuma>> puede 
  *    ejecutarse y listarse como cualquier otro programa
  */
public class ProgramaSuma extends Programa {
    //Pre: --
    //Post: Instancia un programa "Suma", que contiene las instrucciones necesarias
    //      para leer dos números, calcular su suma y mostrarla por salida estándar.
    public ProgramaSuma() {
        super(4);
        instrucciones[0] = new Read();
        instrucciones[1] = new Read();
        instrucciones[2] = new Add();
        instrucciones[3] = new Write();
    }
}
