package ca.monor.tree;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST() {
        this(null);
    }

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) {
            root = creatNode(element, null);
            size++;
            afterAdd(root);
            return;
        }
        Node<E> node = root;
        Node<E> parent = node;
        int compareResult = 0;
        do {
            compareResult = compare(element, node.element);
            // 记录下 parent 结点，因为之后 node 会被覆盖
            parent = node;
            if (compareResult > 0) {
                node = node.right;
            } else if (compareResult < 0) {
                node = node.left;
            } else {
                return;
            }
        } while (node != null);

        // 使用 createNode 方法，而不是直接创建新 node，因爲在 AVLTree class 中
        // override 了 create 方法，子类重写了该方法，则调用子类的方法，创建新的 AVLNode
        Node<E> newNode = creatNode(element, parent);
        if (compareResult > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        if (node.hasTwoChildren()) {
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }

        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            afterRemove(node);
        } else if (node.parent == null) {
            root = null;
            afterRemove(node);
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
    }

    /**
     * 添加node之后的调整
     *
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) { }

    /**
     * 删除node之后的调整
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node) { }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int compareResult = compare(element, node.element);
            if (compareResult == 0) {
                return node;
            }

            if (compareResult > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element must not be null");
        }
    }
}
