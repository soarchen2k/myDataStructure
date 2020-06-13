package ca.monor.leetCode.linkedList.josephusProblem;

public class CircleLinkedListForJosephus<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;
    private static final int ELEMENT_NOT_FOUND = -1;

    private class Node<E> {
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
            return "element: " + element;
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
        last = null;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
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

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /* 定义 reset 方法，在轮询后使当前指针指向 first，从新的 first 开始重新计数 */
    public void reset() {
        current = first;
    }

    /* 定义 next 方法，使指针向前走一格，并返回当前节点的值 */
    public E next() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.element;
    }

    public E remove() {  // 无参的 remove 方法，如果 current 不为空，则记录 next 并 remove current
        // 如果 size==0 , 即链表元素都已经被删空，则 current = null，如果没有删空则把 next 赋值给current
        if (current == null) return null;

        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        } else current = next;
        return element;
    }

    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;  // 分别记录 node 节点的前后节点并相连，断开 node 节点与前后的关系
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            if (node == first) {  // 如果 node 是头节点，那么用 next 替换 node 成为新的头节点
                first = next;
            }
            if (node == last) {   // 如果 node 是尾节点，那么用 prev 替换 node 成为新的尾节点
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        /* 先考虑尾删的情况*/
        if (index == size) {  // 在链表的最尾添加元素
            Node<E> oldLast = last;  // 先记录之前的 last 节点
            last = new Node<>(oldLast, element, first); // 建立新的尾节点并连接前后节点
            if (oldLast == null) {  // oldLast == null 即之前的last为空，这是链表添加的第一个元素
                first = last;  //头 = 尾，并且头节点的 next 和 prev 都指向头
                first.prev = first;
                first.next = first;
            } else {  // 普通的尾插，使前后元素都指向 last
                oldLast.next = last;
                first.prev = last;
            }
        } else {  //非尾插，向其他位置插入元素，因爲非尾，所以 node 的 next 节点必不为空
            Node<E> next = node(index);  // 提前想到头插的情况，如果是 prev=node(index) 的话，
            // prev就已经占据了 first 的位置，无法定位 last
            Node<E> prev = next.prev;    // 这样如果 index == 0，那么头节点的 prev 就是 last
            Node<E> node = new Node<>(prev, element, next);
            prev.next = node;
            next.prev = node;
            if (index == 0) {
                first = node;
            }
        }
        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
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
            for (int i = size - 1; i >= index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }

    @Override
    public String toString() {
        return "size=" + size + ", first=" + first + ", last=" + last + ", current=" + current;
    }

}
