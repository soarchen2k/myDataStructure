package ca.monor.sort;

import java.util.Arrays;

import static com.sun.tools.javac.jvm.ByteCodes.swap;

/**
 * 荷兰国旗问题
 */
public class NetherlandsFlag {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 7, 1, 6, 4, 8};
        System.out.println(Arrays.toString(partition2(arr, 6)));
    }

    private static int[] partition2(int[] arr, int num) {
        int[] arr2 = new int[arr.length];
        int start = 0;
        int end = arr.length - 1;
        int i = 0;
        while (start != end) {
            if (arr[i] < num) {
                arr2[start++] = arr[i];
            } else if (arr[i] > num) {
                arr2[end--] = arr[i];
            }
            i++;
        }
        arr2[start] = num;
        return arr2;
    }

    private static void partition1(int[] arr, int l, int r, int num) {
        int less = -1;
        int more = r + 1;
        while (less < more) {
            if (arr[l] < num) {
                swap(arr, ++less, l++);
            } else if (arr[l] > num) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
