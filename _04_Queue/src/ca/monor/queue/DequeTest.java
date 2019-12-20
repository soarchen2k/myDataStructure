package ca.monor.queue;

public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println(deque.size());
        System.out.println(deque);
        deque.enQueueFront(1);
        System.out.println(deque);
        deque.enQueueFront(2);
        System.out.println(deque);
        deque.enQueueRear(3);
        System.out.println(deque);
        deque.enQueueRear(4);
        System.out.println(deque);
        System.out.println(deque.deQueueFront());
        System.out.println(deque);
        System.out.println(deque.deQueueFront());
        System.out.println(deque);
        System.out.println(deque.deQueueFront());
        System.out.println(deque);
        System.out.println(deque.deQueueFront());
        System.out.println(deque);
    }
}
