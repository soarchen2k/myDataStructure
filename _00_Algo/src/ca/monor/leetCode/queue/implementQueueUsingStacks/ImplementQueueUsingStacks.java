package ca.monor.leetCode.queue.implementQueueUsingStacks;


import ca.monor.stack栈.Stack;

/**
 * 232. Implement Queue using Stacks
 *
 * https://leetcode.com/problems/implement-queue-using-stacks/
 */
class MyQueue {

    private Stack<Integer> entry;
    private Stack<Integer> out;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.entry = new Stack();
        this.out = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        entry.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (out.isEmpty()) {
            if (entry.isEmpty()) {
                return Integer.parseInt(null);
            } else {
                swap4Pop();
                return entry.pop();
            }
        } else return out.pop();
    }

    private void swap4Pop() {
        int size = entry.size();
        for (int i = 0; i < size - 1; i++) {
            out.push(entry.pop());
        }
    }

    /** Get the front element. */
    public int peek() {
        if (out.isEmpty()) {
            if (entry.isEmpty()) {
                return Integer.parseInt(null);
            } else {
                swap4Peek();
            }
        }
        return out.peek();
    }

    private void swap4Peek() {
        int size = entry.size();
        for (int i = 0; i < size; i++) {
            out.push(entry.pop());
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return entry.isEmpty() && out.isEmpty();
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "entry=" + entry +
                ", out=" + out +
                '}';
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue);

    }
}
