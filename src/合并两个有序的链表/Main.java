package 合并两个有序的链表;

public class Main {

    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode Merge(ListNode pHead1, ListNode pHead2) {
        // write code here
        ListNode dummy = new ListNode(-1);
        ListNode now = dummy; // 后续遍历的时候使用 now.next = 指向的那个值
        while (pHead1 != null || pHead2 != null) {
            if (pHead1 != null && pHead2 != null) {
                if (pHead1.val <= pHead2.val) {
                    now.next = pHead1;
                    pHead1 = pHead1.next;
                    now = now.next;// 进行结算
                } else {
                    now.next = pHead2;
                    pHead2 = pHead2.next;
                    now = now.next;
                }
                continue;
            }
            if (pHead1 == null) {
                while (pHead2 != null) {
                    now.next = pHead2;
                    pHead2 = pHead2.next;
                    now = now.next;
                }
                break;
            }
            if (pHead2 == null) {
                while (pHead1 != null) {
                    now.next = pHead1;
                    pHead1 = pHead1.next;
                    now = now.next;
                }
                break;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
