public class BTree {

    node root;

    BTree() {
        root = null;
    }

    class node {
        int data;
        node left;
        node right;

        node(int data) {
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



    public InsertPosNode findInsertPosNode(int data) {
        node tmp = root;

        InsertPosNode insNode = new InsertPosNode();
        while (tmp != null) {
            insNode.insertNodePoint = tmp;
            if (data <= tmp.data) {
                insNode.isLeft = true;
                tmp = tmp.left;
            } else {
                insNode.isLeft = false;
                tmp = tmp.right;
            }
        }
        return insNode;

    }

    public void insert(int data) {

        InsertPosNode insNodePos = findInsertPosNode(data);
        if (insNodePos.insertNodePoint == null) {
            root = new node(data);
            return;
        } else {
            node insNode = insNodePos.insertNodePoint;
            if (insNodePos.isLeft) {
                insNode.left = new node(data);
            } else {
                insNode.right = new node(data);
            }
        }
    }

    public void delete(int data) {

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
        if(nd==null){// End recursive function when node.left or node.right is null.
            // The children of leaf nodes are null. Therefore this is saying that once we've gone past the leaves, there are no further nodes.
            return 0;
        }
        //The current node adds a height of 1 to the height of the subtree currently being calculated.
        // We recursively calculate the height of the left subtree (node.left) and right subtree (node.right).
        // Since we're calculating the maximum depth, we take the maximum of these two depths.
        return 1+ Math.max(height(nd.left),height(nd.right));
    }
    public node getCousinNode(node nd) { //Rajesh

    }


    public static void main(String[] args) {
	// write your code here
        BTree myTree = new BTree();

            myTree.insert(50);
            myTree.insert(40);
            myTree.insert(60);

        myTree.display();
    }
}
