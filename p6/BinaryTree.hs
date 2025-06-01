module BinaryTree where
    import Data.List(sort)
    -- ** TAREA 1. ÁRBOLES BINARIOS 
    -- Definir tipo de dato algebraico para representar un árbol binario
    -- Es un tipo parametrizado ya que el árbol binario es genérico
    data Tree t =  Empty | Leaf t | Branch t (Tree t) (Tree t) -- Árbol puede ser un nodo hoja o ramificación con subárbol izq/der

    -- Instanciación de la clase Show para impresión por pantalla

    instance Show t => Show (Tree t) where
        show t = showIndent t 0 where
            showIndent :: Tree t -> Integer -> String -- Función auxiliar para mostrar con indentación
            showIndent Empty n = replicate (fromIntegral n*2) ' ' ++ "\\- " ++ "()" -- Hijo vacío
            showIndent (Leaf x) n = replicate (fromIntegral n*2) ' ' ++ "\\- " ++ show x 
            showIndent (Branch x t1 t2) n = replicate (fromIntegral n*2) ' ' ++ "\\- " ++ show x ++ "\n" 
                                           ++ showIndent t1 (n+1) ++ "\n" 
                                           ++ showIndent t2 (n+1)

    -- Función que devuelve un árbol vacío, sin ningún elemento
    empty :: Tree t
    empty = Empty

    -- Función que devuelve un árbol que consta de una única hoja
    -- que contiene el elemento x.
    leaf :: t -> Tree t
    leaf x = Leaf x

    -- Función que devuelve un árbol que contiene en la raíz el elemento x,
    -- con hijo izquierdo lc e hijo derecho rc.
    tree :: t -> Tree t -> Tree t -> Tree t
    tree x lc rc = Branch x lc rc

    -- Función que devuelve el número de elementos del árbol t
    size :: Tree t -> Integer
    size (Empty) = 0 -- Si es un árbol vacío, no tiene elementos
    size (Leaf x) = 1 -- Si es un nodo hoja, corresponde a árbol con solo raíz
    size (Branch x t1 t2) = 1 + (size t1) + (size t2) -- Corresponde a la raíz más el número de elementos en el subárbol izquierdo y derecho

    -- ** TAREA 2. ÁRBOLES BINARIOS DE BÚSQUEDA

    -- Función que añade un elemento x a un ABB, devolviendo un ABB.
    --NOTA: Se necesita restricción de instanciación de la clase Ord para
    --      el tipo de dato almacenado por el árbol
    add :: Ord t => Tree t -> t -> Tree t
    add Empty x = (Leaf x) -- Si el árbol, era vacío, se devuelve árbol con solo una hoja (raíz)
    add (Leaf y) x -- Añadir como hoja que sea hijo izquierdo o derecho.
        | y > x = Branch y (Leaf x) (Empty)
        | otherwise = Branch y Empty (Leaf x) 
    add (Branch y t1 t2) x -- Si se trata de un nodo no hoja (tiene hijos),  se debe añadir al subárbol derecho o izquierdo
        | y > x = Branch y (add t1 x) (t2)
        | otherwise = Branch y t1 (add t2 x)

    -- Función que construye un árbol binario de búsqueda, comenzando con unárbol vacío
    -- e insertando sucesivamente elementos de la lista xs
    build :: Ord t => [t] -> Tree t
    build [] = Empty
    build [x] = Leaf x
    build l = foldl add Empty l -- Lista con más de un elemento: Añadir el último elemento al ABB
                                                           -- correspondiente al resto de elementos (para mantener orden de inserción)

    -- ** TAREA 3. ÁRBOLES BINARIOS DE BÚSQUEDA EQUILIBRADOS

    -- Función que construye un árbol equilibrado a partir de los elementos
    -- de una lista
    buildBalanced :: Ord t => [t] -> Tree t
    buildBalanced [] = Empty
    buildBalanced [x] = Leaf x
    buildBalanced xs = buildBalancedAux (sort xs)  where
        -- Función auxiliar para necesitar ordenar solo una vez
        buildBalancedAux :: Ord t => [t] -> Tree t
        buildBalancedAux [] = empty
        buildBalancedAux [x] = Leaf x
        buildBalancedAux xs  = Branch mediana (buildBalancedAux mitadIzq) (buildBalancedAux mitadDer) where 
            mediana = xs !! (div (length xs) 2) -- la mediana es el valor en el 'centro' de la lista (acceso indexado)
            (mitadIzq, med:mitadDer) = span (< mediana) xs -- partir la lista en dos (para construir dos subárboles) por el valor de la mediana
                                                           -- Además si hay más valores repetidos de la mediana, todos van al subárbol derecho

    -- ** TAREA 4. RECORRIDO DE ÁRBOLES BINARIOS

    -- Función que devuelve una lista a partir de recorrer el árbol en preorden

    preorder :: Tree t -> [t]
    preorder Empty = []
    preorder (Leaf a) = [a]
    preorder (Branch a t1 t2) = a : ((preorder t1)++(preorder t2)) -- Se construye como raíz + recorrer izq + recorred der.

    -- Función que devuelve una lista a partir de recorrer el árbol en postorden

    postorder :: Tree t -> [t]
    postorder Empty = []
    postorder (Leaf a) = [a]
    postorder (Branch a t1 t2) = (postorder t1)++(postorder t2)++[a] -- Recorrer izquierdo + recorrer derecho + raíz


    -- Función que devuelve una lista a partir de recorrer el árbol en inorden

    inorder :: Tree t -> [t]
    inorder Empty = []
    inorder (Leaf a) = [a]
    inorder (Branch a t1 t2) = (inorder t1) ++ (a : (inorder t2)) -- recorrer izquierdo + raíz + recorrer derecho

    -- Funcion que devuelve un árbol equilibrado a partir de cualquier otro dado

    balance ::  Ord t => Tree t -> Tree t
    balance t =  buildBalanced (inorder t) -- El recorrido es arbitrario, 'buildBalanced' se encarga de ordenar

    -- ** TAREA 5. BÚSQUEDAS

    -- Función que devuelve una lista con todos los elementos de un árbol de búsqueda
    -- binario que estén entre los valores xmin y xmax

    between :: Ord t => Tree t -> t -> t -> [t]
    between Empty _ _ = []
    between (Leaf x) xmin xmax -- Solo una hoja: Comprobar directamente si está dentro del rango
        | x >= xmin && x <= xmax = [x] 
        | otherwise = []
    between (Branch x t1 t2) xmin xmax
        | x < xmin =  (between t2 xmin xmax) -- Valores como mucho estáran en el subárbol derecho por ser ABB
        | x > xmax = (between t1 xmin xmax) -- Valores como mucho estarán en el subárbol izquierdo por ser ABB
        | x == xmin = [x]++(between t2 xmin xmax) -- No hace falta explorar el subárbol izquierdo (si hay elementos repetidos, estarán el derecho), pero sí el derecho
        | otherwise = (between t1 xmin xmax)++[x]++(between t2 xmin xmax) -- Hace falta explorar todos los subárboles