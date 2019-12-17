package ca.monor.list;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 清除所有元素
     */
    void clear();

    /**
     * @return ArrayList 的长度
     */
    int size();

    /**
     * @return ArrayList 是否为空
     */
    boolean isEmpty();

    /**
     * 传入元素
     * @param element
     * @return 是否包含该元素
     */
    boolean contains(E element);

    /**
     * 在尾部增加元素
     * @param element
     */
    void add(E element);

    /**
     * 在
     * @param index 的位置添加元素
     * @param element
     */
    void add(int index, E element);

    /**
     * 获取
     * @param index 位置的元素，并
     * @return 该元素
     */
    E get(int index);

    /**
     * 替换
     * @param index 位置的元素
     * @param element 并
     * @return 被替换掉元素
     */
    E set(int index, E element);

    /**
     * 删除 index 位置上的元素，并
     * @return 被删除的元素
     */
    E remove(int index);

    /**
     * 如果包含该元素，则返回该元素的下标
     * @param element
     * @return
     */
    int indexOf(E element);
}
