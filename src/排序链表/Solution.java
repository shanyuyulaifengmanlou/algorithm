package 排序链表;

public class Solution {
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

    // 使用归并算法，归并排序堆链表的好处是，不需要额外申请辅助空间，空间代价是 O-1
    public ListNode sortList(ListNode head) {
        return sort(head, null);
    }

    // 可以不使用断开尾指针的做法，但是要传递一个尾指针进去递归函数，这种做法比较灵活和自然
    public ListNode sort(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null; // tail 会留给下一个区间的 sort 函数负责
            return head; // 如果不断开，后续在进行 merge 的时候会出现大问题
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail) {
            fast = fast.next;
            slow = slow.next;
            if (fast != tail) { // 因为有可能存在下一个节点就是 null 的情况
                fast = fast.next;
            }
        }
        ListNode mid = slow;// 快慢指针法取到中点
        ListNode p1 = sort(head, mid);
        ListNode p2 = sort(mid, tail);
        return mergeList(p1, p2);
    }

    // 归并算法中间的合并部分(两个链表已经有顺序了)
    public ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode now = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                now.next = list1;
                list1 = list1.next;
            } else {
                now.next = list2;
                list2 = list2.next;
            }
            now = now.next;
        }
        if (list1 != null) {
            now.next = list1;
        }
        if (list2 != null) {
            now.next = list2;
        }
        return dummy.next;
    }
}
