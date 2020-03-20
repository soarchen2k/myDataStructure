package ca.monor.greedy贪心;

import java.util.Arrays;

public class coinsChange {
    public static void main(String[] args) {
        Integer[] faces = {1, 5, 20, 25};

        System.out.println("Total Coins: " + coinsChange1(faces, 41));
        System.out.println("Total Coins: " + coinsChange2(faces, 41));
    }

    private static int coinsChange1(Integer[] faces, int restMoney) {
        Arrays.sort(faces);  // 1, 5, 10, 25
        int coins = 0;
        for (int i = faces.length - 1; i >= 0; i--) {
            if (restMoney < faces[i]) {
                continue;
            }
            System.out.println(faces[i]);
            restMoney -= faces[i];
            coins++;
            i = faces.length;
        }
        return coins;
    }

    private static int coinsChange2(Integer[] faces, int money) {
        Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
        int coins = 0;
        int pointer = 0;
        while (pointer<faces.length) {
            if (money < faces[pointer]) {
                pointer++;
                continue;
            }
            System.out.println(faces[pointer]);
            money -= faces[pointer];
            coins++;
        }
        return coins;
    }
}
