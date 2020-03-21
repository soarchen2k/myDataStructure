package ca.monor.greedy贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大收益
 */
public class MaxProfit {
    private static class Node {
        public int profit;
        public int cost;

        public Node(int p, int c) {
            this.profit = p;
            this.cost = c;
        }
    }

    public static int findBestProject(int[] profits, int[] costs, int myMoney, int k) {
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

        // 把所有项目加入小根堆
        minCostPriorityQueue.addAll(Arrays.asList(nodes));

        for (int i = 0; i < k; i++) {
            while (!minCostPriorityQueue.isEmpty()
                    && minCostPriorityQueue.peek().cost <= myMoney) {
                maxProfilePriorityQueue.add(minCostPriorityQueue.poll());
            }
            // 资金回笼
            if (maxProfilePriorityQueue.isEmpty()) {
                return myMoney;
            }
            myMoney += maxProfilePriorityQueue.poll().profit;
        }
        int a = 10;
        return myMoney;
    }

    private static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost;
        }
    }

    private static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.profit - o1.profit;
        }
    }
}
