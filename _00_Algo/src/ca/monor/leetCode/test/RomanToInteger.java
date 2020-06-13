package ca.monor.leetCode.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {

    public int romanToInt(String s) {
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        int sum = roman.get(s.charAt(s.length() - 1));
        if (s.length() > 1) for (int i = s.length() - 2; i >= 0; i--) {
            sum = roman.get(s.charAt(i)) >= roman.get(s.charAt(i + 1)) ?
                    sum + roman.get(s.charAt(i)) : sum - roman.get(s.charAt(i));
        }
        return sum;
    }

    public static void main(String[] args) {
        RomanToInteger roman = new RomanToInteger();
        int sum = roman.romanToInt("DCXXI");
        System.out.println(sum);
    }
}
