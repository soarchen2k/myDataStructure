package ca.monor.recursion;

public class Weidiaoyong {
    public static void main(String[] args) {
        fun1();
        Weidiaoyong.fun2(4);
    }

    private static void fun1() {
        int a = 10;
        int b = a + 10;
        fun2(b);
        int c = a + b;
        System.out.println(c);
    }

    private static void fun2(int b) {
        int c = 20;
        int d = 30;
    }
}
