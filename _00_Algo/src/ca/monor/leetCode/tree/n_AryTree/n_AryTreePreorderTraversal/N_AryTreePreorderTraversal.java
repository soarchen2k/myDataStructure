package ca.monor.leetCode.tree.n_AryTree.n_AryTreePreorderTraversal;
/**
 * 589. N-ary Tree Preorder Traversal
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class N_AryTreePreorderTraversal {
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

        Stack<Node> nodesStack = new Stack<>();  //建立一个 Stack 存储nodes
        if (root != null) nodesStack.push(root);

        while (!nodesStack.isEmpty()) {
            root = nodesStack.pop();  //root为栈中弹出元素
            list.add(root.val);
            if (root.children != null) {
                List<Node> levelNodes = root.children;
                for (int i = levelNodes.size() - 1; i >= 0; i--) {   //将 children 逆序入栈
                    nodesStack.push(levelNodes.get(i));
                }
            }
        }
        return list;
    }
}
