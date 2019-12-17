package ca.monor.list;

public class Test {
    public static void main(String[] args) {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
//        long start = System.currentTimeMillis();
//        System.out.println(System.currentTimeMillis() - start);
        System.out.println("removed: " + list.remove(9) + ": " + list);
        System.out.println("removed: " + list.remove(0) + ": " + list);
        System.out.println("removed: " + list.remove(3) + ": " + list);
        list.add(0, 29);
        System.out.println(list);
        list.add(3, 39);
        System.out.println(list);
        list.add(5, 999);
        System.out.println(list);
        list.add(1, 987);
        System.out.println(list);
        System.out.println(list.indexOf(2));
    }
}
