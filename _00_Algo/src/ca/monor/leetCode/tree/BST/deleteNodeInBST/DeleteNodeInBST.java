package ca.monor.leetCode.tree.BST.deleteNodeInBST;

/**
 * 450. Delete Node in a BST
 * https://leetcode.com/problems/delete-node-in-a-bst/
 */

public class DeleteNodeInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        TreeNode node = treeNode(root, key);

        return node;
    }

    private void deleteNode(TreeNode node) {
        if (node == null) {
            return;
        }

    }

    private TreeNode treeNode(TreeNode root, int key) { //找出需要删除的 node
        if (key == root.val) {
            return root;
        }

        TreeNode node = key > root.val ? root.right : root.left;
        while (node != null) {
            if (node.val == key) {
                return node;
            } else {
                node = key > node.val ? node.right : node.left;
            }
        }
        return null;
    }
}
