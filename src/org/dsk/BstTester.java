package org.dsk;

import java.util.List;

/**
 * Created by rajesh on 2/6/16.
 */
public class BstTester {

    void testNumberTree() {
        BinarySearchTree<Integer> numberTree = new BinarySearchTree<Integer>();

        numberTree.insert(50);
        numberTree.insert(40);
        numberTree.insert(60);
        numberTree.insert(35);
        numberTree.insert(45);
        numberTree.insert(55);
        numberTree.insert(65);
        numberTree.insert(64);
        numberTree.insert(70);
        numberTree.insert(30);
        numberTree.insert(41);
        numberTree.insert(29);

        /**
         *                   50
         *              40   -       60
         *           35   45 -   55      65
         *         30  41               64   70
         *      29
         *
         *
         */
        testTreeLevels(numberTree);


    }

    void testTreeLevels(BinarySearchTree tree){
        List<BinarySearchTree<Integer>.node> nodesatLevelCollection = null;
        for (int i = 0; i < 5; i++) {
            nodesatLevelCollection = tree.findnodesAt(i);
            System.out.print("\nsize of node at level[" + i + "] -> [" + nodesatLevelCollection.size() + "].");
            nodesatLevelCollection.stream().forEach(n -> {
                        System.out.print("[" + n.data + "]");//numberTree.inorder(n);
                    }
            );
        }
        System.out.print("\n");
    }

    void testStringTree() {
        BinarySearchTree<String> stringTree = new BinarySearchTree<String>();

        stringTree.insert("50");
        stringTree.insert("40");
        stringTree.insert("60");
        stringTree.insert("35");
        stringTree.insert("45");
        stringTree.insert("55");
        stringTree.insert("65");

        stringTree.display();

        String val = "35";
        BinarySearchTree.node cousinNode = stringTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data);
        }

        val = "55";
        cousinNode = stringTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data);
        }

        testTreeLevels(stringTree);
    }

    public static void main(String[] args) {
        BstTester tester = new BstTester();
        tester.testNumberTree();
        tester.testStringTree();
    }
}
