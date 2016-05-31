public class BTree {

    private node root;

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

    public int height() {  //Ram


        return 0;
    }

    //Get the cousin node, the node that is in the corresponding position in the tree in the other half of the tree
    public node getCousinNode(int data) { //Rajesh

        //Start with rootNode for both the node passed and the cousin Node
        node followNode = root;
        node thisNode = root;
        node cousinNode = null;

        // Algo. start with root and go in one direction towards the node that is passed from root
        // follow the exact opposite direction at each step for finding cousinNode
        while ((followNode != null) && (thisNode != null)) {
            cousinNode = followNode;
            if (data < thisNode.data) {
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


    public static void main(String[] args) {

        BTree myTree = new BTree();

            myTree.insert(50);
            myTree.insert(40);
            myTree.insert(60);
            myTree.insert(35);
            myTree.insert(45);
            myTree.insert(55);
            myTree.insert(65);

        myTree.display();

            int val = 35;
            node cousinNode = myTree.getCousinNode(val);
            if (cousinNode != null) {
                System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
            }

            val = 55;
            cousinNode = myTree.getCousinNode(val);
            if (cousinNode != null) {
                System.out.println("cousin Node for " + val + " is: " + cousinNode.data );
            }

    }
}
