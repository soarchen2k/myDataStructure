package ca.monor.queue;
/**
 * 双端循环队列
 * 实现：动态数组，可以从 front / rear 任意位置入队和出队
 * 各接口时间复杂度： O(1)
 *
 * 设计：int front 储存队头元素下标
 * int size 储存队列元素数量
 * E[] elements 用于储存队列元素的数组
 * final int DEFAULT_CAPACITY = 10
 * @param <E>
 */
public class CircleDeque<E> implements DequeInterface<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;


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
            size = 0;
            front = 0;
        }
    }

    /**
     * 从队尾入队
     * @param element
     */
    @Override
    public void enQueueRear(E element) {
        ensureCapacity(size);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 从队头出队
     * @return
     */
    @Override
    public E deQueueFront() {
        E old = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return old;
    }

    /**
     * 从队头入队
     * @param element
     */
    @Override
    public void enQueueFront(E element) {
        ensureCapacity(size);
        // 把整个queue看成一个整体，那么队头就是0位置，
        // 再插入新的元素时，放到 elements 的尾端，并做爲新的 front
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 从队尾出队
     * @return
     */
    @Override
    public E deQueueRear() {
        E old = elements[index(size - 1)];
        elements[index(size - 1)] = null;
        size--;
        return old;
    }

    @Override
    public E front() {
        return elements[front];
    }

    @Override
    public E rear() {
        return elements[index(size - 1)];
    }

    private int index(int index) {
        // 找出当 index = -1，时，front 的位置
        if (index < 0) {
            return index + elements.length;
        }
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    private void ensureCapacity(int size) {
        int oldCapacity = elements.length;
        if (size + 1 <= oldCapacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;
    }
}
