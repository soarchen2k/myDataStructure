package ca.monor.stack;

import ca.monor.list.ArrayList;

public class Stack<E> implements StackInterface<E> {
    private ArrayList<E> list;

    public Stack() {
        this.list = new ArrayList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E element) {
        list.add(element);
    }

    @Override
    public E pop() {
        return list.remove(size() - 1);
    }

    @Override
    public E top() {
        return list.get(size() - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "list=" + list +
                '}';
    }
}
