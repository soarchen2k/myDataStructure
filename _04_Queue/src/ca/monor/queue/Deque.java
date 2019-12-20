package ca.monor.queue;

import ca.monor.list.LinkedList;
import ca.monor.list.List;

public class Deque<E> implements DequeInterface<E> {
    private List<E> list;

    public Deque() {
        list = new LinkedList<>();
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
    public void clear() {
        list.clear();
    }

    @Override
    public void enQueueRear(E element) {
        list.add(element);
    }

    @Override
    public E deQueueFront() {
        return list.remove(0);
    }

    @Override
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    @Override
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public E rear() {
        return list.get(size() - 1);
    }

    @Override
    public String toString() {
        return "Front-> " + list.toString() + " <-Rear";
    }
}
