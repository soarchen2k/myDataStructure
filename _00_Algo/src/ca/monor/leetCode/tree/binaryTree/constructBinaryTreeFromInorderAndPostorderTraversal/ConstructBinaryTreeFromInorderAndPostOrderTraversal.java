package ca.monor.leetCode.tree.binaryTree.constructBinaryTreeFromInorderAndPostorderTraversal;

import java.util.Arrays;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * recursion 递归
 */

public class ConstructBinaryTreeFromInorderAndPostOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLength = inorder.length;
        int postLength = postorder.length;

        if (inLength == 0) {
            return null;
        }

        if (inLength == 1) {
            return new TreeNode(inorder[0]);
        }

        //后续遍历最后的节点是树的根节点
        int rootVal = postorder[postLength - 1];

        //找出根节点值在中序遍历中的位置，以划分左右子树
        int indexRootInOrder = 0;
        while (indexRootInOrder < inLength) {
            if (rootVal == inorder[indexRootInOrder]) {
                break;
            }
            indexRootInOrder++;
        }

        TreeNode root = new TreeNode(rootVal);

        //以 indexRootInorder 为划分，inorder[] 中截至到 indexRootInOrder 的部分为左子树的 inOrder，
        // postOrder[] 中截至 indexRootInOrder 的部分为左子树的 postOrder
        root.left = buildTree(Arrays.copyOfRange(inorder, 0, indexRootInOrder)
                , Arrays.copyOfRange(postorder, 0, indexRootInOrder));

        //inorder[]中从 indexRootInOrder + 1 开始到 length 的部分为右子树的 inorder，
        // postorder[] 从 indexRootInOrder 开始到倒数第二个数为止，是右子树的 postOrder
        root.right = buildTree(Arrays.copyOfRange(inorder, indexRootInOrder + 1
                , inLength), Arrays.copyOfRange(postorder, indexRootInOrder, postLength - 1));
        return root;
    }
}
