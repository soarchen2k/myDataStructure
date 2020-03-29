package ca.monor.dynamicProgramming动态规划;

public class MinPathSum {
    private static int process(int[][] matrix, int i, int j) {
        if (j == matrix[0].length - 1) { // 来到最后一列，往下走
            return matrix[i][j] + process(matrix, i + 1, j);
        }

        if (i == matrix.length - 1) {  // 来到最后一行，往右走
            return matrix[i][j] + process(matrix, i, j + 1);
        }
        int right = process(matrix, i, j + 1);
        int down = process(matrix, i + 1, j);

        return matrix[i][j] + Math.min(right, down);
    }

    private static int process2(int[][] matrix) {
        int row = matrix.length; // 最后一行
        int col = matrix[0].length; // 最后一列
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 1, 0, 2}, {4, 3, 2, 1}, {5, 2, 1, 0}};
        System.out.println(process2(matrix));
    }
}
