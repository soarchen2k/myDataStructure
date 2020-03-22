package ca.monor.divideAndConquer分治;

public class GetMax {
    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 10, 7};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }

    private static int getMax(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int mid = (left + right) / 2;
        int leftMax = getMax(arr, left, mid);
        int rightMax = getMax(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }
}
