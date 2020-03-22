package ca.monor.divideAndConquer分治;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 53 最大连续子序列
 * maximum subarray
 * -2,1,-3,4,-1,2,1,-5,4
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxArr(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }


    private static int maxArr(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int currentSum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                currentSum += arr[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }
}
