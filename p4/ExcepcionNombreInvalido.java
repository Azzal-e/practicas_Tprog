/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de excepción base <<ExcepcionNombreInvalido.java>> para 
 *          errores relacionados con nombres de nodos inválidos.
 *          Esta clase es la base para las excepciones específicas relacionadas
 *          con nombres de nodo inválidos, como nombres con sintaxis incorrecta
 *          o nombres reservados. Proporciona un mecanismo común para capturar
 *          todos los tipos de errores de nombre.
 ******************************************************************************/
public class ExcepcionNombreInvalido extends ExcepcionArbolFicheros {
    public ExcepcionNombreInvalido(String mensaje) {
        super(mensaje);
    }
    
}
