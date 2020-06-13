package ca.monor.leetCode.linkedList.josephusProblem;

import java.util.Scanner;

public class JosephusProblem {
    void JosephusProblem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many peoples? ");
        int people = Integer.parseInt(scanner.nextLine());
        System.out.print("How many to kill? ");
        int kill = Integer.parseInt(scanner.nextLine());

        JosephusLinkedList peoples = addPeople(people);
        peoples.reset();

        kill(kill, peoples);
    }

    private void kill(int kill, JosephusLinkedList peoples) {
        while (!peoples.isEmpty()) {
            for (int i = 0; i < kill - 1; i++) {
                peoples.next();
            }
            peoples.remove();
        }
    }

    private JosephusLinkedList addPeople(int people) {
        JosephusLinkedList list1 = new JosephusLinkedList();
        for (int i = 1; i <= people; i++) {
            list1.add(i);
        }
        return list1;
    }

    public static void main(String[] args) {
        JosephusProblem run = new JosephusProblem();
        run.JosephusProblem();
    }
}
