package ca.monor.leetCode.tree.binaryTree.binaryTreeLevelOrderTraversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class levelOrder {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        int levelSize = 1;
        while (!nodes.isEmpty()) {
            root = nodes.poll();
            list.add(root.val);
            if (root.left != null) nodes.offer(root.left);
            if (root.right != null) nodes.offer(root.right);
            levelSize--;
            if (levelSize == 0) {
                levels.add(list);
                levelSize = nodes.size();
                list.clear();
            }
        }
        return levels;
    }
}
