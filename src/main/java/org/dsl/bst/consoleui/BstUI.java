package org.dsl.bst.consoleui;

import org.dsl.bst.Bst;
import org.dsl.bst.gui.BstPanel;

/**
 * Created by rajesh on 14/8/16.
 */
public class BstUI {


    public static void main(String[] args) {

        Bst myTree = new Bst();

        myTree.insert(50);

        for (int i=0;i<5; i++) {
            int val = (int)(Math.random() * (50-1)) + 1;
            myTree.insert(val);
        }

        for (int i=5;i<10; i++) {
            int val = (int)(Math.random() * 50) + 50;
            myTree.insert(val);
        }

        //BstDiag diag = new BstDiag();
        //displayDiag(diag);
        BstPanel<Integer> bstView = new BstPanel<Integer>(myTree);
        bstView.refresh();


    }

}
