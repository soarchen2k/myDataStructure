package ca.monor.leetCode.tree.binaryTree.maximumWidthOfBinaryTree;
/**
 * 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 */

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        queue.offer(root);
        int width = 1;
        linkedList.add(1);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = levelSize; i > 0; i--) {
                root = queue.poll();
                int index = linkedList.removeFirst();
                if (root.left != null) {
                    queue.offer(root.left);
                    linkedList.add(index << 1);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                    linkedList.add((index << 1) + 1);
                }
            }
            if (linkedList.size() >= 2) {
                width = Math.max(width, linkedList.getLast() - linkedList.getFirst() + 1);
            }
        }
        return width;
    }
}
