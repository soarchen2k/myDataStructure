package ca.monor.divideAndConquer分治;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 53 最大连续子序列
 * maximum subarray
 * -2,1,-3,4,-1,2,1,-5,4
 * 也就是最大分割/最大区 greatest slicing
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxArr2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//        System.out.println(maxArr(new int[]{-2, 1}));
    }


    private static int maxArr(int[] nums) {
        if (nums==null||nums.length==0) return 0;

        int maxSum = Integer.MIN_VALUE;

        if (nums.length == 1) {
            return nums[0];
        }

        for (int i = 0; i < nums.length; i++) {
            int currentSum = nums[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            for (int j = i + 1; j < nums.length; j++) {
                currentSum += nums[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    // 分治
    private static int maxArr2(int[] nums) {
        if (nums==null||nums.length==0) return 0;

        return maxSubArray(nums, 0, nums.length);
    }

    private static int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) {
            return nums[begin];
        }

        int mid = (begin + end) / 2;
        int leftMax = nums[mid - 1];
        int leftSum = leftMax;
        for (int i = mid - 2; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = nums[mid];
        int rightSum = rightMax;
        for (int i = mid + 1; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max((leftMax+rightMax),
                Math.max(maxSubArray(nums, begin, mid),
                        maxSubArray(nums, mid, end)));
    }
}
