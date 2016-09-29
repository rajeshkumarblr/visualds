package org.dsl.bst.gui;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rajesh on 29/9/16.
 */
class MirrorTreeListener implements ActionListener {

    private final Bst tree;
    private final BstPanel bstPanel;

    MirrorTreeListener(Bst tree, BstPanel bstPanel) {
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
