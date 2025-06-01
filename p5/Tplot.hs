module Tplot (tplot) where
    import Turtle
    
    -- Devuelve una lista de posiciones que representan el recorrido de la tortuga
    -- en el espacio 2D, a partir de una cadena de caracteres que representa
    -- los movimientos de la tortuga.
    -- La cadena de caracteres puede contener los siguientes comandos:
        --  ">" (avanzar), "+" (girar derecha), "-" (girar izquierda)
    tplot :: Turtle -> String -> [Position] 

    tplot (_,_,pos,_) [] = pos:[] -- Caso base: Si la cadena está vacía, se deveulve la posición incial

    tplot (paso, giro, pos, or) (x:xs) 
    | x == '>' = pos : tplot (moveTurtle (paso, giro, pos, or) Forward) xs
    | x == '+' = pos : tplot (moveTurtle (paso, giro, pos, or) TurnRight) xs
    | x == '-' = pos : tplot (moveTurtle (paso, giro, pos, or) TurnLeft) xs
    | otherwise = pos : tplot (paso, giro, pos, or) xs
            

