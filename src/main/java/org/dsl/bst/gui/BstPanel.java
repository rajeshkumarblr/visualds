package org.dsl.bst.gui;

import org.dsl.bst.Bst;
import org.dsl.bst.BstNode;

import javax.swing.*;
import java.awt.*;


/*
 * A Viewer for Binary Trees.
 */
public class BstPanel<T extends Comparable<T>> extends JPanel {

    /* The tree currently being display */
    protected Bst<T> tree;

    /* The max. height of any tree drawn so far.  This
       is used to avoid the tree jumping around when nodes
       are removed from the tree. */
    protected int maxHeight;

    /* The font for the tree nodes. */
    protected Font font = new Font("Roman", 0, 14);

    /* 
     * Create a new window with the given width and height 
     * that is showing the given tree.
     */
    public BstPanel(final Bst<T> tree) {
        this.tree = tree;

        //Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.BLUE);
    }

    /*
     * Set the display to show the given tree.
     */
    public void setTree(Bst<T> t) {
        tree = t;
        maxHeight = tree.getHeight();
    }

    /*
     * Invoke this method whenever you would like the window
     * to be refreshed, such as after updating the tree in some
     * way.
     */
    public void refresh() {
        paintImmediately(0,0, getWidth(), getHeight());
    }

    private Dimension drawNode(Graphics g, int minX, int maxX,  int y, int yStep, BstNode node) {
        String s = node.getText();
        g.setFont(font);

        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();

        int xpos = (minX + maxX)/2;
        int ypos = y + yStep/2;

        g.drawRect(xpos - width/2 -2, ypos- height, width + 2, height + 4);
        g.setColor(Color.gray);
        g.fillRect(xpos - width/2 -2, ypos- height, width + 2, height + 4);
        g.setColor(Color.GREEN);
        g.drawString(s, xpos - width/2, y + yStep/2);
        return  new Dimension(width,height);
    }


    /*
     * Draw the contents of the tree into the given Graphics context.
     * It will place all info between minX and maxX in the x-direction,
     * starting at location y in the y-direction.  Levels of the tree
     * will be separated by yStep pixels.
     */
    protected void drawTree(Graphics g, int minX, int maxX,  int y, int yStep, BstNode node) {

        Dimension textDimension = drawNode(g, minX, maxX, y, yStep, node);

        int xcentre = (minX + maxX)/2;
        int ycentre = y + yStep/2;
        int xSep = Math.min((maxX - minX)/8, 10);

        g.setColor(Color.BLUE);
        if (node.getLeft() != null) {
            // if left tree not empty, draw line to it and recursively // draw that tree
            g.drawLine(xcentre - xSep, ycentre + 5,  (minX + xcentre) / 2,  ycentre + yStep - textDimension.height);
            drawTree(g, minX, xcentre, y + yStep, yStep, node.getLeft());
        }

        if (node.getRight() != null) {
            // same thing for right subtree.
            g.drawLine(xcentre + xSep, ycentre + 5,  (maxX + xcentre) / 2,  ycentre + yStep - textDimension.height);
            drawTree(g, (minX + maxX)/2, maxX, y + yStep, yStep, node.getRight());
        }
    }


    /*
     * paint method inherited from the Swing library.  Just
     * calls drawTree whenever the window needs to be repainted.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);      //clears the background
        if (tree != null && tree.getRoot() != null) {
            int width = getWidth();
            int height = getHeight();
            maxHeight = Math.max(tree.getHeight(), maxHeight);
            int treeHeight = maxHeight;

            drawTree(g, 0, width, 0, height / (treeHeight + 1), tree.getRoot());
        }
    }

}