package org.dsk.tree;

// We want the BST class to be passed any Class/type that can be comparable
// Refer to this stack flow discussion: http://stackoverflow.com/questions/2071929/generics-and-sorting-in-java/2071969#2071969
// and this http://stackoverflow.com/questions/2081663/how-to-set-constraints-on-generic-types-in-java

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private node root;

    BinarySearchTree() {
        root = null;
    }

    class node {
        T data;
        node left;
        node right;

        node(T data) {
            this.data = data;
            left = right = null;
        }
    }

    class InsertPosNode {
        node insertNodePoint;
        boolean isLeft;

        InsertPosNode() {
            insertNodePoint = null;
            isLeft = false;
        }
    }


    public InsertPosNode findInsertPosNode(T data) {
        node tmp = root;

        InsertPosNode insNode = new InsertPosNode();
        while (tmp != null) {
            insNode.insertNodePoint = tmp;
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

    public void insert(T data) {

        InsertPosNode insNodePos = findInsertPosNode(data);
        if (insNodePos.insertNodePoint == null) {
            root = new node(data);
        } else {
            node insNode = insNodePos.insertNodePoint;
            if (insNodePos.isLeft) {
                insNode.left = new node(data);
            } else {
                insNode.right = new node(data);
            }
        }
    }

    public void delete(T data) {

    }

    public List<node> findnodesAt(int level) {
        List<node> levelNodesHolder = new ArrayList<node>();
        findNodesAt(root, level, null,levelNodesHolder);
        return levelNodesHolder;
    }

    /**
     * takes the node to start with and walks to the left of the tree follower by right of tree.
     *
     * Meanwhile it also keeps tracks of the current level using
     * currentDepth and when requesteLevel matches currentDepth, node is added to the list collection.
     *
     * recursion stops when the node is null (or) currentDepth is greater than requested level. (Whichever is earlier)
     *
     * @param n
     * @param level
     * @param currentDepth
     * @param collection
     */
    private void findNodesAt(node n, int level, Integer currentDepth,List<node> collection) {
        if (n == null) {
            currentDepth--;
            return;
        } else {
            if (currentDepth == null) {
                currentDepth = 0;
            }else{
                currentDepth++;
            }
        }

        if(level == currentDepth){
            collection.add(n);
        }

        else if(currentDepth<level) {
            findNodesAt(n.left, level, currentDepth,collection);
            findNodesAt(n.right, level, currentDepth,collection);
        }
    }

    public void inorder(node nd) {
        if (nd == null)
            return;

        inorder(nd.left);
        System.out.println("" + nd.data + " ");
        inorder(nd.right);
    }

    public void display() {
        inorder(root);

    }



    public int height(node nd) {  //Ram
        if (nd == null) {// End recursive function when node.left or node.right is null.
            // The children of leaf nodes are null. Therefore this is saying that once we've gone past the leaves, there are no further nodes.
            return 0;
        }
        //The current node adds a height of 1 to the height of the subtree currently being calculated.
        // We recursively calculate the height of the left subtree (node.left) and right subtree (node.right).
        // Since we're calculating the maximum depth, we take the maximum of these two depths.
        return 1 + Math.max(height(nd.left), height(nd.right));
    }

    //Get the cousin node, the node that is in the corresponding position in the tree in the other half of the tree
    public node getCousinNode(T data) { //Rajesh

        //Start with rootNode for both the node passed and the cousin Node
        node followNode = root;
        node thisNode = root;
        node cousinNode = null;

        // Algo. start with root and go in one direction towards the node that is passed from root
        // follow the exact opposite direction at each step for finding cousinNode
        while ((followNode != null) && (thisNode != null)) {
            cousinNode = followNode;
            //if (data < thisNode.data) {
            if (data.compareTo(thisNode.data) < 0) {
                //if data is less go left
                thisNode = thisNode.left;
                // and go right for cousin node
                followNode = followNode.right;
            } else {
                //if data is not less right
                thisNode = thisNode.right;
                //and go left for cousin node
                followNode = followNode.left;
            }
        }
        //return the cousinNode
        return cousinNode;
    }
}
