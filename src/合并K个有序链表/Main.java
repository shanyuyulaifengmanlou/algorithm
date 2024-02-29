package 合并K个有序链表;

import java.util.List;
import java.util.PriorityQueue;

public class Main {
    class ListNode {
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

    public ListNode mergeKLists(ListNode[] lists) { // 假设总共有K 个子链表
        PriorityQueue<ListNode> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o1.val < o2.val) {
                return -1;
            }
            return 1;
        }));
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            ListNode now = queue.poll();
            head.next = now;
            head = head.next;
            if (now.next != null) {
                queue.offer(now.next); // 队列中最多同时会存在K个元素，在弹的过程塞入元素
                // 因为原来就是有序的，因此可以这么做
            }
        }
        return dummy.next;
    }

}
