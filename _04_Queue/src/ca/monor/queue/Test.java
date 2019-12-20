package ca.monor.queue;

public class Test {
    public static void main(String[] args) {
//        testQueue();
//        testDequeue();
        testCircleQueue();
    }

    private static void testQueue() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 1; i <= 10000; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        System.out.println(queue.front());
        System.out.println(queue);
        System.out.println(queue.deQueue());
        System.out.println(queue);
        System.out.println(queue.deQueue());
        System.out.println(queue);
        queue.enQueue(12);
        System.out.println(queue);
    }

    private static void testDequeue() {
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

    private static void testCircleQueue() {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for (int i = 1; i <= 10; i++) {
            queue.enQueue(i);
        }
        print(queue);
        System.out.println(queue.deQueue());
        print(queue);
        queue.enQueue(1);
        print(queue);
        System.out.println(queue.deQueue());
        print(queue);
        System.out.println(queue.deQueue());
        print(queue);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        for (int i = 2; i < 5; i++) {
            queue.enQueue(i);
        }
        print(queue);
    }

    public static void print(Object queue) {
        System.out.println(queue);
    }
}
