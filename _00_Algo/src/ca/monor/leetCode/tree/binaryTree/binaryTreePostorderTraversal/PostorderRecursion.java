package ca.monor.leetCode.tree.binaryTree.binaryTreePostorderTraversal;

import java.util.LinkedList;
import java.util.List;

public class PostorderRecursion {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        recursion(root, list);
        return list;
    }

    private void recursion(TreeNode root, List<Integer> list) {
        if (root == null) return;
        recursion(root.left, list);
        recursion(root.right, list);
        list.add(root.val);
    }
}
