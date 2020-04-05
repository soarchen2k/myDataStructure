package ca.monor.dynamicProgramming动态规划;

public class LongestIncreasingSubsequence {
    private static int lis(int[] nums) {
        if (nums == null && nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int max = dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {

        int[] nums = {10, 2, 2, 5, 1, 7, 101, 18};
        System.out.println(lis(nums));
    }
}
