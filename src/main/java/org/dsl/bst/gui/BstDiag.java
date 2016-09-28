package org.dsl.bst.gui;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BstDiag extends JDialog {
    private JPanel contentPane;
    private BstPanel<Integer> bstPanel;
    private JButton insertButton;
    private JButton randomTreeButton;
    private JButton deleteNodeButton;
    private JButton findMirrorNodeButton;
    private JButton findCommonAncestorButton;
    private JButton inorderTraversalButton;
    private JTextPane statusPane;
    private JButton btnTreeHeight;
    private JButton btnPreorderTraversal;
    private JButton btnPostorderTraversal;
    Bst<Integer> tree;
    StyledDocument docStyle;
    Style failureStyle;
    Style successStyle;

    public BstDiag() {
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        insertButton.addActionListener(new InsertActionListener());
        randomTreeButton.addActionListener(new RandomActionListener());
        bstPanel.addMouseListener(new TreePanelMouseClickAdapter());
        deleteNodeButton.addActionListener(new DeleteListener());
        findMirrorNodeButton.addActionListener(new FindMirrorListener());
        btnTreeHeight.addActionListener(new TreeHeightListener());
        docStyle = statusPane.getStyledDocument();
        failureStyle = statusPane.addStyle("Failure Style", null);
        StyleConstants.setForeground(failureStyle, Color.red);
        successStyle = statusPane.addStyle("Success Style", null);
        StyleConstants.setForeground(successStyle, Color.MAGENTA);
        inorderTraversalButton.addActionListener(new TraversalListener(TraversalType.INORDER_TRAVERSAL));
        btnPreorderTraversal.addActionListener(new TraversalListener(TraversalType.PREORDER_TRAVERSAL));
        btnPostorderTraversal.addActionListener(new TraversalListener(TraversalType.POSTORDER_TRAVERSAL));
        findCommonAncestorButton.addActionListener(new FindCommonAncestorListener(this));
    }

    public static void main(String[] args) {
        BstDiag dialog = new BstDiag();
        dialog.pack();
        dialog.setSize(800,600);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }

    public BstPanel<Integer> getBstPanel() {
        return bstPanel;
    }

    private void createUIComponents() {
        bstPanel = new BstPanel<Integer>();
        tree = Bst.createRandomeBst();
        bstPanel.setTree(tree);
        bstPanel.setBorder(BorderFactory.createBevelBorder(1));
    }

    private class TreeHeightListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int ht = tree.getHeight();
            String text = "Tree Height is " + ht;
            addStatus(text,true,true);

        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<BstNode> selectedNodes = bstPanel.getSelectedNodes();
            for (BstNode node:selectedNodes) {
                String textval = node.getTextValue();
                Integer val = Integer.parseInt(textval);
                BstNode<Integer> successorNode = tree.delete(val);
                bstPanel.selectNode(successorNode,true);
                addStatus("Node deleted: " + val + " replaced with: " + successorNode.getTextValue(), true, true);
            }
            refreshTreePanel();
        }
    }

    private class FindMirrorListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<BstNode> selectedNodes = bstPanel.getSelectedNodes();
            if (selectedNodes != null && selectedNodes.size() > 0) {
                BstNode node = selectedNodes.get(0);
                BstNode mirrorNode = tree.getMirrorNode(node);
                if (mirrorNode != null) {
                    String msg = "Mirror node for " + node.getTextValue() + " found:" + mirrorNode.getTextValue();
                    addStatus(msg,true, true);
                } else {
                    String msg = "Mirror node for " + node.getTextValue() + " NOT found ";
                    addStatus(msg,false, true);
                }
                bstPanel.selectNode(mirrorNode);
                refreshTreePanel();
            }
        }
    }

    private class TreePanelMouseClickAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            Point point = mouseEvent.getPoint();
            bstPanel.selectNode(point, mouseEvent.isControlDown());
            refreshTreePanel();
        }
    }

    private class InsertActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String textval = JOptionPane.showInputDialog("Enter the data to be added ");
            Integer val = Integer.parseInt(textval);
            BstNode node = tree.insert(val);
            bstPanel.addNode(node);
            addStatus("Added node: " + node.getTextValue(),true, true);
            refreshTreePanel();
        }
    }

    private class RandomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            tree = Bst.createRandomeBst();
            bstPanel.setTree(tree);
            refreshTreePanel();
        }
    }

    private void addStatus(String msg, boolean isSuccess, boolean IsNewLine) {
        try {
            if (IsNewLine) {
                msg += "\n";
            }
            if (isSuccess) {
                docStyle.insertString(statusPane.getDocument().getLength(),msg,successStyle);
            } else {
                docStyle.insertString(statusPane.getDocument().getLength(),msg,failureStyle);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void refreshTreePanel() {
        bstPanel.repaint();
        bstPanel.revalidate();
    }
    enum TraversalType {
        INORDER_TRAVERSAL,
        POSTORDER_TRAVERSAL,
        PREORDER_TRAVERSAL,
    };

    private class TraversalListener implements ActionListener, INodeVistor, Runnable{
        BstNode lastNode = null;
        boolean isFirstNode = true;
        TraversalType traversalType;
        public void actionPerformed(ActionEvent actionEvent) {
            Thread thr = new Thread(this);
            thr.start();
        }

        TraversalListener(TraversalType traversalType) {
            this.traversalType = traversalType;
        }

        public void nodeVisited(BstNode node) {
            bstPanel.selectNode(node);
            refreshTreePanel();
            String msg;
            if (!isFirstNode) {
                msg = ","  + node.getTextValue();
            } else {
                isFirstNode = false;
                msg = node.getTextValue();
            }
            addStatus(msg, true, false);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastNode = node;
        }

        public void run() {
            String msg ="";
            switch (traversalType) {
                case INORDER_TRAVERSAL:
                    msg= "Inorder traversal: ";
                    addStatus(msg,true,false);
                    tree.inorder(tree.getRoot(),this);
                    break;
                case POSTORDER_TRAVERSAL:
                    msg= "Postorder traversal: ";
                    addStatus(msg,true,false);
                    tree.postorder(tree.getRoot(),this);
                    break;
                case PREORDER_TRAVERSAL:
                    msg= "Preorder traversal: ";
                    addStatus(msg,true,false);
                    tree.preorder(tree.getRoot(),this);
                    break;
            }
            addStatus("",true,true);
            if (lastNode != null) {
                bstPanel.selectNode(lastNode,false);
                refreshTreePanel();
            }
        }
    }
}
