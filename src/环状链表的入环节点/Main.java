package 环状链表的入环节点;

public class Main {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 检测出其中的环，并且将入环节点进行返回，如果没有环，那么返回 null
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        if (fast == null || fast.next == null || fast.next == null) {
            return null;
        }
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast != slow) {
            return null;// 不存在环状
        }
        ListNode newStep = head;
        while (newStep != slow) {
            newStep = newStep.next;
            slow = slow.next;
        }
        return newStep;
    }
}
