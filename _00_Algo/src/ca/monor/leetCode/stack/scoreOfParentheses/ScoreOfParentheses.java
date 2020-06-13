package ca.monor.leetCode.stack.scoreOfParentheses;

import ca.monor.stack栈.Stack;

/**
 * 856. 括号的分数
 * https://leetcode-cn.com/problems/score-of-parentheses/
 */

public class ScoreOfParentheses {

    public int scoreOfParentheses(String s) {
        Stack<Integer> parentheses = new Stack<>();
        parentheses.push(0);
        char[] chars = s.toCharArray();
        int length = s.length();
        int score = 0;
        for (int i = 0; i < length; i++) {

            if (chars[i] == '(') {
                parentheses.push(0);
            } else if (chars[i] == ')') {
                int v = parentheses.pop();
                int w = parentheses.pop();
                score = w + Math.max(v * 2, 1);
                parentheses.push(score);
            }
        }
        return score;
    }
}
