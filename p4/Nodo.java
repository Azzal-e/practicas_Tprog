/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de interfaz <<Nodo.java>> con la definición de la clase base
 *          para esta práctica.
 *          Contiene una clase base abstracta <<Nodo>>, que sirve para implementar
 *          herencia mediante polimorfismo por inclusión. De esta clase heredan <<Fichero>>
 *          <<Directorio>> y <<Enlace>>, implementando así una estructura de árbol
 *          de ficheros. Los directorios pueden contener otros nodos, mientras que
 *          los enlaces apuntan a otros nodos existentes.
 *          
 *          La estructura implementa un sistema de excepciones para controlar
 *          diversos errores como bucles en enlaces, nombres inválidos, nodos
 *          inexistentes o duplicados, entre otros.
 ******************************************************************************/

public abstract class Nodo {
    
    // Atributos de la clase
    protected String nombre;
    protected Directorio padre; // Directorio que contiene al nodo, solo null para el nodo raíz.

    // Métodos de la clase
    //Pre: nombre no debe ser null, vacío, contener espacios o barras "/", ni ser "." o ".."
    //     padre puede ser null solo para el nodo raíz
    //Post: Se crea un nuevo nodo con el nombre y padre especificado
    public Nodo(String nombre, Directorio padre) throws ExcepcionNombreNodoInvalidoSintaxis, ExcepcionNombreNodoReservado {
        // Comprobación de nombre de nodo
        if (nombre == null || nombre.isEmpty() || nombre.contains(" ") || nombre.contains("/")) {
            throw new ExcepcionNombreNodoInvalidoSintaxis(nombre);
        }
        if (nombre.equals("..") || nombre.equals(".")) {
            throw new ExcepcionNombreNodoReservado(nombre);
        }
        
        this.nombre = nombre;
        this.padre = padre;
    }
    //Pre: El nodo debe estar correctamente inicializado
    //Post: Devuelve el nombre del nodo sin modificarlo
    public String getNombre(){
        return this.nombre;
    }
    //Pre: True
    //Post: Devuelve el tamaño total del nodo en bytes después de resolver todos los enlaces
    public abstract int size(Integer links) throws ExcepcionBucleEnlace;
}
