package ca.monor.binaryTree;

import java.util.Comparator;

/**
 * 直接实现 BinaryTreeInfo 接口来实现一棵 BinarySearchTree
 *
 * reference:
 *   int size;  维护树的容量
 *   Node<E> root; 记录树的根节点
 *   Comparator<E> comparator; 用于对插入的节点值进行比较
 *
 * API 设计：
 *   public 方法：
 *     BinarySearchTree(Comparator<E> comparator){}
 *                                   带有参数比较器的构造方法
 *     BinarySearchTree(){}          无参构造方法，构造时定义 comparator 为 null
 *     int size(){};                 返回树的大小
 *     boolean isEmpty(){};          判断树是否为空
 *     void clear(){};               清空树的内容
 *     void add(E element){};        新增一个值为 element 的节点
 *     void remove(E element){};     移除一个值为 element 的节点
 *
 *   private 方法：
 *     void remove(Node<E> node){};          移除一个给定的节点
 *     Node<E> successor(Node<E> node){};    查找给定节点的后继结点
 *     Node<E> node(E element){};            查找值为 element 的节点
 *     int compare(E e1, E e2){};            比较 e1 和 e2 的大小
 *     void elementNotNullCheck(E e){};      检查元素是否为空
 *
 * 内部类 class:
 *   private static class Node<E>{}          定义树的节点
 *
 *   内部类属性：
 *     E element;
 *     Node<E> left;
 *     Node<E> right;
 *     Node<E> parent;
 *
 *   内部类方法：
 *     Node(E e, Node<E> node){};        节点的构造方法，node 为新节点的 parent
 *     boolean isLeaf(){};               判断节点是否为叶结点(无子节点)
 *     boolean hasTwoChildren(){};       判断节点是否有两个子节点
 *
 * @param <E>
 */

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;  //维护树的容量
    private Node<E> root;  //树的根节点
    private Comparator<E> comparator;  //用于对插入的节点进行比较

    // 如果用户没有传入 comparator，则 comparator 为 null
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
     * 删除原理：1. 当 node 为叶节点时，可以对其直接删除，令其 parent 的子树为空即可
     *             此时如果 node 的 parent 为空，则说明 node 为 root，并且是唯一节点
     *             直接赋值 root = null 即可
     *
     *         2. 当 node 只有一个子树时，当 node 是其 parent 的左子树时，使其 parent 的
     *         左子树指向 node 的子树，当 node 是其 parent 的右子树时，使其 parent 的
     *         右子树指向 node 的子树，也就是断开了 node 与其 parent 以及其子树的连接，
     *         会被系统回收并删除
     *             此时如果 node 的 parent 为空，说明 node 为 root，但不是唯一节点，
     *             此时将 node 的子树重新赋值为 root 即可
     *
     *         3. 当 node 同时有左右子树时，用 node 的 predecessor 或者 successor 的值
     *         来替换 node 的值，并使其 predecessor 或 successor 成为 node，新 node
     *         一定是叶节点或者度为 1 的节点，按照上面的规则进行删除即可
     *
     * @param node
     * https://java.monor.ca/fr/2019/12/27/binarysearchtree-%e5%88%a0%e9%99%a4%e8%8a%82%e7%82%b9%e5%88%86%e6%9e%90/
     */
    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;

        if (node.hasTwoChildren()) {  // 情况3，node 同时有左右子树
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;  // 将后继结点的 element 赋值给 node 后，重新定义 node 节点
        }

        // 定义 replacement 节点，将 replacement 的 parent 指向 node 的 parent，并将 node
        // 的 parent 指向 replacement，达到回收/删除 node 的目的
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {  // node 是度为 1 的节点
            replacement.parent = node.parent;  // 重新定向 replacement 的 parent 指向
            if (node.parent == null) {  // 这里开始改变 node 的 parent 的子树指向，
                // node 的 parent 为空，说明 node 节点是 root，重新定义 root 节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {  // node == node.parent.right
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            // 没有进入第一个 if，说明 replacement == null，即 node 为叶节点，
            // 此时又因为 node.parent == null，可以判断 node 是唯一根节点，置空
            root = null;
        } else {  // replacement == null && node.parent !=null
                  // node 为叶节点，且不是 root，情况 1，
                  // 如果 node 是其 parent 的左/右子树，则将其 parent 的左右子树指向空
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
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
     *       3.2 当给定节点是右子树的最右节点，即中序遍历的最后一个节点，其 parent
     *           会一直追溯到 root 节点，因为 root 的 parent 是 null，所以最后
     *           节点的 successor 也是 null
     *       3.3 当给定节点是左子树的某右侧叶节点时，其 successor 是其 parent 的
     *           parent 节点
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
     * 查找给定节点的前驱节点，即中序遍历(inOrder)中的前一个节点，
     * 在 BinarySearchTree 中，前驱节点是第一个值比当前节点小的节点
     *
     * 情况1：如果给出的节点为 null，则直接返回 null
     * 情况2：如果给定的节点左子树不为空，如果其左子树还有右子树，则节点的 predecessor
     *       在左子树的最右子树中，如果无右子树，则 predecessor 为 node 的左子树
     * 情况3：节点的左子树为空，
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> predecessor = node.left;
        if (predecessor != null) {
            while (predecessor.right != null) {
                predecessor = predecessor.right;
            }
            return predecessor;
        }

        while (node.parent != null && node == node.parent.left) {
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
     * 则强制要求当前比较元素所属的 class 实现 Comparable 接口，
     * 例如本 package 下的 person 类
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
