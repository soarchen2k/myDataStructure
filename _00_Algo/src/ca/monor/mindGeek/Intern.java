package ca.monor.mindGeek;

import java.util.*;

public class Intern {
    public static List<Integer> divisibleBy3(LinkedList<Integer> list) {
        List<Integer> list1 = new LinkedList<>();
        Random random1 = new Random();
        int i1 = list.get(random1.nextInt(list.size()));
        int i2 = list.get(random1.nextInt(list.size()));
        int i3 = list.get(random1.nextInt(list.size()));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % i1 == 0
                    && list.get(i) % i2 == 0
                    && list.get(i) % i3 == 0) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    public static int distinctValues(List<Integer> list) {
        HashSet<Integer> set = new HashSet<>();
        for (int a : list) {
            set.add(a);
        }
        return set.size();
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(5);
        list.add(3);
        System.out.println(distinctValues(list));
    }
}
