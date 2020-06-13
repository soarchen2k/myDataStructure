package ca.monor.leetCode.linkedList.reverseLinkedList;

public class ReverseLinkedList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(val).append(" ").append(next).append(" ");

            return sb.toString();
        }
    }

    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = null;  // 新建一个node，newHead 新链表的 head，指向 null
        while (head != null) { // 当前链表 head 不为空则进行循环
            ListNode next = head.next;  // 用 ListNode next 记录原链表剩余元素
            head.next = newHead;  // 让 head.next 指向 newHead，断开 head 与 后面的联系
            newHead = head;  // 使 newHead 指向原先的 head
            head = next;  // next 记录了原链表剩余的元素，原链表的 head 此时就从原链表的剩余部分开始
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        System.out.println(listNode);
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        System.out.println(reverseLinkedList.reverseLinkedList(listNode));
    }
}
