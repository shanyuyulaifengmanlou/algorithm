package 删除链表中倒数第N个节点;

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

public class Main {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        int index = n; // would be negative
        ListNode now = head;
        while (now != null) {
            now = now.next;
            index--;
        }
        if (index == 0) {
            return head.next;
        }
        ListNode pre = head;
        while (index < -1) { // 需要往前找前面一个节点
            index++;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        int[] value = new int[]{1, 2, 3, 4, 5};
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        for (int i = 0; i < value.length; i++) {
            ListNode now = new ListNode(value[i]);
            head.next = now;
            head = head.next;
        }
        Main test = new Main();
        ListNode newHead = test.removeNthFromEnd(dummy.next, 5);
        // ListNode node = dummy.next;
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
