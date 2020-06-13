package ca.monor.leetCode.linkedList.deleteNode;

/**
 * 237. 删除链表中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */

public class DeleteNode {
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

    public void deleteNode(ListNode node) {
        node.val = node.next.val; // 让 node 的值等于下一个 node 的值
        node.next = node.next.next;  //让 node 的 next 指向 next 的 next，也就是断开 node 与 next 的关系，
        // 这样操作后原node的值被next的值覆盖，原node就相当于node.next，也就是原node被删除
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(5);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(9);
        DeleteNode deleteNode = new DeleteNode();
        System.out.println(node);
        deleteNode.deleteNode(node.next);
        System.out.println(node);

    }
}
