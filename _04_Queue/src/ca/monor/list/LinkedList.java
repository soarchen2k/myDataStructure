package ca.monor.list;

public class LinkedList<E> extends AbstractList<E> {

    Node<E> first;
    Node<E> last;

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
            Node<E> oldLst = last;
            Node<E> newLast = new Node<>(oldLst, element, null);
            if (index == 0) {
                first = newLast;
            } else {
                oldLst.next = newLast;
            }
            last = newLast;
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            if (index == 0) {
                first = newNode;
            } else {
                prev.next = newNode;
            }
            next.prev = newNode;
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
        Node<E> prev = remove.prev;
        Node<E> next = remove.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
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
        sb.append("[");
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
