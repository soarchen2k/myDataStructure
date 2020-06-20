package ca.monor.Interviews;

import java.util.Arrays;

public class StackWithArray<E> {
    private E[] arr;
    private static int INITIAL_CAPACITY = 10;
    private int currentCapacity;

    public StackWithArray() {
        arr = (E[]) new Object[INITIAL_CAPACITY];

        currentCapacity = 0;
    }

    public void push(E num) {
        ensureCapacity(currentCapacity, arr.length);
        arr[currentCapacity++] = num;
    }

    public E pop() {
        if (currentCapacity <= 0) {
            return null;
        }
        return arr[--currentCapacity];
    }

    @Override
    public String toString() {
        return "StackWithArray{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    private void ensureCapacity(int currentCapacity, int length) {
        if (currentCapacity < length) {
            return;
        }

        E[] arrTemp = (E[]) new Object[length + (length >> 1)];
        for (int i = 0; i < length; i++) {
            arrTemp[i] = arr[i];
        }
        arr = arrTemp;
        System.out.println("extends to : " + (length + (length >> 1)));
    }

    public static void main(String[] args) {
//        StackWithArray<Integer> stack = new StackWithArray<>();
//        for (int i = 1; i < 35; i++) {
//            stack.push(i);
//            System.out.println(stack);
//        }
//
//        System.out.println(stack.pop());
//        System.out.println(stack);
//
//        stack.push(70);
//        System.out.println(stack);

        StackWithArray<String> helloWorld = new StackWithArray<>();
        helloWorld.push("World");
        helloWorld.push("Hello");
        System.out.println(helloWorld);
        System.out.println(helloWorld.pop());
        System.out.println(helloWorld.pop());
        System.out.println(helloWorld.pop());
    }
}
