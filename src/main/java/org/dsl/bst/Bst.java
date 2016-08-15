package org.dsl.bst;

import java.util.*;

public class Bst<T extends Comparable<T>> {

    private Node root;

    Bst() {
        root = null;
    }

    class Node<T extends Comparable<T>> implements PrintableNode {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }

        public PrintableNode getLeft() {
            return left;
        }

        public PrintableNode getRight() {
            return right;
        }

        public String getText() {
            return data.toString();
        }
    }

    class InsertPosNode {
        Node insertNodePoint;
        boolean isLeft;
        InsertPosNode() {
            insertNodePoint = null;
            isLeft = false;
        }
    }


    public InsertPosNode findInsertPosNode(T data) {
        Node<T> tmp = root;

        InsertPosNode insNode = new InsertPosNode();
        while (tmp != null) {
            insNode.insertNodePoint = tmp;
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

    public void insert(T data) {

        InsertPosNode insNodePos = findInsertPosNode(data);
        if (insNodePos.insertNodePoint == null) {
            root = new Node(data);
        } else {
            Node insNode = insNodePos.insertNodePoint;
            if (insNodePos.isLeft) {
                insNode.left = new Node(data);
            } else {
                insNode.right = new Node(data);
            }
        }
    }

    public void delete(int data) {

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



}
