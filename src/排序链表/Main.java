package 排序链表;

import java.util.*;

public class Main {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        PriorityQueue queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;// 返回 -1 的时候不会进行 swap，维持现在 o1.val  < o2.val 的顺序
            }
        });

        while (head != null) {
            queue.offer(head);
            head = head.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode now = dummy;
        while (!queue.isEmpty()) {
            ListNode node = (ListNode) queue.poll();
            now.next = node;
            now = now.next;
        }
        now.next = null;// 把最后一个元素给断开，防止有环出现
        return dummy.next;
    }
}
