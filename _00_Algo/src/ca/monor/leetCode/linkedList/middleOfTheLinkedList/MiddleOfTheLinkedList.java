package ca.monor.leetCode.linkedList.middleOfTheLinkedList;

/**
 * 876. Middle of the Linked List
 * <p>
 * https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val=" + val + ", next=" + next;
        }
    }

    public ListNode middleNode(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) {
                break;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        MiddleOfTheLinkedList test = new MiddleOfTheLinkedList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println(test.middleNode(head));
    }
}