package org.dsl.bst;

/**
 * Created by rajesh on 14/8/16.
 */
public class BstUI {

    public static void main(String[] args) {

        Bst myTree = new Bst();

        for (int i=0;i<10; i++) {
            int val = (int)(Math.random() * (100-1)) + 1;
            myTree.insert(val);
        }

        TreePrinter.print(myTree.getRoot());

        int ht = myTree.getHeight();
        System.out.println("ht="+ht);


    }

}
