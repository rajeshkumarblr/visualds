package org.visualds.bst.gui.functionaliities;

import org.visualds.bst.Bst;
import org.visualds.bst.BstNode;
import org.visualds.bst.gui.BstDiag;
import org.visualds.bst.gui.BstPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by rajesh on 2/10/16.
 */
public class FindShortestPathListener implements ActionListener {

    private final BstDiag bstDiag;
    private final BstPanel bstPanel;

    public FindShortestPathListener(BstDiag bstDiag) {
        this.bstDiag = bstDiag;
        this.bstPanel = bstDiag.getBstPanel();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ArrayList<BstNode> selectedNodes = bstPanel.getSelectedNodes();
        if (selectedNodes == null || selectedNodes.size() != 2)
            return;
        BstNode node1 = selectedNodes.get(0);
        BstNode node2 = selectedNodes.get(1);
        Bst tree = bstPanel.getTree();
        ArrayList<BstNode> shortestPathNodes = tree.getShortestPath(node1, node2);
        bstPanel.clearSelection(node1);
        for (BstNode node: shortestPathNodes) {
            bstPanel.selectNode(node,true);
        }
        bstPanel.refresh();
    }
}
