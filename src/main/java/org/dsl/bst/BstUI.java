package org.dsl.bst;

/**
 * Created by rajesh on 14/8/16.
 */
public class BstUI {

    public static void main(String[] args) {

        Bst myTree = new Bst();

        myTree.insert(50);
        myTree.insert(40);
        myTree.insert(60);
        myTree.insert(35);
        myTree.insert(45);
        myTree.insert(55);
        myTree.insert(65);

        //myTree.display();
        BstPrinter.printNode(myTree.getRoot());
        //BstPrinter.printNode(test2());


        int ht = myTree.getHeight();
        System.out.println("ht="+ht);

        int val = 35;
        Bst.Node cousinNode = myTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
        }

        val = 55;
        cousinNode = myTree.getCousinNode(val);
        if (cousinNode != null) {
            System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
        }

    }

}
