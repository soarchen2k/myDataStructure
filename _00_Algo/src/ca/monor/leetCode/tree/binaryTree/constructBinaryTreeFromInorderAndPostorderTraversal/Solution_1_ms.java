package ca.monor.leetCode.tree.binaryTree.constructBinaryTreeFromInorderAndPostorderTraversal;

public class Solution_1_ms {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private int indexInorder;   // index of inorder array
    private int indexPostorder; // index of postorder array

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        indexInorder = inorder.length - 1;
        indexPostorder = postorder.length - 1;

        return buildTree(inorder, postorder, null);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, TreeNode end) {
        if (indexPostorder < 0) { return null;}

        // create root node
        // 创建 root 节点，然后把 index - 1
        TreeNode root = new TreeNode(postorder[indexPostorder--]);

        // if right node exist, create right subtree
        // 如果中序遍历的最后一个值不等于 root 的值，则该树有右子树，
        // 后续遍历的倒数第二个值就是右子树的 root
        // 由于每次递归 indexPostorder 都 -1，当中序遍历的最后一个值
        // 等于后续遍历的 index 的值的时候，右子树结束
        if (inorder[indexInorder] != root.val) {
            root.right = buildTree(inorder, postorder, root);
        }

        //右子树结束，中序遍历的 index 向前走一个值
        indexInorder--;

        // if left node exist, create left subtree
        // 如果传入的 end 为空，或中序遍历的 index 位置的值
        // 不等于 end 节点的值，则该节点有左子树

        if ((end == null) || (inorder[indexInorder] != end.val)) {
            root.left = buildTree(inorder, postorder, end);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution_1_ms solutin = new Solution_1_ms();
        int[] inorder = {8, 15, 26, 62};
        int[] postorder = {8, 15, 62, 26};
        solutin.buildTree(inorder, postorder);
    }
}
