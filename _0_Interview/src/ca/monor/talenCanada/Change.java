package ca.monor.talenCanada;

class Change {
    long coin2 = 0;
    long bill5 = 0;
    long bill10 = 0;
}

class Solution {
    static Change optimalChange(long s){
        if (s < 2) {
            return null;
        }
        Change change = new Change();

        if (s >= 10) {
            change.bill10 = s / 10;
            s = s % 10;
        }

        if (s >= 5) {
            change.bill5 = s / 5;
            s = s % 5;
        }

        if (s >= 2) {
            change.coin2 = s / 2;
        }
        return change;
    }

    public static void main(String[] args) {
        long s = 11L;
        Change m = Solution.optimalChange(s);
        System.out.println(m.coin2);
        System.out.println(m.bill5);
        System.out.println(m.bill10);
    }
}