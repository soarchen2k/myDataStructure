package ca.monor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SandBox {
    public static void main(String[] args) {
//        System.out.println(6 % 5);

//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>
//                (new MaxComparator());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>
                ((o1, o2) -> o2 - o1);

        priorityQueue.add(6);
        priorityQueue.add(9);
        priorityQueue.add(7);
        priorityQueue.add(21);
        priorityQueue.add(54);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
    static class MaxComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}