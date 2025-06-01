/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de implementación <<Directorio.java>> que representa un
 *          directorio en el sistema de ficheros.
 *          La clase Directorio hereda de Nodo y contiene un mapa que almacena
 *          los nodos (ficheros, directorios o enlaces) que contiene. Implementa
 *          el método size() que calcula recursivamente el tamaño total del 
 *          directorio incluyendo todos sus elementos contenidos.
 *          Proporciona métodos para añadir nodos y acceder a su contenido.
 ******************************************************************************/

 import java.util.HashMap;

 public class Directorio extends Nodo{
     // Atributos de la clase
     private HashMap<String, Nodo> nodes;
     
     //Pre: nombre debe cumplir con las restricciones de nombres de Nodo
     //     padre puede ser null solo para el nodo raíz
     //Post: Se crea un nuevo directorio vacío con el nombre y padre especificados
     public Directorio(String name, Directorio padre) throws ExcepcionNombreInvalido {
         super(name, padre);
         nodes = new HashMap<String, Nodo>();
     }
 
     //Pre: links debe ser un entero no negativo que representa el límite de enlaces a seguir
     //Post: Devuelve el tamaño total del directorio sumando el tamaño de todos sus nodos contenidos,
     //      o lanza una excepción si se excede el límite de enlaces
     @Override
     public int size(Integer links)throws ExcepcionBucleEnlace{
        int sz = 0;
         for(Nodo node: nodes.values()){
             sz = sz + node.size(links); // Se obtiene el tamaño del nodo
         }
 
         return sz;
     }
 
     //Pre: True
     //Post: Devuelve el mapa de nodos contenidos en el directorio sin modificarlo
     public HashMap<String, Nodo> getNodes(){
         return nodes;
     }
 
     //Pre: node no debe ser null y debe tener un nombre único en este directorio
     //Post: Añade el nodo especificado al directorio, utilizando su nombre como clave
     public void addNode(Nodo node){
         nodes.put(node.getNombre(), node);
     }
 }