package ca.monor.leetCode.tree.binaryTree.minimumAbsoluteDifferenceInBST;

/**
 * 530. Minimum Absolute Difference in BST
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumAbsoluteDifferenceInBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private TreeNode prev;
    private int minimum = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        minimumDifference(root);
        return minimum;
    }

    private void minimumDifference(TreeNode root) {
        if (root == null) {
            return;
        }
        minimumDifference(root.left);
        if (prev != null) {
            minimum = Math.min(minimum, Math.abs(root.val = prev.val));
        }
        minimumDifference(root.right);
    }
}
