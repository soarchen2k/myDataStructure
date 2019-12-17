package ca.monor.stack;

public interface StackInterface<E> {
    /**
     * @return 栈内元素的数量
     */
    int size();

    /**
     * @return 栈是否为空
     */
    boolean isEmpty();

    /**
     * 向栈内装入元素
     * @param element
     */
    void push(E element);

    /**
     * 弹出栈顶元素，同时删除弹出的元素
     * @return 弹出的元素
     */
    E pop();

    /**
     * 仅获取栈顶元素
     * @return 获取到元素
     */
    E top();

    /**
     * 清空栈
     */
    void clear();
}
