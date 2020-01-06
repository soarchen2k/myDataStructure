package ca.monor.binaryTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
     * 删除原理：1. 如果 node 有左右子树，即 node.hasTwoChildren == true
     *             用 node 的 successor 的值覆盖 node 本身的值，并使其 successor
     *             成为新的 node，之后删除 node 即可
     *
     *         2. 定义一个 replacement 结点，如果 node 有左子树，replacement 就是 node
     *            的左子树，如果没有左子树，replacement 就定义为 node 的右子树
     *            （如果右子树为空，即 replacement == null）
     *
     *            2.1 如果 replacement 不为空，首先将 replacement 的 parent 指向 node
     *                的 parent，意味着断开了 replacement 与 node 的关系
     *                2.1.1 如果 node.parent 为空，意味着 node 为 root 节点，由于 replacement
     *                      的 parent 已经指向 null，只需要把 root 赋值给 replacement，成为新的
     *                      root 节点，即可断开 node
     *                2.1.2 否则，根据 node 是其 parent 的左还是右节点，判断 replacement 节点是
     *                      node 的 parent 的左还是右节点，重新定向
     *
     *            2.2 如果 node.parent == null ，即(replacement == null && node.parent == null)
     *                则说明 node 为 root，并且是唯一节点，直接令 root = null 即可完成删除
     *
     *            2.3 如果 replacement == null && node.parent != null 即 else 情况下，
     *                根据 node 是其 parent 的左右，来确定 replacement 是 node.parent 的左还是右子树
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
        // 的 parent 的子节点指向 replacement，达到回收/删除 node 的目的
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
        } else if (node.parent == null) {  // replacement == null && node.parent == null
            // 没有进入第一个 if，说明 replacement == null，即 node 为叶节点，
            // 此时又因为 node.parent == null，可以判断 node 是唯一根节点，置空
            root = null;
        } else {  // replacement == null && node.parent !=null
                  // node 为叶节点，且不是 root，情况 1，
                  // 如果 node 是其 parent 的左/右子树，则将其 parent 的左右子树指向空
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {  // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * BinarySearchTree 的遍历
     * 遍历方式：前序遍历 preOrder    ： 先遍历 root 节点，再分别遍历 root 节点的左右子树
     *         中序遍历 inOrder     ： 先遍历 root 节点的左子树，再遍历 root 节点，最后遍历右子树
     *         后续遍历 postOrder   ： 先遍历 root 节点的左子树，再遍历右子树，最后遍历 root 节点
     *         层序遍历 levelOrder  ： root 节点为起点，以从上到下，从左到右的顺序，逐层遍历整棵 BST
     * 实现方法：递归，迭代
     */

    /**
     * BST 的前序遍历，迭代实现
     * 实现方法：建立一个 stack，如果 node 不为空，则将 node 入栈
     *         当栈不为空的情况，node 为 stack 弹出的元素，此时 node
     *         就是我们需要访问的节点，如果 node 有右子树，将右子树入栈，
     *         如果 node 有左子树，将左子树入栈，while 循环执行
     * 入栈原则：先入栈右子树，后入栈左子树
     */
    public void preOrderWithVisitor(Visitor<E> visitor) {
        if (root == null || visitor.stop) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            // 此处即为 visitor 对 BST 的访问
            visitor.stop = visitor.visit(node.element);

            // 如需打印，此处可改为
            // System.out.println(node.element);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.parent);
            }
        }
    }

    public void preOrder() {
        if (root == null) {
            return;
        }
        System.out.print("Pre Order: ");
        Stack<Node<E>> stack = new Stack<>();
        Node<E> node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.element + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    /**
     * BST 的前序遍历，递归实现
     */
    public void preOrder2(Visitor<E> visitor) {
        preOrder2(root, visitor);
    }

    private void preOrder2(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preOrder2(node.left, visitor);
        preOrder2(node.right, visitor);
    }

    /**
     * BST 的中序遍历 inOrder，迭代实现
     * 实现方法：建立一个 stack，当 node 不为空时，将 node 入栈，以 stack 不为空或
     * node 不为空做为循环条件，进行 while 循环，当 node 不为空时，将 node
     * 入栈，并将 node.left 赋值给 node，直到 node.left 为空，开始弹栈，
     * node 被赋值为弹出的节点，并且 visitor 开始访问弹出的节点，之后将
     * node.right 入栈，重新进入 while 循环进行下一轮判断，直到栈为空或 node == null
     */
    public void inOrderWithVisitor(Visitor<E> visitor) {
        inOrder(root, visitor);
    }

    public void inOrder() {
        if (root == null) {
            return;
        }
        System.out.print("In Order: ");
        Stack<Node<E>> stack = new Stack<>();
        Node<E> node = root;
        if (node != null) {
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    System.out.print(node.element + " ");
                    node = node.right;
                }
            }
        }
        System.out.println();
    }

    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (visitor.stop) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
        if (node != null) {
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    visitor.stop = visitor.visit(node.element);
                    node = node.right;
                }
            }
        }
    }

    /**
     * BST 的中序遍历 inOrder，递归 recursion
     */
    public void inOrder2(Visitor<E> visitor) {
        inOrder2(root, visitor);
    }
    private void inOrder2(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inOrder2(node.left, visitor);
        visitor.stop = visitor.visit(node.element);
        inOrder2(node.right, visitor);
    }

    /**
     * BST 的后序遍历 postOrder，迭代
     * 实现方法：创建2个 Stack，利用前序遍历的迭代方式，在遍历点，把被遍历点节点加入
     * 入栈顺序：先入栈左子树，后入栈右子树
     * 第二个 Stack，之后依序弹出第二个 Stack 中的节点即可
     */

    public void postOrderWithVisitor(Visitor<E> visitor) {
        postOrder(root, visitor);
    }

    public void postOrder() {
        if (root == null) {
            return;
        }
        System.out.print("Post Order: ");
        Node<E> node = root;
        Stack<Node<E>> preOrderStack = new Stack<>();
        Stack<Node<E>> postOrderStack = new Stack<>();

        preOrderStack.push(node);
        while (!preOrderStack.isEmpty()) {
            node = preOrderStack.pop();
            postOrderStack.push(node);
            if (node.left != null) {
                preOrderStack.push(node.left);
            }
            if (node.right != null) {
                preOrderStack.push(node.right);
            }
        }

        while (!postOrderStack.isEmpty()) {
            System.out.print(postOrderStack.pop().element + " ");
        }
        System.out.println();
    }

    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        Stack<Node<E>> preOrderStack = new Stack<>();
        Stack<Node<E>> postOrderStack = new Stack<>();

        preOrderStack.push(node);
        while (!preOrderStack.isEmpty()) {
            node = preOrderStack.pop();
            postOrderStack.push(node);
            if (node.left != null) {
                preOrderStack.push(node.left);
            }
            if (node.right != null) {
                preOrderStack.push(node.right);
            }
        }

        while (!postOrderStack.isEmpty()) {
            visitor.stop = visitor.visit(postOrderStack.pop().element);
        }
    }

    /**
     * BST 的后续遍历 postOrder，递归实现
     */
    public void postOrder2(Visitor<E> visitor) {
        postOrder2(root, visitor);
    }

    private void postOrder2(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postOrder2(node.left, visitor);
        postOrder2(node.right, visitor);
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * BST 的后序遍历 postOrder，非递归实现，第二种方法
     */
    public void postOrder3(Visitor<E> visitor) {
        postOrder3(root, visitor);
    }
    public void postOrder3(Node<E> node, Visitor<E> visitor) {
        if (node != null) {
            Stack<Node<E>> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Node<E> temp = stack.peek();
                if (temp.left != null && node != temp.left && node != temp.right) {
                    stack.push(temp.left);
                } else if (temp.right != null && node != temp.right) {
                    stack.push(temp.right);
                } else {
                    visitor.visit(stack.pop().element);
                    node = temp;
                }
            }
        }
        System.out.println();
    }

    /**
     * BST 的层序遍历 levelOrder
     * 实现方法：建立一个队列 Queue，先把 root 入队，然后开始 while 循环，当队列不为空
     *         时，进行出队操作，并将出队的节点赋值为 node，然后开始遍历 node 节点，同
     *         时检查 node 的左右节点是否为空，不为空则执行入队操作，直到队列为空，遍历
     *         结束
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor.stop) {
            return;
        }

        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            visitor.stop = visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 返回 BST 的高度，非递归实现，调用返回某节点的高度的方法，参数: root 节点
     * @return
     */

    public int height() {
        return height(this.root);
    }

    public int height(E element) {
        return height(node(element));
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int levelSize = queue.size();

        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            levelSize--;
            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    /**
     * BST 的高度，递归实现
     */
    public int height2() {
        return height2(root);
    }

    private int height2(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height2(node.left), height2(node.right));
    }

    /**
     * 判断一个 BST 是否是完全二叉树 complete binary tree
     * 完全二叉树性质：度为 1 的节点最多只有一个，且只有左子树，如果存在度为 1 的节点，
     *              则其后的节点都是叶节点（度为 0）
     *              叶节点只会出现在最后两层，最后一层的叶节点都靠左对齐
     * 实现方法：二叉树的层序遍历，建立一个叶节点开关，默认为 false，当遍历到第一个叶节点时，
     *         将叶节点都开关置为 true。
     *         如果节点的度为2，将节点的左右子节点入队
     *         (if node.hasTwoChildren())
     *         无论是否进入了叶节点模式，只有存在度为 1，且只有 right 的节点，即返回 false
     *         (node.left == null && node.right != null)
     *         进入叶节点模式后，一旦再次遍历到非叶节点，即返回 false
     *         (leafMode && !node.isLeaf())
     * @return
     */

    public boolean isComplete() {
        if (root == null) {
            return false;
        }

        boolean leafMode = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leafMode && !node.isLeaf()) {
                // 在 leafMode下，一旦遍历到非叶节点，即判 false
                return false;
            }

            if (node.hasTwoChildren()) {  // if (node.left != null && node.right != null)
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                // 无论 node.left != null，还是 isLeaf()， 都将开启 leafMode
                leafMode = true;
                // 如果 node.left 不为空，一定要入队，否则如果该节点不满足条件，就会误判
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {//中序打印
        if (node == null) return;

        toString(node.left, sb, prefix + "L---");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right, sb, prefix + "R---");
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

    public static abstract class Visitor<E> {
        // 如果 visitor 返回 true，说明访问到了需要访问的节点，即可停止遍历
        boolean stop;

        // 抽象方法，访问包含 element 的节点
        public abstract boolean visit(E element);
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
        return root;
    }

    /**
     * how to get the left child of a node
     *
     * @param node
     */
    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    /**
     * how to get the right child of a node
     *
     * @param node
     */
    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    /**
     * how to print the node
     *
     * @param node
     */
    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";    }
}
