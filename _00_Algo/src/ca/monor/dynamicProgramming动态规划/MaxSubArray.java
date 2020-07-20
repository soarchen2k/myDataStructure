package ca.monor.dynamicProgramming动态规划;

/**
 * 06 动态规划 1
 * https://www.youtube.com/watch?v=UA2DnkxfLvs&list=PL16JNCI8Y_S1dNIXF7c5hThY5ULHkBvff&index=7
 * https://leetcode-cn.com/problems/maximum-subarray/
 * https://leetcode.com/problems/maximum-subarray/
 *
 * 53. 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

public class MaxSubArray {
    /**
     * 原始的双层循环解法
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums) {
        // 数组长度为 0 时，返回 0
        if (nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;  // 设置最大和的初始值为 Integer 的最小值
        for (int begin = 0; begin < nums.length; begin++) {  // 从数组第一个元素开始遍历
            int tempSum = nums[begin];  // 设置临时最大和的初始值为数组的第一个元素的值
            max = Math.max(tempSum, max);  // 记录最大和
            for (int calculate = begin + 1; calculate < nums.length; calculate++) {
                tempSum += nums[calculate];  // 从数组的第一个元素开始依次累加，得到临时的求和结果
                max = Math.max(tempSum, max);  // 记录最大的求和值
            }
        }
        return max;
    }

    private static int maxSubArray0(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            maxSum = Math.max(sum, maxSum);
            if (sum < 0) sum = 0;
        }
        return maxSum;
    }

    private static int maxSubArray1(int[] nums) {  // 空间复杂度 N
        if (nums == null) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = dp[0] = nums[0];

        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
        // if (dp[i-1]>0){
        // dp[i] = dp[i-1]+nums[i];  // 伪码
        // } else {
        // dp[i] = nums[i];
    }

    private static int maxSubArray2(int[] nums) {  // 空间复杂度 1
        if (nums == null) {
            return 0;
        }
        int max = nums[0]; int dp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp > 0) {
                dp = dp + nums[i];
            } else {
                dp = nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
        // if (dp[i-1]>0){
        // dp[i] = dp[i-1]+nums[i];  // 伪码
        // } else {
        // dp[i] = nums[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2,1};
        System.out.println(maxSubArray0(nums));
    }
}
