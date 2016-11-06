package org.dsl.bst.gui.functionaliities;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;
import org.dsl.bst.gui.BstDiag;
import org.dsl.bst.gui.BstPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by rajesh on 28/9/16.
 */
public class FindCommonAncestorListener implements ActionListener {
    private BstDiag bstDiag;
    private BstPanel bstPanel;

    public FindCommonAncestorListener(BstDiag bstDiag) {
        this.bstDiag = bstDiag;
        this.bstPanel = bstDiag.getBstPanel();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ArrayList<BstNode> selectedNodes = bstPanel.getSelectedNodes();
        if (selectedNodes == null || selectedNodes.size() != 2) {
            JOptionPane.showMessageDialog(bstPanel,"Please select two nodes in the tree first");
            return;
        } else {
            BstNode node1 = selectedNodes.get(0);
            BstNode node2 = selectedNodes.get(1);
            Bst tree = bstPanel.getTree();
            BstNode ancestorNode = tree.findCommonAncestorNode(node1,node2);

            if (ancestorNode != null) {
                bstPanel.clearSelection(ancestorNode);
                bstPanel.refresh();
            }
        }
    }

}
