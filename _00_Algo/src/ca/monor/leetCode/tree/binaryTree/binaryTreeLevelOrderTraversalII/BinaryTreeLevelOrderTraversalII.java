package ca.monor.leetCode.tree.binaryTree.binaryTreeLevelOrderTraversalII;
/**
 * 107. Binary Tree Level Order Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return null;

        List<List<Integer>> lists = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int levelSize = 1;
        while (!queue.isEmpty()) {
            List<Integer> elements = new LinkedList<>();
            while (levelSize > 0) {
                root = queue.poll();
                elements.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
                levelSize--;
            }
            lists.add(elements);
            levelSize = queue.size();
        }
        Collections.reverse(lists);
        return lists;
    }

}
