package ca.monor.binaryTree;

public class BST {
    private treeNode root;

    public BST() {
        this.root = null;    }

    public void arrayToTree(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                this.add(arr[i][j]);
            }
        }
    }

    public void add(int val) {
        if (root == null) {
            root = new treeNode(val, null);
            return;
        }
        treeNode parent = root;
        treeNode node = root;

        do {
            parent = node;
            if (val == node.value) {
                return;
            } else if (val > node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        } while (node != null);

        treeNode newNode = new treeNode(val, parent);

        if (val > parent.value) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    public static class treeNode{
        int value;
        treeNode left;
        treeNode right;
        treeNode parent;

        public treeNode(int value, treeNode parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        BST binarySearchTree = new BST();
        int[][] arr = {{52, 657, 7659, 6754}, {32, 25, 13, 27}, {32, 54, 2433, 6756, 2133, 656}};
        binarySearchTree.arrayToTree(arr);
    }
}