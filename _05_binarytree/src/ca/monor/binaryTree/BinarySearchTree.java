package ca.monor.binaryTree;

import java.util.Comparator;

/**
 * 直接实现 BinaryTreeInfo 接口来实现一颗 BinarySearchTree
 * @param <E>
 */

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;  //维护树的容量
    private Node<E> root;  //树的根节点
    private Comparator<E> comparator;  //用于对插入的节点进行比较

    // 如果用户没有传入 comparator，则不使用任何 comparator
    public BinarySearchTree() {
        this(null);
    }

    // 如果用户传入了 comparator，就使用用户传入的 comparator
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 为二叉树添加一个元素，首先判断要添加的元素是否为空，调用 elementNotNullCheck(E element)
     * 如果为空，则抛出 IllegalArgumentException 异常。确定要添加的元素不为空之后：
     *
     * 首先判断是不是该 BinarySearchTree 插入的第一个节点，判断条件： root == null
     * 如果 root == null 则把新加节点做爲树的根节点 root，根节点的 parent 为 null
     *
     * 如果 root != null，那么分别记录 parent 节点和 node 节点
     *
     * @param element
     */
    public void add(E element) {
        elementNotNullCheck(element);  // 判断 element 是否为空

        // 如果 root 为空，说明是添加的第一个节点，即根节点
        if (root == null) {
            root = new Node<>(element, null);  // root 的 parent 为 null
            size++;
            return;
        }

        // 当不是第一种情况时，说明 被插入的节点是其他位置的，
        // 需要与已有节点进行比较，并找出被插入节点的 parent

        Node<E> parent = root;  // 首先定义 parent = root，当 node 没有左右节点时，
                                // root 就是要插入的节点的 parent
        Node<E> node = root;    // 从 root 节点开始查找要插入的节点的 parent
        int elementComparator = 0;
        do {
            elementComparator = compare(element, node.element);
            parent = node;  // node 节点向左或向右移动之前，先记录 parent 节点
            if (elementComparator > 0) {
                node = node.right;
            } else if (elementComparator < 0) {
                node = node.left;
            } else {  // elementComparator == 0，即要插入的节点的元素与当前节点的元素值相同，就进行覆盖
                node.element = element;
                return;
            }
        } while (node != null);  // 只要 node 的左或右节点不为空，就继续查找，

        // 由于已经找到了 parent 节点，现在新建一个要插入的节点，使其 parent 指向找到的 parent
        Node<E> newNode = new Node<>(element, parent);
        // 再根据新节点的 element 值来判断新节点属于 parent 节点的 left 还是 right
        if (elementComparator > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     *
     * @param element
     */
    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 真正进行 node 删除的方法
     *
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;

    }

    /**
     * 查找给定的节点的后继结点 successor
     * 后继结点：中序遍历(inOrder)中，某节点之后的节点，
     * 在 BinarySearchTree 中，后继结点就是第一个比给定节点大的节点
     *
     * 情况1：如果给的的节点为空，直接返回，无后继结点
     * 情况2：给的的节点有右子树，那么其后继结点就在右子树的最左子树中
     *       right.left.left.left....
     * 情况3：给定的节点没有右子树，即叶节点或只有左子树的节点，
     *       其后继结点在 parent 或 grandParent 中，
     *       3.1 当给定节点是其 parent 的 left 时，其 successor 就是其 parent
     *       3.2 当给定节点是最右的节点，即中序遍历的最后一个节点，其 parent 会一直
     *           追溯到 root 节点，因为 root 的 parent 是 null，所以最后节点的
     *           successor 也是 null
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> successor = node.right;
        if (successor != null) {
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }


        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 通过给定的元素值，查找相对应的节点 Node<E>
     * 与 linkedList 的查找方式类似，首先从根节点开始查找
     * 通过 compare 方法，如果 element 值小于 node.element值，
     * 则向左，反之则向右，直到两个值相等，则返回找到的节点
     * 如果未找到相关节点，则返回空，说明不存在该节点
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int elementComparator = compare(element, node.element);
            if (elementComparator == 0) {
                return node;
            }
            if (elementComparator > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 创建 compare 方法，对 elements 进行比较，如果创建的 BST 自带比较器，
     * 就使用其自带的比较器，否则，因为 BST 必须是有序的，如果没有传比较器，
     * 则强制要求当前比较元素所属的 class 实现 Comparable 接口
     * @param element1
     * @param element2
     * @return
     */
    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }

    /**
     * 判断用户传入的 element 是否为空，
     * 如果为空则抛出 IllegalArgumentException 异常
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        // Node<E> 的构造方法，节点不一定有 left 和 right，但是一定有 parent，根节点的 parent 是 null
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        // 判断节点是否为叶节点，当节点无左右节点时，即为叶节点 leaf
        public boolean isLeaf() {
            return left == null && right == null;
        }

        // 判断节点是否同时有左右子节点
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }



    /**
     * who is the root node
     */
    @Override
    public Object root() {
        return null;
    }

    /**
     * how to get the left child of a node
     *
     * @param node
     */
    @Override
    public Object left(Object node) {
        return null;
    }

    /**
     * how to get the right child of a node
     *
     * @param node
     */
    @Override
    public Object right(Object node) {
        return null;
    }

    /**
     * how to print the node
     *
     * @param node
     */
    @Override
    public Object String(Object node) {
        return null;
    }
}
