package ca.monor.leetCode.tree.binaryTree.validateBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> list1 = new ArrayList<>();

        //对树进行中序遍历，并把结果记录到 list 中
        inOrder(root, list1);

        //如果 list 中的前一个元素大于或等于下一个元素，则不是 BST
        for (int i = 0; i < list1.size() - 1; i++) {
            if (list1.get(i) >= (list1.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root, List<Integer> list1) {
        if (root == null) {
            return;
        }

        inOrder(root.left, list1);
        list1.add(root.val);
        inOrder(root.right, list1);
    }
}
