package ca.monor.leetCode.set.intersectionOfTwoArrays;

/**
 * 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/
 */

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = getNewSet(nums1);
        Set<Integer> set2 = getNewSet(nums2);
        set1.retainAll(set2);
        return getNewArray(set1);
    }

    private int[] getNewArray(Set<Integer> set) {
        //把 set 转为 array
        int[] arr = new int[set.size()];
        int index = 0;
        for (int value :
                set) {
            arr[index++] = value;
        }
        return arr;
    }

    private Set<Integer> getNewSet(int[] nums) {
        //把 array 转为 set
        Set<Integer> set = new HashSet<>();
        for (int value :
                nums) {
            set.add(value);
        }
        return set;
    }
}
