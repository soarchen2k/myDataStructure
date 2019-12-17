package ca.monor.queue;

import ca.monor.list.LinkedList;
import ca.monor.list.List;

public class Queue<E> implements QueueInterface<E> {
    private List<E> list;

    public Queue() {
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
    public void enQueue(E element) {
        list.add(element);
    }

    @Override
    public E deQueue() {
        return list.remove(0);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public String toString() {
        return "Front->"+list+"<-Rear";
    }
}
