package ca.monor.leetCode.stack.removeDuplicates;

/**
 * 189. 旋转数组
 * https://leetcode-cn.com/problems/rotate-array/
 */

public class Rotate {
    public void rotate(int[] nums, int k) {
        if (nums.length == 0 || nums.length == 1 || k < 0 || k == nums.length) {
            return;
        }

        if (k > nums.length) k = k % nums.length;

        for (int i = 0; i < k; i++) {
            int tmp = nums[nums.length - 1];
            for (int j = nums.length - 1; j >= 1; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = tmp;
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 1;
        rotate.rotate(arr, k);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
