package ca.monor.leetCode.queue.implementStackUsingQueues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * <p>
 * https://leetcode.com/problems/implement-stack-using-queues/
 */
class MyStack {
    private Queue<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        this.queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.add(x); //首先把元素加入队列，相当于入栈

        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.poll());
        }
//        int size = queue.size();  //记录队列的当前size
//        while (size > 1) {
//            queue.add(queue.remove());  //当 size > 1时，把 front 的元素移至 rear
//            size--;  //size-- ，当 size==1，也就是最后加入的元素来到了 front 时，结束循环
//        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.poll(); //由于 front 已经是最早加入的元素，直接弹栈
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
