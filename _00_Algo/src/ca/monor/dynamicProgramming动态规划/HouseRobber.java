package ca.monor.dynamicProgramming动态规划;

/**
 * https://leetcode.com/problems/house-robber/
 * 198. House Robber
 *
 * https://leetcode-cn.com/problems/house-robber/
 * 198. 打家劫舍
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int first = nums[0];
        if (nums.length == 1) {
            return first;
        }

        int second = Math.max(first, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums1 = {2, 7, 9, 3, 1, 6, 7};
        int[] nums2 = {2, 7, 3, 9, 1, 1, 15,6};
        System.out.println(houseRobber.rob(nums1));
        System.out.println(houseRobber.rob(nums2));
    }
}
