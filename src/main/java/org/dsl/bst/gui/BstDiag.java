package org.dsl.bst.gui;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;

import javax.swing.*;
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
    private JLabel lblHeight;
    private JButton findMirrorNodeButton;
    private JButton findCommonAncestorButton;
    private JButton inorderTraversalButton;
    Bst<Integer> tree;

    public BstDiag() {
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        insertButton.addActionListener(new InsertActionListener());
        randomTreeButton.addActionListener(new RandomActionListener());
        bstPanel.addMouseListener(new TreePanelMouseClickAdapter());
        deleteNodeButton.addActionListener(new DeleteListener());
        findMirrorNodeButton.addActionListener(new FindMirrorListener());
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
        bstPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<BstNode> selectedNodes = bstPanel.getSelectedNodes();
            for (BstNode node:selectedNodes) {
                String textval = node.getText();
                Integer val = Integer.parseInt(textval);
                tree.delete(val);
            }
            bstPanel.repaint();
            bstPanel.revalidate();
        }
    }

    private static class FindMirrorListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    private class TreePanelMouseClickAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            Point point = mouseEvent.getPoint();
            bstPanel.selectNode(point);
            bstPanel.repaint();
            bstPanel.revalidate();
        }
    }

    private class GetHeightActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int ht = tree.getHeight();
            lblHeight.setText(""+ht);
        }
    }

    private class InsertActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String textval = JOptionPane.showInputDialog("Enter the data to be added ");
            Integer val = Integer.parseInt(textval);
            BstNode node = tree.insert(val);
            bstPanel.addNode(node);
            bstPanel.repaint();
            bstPanel.revalidate();
        }
    }

    private class RandomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            tree = Bst.createRandomeBst();
            bstPanel.setTree(tree);
            bstPanel.repaint();
            bstPanel.revalidate();
        }
    }
}
