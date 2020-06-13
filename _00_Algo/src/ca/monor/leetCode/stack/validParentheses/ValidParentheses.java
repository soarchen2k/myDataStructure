package ca.monor.leetCode.stack.validParentheses;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/solution/
 */

import ca.monor.stack栈.Stack;

import java.util.Scanner;

public class ValidParentheses {
    private Stack<Character> leftParentheses;
    private Scanner scanner;
    private String parentheses;

    public ValidParentheses() {
        this.leftParentheses = new Stack<>();
        this.scanner = new Scanner(System.in);
    }

    public void setParentheses() {
        System.out.print("Give some parentheses: ");
        this.parentheses = scanner.nextLine();
    }

    public boolean isValid() {

        for (int i = 0; i < this.parentheses.length(); i++) {
            char c = this.parentheses.charAt(i);
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
