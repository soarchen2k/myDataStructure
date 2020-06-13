package ca.monor.leetCode.tree.binaryTree.binaryTreeInorderTraversal;

import java.util.LinkedList;
import java.util.List;

public class InorderRecursion {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        recursion(root, list);
        return list;
    }

    private void recursion(TreeNode root, List<Integer> list) {
        if (root == null) return;
        recursion(root.left, list);
        list.add(root.val);
        recursion(root.right, list);
    }
}
