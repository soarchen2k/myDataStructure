package ca.monor.leetCode.linkedList.josephusProblem;

public class JosephusLinkedList<E> {
    private int size;
    private Node<E> first;
    private Node<E> current;
    private static final int ELEMENT_NOT_FOUND = -1;

    public void next() {
        current = current.next;
    }

    public void reset() {
        current = first;
    }

    private static class Node<E> {
        Node<E> next;
        E element;

        Node(E element, Node<E> next) {
            this.next = next;
            this.element = element;
        }

        @Override
        public String toString() {
            return "" + element + "_" + next.element;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        first = null;
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        /* 当 index == 0 时，进行头插，先建立一个新节点，next 指向之前的头节点*/

        if (index == 0) {
            Node<E> node = new Node<>(element, first);
            Node<E> last = (size == 0) ? node : node(size - 1);
            last.next = node;
            first = node;
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    public E remove() {
        if (current == null) return null;

        Node<E> next = current.next;
        E e = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return e;
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        remove(node);
        return node.element;
    }

    public E remove(Node<E> node) {
        E e = node.element;
        if (node == first) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                last.next = first.next;
                first = first.next;
            }
        } else {
            Node<E> prev = node(indexOf(node.element) - 1);
            prev.next = node.next;
        }
        size--;
        return e;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            node = node.next;
        }
        return node;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return "size=" + size + ", first=" + first + ", element:" + current + "_" + current.next;
    }
}
