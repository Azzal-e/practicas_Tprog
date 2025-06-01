module Lsystem where
    import Turtle
    import Data.Char(isUpper)
    import Data.Char(isLower)

    -- TAREA 1 -> GRÁFICOS DE TORTUGA

    -- Devuelve una lista de posiciones que representan el recorrido de la tortuga
    -- en el espacio 2D, a partir de una cadena de caracteres que representa
    -- los movimientos de la tortuga.
    -- La cadena de caracteres puede contener los siguientes comandos:
        --  ">" (avanzar), "+" (girar derecha), "-" (girar izquierda), letra Mayúscula (avanza) y letra minúsucla (ningún efecto)
    tplot :: Turtle -> String -> [Position] 

    tplot (_,_,pos,_) [] = pos:[] -- Caso base: Si la cadena está vacía, se devuelve la posición incial (no hay ningún movimiento)

    tplot turt@(_, _, pos, _) (x:xs) -- Se emplea ajuste de patrones para trabajar con la modificación de la posición, pero también se asocia un nombre
        | x == '>' = pos : tplot (moveTurtle turt Forward) xs
        | x == '+' = tplot (moveTurtle turt TurnRight) xs
        | x == '-' = tplot (moveTurtle turt TurnLeft) xs
        | isUpper x  = pos :  tplot (moveTurtle turt Forward) xs
        | otherwise = pos : tplot turt xs -- Minúsculas se ignoran a la hora de dibujar

    -- TAREA 2 -> Sistemas de Lindenmayer

    -- La función lsystem devuelve una cadena de caracteres resultante de aplicar sobre 
    -- un axioma inicial, una serie de reglas de re-escritura un número de veces
    -- determinado.
    lsystem :: (Char -> String) -> String -> Int -> String


    lsystem _ axiom 0 = axiom -- Xaso base: Devolver la cadena original al no tener que evaluar ninguna vez

    lsystem rule axiom n = lsystem rule newAxiom (n-1) -- Aplicar regla sobre el axioma 
        where newAxiom = concat (map rule axiom) -- Aplicar la regla a cada caracter del axioma y concatenar los resultados (pues map devuelve una lista de strings, una lista de listas de caracteres)


