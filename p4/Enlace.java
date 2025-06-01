/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de implementación <<Enlace.java>> que representa enlaces
 *          simbólicos en el sistema de ficheros.
 *          La clase Enlace hereda de Nodo y contiene una referencia a otro nodo
 *          (fichero, directorio o incluso otro enlace). Implementa mecanismos
 *          para resolver el nodo "duro" al que apunta, incluso a través de
 *          cadenas de enlaces, con protección contra bucles infinitos.
 *          El tamaño de un enlace es el tamaño del nodo final al que apunta.
 ******************************************************************************/


 public class Enlace extends Nodo {
     // Atributos de clase
     Nodo nodeReference;
 
     //Pre: nombre debe cumplir con las restricciones de nombres de Nodo
     //     nodo no debe ser null y es el nodo al que apuntará este enlace
     //     padre no puede ser null (excepto para la raíz)
     //Post: Se crea un nuevo enlace con el nombre, referencia y padre especificados
     public Enlace(String nombre, Nodo nodo, Directorio padre) throws ExcepcionNombreInvalido{
         super(nombre,padre);
         nodeReference = nodo;
     }
 
     //Pre: links debe ser un entero no negativo que representa el límite de enlaces a seguir
     //Post: Devuelve el tamaño del nodo final al que apunta este enlace,
     //      o lanza una excepción si se excede el límite de enlaces o se detecta un bucle
     @Override
     public int size(Integer links) throws ExcepcionBucleEnlace {
        // Se obtiene referencia al nodo 'hard'
        if (links > 50){ // Se establece límite a bucle infinito
            throw new ExcepcionBucleEnlace();
        }
        Nodo node = this.getHardNode(20);
        return node.size(links+1); // Se obtiene el tamaño del nodo 'hard'
     }
 
     //Pre: iterLimits debe ser un entero positivo que representa el máximo de 
     //     enlaces encadenados que se pueden seguir
     //Post: Devuelve el nodo "duro" (no enlace) al que apunta este enlace
     //      siguiendo la cadena de enlaces, o lanza una excepción si se excede
     //      el límite de iteraciones para evitar bucles infinitos
     public Nodo getHardNode(int iterLimits) throws ExcepcionBucleEnlace {
         if (iterLimits == 0){ // Con esto se establece límite a bucle infinito
             throw new ExcepcionBucleEnlace();
         }
         else if (nodeReference instanceof Enlace){ //  Se el nodo referencia es enlace, se llama recursivamente
             return ((Enlace) nodeReference).getHardNode(iterLimits - 1);
         }
         else{ // Se llega a un hard node.
             return nodeReference;
         }
     }
 }