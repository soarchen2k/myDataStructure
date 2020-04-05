package ca.monor.dynamicProgramming动态规划;

public class MaxSubArray {
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
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(nums));
    }
}
