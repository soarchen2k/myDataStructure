package ca.monor.leetCode.stack.removeDuplicates;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int repeatNum = nums[0];
        int newLength = nums.length;
        for (int i = 1; i < newLength; i++) {
            if (nums[i] == repeatNum) {
                if (newLength - 1 - i >= 0) System.arraycopy(nums, i + 1, nums, i, newLength - 1 - i);
                newLength--;
                i--;
            } else repeatNum = nums[i];
        }
        return newLength;
    }

    public static void main(String[] args) {
        RemoveDuplicates r = new RemoveDuplicates();
        int[] nums = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int length = r.removeDuplicates(nums);
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
