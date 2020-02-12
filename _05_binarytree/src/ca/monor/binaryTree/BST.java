package ca.monor.binaryTree;

public class BST {
    private treeNode root;

    public BST(treeNode root) {
        this.root = root;
    }

    public void arrayToTree(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {

        }
    }

    public void add(int val) {
        if (root == null) {
            root.value = val;
            return;
        }

        treeNode node = root;
        if (val > root.value) {

        }
    }

    public static class treeNode{
        int value;
        treeNode left;
        treeNode right;
    }
}