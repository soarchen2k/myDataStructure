package ca.monor.dynamicProgramming动态规划;

public class FindAimFromArray {
    // recursion
    private static boolean findAim1(int[] arr, int aim, int sum, int i) {
        if (i == arr.length) {
            return sum == aim;
        }

        return findAim1(arr, aim, sum, i + 1) || findAim1(arr, aim, sum + arr[i], i + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8, 9, 3};
        System.out.println(findAim1(arr, 2, 0, 0));
    }
}
