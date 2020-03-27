package ca.monor.dynamicProgramming动态规划;

/**
 * leetCode 322
 */
public class CoinChange {
    private static int coinChange1(int n) { // n: 硬币的个数
        if (n < 1) {
            return Integer.MAX_VALUE;
        }

        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }

        int min1 = Math.min(coinChange1(n - 25), coinChange1(n - 20));
        int min2 = Math.min(coinChange1(n - 5), coinChange1(n - 1));
        return Math.min(min1, min2) + 1;
    }

    /**
     * 记忆化搜索
     * @param n
     * @return
     */
    private static int coinChange2(int n) {
        if (n < 1) {
            return Integer.MAX_VALUE;
        }

        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1]; // 凑够 n 分钱需要的硬币数量
        int[] faces = {1, 5, 20, 25}; // 硬币面值

        for (int face : faces) {
            if (n < face) {
                continue; // 如果 faces 数组中是顺序的，则可以使用 break，如果是乱序的，则使用 continue
            }
            dp[face] = 1;
        }

        return coins(n, dp);
    }

    private static int coins(int n, int[] dp) {
        if (n < 1) {
            return Integer.MAX_VALUE;
        }

        if (dp[n] == 0) { // 说明当前未拿到凑够 n 分钱所需的硬币数量
            int min1 = Math.min(coins(n - 25, dp), coins(n - 20, dp));
            int min2 = Math.min(coins(n - 5, dp), coins(n - 1, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 从 coinChange2 记忆化搜索中知道，要算出较大的值，需要先算出较小的值
     * 那么递推则是：先算出较小的值，再依次算出较大的值
     * @param n
     * @return
     */
    private static int coinChange3(int n) {
        if (n < 1) {
            return Integer.MAX_VALUE;
        }
        int[] dp = new int[n + 1]; // 凑够 n 分钱需要的硬币数量
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1) min = Math.min(dp[i - 1], min);
            if (i >= 5) min = Math.min(dp[i - 5], min);
            if (i >= 20) min = Math.min(dp[i - 20], min);
            if (i >= 25) min = Math.min(dp[i - 25], min);
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(coinChange3(76));
    }
}
