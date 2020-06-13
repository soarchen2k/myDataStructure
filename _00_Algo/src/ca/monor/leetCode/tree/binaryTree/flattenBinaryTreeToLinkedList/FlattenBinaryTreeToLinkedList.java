package ca.monor.leetCode.tree.binaryTree.flattenBinaryTreeToLinkedList;

/**
 * 114. Flatten Binary Tree to Linked List
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */

public class FlattenBinaryTreeToLinkedList {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public void flatten(TreeNode root) {
        if (root==null) return;
        while (root != null) {
            if (root.left != null) {  //如果 root 的左子树不为空

                // 找出左子树的结束点
                    TreeNode leftEnd = root.left;
                    while (leftEnd.right != null) {
                        leftEnd = leftEnd.right;
                    }

                    // 更换各个节点的指向
                    leftEnd.right = root.right;
                    root.right = root.left;
                    root.left = null;
                    root = root.right;
            } else { // root.left == null;
                root = root.right;
            }
        }
    }
}
