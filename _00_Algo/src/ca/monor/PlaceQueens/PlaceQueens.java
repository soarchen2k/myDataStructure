package ca.monor.PlaceQueens;

public class PlaceQueens {
    // 数组的索引是棋盘的行，数组中的元素的值，是棋盘的列，
    // 存放皇后摆放的位置 cols[4] = 5; 表示第四行的皇后在第五列上
    private int[] cols;

    // 记录一共有多少种摆法
    private int ways;

    public void placeQueens(int n) {
        if (n < 4) {
            System.out.println("There is 0 ways to place " + n + " queens");
            return;
        }
        cols = new int[n];
        place(0);
        System.out.println("There is " + ways + " ways to place " + n + " queens");
    }

    /**
     * 从第 row 行开始摆放皇后
     * @param row
     */
    private void place(int row) {
        // 一定要前八行都摆放完成，开始摆放第九行的时候，进行 return，不然只摆放七行
        if (row == cols.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col; //把第 n 个皇后摆放好
                place(row + 1); //回溯
            }
        }
    }

    private boolean isValid(int row, int col) {
        // i 是之前行的行号：之前 queen 所处的位置
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) { // 在同一行
                return false;
            }
            if ((row - i) == (Math.abs(col - cols[i]))) { //在同一条对角线上
                return false;
            }
        }
        return true;
    }

    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("X ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new PlaceQueens().placeQueens(6);
    }
}
