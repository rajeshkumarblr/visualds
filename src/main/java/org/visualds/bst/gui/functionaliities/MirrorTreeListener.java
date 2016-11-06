package org.visualds.bst.gui.functionaliities;

import org.visualds.bst.Bst;
import org.visualds.bst.BstNode;
import org.visualds.bst.gui.BstPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rajesh on 29/9/16.
 */
public class MirrorTreeListener implements ActionListener {

    private final Bst tree;
    private final BstPanel bstPanel;

    public MirrorTreeListener(Bst tree, BstPanel bstPanel) {
        this.tree = tree;
        this.bstPanel = bstPanel;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        mirrorTree(tree.getRoot());
    }

    public void mirrorTree(BstNode node) {
        if (node != null) {
            mirrorTree(node.getLeft());
            node.swapLeftRight();
            mirrorTree(node.getRight());
        }
        bstPanel.refresh();
    }

}
