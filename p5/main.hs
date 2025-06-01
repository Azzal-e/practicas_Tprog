import Turtle
import SVG
import Lsystem

-- ** DEFINICIÓN DE FIGURAS PARA TAREA 1 **
figuraCuadrado = tplot (1,90,(0,0),90) ">+>+>+>+"

figuraTriangulo = tplot (1,60, (0,0), 90) "+>+>+>"



-- ** DEFINICIÓN L-SYSTEMS PARA TAREA 2 **

-- ***********L-System de la Sierpinsky Arrowhead *************
rulesArrowhead :: Char -> String

rulesArrowhead 'F' = "G+F+G"
rulesArrowhead 'G' = "F-G-F"
rulesArrowhead x = [x]
figuraArrowhead = tplot (1,60,(0,0),0) (lsystem rulesArrowhead "F" 6)

-- ***********L-System de la curva de Koch* ************

rulesKoch :: Char -> String

rulesKoch 'F' = "F-F++F-F"
rulesKoch x = [x]

figuraKoch = tplot (1,60,(0,0),0) (lsystem rulesKoch "F" 4)


-- ***********L-System de la curva de Koch cuadrada*************

rulesKochCuadrada :: Char -> String

rulesKochCuadrada 'F' = "F-F+F+F-F"
rulesKochCuadrada x = [x]

figuraKochCuadrada = tplot (1,90,(0,0),0) (lsystem rulesKochCuadrada "F" 4)

-- ***********L-System de la curva de Koch Snoflake*************

rulesKochSnowflake :: Char -> String

rulesKochSnowflake 'F' = "F-F++F-F"
rulesKochSnowflake x = [x]

figuraKochSnowflake = tplot (1,60,(0,0),0) (lsystem rulesKochSnowflake "F++F++F" 4)

-- *********** L-System de la Isla de Minkowski *************

rulesMinkowski :: Char -> String

rulesMinkowski 'F' = "F-F+F+FF-F-F+F"
rulesMinkowski x = [x]

figuraMinkowski = tplot (1,90,(0,0),0) (lsystem rulesMinkowski "F+F+F+F" 4)

-- *********** L-System del Triángulo de Sierpinsky *************

rulesSierpinsky :: Char -> String

rulesSierpinsky 'F' = "F-G+F+G-F"
rulesSierpinsky 'G' = "GG"
rulesSierpinsky x = [x]

figuraSierpinsky = tplot (1,120,(0,0),0) (lsystem rulesSierpinsky "F-G-G" 6)

-- *********** L-System de la curva de Hilbert *************

rulesHilbert :: Char -> String

rulesHilbert 'f' = "-g>+f>f+>g-"
rulesHilbert 'g' = "+f>-g>g->f+"
rulesHilbert x = [x]

figuraHilbert = tplot (1,90,(0,0),0) (lsystem rulesHilbert "f" 6)


-- *********** L-System de la curva de Gosper *************

rulesGosper :: Char -> String

rulesGosper 'F' = "F-G--G+F++FF+G-"
rulesGosper 'G' = "+F-GG--G-F++F+G"
rulesGosper x = [x]

figuraGosper = tplot (1,60,(0,0),0) (lsystem rulesGosper "F" 4)



-- *********** Figura de un círculo *************

rulesCircle :: Char -> String
rulesCircle '>' = ">+>"
rulesCircle c = [c]

figuraCirculo = tplot (1,4,(0,0),0) (lsystem rulesCircle ">+" 15)



-- *********** Figura triángulo *************

rulesTriangle :: Char -> String
rulesTriangle 'F' = "F+F+F"
rulesTriangle c = [c]

-- Aumenta el tamaño del paso (por ejemplo, de 1 a 5)
figuraTriangle = tplot (5, 120, (0, 0), 0) (lsystem rulesTriangle "F" 1)





-- Definición de la figura de la curva de Koch


main = do
  print figuraCuadrado
  savesvg "figuraCuadrado" figuraCuadrado

  print figuraTriangulo
  savesvg "figuraTriangulo" figuraTriangulo

-- Generar imágines para tarea 2
  savesvg "figuraArrowHead" figuraArrowhead
  savesvg "figuraKoch" figuraKoch
  savesvg "figuraKochCuadrada" figuraKochCuadrada
  savesvg "figuraKochSnowflake" figuraKochSnowflake
  savesvg "figuraMinkowski" figuraMinkowski
  savesvg "figuraSierpinsky" figuraSierpinsky

  
  savesvg "figuraCirculo" figuraCirculo
  print (lsystem rulesHilbert "f" 2)
  savesvg "figuraHilbert" figuraHilbert

  savesvg "figuraGosper" figuraGosper
  savesvg "figureTriangle" figuraTriangle
