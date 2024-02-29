package 两个链表的相交节点;

public class Main {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptrOne = headA;
        ListNode ptrTwo = headB;
        if (headA == null || headB == null) {
            return null; // 后面的 for 循环无法覆盖这种情况，并且会陷入一种无法猜中情况的流转中
        }
        while (ptrOne != ptrTwo) {
            ptrOne = ptrOne == null ? headB : ptrOne.next;
            ptrTwo = ptrTwo == null ? headA : ptrTwo.next;
        }
        return ptrOne;// 返回其中一个节点
    }
}
