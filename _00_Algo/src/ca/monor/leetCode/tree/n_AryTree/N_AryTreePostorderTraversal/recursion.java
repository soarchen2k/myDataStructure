package ca.monor.leetCode.tree.n_AryTree.N_AryTreePostorderTraversal;

import java.util.LinkedList;
import java.util.List;

public class recursion {
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
        postorder(root, list);
        return list;
    }

    private void postorder(Node root, List<Integer> list) {
        if (root == null) return;
        if (!root.children.isEmpty()) {
            for (int i = 0; i < root.children.size(); i++) {
                postorder(root.children.get(i), list);
            }
        }
        list.add(root.val);
    }
}
