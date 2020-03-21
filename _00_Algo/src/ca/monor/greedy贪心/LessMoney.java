package ca.monor.greedy贪心;

/**
 * 分金条最少花费
 */

import java.util.PriorityQueue;

public class LessMoney {
    public static void main(String[] args) {
        int[] arr = {30, 10, 20};
        System.out.println(lessMoney(arr));
    }

    private static int lessMoney(int[] arr) {
        // jkd 中有集成堆结构：优先级队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 1. 建立 minheap 最小堆，小根堆
        for (int a : arr) {
            priorityQueue.add(a);
        }

        int sum = 0;
        int current = 0;
        // 2. 每次从堆的顶部弹出元素，弹2次
        while (priorityQueue.size() > 1) {
            current = priorityQueue.poll() + priorityQueue.poll();
            sum += current;
            priorityQueue.add(current);
        }
        return sum;
    }
}
