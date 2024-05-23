package 链表两数相加;

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
    // 链表中两个数字相加，一个链表代表一个数字，每个位置代表一个个位，链表是逆序的
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode now = dummy;
        int carry = 0;
        int value1;
        int value2;
        int sum;
        while (l1 != null || l2 != null) { // 其中一个不是空
            value1 = l1 == null ? 0 : l1.val;
            value2 = l2 == null ? 0 : l2.val;
            sum = value1 + value2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            now.next = new ListNode(sum);
            now = now.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            now.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
