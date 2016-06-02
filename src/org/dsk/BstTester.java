package org.dsk;

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
