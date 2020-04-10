package ca.monor.sort;

import java.util.Arrays;

/**
 * 插入排序：打扑克
 */

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 3, 7, 9};
        int[] arr2 = {3, 1};
        insertSort2(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insert = arr[i];
            int j = i - 1;
            while ((j >= 0) && (arr[j] > insert)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insert;
        }
    }

    private static void insertSort2(int[] arr) {
        if (arr.length == 2 && arr[0] > arr[1]) {
            swap(arr, 0, 1);
        }

        if (arr.length > 2) {
            for (int i = 1; i < arr.length - 1; i++) {
                for (int j = i; j >= 0; j--) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int i1) {
        int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }
}
