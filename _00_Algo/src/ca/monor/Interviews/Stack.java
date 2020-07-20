package ca.monor.Interviews;

/**
 * WorkJam 面试题
 */

public class Stack {
    private Long[] arr;
    private static int INITIAL_CAPACITY = 10;
    private int currentCapacity;

    public Stack() {
        arr = new Long[INITIAL_CAPACITY];
        currentCapacity = 0;
    }

    public void push(Long i) {
        arr[currentCapacity++] = i;
    }

    public Long pop() {
        if (currentCapacity <= 0) {
            return null;
        }
        return arr[--currentCapacity];
    }


    public static void main(String[] args) {
        final Stack stack = new Stack();
        stack.push(5L);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
