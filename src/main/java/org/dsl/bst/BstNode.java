package org.dsl.bst;

/** A simple interface for displaying the  BST */
public interface BstNode
{
    /** Get left child */
    BstNode getLeft();


    /** Get right child */
    BstNode getRight();


    /** Get text to be printed */
    String getText();
}
