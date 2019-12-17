package ca.monor.list;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return (indexOf(element) != -1);
    }

    @Override
    public void add(E element) {
        add(size, element);
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

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("size = " + size + ", index = " + index);
    }

}
