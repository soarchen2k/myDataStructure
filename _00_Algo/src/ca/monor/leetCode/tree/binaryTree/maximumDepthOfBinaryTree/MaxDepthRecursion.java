package ca.monor.leetCode.tree.binaryTree.maximumDepthOfBinaryTree;

/**
 * 递归法解最大深度问题
 */
public class MaxDepthRecursion {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = 0;
            int rightDepth = 0;
            leftDepth = maxDepth(root.left);
            rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
}
