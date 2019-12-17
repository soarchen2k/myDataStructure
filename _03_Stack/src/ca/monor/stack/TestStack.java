package ca.monor.stack;

import java.util.LinkedList;

public class TestStack {
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(stack.top());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        stack.clear();
        System.out.println(stack);
    }
}
