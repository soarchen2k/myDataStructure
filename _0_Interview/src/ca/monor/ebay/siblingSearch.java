package ca.monor.ebay;

import java.util.Arrays;

public class siblingSearch {
    public static int[] siblingSearch(int input1, int[] input2, int input3) {
        return new int[]{-1};
    }

    public static void main(String[] args) {
        int a = 6;
        int[] ints = {1, 2, 3, 4, 5, 6};
        int b = 5;
        int[] ints1 = siblingSearch(a, ints, b);
        String s = Arrays.toString(ints1);
        System.out.println(s);
    }
}
