package ca.monor.mthree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Marathon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String[] times = new String[N];

        if (N == 0) {
            System.out.println("");
            return;
        }

        for (int i = 0; i < N; i++) {
            String t = in.next();
            times[i] = t;
        }

        if (N == 1) {
            System.out.println(times[0]);
            return;
        }

        String bestOne = "23:59:59";
        for (String time : times) {
            try {
                if (compare(time, bestOne)) {
                    bestOne = time;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bestOne);
    }

    private static boolean compare(String time1, String time2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date a = sdf.parse(time1);
        Date b = sdf.parse(time2);
        return a.before(b);
    }
}
