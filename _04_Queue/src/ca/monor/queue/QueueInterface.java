package ca.monor.queue;

public interface QueueInterface<E> {

    /**
     * @return 队列的元素个数
     */
    int size();

    /**
     * @return 队列是否为空
     */
    boolean isEmpty();

    /**
     * 清空队列
     */
    void clear();

    /**
     * 向队尾添加元素
     * @param element
     */
    void enQueue(E element);

    /**
     * 从队头获取元素
     * @return 队头元素，并删除该元素
     */
    E deQueue();

    /**
     * @return 队头元素，不删除该元素
     */
    E front();
}
