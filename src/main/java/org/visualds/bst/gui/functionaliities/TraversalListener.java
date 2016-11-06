package org.visualds.bst.gui.functionaliities;

import org.visualds.bst.Bst;
import org.visualds.bst.BstNode;
import org.visualds.bst.gui.BstDiag;
import org.visualds.bst.gui.BstPanel;
import org.visualds.bst.gui.INodeVisitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rajesh on 28/9/16.
 */
public class TraversalListener implements ActionListener, INodeVisitor, Runnable {

    public enum TraversalType {
        INORDER_TRAVERSAL,
        POSTORDER_TRAVERSAL,
        PREORDER_TRAVERSAL,
        LEVELORDER_TRAVERSAL
    }

    private BstDiag bstDiag;
    private Bst tree;
    private BstPanel bstPanel;
    BstNode lastNode = null;
    boolean isFirstNode = true;
    TraversalType traversalType;

    public void actionPerformed(ActionEvent actionEvent) {
        Thread thr = new Thread(this);
        thr.start();
    }

    public TraversalListener(BstDiag bstDiag, TraversalType traversalType) {
        this.bstDiag = bstDiag;
        this.bstPanel = bstDiag.getBstPanel();
        this.traversalType = traversalType;
        this.tree = bstPanel.getTree();
        bstPanel.getTree();
    }

    public void nodeVisited(BstNode node) {
        bstPanel.selectNode(node);
        bstPanel.refresh();
        String msg;
        if (!isFirstNode) {
            msg = "," + node.getTextValue();
        } else {
            isFirstNode = false;
            msg = node.getTextValue();
        }
        bstDiag.addStatus(msg, true, false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastNode = node;
    }

    public void run() {
        String msg = "";
        switch (traversalType) {
            case INORDER_TRAVERSAL:
                msg = "Inorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                tree.inorder(tree.getRoot(), this);
                break;
            case POSTORDER_TRAVERSAL:
                msg = "Postorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                tree.postorder(tree.getRoot(), this);
                break;
            case PREORDER_TRAVERSAL:
                msg = "Preorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                tree.preorder(tree.getRoot(), this);
                break;
            case LEVELORDER_TRAVERSAL:
                msg = "Levelorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                tree.levelOrder(tree.getRoot(), this);
                break;
        }
        bstDiag.addStatus("", true, true);
        if (lastNode != null) {
            bstPanel.selectNode(lastNode, false);
            bstPanel.refresh();
        }
    }

}
