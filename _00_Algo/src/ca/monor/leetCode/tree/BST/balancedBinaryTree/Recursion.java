package ca.monor.leetCode.tree.BST.balancedBinaryTree;

public class Recursion {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        height(root);
        return res;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = height(node.left) + 1;
        int rightHeight = height(node.right) + 1;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            res = false;
            return 0;
        }
        return Math.max(leftHeight, rightHeight);
    }
}
