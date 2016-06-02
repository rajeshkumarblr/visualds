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
        numberTree.display();

        int val = 35;
        BinarySearchTree.node cousinNode = numberTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
        }

        val = 55;
        cousinNode = numberTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
        }


        List<BinarySearchTree<Integer>.node> nodesatLevelCollection = null;
        int treeHieght = numberTree.height(null);
        for (int i = 0; i < treeHieght; i++) {
            nodesatLevelCollection = numberTree.getnodeAt(i);
            System.out.println("size of node at level[" + i + "] -> [" + nodesatLevelCollection.size() + "]. children are as follows");
            nodesatLevelCollection.stream().forEach(n ->
                            numberTree.inorder(n)
            );
        }
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
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
        }

        val = "55";
        cousinNode = stringTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
        }
    }

    public static void main(String[] args) {
        BstTester tester = new BstTester();
        tester.testNumberTree();
        tester.testStringTree();
    }
}
