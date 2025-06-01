/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de implementación <<Fichero.java>> para la clase que representa
 *          un archivo dentro del sistema de ficheros.
 *          La clase Fichero hereda de Nodo y contiene un atributo para representar
 *          el tamaño del archivo en bytes. Implementa el método size() que devuelve
 *          el tamaño del fichero.
 *          Permite la creación de ficheros con un nombre y tamaño especificado,
 *          así como la modificación posterior del tamaño.
 ******************************************************************************/

 public class Fichero extends Nodo {
    // Atributos de clase
    protected int size;

    //Pre: nombre debe cumplir con las restricciones de nombres de Nodo
    //     size debe ser un valor no negativo
    //     padre no puede ser null (excepto para la raíz)
    //Post: Se crea un nuevo fichero con el nombre, tamaño y padre especificados
    public Fichero(String nombre, int size, Directorio padre) throws ExcepcionNombreInvalido, ExcepcionSizeNegativo {
        super(nombre, padre);
        if (size < 0) {
            throw new ExcepcionSizeNegativo(size);
        }
        else{
            this.size = size;
        }
    }

    //Pre: links debe ser un entero no negativo que representa el límite de enlaces a seguir
    //Post: Devuelve el tamaño del fichero, o lanza una excepción si se excede el límite de enlaces
    @Override
    public int size(Integer links) throws ExcepcionBucleEnlace {
        if (links > 100) {
            throw new ExcepcionBucleEnlace();
        }
        return size;
    }

    //Pre: size debe ser un valor no negativo
    //Post: Modifica el tamaño del fichero al valor especificado o lanza una excepción si el valor es negativo
    public void setSize(int size) throws ExcepcionSizeNegativo{
        if (size < 0) {
            throw new ExcepcionSizeNegativo(size);
        }
        else{
            this.size = size;
        }
    }
}