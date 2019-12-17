package ca.monor.list;

/**
 * 单向链表
 * 单向链表只有 first 头节点
 * 每个节点都只有 next 节点，最后一个节点的 next 为 null
 */

public class SingleLinkedList<E> extends AbstractList<E> {
    Node<E> first;

    /**
     * first = null，断开 first 和剩余节点的联系，其余节点就会被系统回收
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * 在 @param index 的位置添加元素
     * 如果 index==0，说明是头插，新节点就是新的 first
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            first = new Node<E>(element, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    /**
     * 调用 node 方法来获取 index 位置的节点，依次获取
     * @param index 位置的元素，并
     * @return 该元素
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 记录
     * @param index 位置的元素
     * @param element 并
     * @return 被替换的元素
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    /**
     * 单链表的删除分为头删和其他位置的删除
     * 首先考虑头删，记录 removed 节点为头节点
     * 令 first 的 next 为新的头节点，即可删除当前头节点
     * 非头删的时候，找到 prev 节点，用 removed 节点记录 prev 的 next，即将被删除的节点
     * 让 prev 的 next 指向 removed 的 next，断开 removed 与其他节点的联系
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> removed = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prev = node(index - 1);
            removed = prev.next;
            prev.next = removed.next;
        }
        size--;

        return removed.element;
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

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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
