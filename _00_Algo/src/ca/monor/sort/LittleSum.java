package ca.monor.sort;

/**
 * 小和问题
 */
public class LittleSum {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        int[] arr2 = {2,7,5};
        System.out.println(littleSum1(arr2));
    }
    private static int littleSum1(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int count = 0;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] < arr[j]) count++;
            }
            sum += arr[i] * count;
        }
        return sum;
    }
}
