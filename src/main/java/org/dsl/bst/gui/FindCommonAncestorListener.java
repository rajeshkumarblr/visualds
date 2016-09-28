package org.dsl.bst.gui;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by rajesh on 28/9/16.
 */
class FindCommonAncestorListener implements ActionListener {
    private BstDiag bstDiag;
    private BstPanel<Integer> bstPanel;
    private final Bst<Integer> tree;

    public FindCommonAncestorListener(BstDiag bstDiag) {
        this.bstDiag = bstDiag;
        this.bstPanel = bstDiag.getBstPanel();
        tree = bstDiag.tree;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ArrayList<BstNode> selectedNodes = bstPanel.getSelectedNodes();
        if (selectedNodes == null || selectedNodes.size() != 2) {
            JOptionPane.showMessageDialog(bstPanel,"Please select two nodes in the tree first");
            return;
        } else {
            BstNode node1 = selectedNodes.get(0);
            BstNode node2 = selectedNodes.get(1);

            BstNode currentNode1 = tree.getRoot();
            BstNode currentNode2 = tree.getRoot();

            BstNode ancestorNode = null;
            while (currentNode1 == currentNode2) {
                ancestorNode = currentNode1;
                currentNode1 = getNextNodeInPathTo(node1,currentNode1);
                currentNode2 = getNextNodeInPathTo(node2,currentNode2);
            };
            if (ancestorNode != null) {
                bstPanel.selectNode(ancestorNode);
                bstPanel.refresh();
            }
        }
    }

    private BstNode getNextNodeInPathTo(BstNode destNode, BstNode currentNode) {
        int result = destNode.getData().compareTo(currentNode.getData());
        if (result== 0) {
            return currentNode;
        } else if (result > 0) {
            return currentNode.getRight();
        } else if (result < 0) {
            return currentNode.getLeft();
        }
        return  null;
    }
}
