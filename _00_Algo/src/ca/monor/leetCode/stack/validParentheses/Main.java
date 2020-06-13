package ca.monor.leetCode.stack.validParentheses;

public class Main {
    public static void main(String[] args) {
        ValidParentheses test = new ValidParentheses();
        test.setParentheses();
        System.out.println(test.isValid());

        int s;
        int i = 0;
        s = i++;
        System.out.println(s);
        System.out.println(i);
    }
}
