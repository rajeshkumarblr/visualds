package org.dsl.bst.gui;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;

import javax.swing.*;
import javax.swing.border.Border;
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
    private JButton btnClear;
    private JButton btnTreeHeight;
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
        inorderTraversalButton.addActionListener(new InorderTraversalListener());
        btnTreeHeight.addActionListener(new TreeHeightListener());
        docStyle = statusPane.getStyledDocument();
        failureStyle = statusPane.addStyle("Failure Style", null);
        StyleConstants.setForeground(failureStyle, Color.red);
        successStyle = statusPane.addStyle("Success Style", null);
        StyleConstants.setForeground(successStyle, Color.MAGENTA);
    }

    public static void main(String[] args) {
        BstDiag dialog = new BstDiag();
        dialog.pack();
        dialog.setSize(800,600);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        bstPanel = new BstPanel<Integer>();
        tree = Bst.createRandomeBst();
        bstPanel.setTree(tree);
        bstPanel.setBorder(BorderFactory.createBevelBorder(1));
    }

    private class InorderTraversalListener implements ActionListener, INodeVistor, Runnable{
        public void actionPerformed(ActionEvent actionEvent) {
            Thread thr = new Thread(new InorderTraversalListener());
            thr.start();
        }

        public void nodeVisited(BstNode node) {
            bstPanel.selectNode(node);
            refreshTreePanel();
            addStatus(node.getTextValue() + ",",true,false);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            addStatus("Inorder traversal:",true,false);
            tree.inorder(tree.getRoot(),this);
        }
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
                    String msg = "Mirror node for " + node.getTextValue() + " found:" + mirrorNode.getTextValue() + "\n";
                    addStatus(msg,true, true);
                } else {
                    String msg = "Mirror node for " + node.getTextValue() + " NOT found \n";
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
            bstPanel.selectNode(point);
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
}
