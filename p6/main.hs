import BinaryTree

tree0 = tree 1 (tree 2 (leaf 3) (leaf 4)) (tree 5 empty (leaf 6))
tree1 = tree "R" (tree "HI" (leaf "NII") (leaf "NID")) (tree "HD" (leaf "NDI") (leaf "NDD"))
tree2 = build [3, 2, 2, 5, 1, 4, 4]
tree31= build [1..6]
tree32 = buildBalanced [1..6]
tree33 = buildBalanced[3,3,3,3,3]
exampleTree = Branch 5 
                (Branch 3 
                    (Leaf 1) 
                    (Leaf 4))
                (Branch 7 
                    Empty 
                    (Leaf 9))
listaPreorden = preorder exampleTree
listaPostOrden = postorder  exampleTree
listaInorden = inorder exampleTree
names= build ["Adolfo","Diego","Juan","Pedro","Tomas"]

main = do
    print tree0
    print tree1
    print tree2
    print tree31
    print tree32
    print tree33
    print exampleTree
    print listaPreorden
    print listaPostOrden
    print listaInorden
    print names
    print (balance names)