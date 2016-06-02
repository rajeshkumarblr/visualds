package org.dsk;

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

    public List<node> getnodeAt(int lvl) {

        List<node> nodesatLevel = null;
        /*
            If the level is 0, return root node
         */
        if (lvl == 0) {
            nodesatLevel = new ArrayList<node>(1);
            nodesatLevel.add(root);
        } else {
            /*
                iterate through all nodes calculating the depth of the traversal and pickup only nodes equialent to the leval input.
             */
            int depth = 0;
            node thisnode = root, parentnode = root;
            node leftChild = thisnode.left, rightchild = thisnode.right;
            nodesatLevel = new ArrayList<node>();
            while (((leftChild != null || rightchild != null) && depth < lvl)) {
                depth++;
                if (leftChild != null && depth == lvl) {
                    nodesatLevel.add(leftChild);
                }
                if (rightchild != null && depth == lvl) {
                    nodesatLevel.add(rightchild);
                }
                /*
                    Since the while loop is done for <= level, there could be iterations where level does not match with current depth.
                    In such cases, selecting left node.
                 */
                if (leftChild != null && (leftChild.left != null || leftChild.right != null)) {
                    parentnode = thisnode;
                    thisnode = leftChild;
                    leftChild = thisnode.left;
                    rightchild = thisnode.right;
                } else if (rightchild != null && (rightchild.left != null || rightchild.right != null)) {
                    parentnode = thisnode;
                    thisnode = rightchild;
                    leftChild = rightchild.left;
                    rightchild = rightchild.right;
                } else if (parentnode.right != null) {
                    // parentnode = parentnode.right;
                    if ((parentnode.right.left != null && !nodesatLevel.contains(parentnode.right.left)) || (parentnode.right.right != null && !nodesatLevel.contains(parentnode.right.right))) {
                        thisnode = parentnode.right;
                        leftChild = thisnode.left;
                        rightchild = thisnode.right;
                        depth--;
                    }
                }
            }


        }

        return nodesatLevel;

    }

    public int height(node nd) {  //Ram
        if(nd==null){// End recursive function when node.left or node.right is null.
            // The children of leaf nodes are null. Therefore this is saying that once we've gone past the leaves, there are no further nodes.
            return 0;
        }
        //The current node adds a height of 1 to the height of the subtree currently being calculated.
        // We recursively calculate the height of the left subtree (node.left) and right subtree (node.right).
        // Since we're calculating the maximum depth, we take the maximum of these two depths.
        return 1+ Math.max(height(nd.left),height(nd.right));
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
        return  cousinNode;
    }
}
