package ca.monor.leetCode.linkedList.removeLinkedListElements;

/**
 * 203. Remove Linked List Elements
 * <p>
 * https://leetcode.com/problems/remove-linked-list-elements/
 * <p>
 * 自己写的解法，无法删除尾节点，
 */
public class RemoveLinkedListElements {

    public static class ListNode {
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

    static class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode node = head;
            while (node.val != val) {
                node = node.next;
            }
            if (node.next != null) {
                node.val = node.next.val;
                node.next = node.next.next;
            } else {
                node = null;
            }
            return node;
        }

        public void add(ListNode head, int element) {

        }
    }

    static class Solution2 {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null)
                return null;
            head.next = removeElements(head.next, val);
            if (head.val == val) {
                return head.next;
            } else {
                return head;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        Solution remover = new Solution();
        remover.removeElements(head, 1);
        System.out.println(head);

    }
}
