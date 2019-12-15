package ca.monor.arrayList;

public class testArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            integerArrayList.add(i);
        }
        integerArrayList.add(null);
        integerArrayList.add(7);
        System.out.println(integerArrayList.contains(9));
        System.out.println(integerArrayList.isEmpty());
        System.out.println(integerArrayList);
        System.out.println(integerArrayList.remove(integerArrayList.indexOf(null)));
        System.out.println(integerArrayList);
        integerArrayList.add(2,3);
        System.out.println(integerArrayList);
        integerArrayList.clear();
        System.out.println(integerArrayList.isEmpty());
    }
}
