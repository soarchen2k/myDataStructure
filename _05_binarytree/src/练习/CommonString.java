package 练习;

import java.util.ArrayList;

public class CommonString {
    public static String commonString(String s1, String s2) {
        ArrayList<Character> commonString = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j) && !commonString.contains(s1.charAt(i))) {
                    commonString.add(s1.charAt(i));
                }
            }
        }
        return commonString.toString();
    }

    public static void main(String[] args) {
        String s1 = "java is good";
        String s2 = "hello abstract";
        System.out.println(commonString(s1, s2));
    }
}
