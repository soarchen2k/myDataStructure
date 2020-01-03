package ca.monor.binaryTree;
import ca.monor.binaryTree.printer.BinaryTrees;
import java.util.*;

public class Main {
    /**
     * 年龄的正序排列
     */
    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    /**
     * 年龄的逆序排列
     */
    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o2.getAge() - o1.getAge();
        }
    }

    private static Queue<Integer> creatQueue(int length) {
        Random random = new Random();
        Queue<Integer> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temp = random.nextInt(200);
            if (list.contains(temp)) {
                i--;
                continue;
            } else {
                list.offer(temp);
            }
        }
        return list;
    }
    private static BinarySearchTree<Integer> creatBST(int length) {
        Queue<Integer> list = creatQueue(length);

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        length = list.size();
        for (int i = 0; i < length; i++) {
            bst.add(list.poll());
        }
        return bst;
    }

    static BinarySearchTree<Integer> creatBSTArray() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        return bst;
    }

    static void test1(int length) {
        BinarySearchTree<Integer> bst = creatBST(length);
        BinaryTrees.println(bst);
        bst.preOrder();
        bst.inOrder();
        bst.postOrder();
        System.out.println("Height = " + bst.height());
    }

    static void test2(int length) {
        BinaryTrees.println(creatBST(length));
    }

    public static void main(String[] args) {
        test1(8);
    }
}
