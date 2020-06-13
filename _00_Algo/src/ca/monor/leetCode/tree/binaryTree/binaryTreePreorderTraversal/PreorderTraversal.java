package ca.monor.leetCode.tree.binaryTree.binaryTreePreorderTraversal;
/**
 * 144. Binary Tree Preorder Traversal
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();

        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                list.add(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        return list;
    }
}