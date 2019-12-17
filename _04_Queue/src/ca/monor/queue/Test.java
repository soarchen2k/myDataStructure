package ca.monor.queue;

public class Test {
    public static void main(String[] args) {
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
}
