package ca.monor.leetCode.tree.binaryTree.deleteNodeInABST;

/**
 * 450. Delete Node in a BST
 * https://leetcode.com/problems/delete-node-in-a-bst/
 */
public class DeleteNodeInABST {
    class TreeNode {
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

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        //先定位需要删除的 node
        if (key < root.val) {  //如果 key 小于 roo 的值，则往左边找
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {  //如果 key 大于 root 的值，则往右边找
            root.right = deleteNode(root.right, key);
            return root;
        } else {  //如果 key = root 的值，则表示找到了要删除的点
            if (root.left == null) {  //如果 root 的左为空，直接返回右
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            }
            if (root.right == null) {
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            }

            TreeNode successor = minimum(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;
            root.left = null;
            root.right = null;
            return successor;
        }
    }

    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private TreeNode minimum(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            // we have to delete the root node itself
            if (root.left == null) {  //如果左子树为空，直接返回右子树
                return root.right;
            } else if (root.right == null) {  //如果右子树为空，直接返回左子树
                return root.left;
            } else {  //如果左右子树均不为空，从右子树中找出后继结点
                TreeNode newRoot = root.right, parent = null;
                // find the smallest value in the right subtree
                // 利用 while 循环查找右子树的最小值，也就是被删除的节点的后继结点
                while (newRoot.left != null) {
                    parent = newRoot;
                    newRoot = newRoot.left;
                }
                // right node is the smallest node in the right subtree
                // 如果 parent 为空，说明 while 循环没有被执行，右子节点即是右子树的最小节点
                if (parent == null) {
                    // connect the left subtree to new root node
                    // 将右子节点的 left 指向原 root 节点的 left，断开 root 节点与 left 的关系
                    newRoot.left = root.left;
                    return newRoot;
                }

                // 当 parent 不为空的时候，也就是 newRoot 有左子节点
                parent.left = newRoot.right;  // 断开 parent 与 newRoot 的关系，
                // newRoot 如果有右，则为 newRoot 的后继，如果没有右则 parent 的左为空
                newRoot.left = root.left;
                newRoot.right = root.right;//通过建立 newRoot 与原 root 的左右子节点的关系，
                // 来断开 root 与原左右子节点的关系，从而达到删除 root 的目的
                return newRoot;
            }
        }
        return root;
    }
}
