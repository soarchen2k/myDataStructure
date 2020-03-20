package ca.monor.greedy贪心.bag;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定几个字符串，按最小字典序组合字符串
 */
public class SmallestLexicographical {
    public static void main(String[] args) {
        String[] strings = {"abc", "ab", "bc", "ca"};
        String[] strings1 = {"b", "ba"};
        System.out.println(smallestLexicographical(strings1));
    }

    private static String smallestLexicographical(String[] strings) {
        Arrays.sort(strings, new MyComparator());
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            result += strings[i];
        }
        return result;
    }

    /**
     * 比较器是本题的重点
     */
    private static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            // String 自带比较器，并且是按字典序进行比较
            // 找单个字符串的最小字典序是错误的，而是需要找到待拼接的两个字符串，拼接后的最小字典序
            return (a + b).compareTo(b + a);
        }
    }
}
