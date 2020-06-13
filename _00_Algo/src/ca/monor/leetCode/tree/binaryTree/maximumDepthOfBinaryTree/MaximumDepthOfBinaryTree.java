package ca.monor.leetCode.tree.binaryTree.maximumDepthOfBinaryTree;
/**
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * 层序遍历法解最大深度问题
 */

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int levelSize = 1;
        int depth = 0;

        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            levelSize--;
            if (levelSize == 0) {
                depth++;
                levelSize = queue.size();
            }
        }
        return depth;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
