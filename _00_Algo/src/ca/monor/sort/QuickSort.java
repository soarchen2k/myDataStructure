package ca.monor.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 7, 1, 6, 4, 8};
        quickSort(arr);
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            quickSort(arr, left, right);
        }
    }
}
