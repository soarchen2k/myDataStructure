package ca.monor.arrayList;

import java.util.Arrays;

/**
 * ArrayList
 * 参数设置，int size, E[] elements, DEFAULT_CAPACITY, ELEMENT_NOT_FOUND
 *
 * 构造方法：
 * 无参构造，默认设置 capacity 为10，有参构造，初始容量为用户传入的容量如果用户传入
 * 的容量小于最小容量，则使用系统最小容量
 *
 * 需要实现的方法(public)：
 * int size(); 返回长度
 * boolean isEmpty(); 判断数组是否为空
 * void clear(); 清空数组
 * void add(E element); 在末尾为数组添加元素
 * void add(int index, E element); 在指定的 index 位置为数组添加元素
 * E remove(int index); 删除指定位置的元素并返回被删除的元素
 * boolean contains(E element); ArrayList 中是否包含需要查找的元素
 * E get(int index); 获取指定位置的元素
 * E set(int index, E element); 指定位置的元素替换
 * int indexOf(E element); 返回指定元素的 index，如果没有找到相应的元素就返回 -1
 * string toString();
 *
 * 需要实现的方法(private):
 * void ensureCapacity(int capacity); 判断数组的容量是否够用，不够则扩容(1.5倍)
 * void rangeCheck(int index);  检查用户输入的 index 是否越界(outOfBounds)
 * void rangeCheckForAdd(int index); 检查用户输入的 index 是否会在 add 的时候越界
 * void outOfBounds(int index); 输出越界信息
 * @param <E>
 */
public class ArrayList<E> {
    /**
     * 记录 List 的长度
     */
    private int size;

    /**
     * 用来记录元素的数组
     */
    private E[] elements;

    /**
     * 默认的数组长度
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 未发现元素时的返回值
     */
    private static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 无参构造，如果用户没有带参数，则创建默认大小的数组
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 有参构造，创建一个 Object 数组，并把数组向下造型为 E 类型
     * @param capacity ArrayList 的容量
     */
    public ArrayList(int capacity) {
        elements = (E[]) new Object[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    /**
     * @return ArrayList 的长度
     */
    public int size() {
        return this.size;
    }

    /**
     * size == 0 即为空
     * @return ArrayList 是否为空
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 清空 ArrayList，需要置空每一个元素，并把 size 归 0
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[size] = null;
        }
        size = 0;
    }

    /**
     * 在 ArrayList 的末尾添加元素
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 在 ArrayList 的任意位置添加元素
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1); //因为要判断容量是否足够再增加一个元素，所以要判断 size+1
        //如果是添加到最后的位置，index == size，则不会执行 for 循环，直接跳到赋值操作
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 移除 ArrayList 中位于
     * @param index 位置的元素
     * @return 被删除的元素
     */
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = this.elements[index];

        //40 000 000 个数据，头删耗时 16ms
        if (size - 1 - index >= 0){
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        }

        //40 000 000 个数据，头删耗时 28ms
//        for (int i = index; i < size - 1; i++) {
//            elements[i] = elements[i + 1];
//        }
        elements[--size] = null;
        return oldElement;
    }

    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
                // 不可以用 if (elements[i].equals(element)) return i;
                // 当 AL 中含有空元素时，会报空指针错误 NullPointException
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ArrayList{");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(i == (size - 1) ? elements[i] : (elements[i] + ", "));
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
        System.out.println("扩容为: " + newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("size: " + size + " index: " + index);
    }
}
