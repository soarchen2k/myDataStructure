package ca.monor.queue;

/**
 * 双端队列是能在头尾两端添加、删除的队列
 * 英文deque 是double ended queue 的简称
 *
 * 双端队列设计：
 * int size(); // 元素的数量
 * boolean isEmpty(); // 是否为空
 * void clear(); //清空
 * void enQueueRear(E element); // 从队尾入队
 * E deQueueFront(); // 从队头出队
 * void enQueueFront(E element); // 从队头入队
 * E deQueueRear(); // 从队尾出队
 *
 * //		Deque<Integer> queue = new Deque<>();
 * //		queue.enQueueFront(11);
 * //		queue.enQueueFront(22);
 * //		queue.enQueueRear(33);
 * //		queue.enQueueRear(44);
 * //
 * //		<rear 尾  44  33   11  22 头 front>
 *
 * 双端队列的实现：LinkedList
 *
 * @param <E>
 */

public interface DequeInterface<E> {
    int size(); // 元素的数量

    boolean isEmpty(); // 是否为空

    void clear(); //清空

    void enQueueRear(E element); // 从队尾入队

    E deQueueFront(); // 从队头出队

    void enQueueFront(E element); // 从队头入队

    E deQueueRear(); // 从队尾出队
}