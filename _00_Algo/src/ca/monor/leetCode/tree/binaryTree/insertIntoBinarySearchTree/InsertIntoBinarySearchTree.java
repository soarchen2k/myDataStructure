package ca.monor.leetCode.tree.binaryTree.insertIntoBinarySearchTree;

/**
 * 701. Insert into a Binary Search Tree
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */
public class InsertIntoBinarySearchTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            if (val < node.val) {
                parent = node;
                node = node.left;
            } else {
                parent = node;
                node = node.right;
            }
        }
        if (val < parent.val) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }

        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else if (val < root.val) {
            root.left = insertIntoBST2(root.left, val);
        } else {
            root.right = insertIntoBST2(root.right, val);
        }
        return root;
    }
}
