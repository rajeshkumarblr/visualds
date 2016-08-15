package org.dsl.bst;

/** Node that can be printed */
public interface PrintableNode
{
    /** Get left child */
    PrintableNode getLeft();


    /** Get right child */
    PrintableNode getRight();


    /** Get text to be printed */
    String getText();
}
