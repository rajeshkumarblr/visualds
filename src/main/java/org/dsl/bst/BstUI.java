package org.dsl.bst;

import java.util.ArrayList;

/**
 * Created by rajesh on 14/8/16.
 */
public class BstUI {

    public static void main(String[] args) {

        Bst myTree = new Bst();

        for (int i=0;i<5; i++) {
            int val = (int)(Math.random() * (50-1)) + 1;
            myTree.insert(val);
        }
        for (int i=5;i<10; i++) {
            int val = (int)(Math.random() * (100-50)) + 1;
            myTree.insert(val);
        }

        System.out.println("CheckBST:" + myTree.checkBST(myTree.getRoot()));

        TreePrinter.print(myTree.getRoot());

        int ht = myTree.getHeight();
        System.out.println("ht="+ht);

        ArrayList<Bst.Node> nodes = myTree.getLevelOrderNodes(myTree.getRoot());
        for (Bst.Node node : nodes) {
            System.out.printf("%d ",node.data);
        }

    }

}
