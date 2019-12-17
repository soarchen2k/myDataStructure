package ca.monor.list;

/**
 * 抽象类实现接口，不需要实习接口中的抽象方法，而是需要由继承该类的子类来实现
 * 创建抽象类 List，把 ArrayList 和 LinkedList 可以共有的方法抽出来做爲公共父类
 * 创建 ArrayList 和 LinkedList 时只需要继承本类即可
 * @param <E>
 */

public abstract class AbstractList<E> implements List<E> {
    /**
     * 记录元素的数量
     * protected --> sub Class
     */
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element) {
        add(size, element);
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Size: " + size + ", index: " + index);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}