package ca.monor.leetCode.tree.n_AryTree.n_AryTreePreorderTraversal;

import java.util.LinkedList;
import java.util.List;

public class Recursion {
    public class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new LinkedList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            preorder(root.children.get(i), list);
        }
    }
}
