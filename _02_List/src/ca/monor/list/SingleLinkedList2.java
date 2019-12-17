package ca.monor.list;

/**
 * 使用虚拟头节点的单链表，在构造时创建虚拟头节点 first
 */

public class SingleLinkedList2<E> extends AbstractList<E> {
    private Node<E> first;

    /**
     * 创建一个无参构造函数，在构造函数中初始化一个虚拟头节点，
     * element 和 next 均为 null
     */
    public SingleLinkedList2() {
        this.first = new Node<>(null, null);
    }

    @Override
    public void clear() {
        // 有虚拟头节点，并且不能置空，所有要从第一个真实节点开始置空
        first.next = null;
        size = 0;
    }

    /**
     * 因为有虚拟头节点的存在，当 index==0，也就是头插时，prev 节点就是虚拟头节点
     * 否则 prev 为node(index-1)，之后直接让 prev 的 next 指向新节点，新节点的 next 指向 prev 的 next
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> prev = (index == 0) ? first : node(index - 1);
        prev.next = new Node<>(element, prev.next);
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
        rangeCheck(index);
        // 不使用下面这个方法来获取 remove，因为当 index 非常大的时候，会引发 2n 操作
        // 尽量都使用 O(1) 操作
//        Node<E> remove = node(index);
        Node<E> prev = (index == 0) ? first : node(index - 1);
        Node<E> remove = prev.next;
        prev.next = remove.next;
        return remove.element;
    }

    @Override
    public int indexOf(E element) {
        //从第一个真实节点开始查找并记录 i 的值
        Node<E> node = first.next;
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
        sb.append("List{");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            sb.append((i == 0) ? node.element : (", " + node.element));
            node = node.next;
        }
        sb.append("}");
        return sb.toString();
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        //从第一个真实节点开始查找并记录 i 的值
        Node<E> node = first.next;
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
