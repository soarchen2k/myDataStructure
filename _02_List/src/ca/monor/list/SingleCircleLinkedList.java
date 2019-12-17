package ca.monor.list;

/**
 * 单循环链表设计： first 节点，节点的 next
 * @param <E>
 */

public class SingleCircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * 单向循环链表分一下几种情况考虑：
     * 1、index == 0
     *    此时表示是在链表的头部添加节点，新添加的节点就是新的 first 节点
     *    首先新建一个节点 newFirst 来记录该节点
     *    链表中的尾节点需要指向头节点，那么记录尾节点
     *    当 size == 0，也就是链表为空时，newFirst 是我们添加的第一个节点，同时也是尾节点
     *    当 size != 0 时，也就是链表不为空，尾节点为 node(size-1)
     *    使尾节点的 next 重定向到 newFirst，并让 first = newFirst 即可完成头插
     *
     * 2、index != 0
     *    此时是在链表的任意位置，包括尾部插入节点，先记录 prev 节点
     *    node(index-1)，当是尾插时，因爲 index==size，所以 index-1
     *    节点就是尾节点，尾节点的 next 是 first
     *
     * @param index 的位置添加元素
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            Node<E> newFirst = new Node<>(element, first);
            Node<E> last = size == 0 ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
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

    /**
     * 删除操作需要考虑头删和其他位置删除两种情况
     * 头删(index == 0)时需分别考虑 size == 1 和 其他情况
     *   size == 1 时，直接让 first = null
     *   size != 1 时，找出 prev 节点，也就是 last 节点，修改 last 的 next 和 first 节点即可
     * 其他位置的删除只需要找出 prev 节点，修改 prev 的 next 的指向即可
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> remove = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                last.next = first.next;
                first = first.next;
            }
        } else {
            Node<E> prev = node(index - 1);
            remove = prev.next;
            prev.next = remove.next;
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
        sb.append("List[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sb.append((i == 0) ? node.element : (", " + node.element));
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
