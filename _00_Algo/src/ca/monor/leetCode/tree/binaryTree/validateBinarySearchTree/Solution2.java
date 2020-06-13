package ca.monor.leetCode.tree.binaryTree.validateBinarySearchTree;

import java.util.Stack;

public class Solution2 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidBST(TreeNode root) {
        long prev = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (root.val <= prev) {
                        return false;
                    }
                    prev = root.val;
                    root = root.right;
                }
            }
        }
        return true;
    }

    private TreeNode pre = null;

    public boolean isValidBST2(TreeNode root) {
        // 中序遍历
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        return isValidBST(root.right);
    }
}
