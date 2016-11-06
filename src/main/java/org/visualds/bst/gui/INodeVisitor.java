package org.visualds.bst.gui;

import org.visualds.bst.BstNode;

/**
 * Created by rajesh on 26/9/16.
 */
public interface INodeVisitor {
    public void nodeVisited(BstNode node);
}
