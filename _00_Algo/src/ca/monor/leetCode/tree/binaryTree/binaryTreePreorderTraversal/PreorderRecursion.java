package ca.monor.leetCode.tree.binaryTree.binaryTreePreorderTraversal;

import java.util.LinkedList;
import java.util.List;

public class PreorderRecursion {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new LinkedList<>();
            recursion(root, list);

            return list;
        }

        private void recursion(TreeNode root, List<Integer> list) {
            if (root == null) return;

            list.add(root.val);
            recursion(root.left, list);
            recursion(root.right, list);
        }
    }
}
