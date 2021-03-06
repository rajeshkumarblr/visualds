package org.visualds.bst.gui;

import org.visualds.bst.Bst;
import org.visualds.bst.BstNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


/*
 * A Viewer for Binary Trees.
 */
public class BstPanel extends JPanel {

    /* The tree currently being display */
    protected Bst tree;

    /* The font for the tree nodes. */
    protected Font font = new Font("Roman", 0, 14);

    int maxheight;

    private class BstNodeInfo {
        boolean isSelected;
        Rectangle nodeRect;
    }

    HashMap<BstNode, BstNodeInfo> nodeRectData;

    /* 
     * Create a new window with the given width and height 
     * that is showing the given tree.
     */
    public BstPanel() {
        //Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.BLUE);
    }

    /*
     * Set the display to show the given tree.
     */
    public void setTree(Bst tree) {
        this.tree = tree;
        maxheight = tree.getHeight();
        nodeRectData = new HashMap<BstNode, BstNodeInfo>();
    }

    public Bst getTree() {
        return  tree;
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
        String s = node.getTextValue();
        g.setFont(font);

        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();

        int xpos = (minX + maxX)/2;
        int ypos = y + yStep/2;

        Rectangle rectangle = new Rectangle(xpos - width/2 -2, ypos- height, width + 2, height + 4);

        BstNodeInfo info;
        if (nodeRectData.containsKey(node)) {
            info = nodeRectData.get(node);
        } else {
            info = new BstNodeInfo();
        }
        info.nodeRect = rectangle;
        nodeRectData.put(node,  info);

        g.drawRect(xpos - width / 2 - 2, ypos - height, width + 2, height + 4);
        if (info.isSelected) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.lightGray);
        }
        g.fillRect(xpos - width / 2 - 2, ypos - height, width + 2, height + 4);
        g.setColor(Color.BLUE);
        g.drawString(s, xpos - width / 2, y + yStep / 2);
        return  new Dimension(width,height);
    }


    /*
     * Draw the contents of the tree into the given Graphics context.
     * It will place all info between minX and maxX in the x-direction,
     * starting at location y in the y-direction.  Levels of the tree
     * will be separated by yStep pixels.
     */
    protected void drawTree(Graphics g, int minX, int maxX,  int y, float yStep, BstNode node) {

        Dimension textDimension = null;
        textDimension = drawNode(g, minX, maxX, y, (int) yStep, node);

        int xcentre = (minX + maxX)/2;
        int ycentre = y + (int) yStep/2;
        int xSep = Math.min((maxX - minX)/8, 10);

        g.setColor(Color.BLUE);
        if (node.getLeft() != null) {
            // if left tree not empty, draw line to it and recursively // draw that tree
            g.drawLine(xcentre - xSep, ycentre + 5, (minX + xcentre) / 2, ycentre + (int) yStep - textDimension.height);
            drawTree(g, minX, xcentre, y + (int)yStep, yStep, node.getLeft());
        }

        if (node.getRight() != null) {
            // same thing for right subtree.
            g.drawLine(xcentre + xSep, ycentre + 5, (maxX + xcentre) / 2, ycentre + (int) yStep - textDimension.height);
            drawTree(g, (minX + maxX)/2, maxX, y + (int) yStep, yStep, node.getRight());
        }
    }

    public void clearSelection(BstNode selectNode) {
        for (BstNode node: nodeRectData.keySet()) {
            BstNodeInfo info = nodeRectData.get(node);
            if (node != selectNode) {
                info.isSelected = false;
            } else {
                info.isSelected = true;
            }
        }
    }

    public void selectNode(Point point, boolean isMultipleSelection) {
        boolean isNodeSelected = false;
        BstNode selectnode = null;
        for (BstNode node: nodeRectData.keySet()) {
            BstNodeInfo info = nodeRectData.get(node);
            if (info.nodeRect.contains(point)) {
                info.isSelected = !info.isSelected;
                isNodeSelected = true;
                selectnode = node;
                break;
            }
        }
        if (!isNodeSelected) {
            clearSelection(null);
        } else {
            if (!isMultipleSelection) {
                clearSelection(selectnode);
            }
        }
    }

    public void selectNode(BstNode node) {
        BstNodeInfo info = nodeRectData.get(node);
        if (info != null) {
            info.isSelected = !info.isSelected;
        }
        clearSelection(node);
    }

    public void selectNode(BstNode node, boolean isSelected) {
        BstNodeInfo info = nodeRectData.get(node);
        if (info != null) {
            info.isSelected = isSelected;
        }
    }

    public void addNode(BstNode node) {
        BstNodeInfo info = new BstNodeInfo();
        info.isSelected = true;
        nodeRectData.put(node, info);
    }

    public ArrayList<BstNode> getSelectedNodes() {
        ArrayList<BstNode> nodes = new ArrayList<BstNode>();
        for (BstNode node: nodeRectData.keySet()) {
            BstNodeInfo info = nodeRectData.get(node);
            if (info.isSelected) {
                nodes.add(node);
            }
        }
        return nodes;
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
            int treeHeight = Math.max(tree.getHeight(), maxheight);
            float ystep = (float) height / treeHeight;

            drawTree(g, 0, width, 0, ystep, tree.getRoot());
        }
    }


}