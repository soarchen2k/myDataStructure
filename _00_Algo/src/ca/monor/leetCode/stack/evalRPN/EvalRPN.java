package ca.monor.leetCode.stack.evalRPN;

/**
 * 150. 逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */

import ca.monor.stack栈.Stack;

public class EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>(); // 定义一个栈

        for (String s : tokens) {
            if (s.equals("+")) {         //遇到 + 则弹出栈顶两个元素相加，并把结果入栈
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {  //遇到 - 则弹出栈顶 2 元素，并用后弹出的元素减去先弹出的元素，结果入栈
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {  //遇到 * 则弹出栈顶2元素相乘，结果入栈
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {  //遇到 / 弹出栈顶 2 元素，后弹出的元素除以先弹出的元素，结果入栈
                int b = stack.pop();
                stack.push(stack.pop() / b);
            } else {                     // 如未遇到运算符，将 s 的 int 值入栈
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();              //弹出栈顶元素就是结果
    }

    public static void main(String[] args) {
        EvalRPN test = new EvalRPN();
        String[] tokens = {"154", "275", "+", "387", "2", "-", "*"};
        System.out.println(test.evalRPN(tokens));
    }
}
