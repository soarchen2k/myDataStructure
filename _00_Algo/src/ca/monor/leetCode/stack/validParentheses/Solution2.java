package ca.monor.leetCode.stack.validParentheses;

import ca.monor.stack栈.Stack;

public class Solution2 {
    public boolean isValid(String s) {
        Stack<Character> leftParentheses = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {  // 用单引号包裹 char 划重点
                // 左括号一律进Stack
                leftParentheses.push(c);
            } else {  // 否则就是右括号
                if (leftParentheses.isEmpty()) return false;  //栈为空，也就是第一个就是右括号
                char left = leftParentheses.pop();            //遇到右括号就弹栈，右与左配对则继续判断
                if (left == '(' && c != ')') return false;    //右与左不配对则返回 false
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
            }
        }
        return leftParentheses.isEmpty();   //如果配对完成，则栈一定为空
    }
}
