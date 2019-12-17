package ca.monor.list;

/**
 * 标准双向链表
 * 链表有 first 和 last 节点
 * 每个节点都有 next 和 prev
 * first 的 prev 是 null，last 的 next 也是 null
 * -----------------------------------------------
 * 创建一个 LinkedList，继承 AbstractList 类
 * 创建两个 private Node<E> first 与 last
 * 创建私有静态类 Node<E>{}
 * 类中定义 节点 prev 与 next，以及元素 element，并创建相应的构造方法
 * void clear() : size 置 0，first 与 last 置空
 * E get(int index) : 返回位于 index 的节点的 element
 * void add() : 尾插：如果 index == size, 则表示此时在链表的尾部添加元素，
 *              先创建一个节点记录当前的尾节点 last，再创建新的尾节点
 *              size==0，则说明是新链表，在"0"位置添加
 *              否则，则创建3个节点，分别记录 prev, next 和新加节点 newNode
 *              使 next 的 prev 指向新节点
 *              头插：index==0 时，新节点就是 newNode
 * E remove(int index) :
 * @param <E>
 */

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) { //尾插，原先的 last 就是新节点的 prev
            Node<E> prev = last;
            Node<E> newLast = new Node<>(prev, element, first);
            if (size == 0) { //链表的第一个节点，其 prev 也是 null
                first = newLast;
            } else {
                prev.next = newLast;
            }
            last = newLast;
        } else {  //在其他位置插入
            Node<E> next = node(index);  //当前index位置的节点是插入后节点的 next
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            next.prev = newNode;
            if (index == 0) {  //说明是头插
                first = newNode;
            } else {
                prev.next = newNode;
            }
        }
        size++;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        /*
        删除元素，重要的是理清楚被删除节点与其 prev 以及 next 之间的关系
        */
        // rangeCheck(index); // 因为会调用 node 方法，所以这里不需要 rangeCheck
        Node<E> remove = node(index);
        Node<E> prev = remove.prev;
        Node<E> next = remove.next;

        /*
        index == 0，或者 prev == null，说明是头删，因为非循环链表中的 first 不再有 prev，
        所以直接 first = next，即可将当前的头节点置空
        如果不是头删，让 prev 的 next 指向被删除节点的 next，即可断开被删除节点与 next 的联系
        */
        if (index == 0) {  // if (prev == null)
            first = next;
        } else {
            prev.next = next;
        }

        /*
        如果 next == null，或者 index == size - 1 则说明被删除的节点是尾节点，其 next 为空
        否则则不是尾删，只需要将 next 的 prev 指向被删除节点的 prev 即可断开被删除节点与 prev 的联系
        当被删除节点的与其prev和next都失去了联系，就会被系统回收，达到删除的目的
        */
        if (index == size - 1) { // if (next == null)
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return remove.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = first;
        sb.append("LinkedList: [");
        for (int i = 0; i < size; i++) {
            sb.append(i == size - 1 ? node : node + ", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private static class Node<E> {
        Node<E> prev;
        E element;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
