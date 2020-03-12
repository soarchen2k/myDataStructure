package ca.monor.recursion;

public class Fibonacci_01 {

    public static void main(String[] args) {
//        testFib(55);
//        System.out.println(sum(100));
        System.out.println(fib3(80));
    }

    private static void testFib(int n) {
        TimesTester.test("Fib1", new TimesTester.Task() {
            @Override
            public void execute() {
                System.out.println(fib1(n));
            }
        });

        TimesTester.test("Fib2", new TimesTester.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(n));
            }
        });
    }

    // 通过递归，计算斐波那契数列
    private static long fib1(long n) {
        // 递归的边界条件：base case
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }

    // 递归计算斐波那契数列的第一次优化，使用一个数组来记录每次递归的值，以避免重复计算
    private static long fib2(long n) {
        if (n <= 1) return n;
        long[] arr = new long[(int) (n + 1)];
        arr[1] = 1;
        arr[2] = 1;
        return fib2(n, arr);
    }

    private static long fib2(long n, long[] arr) {
        if (arr[(int) n] == 0) {
            arr[(int) n] = fib2(n - 1, arr) + fib2(n - 2, arr);
        }
        return arr[(int) n];
    }

    // 斐波那契数列计算的再次优化，使用滚动数组
    private static long fib3(long n) {
        if (n <= 1) return n;
        long[] arr = new long[2];
        arr[0] = arr[1] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
        }
        return arr[(int) (n % 2)];
    }

    // 通过递归计算 n 个数的相加
    private static int sum(int n) {
        if (n <= 1) return n;
        return n + sum(n - 1);
    }
}
