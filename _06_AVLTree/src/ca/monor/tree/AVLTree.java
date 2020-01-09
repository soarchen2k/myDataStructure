package ca.monor.tree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    /**
     * 新添加的节点一定是叶节点(leaf)，即没有左右子树的节点，其高度一定是 1，
     * 所以我们从 node 的 parent 开始查看是否平衡，并进行高度的更新
     */
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                reBalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                reBalance(node);
            }
        }
    }

    protected Node<E> creatNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 传入的不平衡的点，为 grand 节点，也就是高度最低的，第一个不平衡的节点
     *
     * parent 节点是 grand 的左右子树中，高度较高的子树，当两边高度一样时，
     * 如果 grand 节点是其 parent 的左子树，则取 grand 的左子树为 parent，
     * 反之则取 grand 的右子树
     *
     * node 节点是 parent 节点的左右子树中，高度较高的子树，当两边高度一样时，
     * 如果 parent 节点是其 parent 的左子树，则取 parent 的左子树做爲 node，
     * 反之则取 parent 得右子树
     * @param grand
     */
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) {
            if (!node.isLeftChild()) {
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) {
                rotateRight(parent);
            }
            rotateLeft(grand);
        }
    }

    private void rotateLeft(Node<E> parent) {

    }

    private void rotateRight(Node<E> grand) {

    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        // 每个 AVLNode 都需要维护自己的高度，而不是整棵树的高度，每个结点新建时
        // 都是叶节点，且节点高度都是1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 节点的平衡因子，即节点的左右子树的高度差
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新节点的高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            // 这里的 1 是节点自身的 height，
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return this.isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
