/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 4
 * Autores: Athanasios Usero (NIP:839543) y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 29/03/2025
 * Resumen: Fichero de interfaz <<Shell.java>> que representa un intérprete de
 *        comandos para un sistema de archivos virtual. Este intérprete consta de una
 *         estructura de archivos en árbol (donde los nodos pueden ser ficheros, directorios o
 *         enlaces) con un directorio raíz y ruta de trabajo. Permite realizar las operaciones:
 *          pwd, ls, du, vi, mkdir, cd, ln, stat y rm.
 *          
 *          La estructura implementa un sistema de excepciones para controlar
 *          diversos errores como bucles en enlaces, nombres inválidos, nodos
 *          inexistentes o duplicados, entre otros.
 ******************************************************************************/

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;


public class Shell{

    // Atributos de clase
    private Directorio root;
    private Stack<Nodo> workPath;
    private Directorio CWD;
    private int iterLimits = 50; // Límite de iteraciones de enlaces para evitar bucles infinitos
                                 // Es decir, número máximo de enlaces concatenados para que el "hard node"
                                 // pueda ser efectivamente referenciado por el primero.
    // Métodos de clase auxiliares

    /*
     * Pre: <path> constituye una ruta válida, sea relativa o absoluta a un nodo.
     *  Post: Devuelve el Nodo referenciado por la ruta <path
     */
    private Nodo getNodeFromPath(String path) throws ExcepcionBucleEnlace, ExcepcionDirNoExiste, ExcepcionNodoNoExiste{

        // Se hace copia de la ruta de datos para navegar por ella
        Stack<Nodo> auxWorkPath = new Stack<>();
        auxWorkPath.addAll(workPath);

        // Una cosa distinta es el nodo a devolver y otra el nodo por el que se navega.
        // En caso de enlaces, se navega por el hard node referenciado, pero el nodo a devolver
        // es el enlace.
        Directorio node = CWD;
        Nodo nodeReturn = workPath.isEmpty() ? root : workPath.peek();

        // Comprobar si la ruta es absoluta o relativa
        if(path.startsWith("/")){ // La ruta es absoluta
            // Si la ruta es absoluta, se limpia el workPath.
            auxWorkPath.clear();
            node = root;
            nodeReturn = root;
            path = path.substring(1); // Se elimina el primer caracter "/" de la ruta
        }

        if (path.isEmpty()){
            // Si la ruta es vacía, se devuelve nodo actual
            return nodeReturn;
        }

        // Se divide el path en nodos con "/" como separador
        String[] pathNodes = path.split("/");


        for(int i = 0; i < pathNodes.length - 1; i++){
            String node_name = pathNodes[i];
            if(node_name.equals(".")){
                // NADA
            }
            else if (node_name.equals("..")){
                // Se retrocede al anterior directorio de la ruta
                // Esto equivale a eliminar el último nodo de la pila de trabajo
                if(!auxWorkPath.isEmpty()){
                    auxWorkPath.pop(); // Siempre se elimina el último nodo de la ruta
                    if(auxWorkPath.isEmpty()){ // Si ruta queda vacia, estamos en root.
                        node = root;
                    }
                    else{ // Si no, se retrocede al directorio anterior
                        if(auxWorkPath.peek() instanceof Enlace){ // Hay que acceder al directorio referenciado por el enlace
                            Enlace enlace = (Enlace) auxWorkPath.peek();
                            try {
                                node = (Directorio) enlace.getHardNode(this.iterLimits);                                
                            } catch (ClassCastException e){
                                // Si el enlace no es un directorio, se lanza una excepción
                                throw new ExcepcionDirNoExiste(node_name);
                            }
                        }
                        else{
                            // Por construcción de una ruta, es imposible que sea fichero (que se haya apilado un nodo fichero en la pila)
                            node = (Directorio) auxWorkPath.peek();
                        }
                    }
                }
            }
            else{ // <nombre> no tiene ningún significado especial, es un directorio (o enlace a directorio alcanzable)
                // que se encuentra en el directorio actual.

                Nodo dir = node.getNodes().get(node_name);
                if(dir == null){
                    throw new ExcepcionDirNoExiste(node_name);
                }

                if(dir instanceof Enlace){ // Si es un enlace, se accede al directorio referenciado
                    Enlace enlace = (Enlace) dir;
                    try {
                        node = (Directorio) enlace.getHardNode(this.iterLimits);
                        auxWorkPath.push(enlace);
                    } catch (ClassCastException e){
                        throw new ExcepcionDirNoExiste(node_name);
                    }
                }
                else if (dir instanceof Directorio){ // Si es un directorio, se accede directamente a él
                    node = (Directorio) dir;
                    auxWorkPath.push(dir); // Se añade el directorio a la ruta de trabajo
                }
                else{
                    // Si el nodo es un fichero, debe saltar una excepción.
                    throw new ExcepcionDirNoExiste(node_name);
                }
            }
        }

        // Tratar el último nodo de la ruta
        String nodeTarget = pathNodes[pathNodes.length - 1];

        if(nodeTarget.equals(".")){ // El nodo a devolver será la cima de la pila
            return workPath.isEmpty() ? root : workPath.peek();
        }

        if(nodeTarget.equals("..")){// El nodo a devolver será el padre del último nodo de la ruta (el propio root si está vacía)
            if(workPath.isEmpty()){
                return root;
            }
            auxWorkPath.pop();
            return workPath.isEmpty() ? root : workPath.peek();
        }

        // El nombre del nodo no tiene significado especial, debe buscarse en el directorio
        if(node.getNodes().containsKey(nodeTarget)){
            nodeReturn = node.getNodes().get(nodeTarget);
        }
        else{
            throw new ExcepcionNodoNoExiste(nodeTarget);
        }
        return nodeReturn; // Se devuelve el nodo referenciado por la ruta
    }

