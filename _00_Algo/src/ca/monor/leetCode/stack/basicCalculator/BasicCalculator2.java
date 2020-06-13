package ca.monor.leetCode.stack.basicCalculator;
/**
 * 改成只有加减法的
 */

import ca.monor.stack栈.Stack;

public class BasicCalculator2 {
    public int calculate(String s) {
        return evalRPN(toStringArray(toEvalRPN(s)));
    }

    private String[] toStringArray(Stack<String> tokens) {
        int size = tokens.size();
        String[] strings = new String[size];
        for (int i = size - 1; i >= 0; i--) {
            strings[i] = tokens.pop();
        }
        return strings;
    }

    public Stack<String> toEvalRPN(String s) {

        Stack<String> symbol = new Stack<>();
        symbol.push("#");
        Stack<String> eval = new Stack<>();
        s = s.replaceAll(" ", "");
        int i = 0;
        String empty = "";
        String number = "";
        while (i < s.length()) {

            if (Character.isDigit(s.charAt(i))) {
                number += s.charAt(i);
            } else {
                if (number != "") {
                    eval.push(number);
                    number = "";
                }
            }

            if (s.charAt(i) == '(') {
                symbol.push(s.charAt(i) + empty);
                //若取出的字符是“（”，则直接送入S1栈顶。

            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                while (!symbol.peek().equals("#") && !symbol.peek().equals("(")) {
                    eval.push(symbol.pop());
                }
                symbol.push(s.charAt(i) + empty);
                //如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，
                // 则将该运算符进S1栈，否则，将S1栈的栈顶运算符弹出，送入S2栈中，
                // 直至S1栈栈顶运算符低于（不包括等于）该运算符优先级，最后将该
                // 运算符送入S1栈。

            } else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                if (symbol.peek().equals("#") || symbol.peek().equals("+") || symbol.peek().equals("-")) {
                    symbol.push(s.charAt(i) + empty);  //如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，
                    // 则将该运算符进S1栈
                } else {
                    while (!symbol.peek().equals("#") && !symbol.peek().equals("(")) {
                        eval.push(symbol.pop());
                    }
                    symbol.push(s.charAt(i) + empty);
                }
            } else if (s.charAt(i) == ')') {
                while (!symbol.peek().equals("(")) {
                    eval.push(symbol.pop());
                }
                symbol.pop();
            }
            i++;
        }
        if (number != "") {
            eval.push(number);
            number = "";
        }
        while (!symbol.peek().equals("#")) {
            eval.push(symbol.pop());
        }
        return eval;
    }

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
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();              //弹出栈顶元素就是结果
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BasicCalculator calculator = new BasicCalculator();
        System.out.println(calculator.calculate("(1+(4+532+2)-3)+(6+8789)"));
        System.out.println(System.currentTimeMillis() - start);
    }
}
