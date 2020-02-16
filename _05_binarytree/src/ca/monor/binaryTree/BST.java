package ca.monor.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private treeNode root;
    public BST() { }

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
        treeNode parent;
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

    public void levelTravel() {
        Queue<treeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            treeNode node = queue.poll();
            System.out.println(node.value);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST binarySearchTree = new BST();
        int[][] arr = {{52, 657, 7659, 6754}, {32, 25, 13, 27}, {32, 54, 2433, 6756, 2133, 656}};
        binarySearchTree.arrayToTree(arr);
        binarySearchTree.levelTravel();
    }
}