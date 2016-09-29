package org.dsl.bst.gui;

import org.dsl.bst.BstNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rajesh on 28/9/16.
 */
class TraversalListener implements ActionListener, INodeVisitor, Runnable {

    enum TraversalType {
        INORDER_TRAVERSAL,
        POSTORDER_TRAVERSAL,
        PREORDER_TRAVERSAL,
        LEVELORDER_TRAVERSAL
    }

    private BstDiag bstDiag;
    private BstPanel bstPanel;
    BstNode lastNode = null;
    boolean isFirstNode = true;
    TraversalType traversalType;

    public void actionPerformed(ActionEvent actionEvent) {
        Thread thr = new Thread(this);
        thr.start();
    }

    TraversalListener(BstDiag bstDiag, TraversalType traversalType) {
        this.bstDiag = bstDiag;
        this.bstPanel = bstDiag.getBstPanel();
        this.traversalType = traversalType;
    }

    public void nodeVisited(BstNode node) {
        bstPanel.selectNode(node);
        bstDiag.refreshTreePanel();
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
                bstDiag.tree.inorder(bstDiag.tree.getRoot(), this);
                break;
            case POSTORDER_TRAVERSAL:
                msg = "Postorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                bstDiag.tree.postorder(bstDiag.tree.getRoot(), this);
                break;
            case PREORDER_TRAVERSAL:
                msg = "Preorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                bstDiag.tree.preorder(bstDiag.tree.getRoot(), this);
                break;
            case LEVELORDER_TRAVERSAL:
                msg = "Levelorder traversal: ";
                bstDiag.addStatus(msg, true, false);
                bstDiag.tree.levelOrder(bstDiag.tree.getRoot(), this);
                break;
        }
        bstDiag.addStatus("", true, true);
        if (lastNode != null) {
            bstPanel.selectNode(lastNode, false);
            bstDiag.refreshTreePanel();
        }
    }

}
