package org.visualds.bst;

import org.visualds.bst.gui.INodeVisitor;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Bst<T extends Comparable<T>> {

    private BstNode root;

    public Bst() {
        root = null;
    }

    class InsertNodePosition {
        BstNode parentNode;
        boolean isLeft;
        InsertNodePosition() {
            parentNode = null;
            isLeft = false;
        }
    }

    public InsertNodePosition findInsertPosNode(T data) {
        BstNode<T> tmp = root;

        InsertNodePosition insNode = new InsertNodePosition();
        while (tmp != null) {
            insNode.parentNode = tmp;
            if (data.compareTo(tmp.data) < 0) {
                insNode.isLeft = true;
                tmp = tmp.left;
            } else {
                insNode.isLeft = false;
                tmp = tmp.right;
            }
        }
        return insNode;
    }

    public BstNode getRoot() {
        return root;
    }

    public BstNode insert(T data) {

        InsertNodePosition insNodePos = findInsertPosNode(data);
        BstNode node;
        if (insNodePos.parentNode == null) {
            root = node = new BstNode(data);
        } else {
            BstNode insNode = insNodePos.parentNode;
            if (insNodePos.isLeft) {
                insNode.left = node = new BstNode(data);
            } else {
                insNode.right = node = new BstNode(data);
            }
        }
        return node;
    }

    private void removeNode(BstNode parentNode, BstNode successorNode, boolean isLeftChild) {
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
    public BstNode delete(T data) {

        BstNode delNode = root;
        BstNode parentNode = null;
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
        BstNode successorNode = null;
        // Check if the delNode is a Leaf node.
        if (delNode.left == null && delNode.right == null) {
            removeNode(parentNode, successorNode, isLeftChild);
        } else if (delNode.left == null || delNode.right == null) {
            // Check if the delNode's left or right child is null.
            successorNode = (delNode.left != null) ? delNode.left : delNode.right;
            removeNode(parentNode, successorNode, isLeftChild);
        } else {
            // So the delNode has both left and right child. So find the inorder predecessor node and replace delNode with it.
            BstNode leftnode = delNode.left;
            // First check if the right subtree of left child is empty. If so, left node is the successor node.
            if (leftnode.right == null) {
                removeNode(parentNode,delNode.left,isLeftChild);
                leftnode.right = delNode.right;
                successorNode = leftnode;
            }else {
                // Find the right most child of the leftnode.
                BstNode maxparent = leftnode;
                BstNode maxNode = leftnode.right;
                while (maxNode.right != null) {
                    maxparent = maxNode;
                    maxNode = maxNode.right;
                }
                // Now remove the maxnode from it's parent.
                removeNode(maxparent, maxNode.left, false);
                // Copy the max node's value into the delnode, effectively deleting delNode.
                delNode.data = maxNode.data;
                successorNode = delNode;
            }
        }
        return  successorNode;
    }


    public void inorder(BstNode nd, INodeVisitor vistor) {
        if (nd != null) {
            inorder(nd.left, vistor);
            vistor.nodeVisited(nd);
            inorder(nd.right,vistor);
        }
    }

    public boolean isParent(BstNode parentNode, BstNode childNode) {
        return ((parentNode.getLeft() == childNode) || (parentNode.getRight() == childNode));
    }

    BstNode getNextNodeInPathTo(BstNode destNode, BstNode currentNode, AtomicBoolean isNodeFound) {
        isNodeFound.set(false);
        if (currentNode == null) return null;
        int result = destNode.getData().compareTo(currentNode.getData());
        if (result== 0) {
            isNodeFound.set(true);
            return currentNode;
        } else if (result > 0) {
            return currentNode.getRight();
        } else if (result < 0) {
            return currentNode.getLeft();
        }
        return  null;
    }


    public  BstNode findCommonAncestorNode(BstNode node1, BstNode node2) {
        BstNode currentNode1 = root;
        BstNode currentNode2 = root;

        BstNode ancestorNode = null;
        while (currentNode1 == currentNode2) {
            ancestorNode = currentNode1;
            AtomicBoolean isNodeFound1 = new AtomicBoolean(false);
            currentNode1 = getNextNodeInPathTo(node1, currentNode1,isNodeFound1);
            AtomicBoolean isNodeFound2 = new AtomicBoolean(false);
            currentNode2 = getNextNodeInPathTo(node2, currentNode2, isNodeFound2);
            if ((currentNode1 == null) || (currentNode2 == null)) {
                break;
            }
            if (isNodeFound1.get() && isParent(currentNode1,currentNode2)) {
                ancestorNode = currentNode1;
                break;
            }
            if (isNodeFound2.get() && isParent(currentNode2,currentNode1)) {
                ancestorNode = currentNode2;
                break;
            }
        };
        return  ancestorNode;
    }

    public ArrayList<BstNode> getShortestPath(BstNode node1, BstNode  node2) {
        ArrayList<BstNode> nodes = new ArrayList<BstNode>();
        BstNode ancestorNode = findCommonAncestorNode(node1, node2);
        BstNode node = ancestorNode;
        AtomicBoolean isNodeFound = new AtomicBoolean(false);
        while (node != node1) {
            nodes.add(node);
            node = getNextNodeInPathTo(node1, node, isNodeFound);
        }
        Collections.reverse(nodes);
        node = ancestorNode;
        while (node != node2) {
            nodes.add(node);
            node = getNextNodeInPathTo(node2, node, isNodeFound);
        }
        nodes.add(node2);
        return nodes;
    }


    public void preorder(BstNode nd, INodeVisitor vistor) {
        if (nd != null) {
            vistor.nodeVisited(nd);
            preorder(nd.left, vistor);
            preorder(nd.right,vistor);
        }
    }

    public void postorder(BstNode nd, INodeVisitor vistor) {
        if (nd != null) {
            preorder(nd.left, vistor);
            preorder(nd.right,vistor);
            vistor.nodeVisited(nd);
        }
    }

    public ArrayList<BstNode> levelOrder(BstNode node, INodeVisitor iNodeVisitor) {
        ArrayList<BstNode> nodes = new ArrayList<BstNode>();
        Queue<BstNode> queue = new  LinkedList();
        queue.add(node);
        while (!queue.isEmpty()) {
            BstNode nd = queue.remove();
            iNodeVisitor.nodeVisited(nd);
            if (nd.getLeft() != null)
                queue.add(nd.getLeft());
            if (nd.getRight() != null)
                queue.add(nd.getRight());
        }
        return nodes;
    }

    private int height(BstNode root) {
        if (root != null) {
            int lheight = height(root.left);
            int rheight = height(root.right);
            return Math.max(lheight,rheight) + 1;
        } else {
            return 0;
        }
    }

    public int getHeight() {  //Ram
        return height(root);
    }

    //Get the cousin BstNode, the BstNode that is in the corresponding position in the tree in the other half of the tree
    public BstNode getMirrorNode(BstNode node) {

        //Start with rootNode for both the BstNode passed and the cousin BstNode
        BstNode<T> followNode = root;
        BstNode<T> thisNode = root;
        BstNode<T> cousinNode = null;

        boolean isMirrorNodeFound = false;

        // Algo: start with root and go in one direction towards the BstNode that is passed from root
        // follow the exact opposite direction at each step for finding cousinNode
        while ((thisNode != null) && (followNode != null)) {

            if (node.data.compareTo(thisNode.data) == 0) {
                isMirrorNodeFound = true;
                break;
            }
            if (node.data.compareTo(thisNode.data) < 0) {
                //if data is less go left
                thisNode = thisNode.left;
                // and go right for cousin BstNode
                followNode = followNode.right;
            } else {
                //if data is not less right
                thisNode = thisNode.right;
                //and go left for cousin BstNode
                followNode = followNode.left;
            }
        }
        //return the cousinNode
        if (isMirrorNodeFound) {
            cousinNode = followNode;
        }
        return  cousinNode;
    }


    BstNode prevBstNode = null;

    /** Check if a given tree is a BST. How to check that?
     * Simple, do an inorder traversal of the tree and make sure if the data you encounter are sorted
     * (i.e, the previous node's value in inorder traversal is less than current node's value.
     * @param root
     * @return flag that indicates if this is a BST or not.
     */
    boolean checkBST(BstNode root) {
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

    public ArrayList<BstNode> getTopViewNodes() {
        ArrayList<BstNode> nodes = new ArrayList<BstNode>();
        BstNode node = root;
        while (node!= null) {
            nodes.add(node);
            if (node.left == null) {
                node = node.right;
            } else  {
                node = node.left;
            }
        }
        node = root;
        while (node!= null) {
            nodes.add(node);
            if (node.right == null) {
                node = node.left;
            } else  {
                node = node.right;
            }
        }
        return nodes;
    }

}