    // Pre: true
    // Post: Si <node> es un directorio, se eliminan sus contenidos de forma recursiva.
    //       Si no, no se hace nada.
    private void removeNode(Nodo node){
        // Si el Nodo es un directorio, se eliminan recursivamente sus contenidos
        if(node instanceof Directorio){

            // Se emplea ITERATOR para poder eliminar del diccionario de contenidos del directorio
            // a la par que se itera sobre él
            Iterator<Map.Entry<String, Nodo>> iterator = ((Directorio) node).getNodes().entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry<String, Nodo> entry = iterator.next();
                Nodo child = entry.getValue();

                // Se llama recursivamente para eliminar el contenido del directorio, antes de eliminarlo
                removeNode(child);

                // Se elimina del directorio de forma segura.
                iterator.remove();
            }
        }
        // Si es enlace o fichero, no se hace nada, es el padre
        // (un directorio) el que se encarga de desreferenciarlos
    }


    // Métodos  públicos de la clase shell.

    /*
     *   Pre : true
     *  Post: Se crea una nueva instancia de la clase Shell, inicializando el directorio raíz y con la ruta de trabajo en él.
     */
    public Shell(){
        try {
            root = new Directorio("root", null);
        }
        catch(ExcepcionNombreInvalido e){
            System.out.println("Error: " + e.getMessage()); // Código inalcanzable 
        }
        
        this.CWD = root;
        workPath = new Stack<Nodo>();
    }

    // Pre: True
    // Post: Devuelve una cadena con la representación textual del directorio actual
    //       de trabajo a partir del directorio raíz. El formato será el siguiente:
    //       /<nombre_directorio1>/<nombre_directorio2>/.../<nombre_directorioN>
    //
    public String pwd() {
        String path = "";
        for (Nodo n : workPath) {
            // Recorrer la pila de directorios (o enlaces a directorios)
            path += "/" + n.getNombre();
        } 
        return path.isEmpty() ? "/" : path; // Si la ruta es vacía, se devuelve "/"
    }

    // Pre: True
    // Post: Devuelve una cadena con el listado de los elementos del directorio actual,
    //       uno por línea.
    public String ls() {
        String listado = "";
        for ( String nombre : CWD.getNodes().keySet() ) {
            // Los nodos de un directorio se almacenan en un diccionario
            // String -> Nodo, donde la clave es el nombre del nodo. De este modo
            // Las propias claves constituyen el listado de los elementos del directorio.
            listado += nombre + "\n";
        }
        return listado;
    }

    // Pre: True
    // Post: Devuelve una cadena con el listado de los elementos del directorio actual,
    //       especificando el nombre y tamaño de cada uno por línea.
    public String du() throws ExcepcionBucleEnlace {
        String listado = "";
        for (Nodo node : CWD.getNodes().values()) {
            // .Todo nodo tiene asociado un tamaño
            listado += node.getNombre() + " " + node.size(0) + "\n";
        }
        return listado;
    }

    // Pre: <name> es una cadena no vacía, sin espacios, que no contiene
    //      el carácter '/' de separación y que además es distinta de "." y "..".
    //      Además, si existe un nodo en el directorio <CWD> con nombre <name>, este es un fichero,
    //      o un enlace referenciando en última instancia a un fichero (y alcanzable).
    //      <size> >= 0.
    // Post: Si existe un fichero con nombre <name> en el directorio actual, se modifica su tamaño a <size>.
    //       Si no existe, se crea un nuevo fichero con nombre <name> y tamaño <size> en el directorio actual.
    public void vi(String name, int size) throws ExcepcionBucleEnlace, ExcepcionNombreInvalido, ExcepcionSizeNegativo,
                                                 ExcepcionNodoEsDirectorio {

        if(CWD.getNodes().containsKey(name)) {
            // El fichero <name> ya existe, se modifica su tamaño a <size>
            Nodo node = CWD.getNodes().get(name);
            Nodo nodeAux = node;
            // Si es un enlace, se busca el nodo referenciado y se modifica su tamaño si procede
            if(node instanceof Enlace){
                // link = true;
                Enlace enlace = (Enlace) node;
                nodeAux = enlace.getHardNode(this.iterLimits);
            }
            if ( nodeAux instanceof Fichero){
                // Se modifica el tamaño del fichero
                ((Fichero) node).setSize(size);
            } else {
                // Si el nodo es un directorio, se lanza una excepción
                throw new ExcepcionNodoEsDirectorio(name);
            }

        }
        else {
            // Si no existe un nodo con nombre <name> en el directorio actual
            // se crea con los valores correspondientes
            Fichero fichero = new Fichero(name, size, CWD);

            // Se añade el fichero al directorio actual
            CWD.addNode(fichero);

        }
    }

    // Pre: <name> es una cadena no vacía, sin espacios, que no contiene
    //      el carácter '/' de separación y que además es distinta de "." y "..".
    //      Además, no existe ningún nodo en el directorio actual con nombre <name>.
    // Post: Se crea un nuevo directorio vacío en el directorio actual, con nombre
    //       <name>.
    public void mkdir(String name) throws ExcepcionNombreInvalido, ExcepcionNodoYaExistente {
        if (CWD.getNodes().containsKey(name)) {
            // El nodo <name> ya existe, se lanza una excepción
            throw new ExcepcionNodoYaExistente(name);
        }
        Directorio directorio = new Directorio(name, CWD); // Crear nuevo directorio
        // Se añade el directorio al directorio actual  

        
        CWD.addNode(directorio);
    }

    // Pre: <path> constituye una ruta válida, sea relativa o absoluta a un directorio (o enlace a directorio alcanzable).
    //      Es decir, si <path> es relatova (no comienza desde "/"), esta compuesta
    //      por una secuencia de nombres de directorios (o enlaces a directorios alcanzables)
    //      separados por el caracter '/', y que además pueden ir alcanzándose desde el directorio
    //      anterior (comenzando por el actual).
    //      Si <path> es absoluta, comienza desde el directorio raíz "/", y esta compuesta por
    //      una secuencia de nombres de directorios (o enlaces a directorios alcanzables) separados
    //      por el caracter '/', y que además pueden ir alcanzándose desde el directorio anterior (comenzando por el raíz).
    //
    // Post: Se cambia el directorio de trabajo actual al directorio especificado por <path>, así como la  ruta de trabajo.
    public void cd(String path) throws ExcepcionBucleEnlace, ExcepcionDirNoExiste{
        String pathAux = path;

        // Se guarda el directorio actual y la ruta por si ocurren fallos
        Directorio CWDaux = CWD;
        Stack<Nodo> workPathAux = new Stack<Nodo>();
        workPathAux.addAll(workPath);

        // Comprobar si la ruta es absoluta o relativa
        if(path.startsWith("/")){ // La ruta es absoluta
            // Si la ruta es absoluta, se limpia el workPath.
            workPath.clear();
            CWD = root;
            pathAux = path.substring(1); // Se elimina el primer caracter "/" de la ruta

        }
        if (!pathAux.isEmpty()) { // Si la ruta es vacía, nada ha de hacerse

            // Se divide el path en nodos con "/" como separador
            String[] pathNodes = pathAux.split("/");
            for(String node_name : pathNodes){
                if(node_name.equals(".")){
                    // NADA
                }
                else if (node_name.equals("..")){
                    // Se retrocede al anterior directorio de la ruta
                    // Esto equivale a eliminar el último nodo de la pila de trabajo
                    if(!workPath.isEmpty()){
                        workPath.pop(); // Siempre se elimina el último nodo de la ruta
                        if(workPath.isEmpty()){ // Si ruta queda vacia, estamos en root.
                            CWD = root;
                        }
                        else{ // Si no, se retrocede al directorio anterior
                            if(workPath.peek() instanceof Enlace){ // Hay que acceder al directorio referenciado por el enlace
                                Enlace enlace = (Enlace) workPath.peek();
                                try {
                                    CWD = (Directorio) enlace.getHardNode(this.iterLimits);
                                } catch (ExcepcionBucleEnlace e) {
                                    // Restaurar estado original
                                    this.CWD = CWDaux;
                                    this.workPath = workPathAux;
                                    throw new ExcepcionBucleEnlace();
                                } catch (ClassCastException e){ 
                                    this.CWD = CWDaux;
                                    this.workPath = workPathAux;
                                    throw new ExcepcionDirNoExiste(node_name);
                                }
                            }
                            else{
                                CWD = (Directorio) workPath.peek();
                            }
                        }
                    }
                }
                else{ // <nombre> no tiene ningún significado especial, es un directorio (o enlace a directorio alcanzable)
                    // que se encuentra en el directorio actual.

                    // Se busca directorio en el directorio actual, y si no existe se genera excepción.
                    Nodo dir = CWD.getNodes().get(node_name);
                    if(dir == null){
                        // Restaurar estado original
                        this.CWD = CWDaux;
                        this.workPath = workPathAux;
                        throw new ExcepcionDirNoExiste(node_name);
                    }

                    if(dir instanceof Enlace){ // Si es un enlace, se accede al directorio referenciado
                        Enlace enlace = (Enlace) dir;

                        try {
                            Nodo hardNode = enlace.getHardNode(this.iterLimits); // Se obtiene el nodo referenciado por el enlace
                            if (!(hardNode instanceof Directorio)) {
                                // Si el nodo referenciado no es un directorio, se lanza una excepción
                                // Restaurar estado original
                                this.CWD = CWDaux;
                                this.workPath = workPathAux;
                                throw new ExcepcionDirNoExiste(node_name);
                            }
                            CWD = (Directorio) enlace.getHardNode(this.iterLimits);
                            
                        } catch (ExcepcionBucleEnlace e) {
                            // Restaurar estado original
                            this.CWD = CWDaux;
                            this.workPath = workPathAux;
                            throw new ExcepcionBucleEnlace();
                        }
                        workPath.push(enlace); // Lo que se añade a la ruta de trabajo es el enlace, no el directorio
                    }
                    else if (dir instanceof Directorio){ // Si es un directorio, se accede directamente a él
                        CWD = (Directorio) dir;
                        workPath.push(dir); // Se añade el directorio a la ruta de trabajo
                    }
                    else{
                        // Si el nodo no es un directorio ni un enlace, se lanza una excepción
                        // Restaurar estado original
                        this.CWD = CWDaux;
                        this.workPath = workPathAux;
                        throw new ExcepcionDirNoExiste(node_name);
                    }
                }
            }
        }
    }

    // Pre: <name> es una cadena no vacía, sin espacios, que no contiene
    //      el carácter '/' de separación y que además es distinta de "." y "..".
    //      Además, no existe ningún nodo en el directorio actual con nombre <name>.
    //      <path> constituye una ruta válida, sea relativa o absoluta a un directorio (o enlace a directorio alcanzable).
    //      Es decir, si <path> es relatova (no comienza desde "/"), esta compuesta
    //      por una secuencia de nombres de directorios (o enlaces a directorios alcanzables)
    //      separados por el caracter '/', y que además pueden ir alcanzándose desde el directorio
    //      anterior (comenzando por el actual).
    //      Si <path> es absoluta, comienza desde el directorio raíz "/", y esta compuesta por
    //      una secuencia de nombres de directorios (o enlaces a directorios alcanzables) separados
    //      por el caracter '/', y que además pueden ir alcanzándose desde el directorio anterior (comenzando por el raíz).
    //Post: Se crea un nuevo enlace simbólico en el directorio actual con nombre <name>, que referencia al nidi especificado por <path>.
    public void ln(String path, String name) throws ExcepcionNombreInvalido, ExcepcionBucleEnlace,
                                                    ExcepcionDirNoExiste, ExcepcionNodoNoExiste,
                                                    ExcepcionNodoYaExistente{
        if (CWD.getNodes().containsKey(name)) {
            // El directorio <name> ya existe, se lanza una excepción
            throw new ExcepcionNodoYaExistente(name);
        }
        Nodo node = getNodeFromPath(path); // Se obtiene el nodo referenciado por la ruta
        Enlace enlace = new Enlace(name, node, CWD); // Crear nuevo enlace referenciando al nodo
        CWD.addNode(enlace); // Añadir el enlace al directorio actual

    }

    // TODO ACTUALIZAR PRE DE SIGUIENTES DOS FUNCIONES PORQUE FIN DE PATH PUEDE SER CUALQUIER NODO, NO SOLO DIRECTORIO

    // Pre: <path> constituye una ruta válida, sea relativa o absoluta a un nodo.
    //      Es decir, si <path> es relativa (no comienza desde "/"), esta compuesta
    //      por una secuencia de nombres de directorios (o enlaces a directorios alcanzables)
    //      separados por el caracter '/', y que además pueden ir alcanzándose desde el directorio
    //      anterior (comenzando por el actual); y que terminan en un nodo existente.
    //      Si <path> es absoluta, comienza desde el directorio raíz "/", y esta compuesta por
    //      una secuencia de nombres de directorios (o enlaces a directorios alcanzables) separados
    //      por el caracter '/', y que además pueden ir alcanzándose desde el directorio anterior (comenzando por el raíz);
    //      y que terminan en un nodo existente.
    //Post: Se devuelve el tamaño del nodo referenciado por la ruta <path>.
    public int stat(String path) throws ExcepcionNombreInvalido, ExcepcionBucleEnlace,
                                        ExcepcionDirNoExiste, ExcepcionNodoNoExiste, ExcepcionBucleEnlace{
        Nodo node = getNodeFromPath(path); // Se obtiene el nodo referenciado por la ruta
        return node.size(0); // Se devuelve el tamaño del nodo
    }

    // Pre: <path> constituye una ruta válida, sea relativa o absoluta a un nodo.
    //      Es decir, si <path> es relatova (no comienza desde "/"), esta compuesta
    //      por una secuencia de nombres de directorios (o enlaces a directorios alcanzables)
    //      separados por el caracter '/', y que además pueden ir alcanzándose desde el directorio
    //      anterior (comenzando por el actual); y que terminan en un nodo existente.
    //      Si <path> es absoluta, comienza desde el directorio raíz "/", y esta compuesta por
    //      una secuencia de nombres de directorios (o enlaces a directorios alcanzables) separados
    //      por el caracter '/', y que además pueden ir alcanzándose desde el directorio anterior (comenzando por el raíz);
    //      y que terminan en un nodo existente.
    // Post: Se elimina el nodo referenciado por la ruta <path>.
    //       Si es un fichero, simplemente se elimina del directorio que lo contiene. Si es un enlace, elimina el enlace pero no
    //       el nodo referenciado. Si es un directoriom se elimina todo su contenido.
    // NOTE: En JAVA se lleva a cabo la recolección automática de basura, por lo que no es necesaria una gestión
    //       del número de referencia a nodos bien a partir de enlaces o de la ruta de trabajo (cuando el objeto
    //       se quede sin referencias, el recolector se encargará de destruirlo).
    public void rm(String path) throws ExcepcionNombreInvalido, ExcepcionBucleEnlace,
                                        ExcepcionDirNoExiste, ExcepcionNodoNoExiste{
        Nodo nodeToErase = getNodeFromPath(path); // Se obtiene el nodo referenciado por la ruta
        
        if (nodeToErase.padre != null) {
            // Se obtiene el directorio padre del nodo a eliminar
            Directorio borrar = nodeToErase.padre;
            // Se elimina el nodo del directorio padre
            borrar.getNodes().remove(nodeToErase.getNombre());
        }

        if (nodeToErase instanceof Directorio){
            removeNode(nodeToErase);
        }
    }
}
