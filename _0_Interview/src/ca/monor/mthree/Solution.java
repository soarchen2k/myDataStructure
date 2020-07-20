package ca.monor.mthree;

import java.util.Scanner;

/**
 * 给出一组温度，返回最接近 0 的温度，如果同时有零上和零下，取零上
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        if (N == 0) {
            System.out.println(0);
        } else {
            int lowTemp = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int t = in.nextInt();
                if (Math.abs(t) < Math.abs(lowTemp) || (t == -lowTemp && t > 0)) {
                    lowTemp = t;
                }
            }
            System.out.println(lowTemp);
        }
    }

    private static int replaceLowTemp(int lowTemp, int t) {
//        if (Math.abs(t) > lowTemp) {
//            return lowTemp;
//        }
        if (Math.abs(t) < lowTemp && t > 0) {
            return t;
        } else {
            return t;
        }
    }
}
