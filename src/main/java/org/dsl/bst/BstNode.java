package org.dsl.bst;

/**
 * Created by rajesh on 25/9/16.
 */
public class BstNode<T extends Comparable<T>>{
    T data;
    BstNode left;
    BstNode right;

    BstNode(T data) {
        this.data = data;
        left = right = null;
    }

    public BstNode getLeft() {
        return  left;
    }

    public BstNode getRight() {
        return  right;
    }

    public String getTextValue() {
        return data.toString();
    }

    public T getData() {
        return data;
    }
}
