package ca.monor.greedy贪心;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大收益
 */
public class MaxProfit {
    private static class Node {
        public int profits;
        public int costs;

        public Node(int p, int c) {
            this.profits = p;
            this.costs = c;
        }
    }

    public static int findBestProject(int[] profits, int[] costs, int startMoney, int k) {
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(profits[i], costs[i]);
        }

        // 建立小根堆，按照 cost 划分
        PriorityQueue<Node>  minCostPriorityQueue =
                new PriorityQueue<>(new MinCostComparator());

        // 建立大根堆，按照 profit 划分
        PriorityQueue<Node> maxProfilePriorityQueue =
                new PriorityQueue<>(new MaxProfitComparator());


    }

    private static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.costs - o2.costs;
        }
    }

    private static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.profits - o1.profits;
        }
    }
}
