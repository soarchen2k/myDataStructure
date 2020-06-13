package ca.monor.leetCode.linkedList.josephusProblem;

public class CircleLinkedList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private static final int ELEMENT_NOT_FOUND = -1;

    private static class Node<E> {
        Node<E> next;
        E element;
        Node<E> prev;

        public Node(Node<E> next, E element, Node<E> prev) {
            this.next = next;
            this.element = element;
            this.prev = prev;
        }
    }

    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        /* index == size : 在链表的最后位置插入数据 */
        if (index == size) {
            Node<E> oldLast = last;  /* 因为是尾插，需要记录之前的 last 值 */
            last = new Node<>(oldLast, element, first); /* 用新建的 last 分别链接 oldLast 和 first */
            if (size == 0) {    /* size == 0 即 index == 0，说明是链表添加的第一个元素
                                也可以写成 index == 0 或 oldlast == null */
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {  /* else 的情况即index != size，可能是任意位置插入，也可能是头插*/
            Node<E> next = node(index);  /* 因爲不是尾插，所以一定有当前节点的 next 节点*/
            Node<E> prev = next.prev;    /* 记录 prev 为next的prev */
            Node<E> node = new Node<>(prev, element, next); /* 新增一个节点，分别指向 prev 和 next */
            prev.next = node;
            next.prev = node;
            if (index == 0) {          /* index == 0 表示当前是进行头插 */
                first = node;
            }
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        /* 记录 node */
        Node<E> node = node(index);

        /* 当链表中只有一个元素的时候，删除只需要把该链表的头尾都置空 */
        if (size == 1) {
            first = null;
            last = null;
        } else {  // 当链表中不止一个元素时

            /* 先记录 prev 和 next */
            /** 这里是赋值操作 */
            Node<E> prev = node.prev;
            Node<E> next = node.next;

            /* 令 prev 与 next 相连，即可断开 node 与链表的连接，被 JC 回收 */
            /** 这里是指向操作 */
            prev.next = next;
            next.prev = prev;

            /* 当需要删除的元素是非唯一的头元素时，即 index == 0 时
            把 next 做爲 first */
            if (node == first) {
                first = next;
            }

            /* 当需要删除的元素是尾元素时，把 prev 做爲新的尾 */
            if (node == last) {
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E old = node(index).element;
        node(index).element = element;
        return old;
    }

    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node == null) return i;
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

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + " size: " + size);
    }


}
