package ca.monor.leetCode.tree.binaryTree.constructBinaryTreeFromInorderAndPreorderTraversal;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromInorderAndPreorderTraversal {
    private int inorderIndex = 0;
    private int preorderIndex = 0;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return left +
                    "<--" + val +
                    "-->" + right;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //用 integer 的 MAX_VALUE 做截止点，避免与 Integer 冲突
        return buildTree(preorder, inorder, (long) Integer.MAX_VALUE + 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, long stop) {
        if (preorderIndex > preorder.length - 1) {
            return null;
        }

        if (inorder[inorderIndex] == stop) {
            inorderIndex++;
            return null;
        }

        int rootVal = preorder[preorderIndex++];

        TreeNode root = new TreeNode(rootVal);

        root.left = buildTree(preorder, inorder, rootVal);
        root.right = buildTree(preorder, inorder, stop);

        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPreorderTraversal solution = new
                ConstructBinaryTreeFromInorderAndPreorderTraversal();

        int[] inorder = {21, 23, 36, 37, 64, 73, 81, 90};
        int[] preorder = {64, 36, 21, 23, 37, 81, 73, 90};
        solution.buildTree(preorder, inorder);
    }
}
