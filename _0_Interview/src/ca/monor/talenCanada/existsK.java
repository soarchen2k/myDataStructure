package ca.monor.talenCanada;

import java.util.Arrays;

/**
 * 判断一个已排序的数组中是否包含 K
 */

public class existsK {
    public static void main(String[] args) {
        int[] ints = {-9, 14, 37, 102, 150};
        for (int a : ints) {
            System.err.println(a);
        }
//        System.out.println(exists(ints, 25));
//        System.out.println(exists(ints, 150));
//        System.out.println(exists(ints, 5));
    }

    static boolean existsK(int[] ints, int k) {
        // 调用自定义 binarySearch 方法来查找
//        return existsK(ints, 0, ints.length - 1, k);

        // 调用 Arrays API 来判断
        return Arrays.binarySearch(ints, k) >= 0;
    }

    private static boolean existsK(int[] ints, int start, int end, int k) {
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (ints[mid] == k) {
                return true;
            } else if (ints[mid] > k) {
                end = mid - 1;
            } else if (ints[mid] < k) {
                start = mid + 1;
            }
        }
        return false;
    }

}
