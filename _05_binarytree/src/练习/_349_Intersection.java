package 练习;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 * 说明：
 *
 *     输出结果中的每个元素一定是唯一的。
 *     我们可以不考虑输出结果的顺序。
 */

public class _349_Intersection {
    public static HashSet<Integer> toSet(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        return set;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = toSet(nums1);
        HashSet<Integer> set2 = toSet(nums2);

//        int size = nums1.length >= nums2.length ? nums2.length : nums1.length;
//        int[] res = new int[size];

        List<Integer> list = new ArrayList<>();

        Iterator<Integer> iterator1 = set1.iterator();
        Iterator<Integer> iterator2 = set2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next().equals(iterator2.next())) {
                list.add(iterator1.next());
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
