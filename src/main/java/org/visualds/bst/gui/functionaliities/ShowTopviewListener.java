package org.visualds.bst.gui.functionaliities;

import org.visualds.bst.Bst;
import org.visualds.bst.BstNode;
import org.visualds.bst.gui.BstDiag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by rajesh on 2/10/16.
 */
public class ShowTopviewListener implements ActionListener {
    private BstDiag bstDiag;
    private Bst tree;

    public ShowTopviewListener(BstDiag bstDiag) {
        this.bstDiag = bstDiag;
        tree = bstDiag.getBstPanel().getTree();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ArrayList<BstNode> topViewNodes = tree.getTopViewNodes();
        for (BstNode node: topViewNodes) {
            bstDiag.getBstPanel().selectNode(node,true);
        }
        bstDiag.getBstPanel().refresh();
    }
}
