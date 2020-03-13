package ca.monor.recursion;

public class FemaleCow {
    public static void main(String[] args) {
        System.out.println(femaleCow(9));
        System.out.println(femaleCow2(9));
    }

    private static int femaleCow(int n) {
        if (n < 1) return 0;
        if (n <= 4) return n;
        int sum = 0;
        for (int i = 4; i < n; i++) {
            sum += n - i;
        }
        return sum + n;
    }

    private static int femaleCow2(int n) {
        if (n < 1) return 0;
        if (n <= 4) return n;
        return femaleCow2(n - 1) + femaleCow2(n - 3);
    }
}
