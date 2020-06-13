package ca.monor.leetCode.tree.n_AryTree.maximumDepth;
/**
 * 559. Maximum Depth of N-ary Tree
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumDepthOfN_AryTree {
    public class Node {
        int val;
        List<Node> children;

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

        public Node() {
        }
    }

    public int maxDepth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                root = queue.poll();
                if (!root.children.isEmpty()) {
                    for (int i = 0; i < root.children.size(); i++) {
                        queue.offer(root.children.get(i));
                    }
                }
                levelSize--;
            }
            maxDepth++;
        }
        return maxDepth;
    }
}
