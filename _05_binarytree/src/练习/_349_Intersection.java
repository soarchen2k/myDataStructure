package 练习;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
