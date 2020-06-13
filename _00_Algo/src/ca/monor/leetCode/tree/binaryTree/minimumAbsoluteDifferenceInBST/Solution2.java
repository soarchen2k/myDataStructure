package ca.monor.leetCode.tree.binaryTree.minimumAbsoluteDifferenceInBST;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (hasNoChild(root)) {
            return Math.abs(root.val);
        }

        List<Integer> list1 = new ArrayList<>();
        int minimum = Integer.MAX_VALUE;
        //对树进行中序遍历，并把结果记录到 list 中
        inOrder(root, list1);

        //如果 list 中的前一个元素大于或等于下一个元素，则不是 BST
        for (int i = 0; i < list1.size() - 1; i++) {
            minimum = Math.min(minimum, Math.abs(list1.get(i) - list1.get(i + 1)));
        }
        return minimum;
    }

    private boolean hasNoChild(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private void inOrder(TreeNode root, List<Integer> list1) {
        if (root == null) {
            return;
        }

        inOrder(root.left, list1);
        list1.add(root.val);
        inOrder(root.right, list1);
    }
}
