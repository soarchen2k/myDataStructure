package ca.monor.mthree;

import java.util.Scanner;

/**
 * https://www.codingame.com/ide/puzzle/the-lucky-number
 *
 * A lucky number is a 10-based number, which has at least a "6"
 * or an "8" in its digits. However, if it has "6" and "8" at the
 * same time, then the number is NOT lucky. For example, "16", "38",
 * "666" are lucky numbers, while "234" , "687" are not.
 *
 * Now we want to know how many lucky numbers (without leading
 * zeroes) are there between L and R, inclusive?
 */
public class LuckyNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            long l = in.nextLong();
            long h = in.nextLong();
            int luckyNumbers = 0;
            for (long i = l; i <=h ; i++) {
                if (!isLuckyNumber(i)) {
                    System.out.println(i);
                }
                if (isLuckyNumber(i)) luckyNumbers++;
            }
            System.out.println(luckyNumbers);
        }
    }

    private static boolean isLuckyNumber(long i) {
        String string = Long.toString(i);

        if (string.contains("6") && string.contains("8")) {
            return false;
        }

        if (string.contains("6") || string.contains("8")) {
            return true;
        }
        return false;
    }
}
