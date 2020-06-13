package ca.monor.leetCode.tree.binaryTree.binaryTreeLevelOrderTraversal;
/**
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int levelSize = 1;

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            while (levelSize > 0) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
                levelSize--;
            }
            lists.add(list);
            levelSize = queue.size();
        }
        Collections.reverse(lists);
        return lists;
    }
}
