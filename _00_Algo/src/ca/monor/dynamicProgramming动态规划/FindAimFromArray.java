package ca.monor.dynamicProgramming动态规划;

public class FindAimFromArray {
    public static void main(String[] args) {
        int[] arr = {1, 4, 8, 9, 3};
        System.out.println(findAim1(arr, 21));
        System.out.println(findAim2(arr, 21));
    }

    // recursion
    private static boolean findAim1(int[] arr, int aim, int sum, int i) {
        if (sum == aim) {
            return true;
        }
        if (i == arr.length) {
            return false;
        }

        return findAim1(arr, aim, sum, i + 1) || findAim1(arr, aim, sum + arr[i], i + 1);
    }

    private static boolean findAim1(int[] arr, int aim) {
        return findAim1(arr, aim, 0, 0);
    }

    private static boolean findAim2(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];  // 只要下一行同列中有 true，本位置就为 true
                if (j + arr[i] <= aim) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        print(dp);
        return dp[0][0];
    }

    private static void print(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
