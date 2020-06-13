package ca.monor.leetCode.stack.scoreOfParentheses;

public class Main {
    public static void main(String[] args) {
        ScoreOfParentheses score = new ScoreOfParentheses();
        String parentheses = "(((())(()))())";
        System.out.println(score.scoreOfParentheses(parentheses));
    }
}
