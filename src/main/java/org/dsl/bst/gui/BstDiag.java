package org.dsl.bst.gui;

import org.dsl.bst.Bst;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BstDiag extends JDialog {
    private JPanel contentPane;
    private BstPanel<Integer> bstPanel1;
    private JTextField textData;
    private JButton insertButton;
    private JButton getHeightButton;
    private JButton randomTreeButton;
    private JButton deleteButton;
    private JLabel lblHeight;
    Bst<Integer> tree;

    public BstDiag() {
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertButton.addActionListener(new InsertActionListener());
        randomTreeButton.addActionListener(new RandomActionListener());
        getHeightButton.addActionListener(new GetHeightActionListener());
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
        tree = Bst.createRandomeBst();
        bstPanel1 = new BstPanel<Integer>(tree);
    }

    private class GetHeightActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int ht = tree.getHeight();
            lblHeight.setText(""+ht);
        }
    }

    private class InsertActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String textval = textData.getText();
            Integer val = Integer.parseInt(textval);
            tree.insert(val);
            bstPanel1.repaint();
            bstPanel1.revalidate();
        }
    }

    private class RandomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            tree = Bst.createRandomeBst();
            bstPanel1.setTree(tree);
            bstPanel1.repaint();
            bstPanel1.revalidate();
        }
    }
}
