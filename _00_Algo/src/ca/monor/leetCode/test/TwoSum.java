package ca.monor.leetCode.test;

import java.util.Arrays;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = 0;
        int[] twoSUm = new int[2];
        for (; i < nums.length - 1; i++) {
            int diff = target - nums[i];
            for (j = i + 1; j < nums.length; j++) {
                if (nums[j] == diff) {
                    twoSUm[0] = i;
                    twoSUm[1] = j;
                }
            }
        }
        return twoSUm;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] arr = {2, 7, 11, 9};
        int target = 9;
        System.out.println(Arrays.toString(twoSum.twoSum(arr, target)));
    }
}
