package org.dsl.bst.gui;

import org.dsl.bst.BstNode;

/**
 * Created by rajesh on 26/9/16.
 */
public interface INodeVisitor {
    public void nodeVisited(BstNode node);
}