package ca.monor.list;

import java.util.Arrays;

/**
 * 有动态缩容操作的 ArrayList
 */
public class DynamicArrayList<E> extends AbstractList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    protected E[] elements;

    public DynamicArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArrayList(int defaultCapacity) {
        elements = (E[]) new Object[Math.max(defaultCapacity, DEFAULT_CAPACITY)];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    private void ensureCapacity(int size) {
        if (size + 1 <= elements.length) {
            return;
        }
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
//        E[] newElements = (E[]) new Object[newCapacity];
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < size; i++) {
//            newElements[i] = elements[i];
//        }
        copyArray(newCapacity);
//        elements = newElements;
//        System.out.println("数据规模：" + size + ", 耗时：" + (System.currentTimeMillis() - start));
//        System.out.println("扩容为：" + elements.length);
    }

    private void resetCapacity() {
        //动态缩容：如果 ArrayList 有一半以上的空元素，就缩减容量
        if (size > (elements.length >> 1) || size < 10) return;
//        long start = System.currentTimeMillis();
        copyArray(size);
//        elements = Arrays.copyOf(elements, size);
//        System.out.println("数据规模：" + size + ", 耗时：" + (System.currentTimeMillis() - start));
//        System.out.println("缩容为：" + elements.length);
    }

    private void copyArray(int newCapacity) {
        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        //因为最终是 index == size - 1 位置上的元素需要被清空，所以这里用 --size
        elements[--size] = null;
        resetCapacity();
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        return "DynamicArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
