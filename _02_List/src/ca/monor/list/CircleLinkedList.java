package ca.monor.list;
/**
 * 循环链表设计：
 * 节点两个: first, last
 * 节点设计：prev, element, next
 *
 * @param <E>
 */
public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 双向循环链表的添加，首先考虑尾部添加的情况，即 index == size 时的添加
     * 首先记录 oldLast，即添加前的尾节点，因爲尾插会生产新的尾节点
     * 当 oldLast 为空时，说明是插入链表的第一个节点，否则是普通的尾插
     * 其他位置的插入，只需要记录 prev 和 插入位置的节点，插入位置的节点将成为新节点的 next
     *
     * @param index 的位置添加元素
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) {  // if (index == 0)
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            prev.next = newNode;
            next.prev = newNode;
            if (index == 0) {
                first = newNode;
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
        Node<E> remove = node(index);
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = remove.prev;
            Node<E> next = remove.next;
            prev.next = remove.next;
            next.prev = remove.prev;
            if (index == 0) {  // remove = first
                first = next;
            }
            if (index == size - 1) {  // remove = last
                last = prev;
            }
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
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = first;
        sb.append("CircleLinkedList: [");
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

    private static class Node<E>{
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
            return prev.element + "<-" + element + "->" + next.element;
        }
    }
}
