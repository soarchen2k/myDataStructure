package ca.monor.queue;

/**
 * 单端循环队列
 * 实现：动态数组
 * 各接口时间复杂度： O(1)
 *
 * 设计：int front 储存队头元素下标
 * int size 储存队列元素数量
 * E[] elements 用于储存队列元素的数组
 * final int DEFAULT_CAPACITY = 10
 * @param <E>
 */

public class CircleQueue<E> implements QueueInterface<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;  // 重置 front 的位置，重要
    }


    @Override
    public void enQueue(E element) {
        ensureCapacity(size);
        elements[index(size)] = element;
        size++;
    }

    @Override
    public E deQueue() {
        E old = elements[front]; //获取头元素，也就是获取 elements[index(0)]
        elements[front] = null;  //把头元素置空，也就是 elements[index(0)] = null
        front = index(1);
        size--;
        return old;
    }

    @Override
    public E front() {
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i <  elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[index(i)]);
        }
        string.append("]");
        return string.toString();
    }

//    @Override
//    public String toString() {
//        StringBuilder string = new StringBuilder();
//        string.append("capcacity=").append(elements.length)
//                .append(" size=").append(size)
//                .append(" front=").append(front)
//                .append(", [");
//        for (int i = 0; i < elements.length; i++) {
//            if (i != 0) {
//                string.append(", ");
//            }
//
//            string.append(elements[i]);
//        }
//        string.append("]");
//        return string.toString();
//    }

    private void ensureCapacity(int size) {
        // 传入的参数 size 是实际 size，进行判断的时候需要进行 +1 操作
        if (size + 1 <= elements.length) {
            return;
        }
        int newCapacity = elements.length + (elements.length >> 1); //位运算中的小括号必不可少
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            //原先的 elements 数组，实际的 index 位置要用 index(int index) 来计算
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;  //重置 front 的位置，重要
    }

    /**
     * 返回指定 index 位置的元素，在 elements 中的实际 index 位置
     * 例如当 front == 2 时，index == 0 的元素，在 elements 中的实际位置是 2
     * 计算方法是指定 index 的值，加上当前 front 的索引，再模 elements 的 length
     * 因为模运算比较消耗资源，应尽量避免，经分析可知，front + index 一定小于 2 倍的 size
     * 在此前提下，实际的 index 位置，当 front + index >= elements.length 时，
     * 实际的 index 值是 front + index - length
     * 否则就是 front + index
     * @param index
     * @return 实际的元素在 elements 中的 index 值
     */
    private int index(int index) {
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

}
