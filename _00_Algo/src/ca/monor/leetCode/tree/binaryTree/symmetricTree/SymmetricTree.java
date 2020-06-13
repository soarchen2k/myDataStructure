package ca.monor.leetCode.tree.binaryTree.symmetricTree;

import java.util.Stack;

/**
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 * 先比较 root 的左右是否一致，不一致则 false，如果一致，则比较两路是否对称，各个元素进行比较
 */

public class SymmetricTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        do {
            if (left != null) {
                stackLeft.push(left);
                left = left.left;
            } else {
                left = stackLeft.pop();
                left = left.right;
            }

            if (right != null) {
                stackRight.push(right);
                right = right.right;
            } else {
                right = stackRight.pop();
                right = right.left;
            }

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || left.val != right.val) {
                return false;
            }
        } while (!stackLeft.isEmpty() && !stackRight.isEmpty() || left != null);

        return true;
    }
}
