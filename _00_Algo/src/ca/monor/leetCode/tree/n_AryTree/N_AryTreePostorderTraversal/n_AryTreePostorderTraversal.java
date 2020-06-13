package ca.monor.leetCode.tree.n_AryTree.N_AryTreePostorderTraversal;

/**
 * 590. N-ary Tree Postorder Traversal
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class n_AryTreePostorderTraversal {
    public class Node {
        int val;
        List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> list = new LinkedList<>();
        Stack<Node> nodeStack = new Stack<>();
        if (root != null) {
            nodeStack.push(root);
        }
        while (!nodeStack.isEmpty()) {
            root = nodeStack.pop();
            list.add(root.val);
            if (!root.children.isEmpty()) {
                for (int i = 0; i < root.children.size(); i++) {
                    nodeStack.push(root.children.get(i));
                }
            }
        }
        Collections.reverse(list);
        return list;
    }
}
