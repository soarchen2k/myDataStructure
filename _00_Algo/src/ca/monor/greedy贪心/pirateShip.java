package ca.monor.greedy贪心;

import java.util.Arrays;

public class pirateShip {
    private static int pirateShip() {
        int capacity = 30;
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        int count = 0, weight = 0;
        Arrays.sort(weights);

        while (weight <= capacity) {
            weight += weights[count];
            count++;
        }
        return weight;
    }

    public static void main(String[] args) {
        System.out.println(pirateShip());
    }
}
