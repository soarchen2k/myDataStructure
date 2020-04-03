package ca.monor.recursion;

/**
 * 阿里面试题 楼梯问题
 * 假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，你能有多少种不同的方法爬到楼顶部？
 *
 * 思路：
 * 假设n 阶台阶有f(n) 种走法，第1 步有2 种走法
 * 如果上1 阶，那就还剩n – 1 阶，共f(n – 1) 种走法
 * 如果上2 阶，那就还剩n – 2 阶，共f(n – 2) 种走法
 * 所以f(n) = f(n – 1) + f(n – 2)
 */
public class Stairs {
    public static void main(String[] args) {
        System.out.println(stairs1(38));
        System.out.println(stairs2(38));
    }

    private static int stairs1(int n) {
        if (n <= 2) return n;
        return stairs1(n - 1) + stairs1(n - 2);
    }

    private static int stairs2(int n) {
        if (n <= 2) return n;
        int[] arr = new int[(n + 1)];

        arr[1] = 1;
        arr[2] = 2;
        return stairs2(n, arr);
    }

    private static int stairs2(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = stairs2(n - 1, arr) + stairs2(n - 2, arr);
        }
        return arr[n];
    }
}
