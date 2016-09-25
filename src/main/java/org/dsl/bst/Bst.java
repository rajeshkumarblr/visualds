package org.dsl.bst;

import java.util.*;

public class Bst<T extends Comparable<T>> {

    private Node root;

    public Bst() {
        root = null;
    }

    class Node<T extends Comparable<T>> implements BstNode {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }

        public BstNode getLeft() {
            return left;
        }

        public BstNode getRight() {
            return right;
        }

        public String getText() {
            return data.toString();
        }
    }

    class InsertNodePosition {
        Node parentNode;
        boolean isLeft;
        InsertNodePosition() {
            parentNode = null;
            isLeft = false;
        }
    }

    public InsertNodePosition findInsertPosNode(T data) {
        Node<T> tmp = root;

        InsertNodePosition insNode = new InsertNodePosition();
        while (tmp != null) {
            insNode.parentNode = tmp;
            if (data.compareTo(tmp.data) < 0) {
            //if (data <= tmp.data) {
                insNode.isLeft = true;
                tmp = tmp.left;
            } else {
                insNode.isLeft = false;
                tmp = tmp.right;
            }
        }
        return insNode;
    }

    public  Node getRoot() {
        return root;
    }

    public Node insert(T data) {

        InsertNodePosition insNodePos = findInsertPosNode(data);
        Node node;
        if (insNodePos.parentNode == null) {
            root = node = new Node(data);
        } else {
            Node insNode = insNodePos.parentNode;
            if (insNodePos.isLeft) {
                insNode.left = node = new Node(data);
            } else {
                insNode.right = node = new Node(data);
            }
        }
        return node;
    }

    private void removeNode(Node parentNode,Node successorNode, boolean isLeftChild) {
        if (parentNode != null) {
            if (isLeftChild) {
                parentNode.left = successorNode;
            } else {
                parentNode.right = successorNode;
            }
        } else {
            root = successorNode;
        }
    }

    /** BST delete function */
    public Node delete(T data) {

        Node delNode = root;
        Node parentNode = null;
        boolean isLeftChild = false;

        // First, find the node to be deleted in the BST and it's parent.
        while (delNode != null) {
            int result = delNode.data.compareTo(data);
            if (result == 0) {
                break;
            } else if (result > 0) {
                parentNode = delNode;
                isLeftChild = true;
                delNode = delNode.left;
            }  else if (result < 0) {
                parentNode = delNode;
                isLeftChild = false;
                delNode = delNode.right;
            }
        }

        // If the data is not present in tree, nothing to do; return null.
        if (delNode == null) {
            return null;
        }
        Node successorNode = null;
        // Check if the delNode is a Leaf node.
        if (delNode.left == null && delNode.right == null) {
            removeNode(parentNode, successorNode, isLeftChild);
        } else if (delNode.left == null || delNode.right == null) {
            // Check if the delNode's left or right child is null.
            successorNode = (delNode.left != null) ? delNode.left : delNode.right;
            removeNode(parentNode, successorNode, isLeftChild);
        } else {
            // So the delNode has both left and right child. So find the inorder predecessor node and replace delNode with it.
            Node leftnode = delNode.left;
            // First check if the right subtree of left child is empty. If so, left node is the successor node.
            if (leftnode.right == null) {
                removeNode(parentNode,delNode.left,isLeftChild);
                leftnode.right = delNode.right;
                successorNode = leftnode;
            }else {
                // Find the right most child of the leftnode.
                Node maxparent = leftnode;
                Node maxNode = leftnode.right;
                while (maxNode.right != null) {
                    maxparent = maxNode;
                    maxNode = maxNode.right;
                }
                // Now remove the maxnode from it's parent.
                removeNode(maxparent, null, false);
                // Copy the max node's value into the delnode, effectively deleting delNode.
                delNode.data = maxNode.data;
            }
        }
        return  successorNode;
    }


    public void inorder(Node nd) {
        if (nd == null)
            return;

        inorder(nd.left);
        System.out.println("" + nd.data + " ");
        inorder(nd.right);
    }

    public void display() {
        inorder(root);

    }

    private int height(Node root) {
        if (root != null) {
            int lheight = height(root.left);
            int rheight = height(root.right);
            return Math.max(lheight,rheight) + 1;
        } else {
            return -1;
        }
    }

    ArrayList<Node> getLevelOrderNodes(Node root) {
        if (root == null) {
            return  null;
        }
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(root);
        int index = 0;
        do {
            Node current = nodes.get(index);
            if (current != null) {
                if (current.left != null) {
                    nodes.add(current.left);
                }
                if (current.right != null) {
                    nodes.add(current.right);
                }
            }
            index++;
        } while (index < nodes.size());

        return nodes;
    }

    public int getHeight() {  //Ram
        return height(root);
    }

    //Get the cousin Node, the Node that is in the corresponding position in the tree in the other half of the tree
    public Node getCousinNode(T data) { //Rajesh

        //Start with rootNode for both the Node passed and the cousin Node
        Node<T> followNode = root;
        Node<T> thisNode = root;
        Node<T> cousinNode = null;

        // Algo. start with root and go in one direction towards the Node that is passed from root
        // follow the exact opposite direction at each step for finding cousinNode
        while ((followNode != null) && (thisNode != null)) {
            cousinNode = followNode;
            if (data.compareTo(thisNode.data) < 0) {
                //if data is less go left
                thisNode = thisNode.left;
                // and go right for cousin Node
                followNode = followNode.right;
            } else {
                //if data is not less right
                thisNode = thisNode.right;
                //and go left for cousin Node
                followNode = followNode.left;
            }
        }
        //return the cousinNode
        return  cousinNode;
    }


    Node prevBstNode = null;

    /** Check if a given tree is a BST. How to check that?
     * Simple, do an inorder traversal of the tree and make sure if the data you encounter are sorted
     * (i.e, the previous node's value in inorder traversal is less than current node's value.
     * @param root
     * @return flag that indicates if this is a BST or not.
     */
    boolean checkBST(Node root) {
        boolean isBST = true;
        if (root != null) {
            checkBST(root.left);
            if (prevBstNode != null) {
                isBST = (root.data.compareTo(prevBstNode.data) > 0);
            }
            checkBST(root.right);
        }
        return isBST;
    }

    public static Bst<Integer> createRandomeBst() {
        Bst<Integer> tree = new Bst<Integer>();
        tree.insert(50);

        for (int i=0;i<5; i++) {
            int val = (int)(Math.random() * (50-1)) + 1;
            tree.insert(val);
        }

        for (int i=5;i<10; i++) {
            int val = (int)(Math.random() * 50) + 50;
            tree.insert(val);
        }
        return tree;
    }

}
