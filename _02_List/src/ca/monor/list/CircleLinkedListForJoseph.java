package ca.monor.list;

/**
 * 约瑟夫问题循环链表设计：
 * 节点三个: first, last, current
 * 节点设计：prev, element, next
 * 增设 void reset() 方法，用于重置 first
 * 增设 E remove(Node<E> node) 方法，直接删除某节点
 * @param <E>
 */

public class CircleLinkedListForJoseph<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    public void reset() {
        current = first;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (index == 0) {
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                first.prev = last;
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            next.prev = newNode;
            prev.next = newNode;

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
        return remove(node(index));
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            if (node == first) {
                first = next;
            }
            if (node == last) {
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node == null) {
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
        sb.append("CircleLinkedList, Size = ")
                .append(size)
                .append(", {");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sb.append(i == size - 1 ? node : node + ", ");
            node = node.next;
        }
        sb.append("}");
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
            return (prev != null ? prev.element : "null") +
                    "<-" +
                    element +
                    "->" +
                    (next != null ? next.element : "null");
        }
    }
}
