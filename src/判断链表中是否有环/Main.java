package 判断链表中是否有环;

public class Main {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 判断链表中是否存在环状结构
    public boolean hasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p2 != null) {
            if (p1.next == null || p1.next.next == null) {
                return false;
            }
            p1 = p1.next.next;
            if (p2.next == null) {
                return false;
            }
            p2 = p2.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }
}
