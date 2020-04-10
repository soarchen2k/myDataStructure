package ca.monor.sort;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {

    }

    private static void mergeSort(int[] arr, int l, int r) {
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = Math.min(arr[p1++], arr[p2++]);
        }

        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {

        }
    }
}
