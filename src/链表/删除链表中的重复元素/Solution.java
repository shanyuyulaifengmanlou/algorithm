package 链表.删除链表中的重复元素;

import java.util.*;

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

public class Solution {
    // 有两种做法，第一种是遍历两次，时间复杂度是  O-2N
    // public ListNode deleteDuplicates(ListNode head) {
    //     if (head == null) {
    //         return null;
    //     }
    //     HashSet<Integer> set = new HashSet<>();
    //     ListNode now = head;
    //     ListNode dummyHead = new ListNode(-111);
    //     dummyHead.next = head;
    //     ListNode prev = dummyHead;
    //     while (now != null) {
    //         if (prev.val == now.val) {
    //             set.add(now.val);
    //         }
    //         now = now.next;
    //         prev = prev.next;
    //     }
    //     now = head;
    //     prev = dummyHead;
    //     while (now != null) {
    //         if (set.contains(now.val)) {
    //             prev.next = now.next; // 进行了删除
    //         } else {
    //             prev = prev.next;
    //         }
    //         now = prev.next; // 无论如何这句都是成立的
    //     }
    //     return dummyHead.next;
    // }

    // 第二种做法是 只是遍历一次，但是要主要链表可能越界的问题，不能出现 null.val 这种行为
    // [1,2,3,3,3,3,4,4,5]
    // [1,2,3,3,4,4,5]
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-111111);
        dummyHead.next = head;
        ListNode now = dummyHead; // 这是最关键的：因为去除了一个 prev 变量，程序变得简单，并且 now 有办法操控删除链表中的所有任意一个节点
        while (now.next != null && now.next.next != null) {
            if (now.next.val == now.next.next.val) {
                int x = now.next.val;
                while (now.next != null && now.next.val == x) { // 不能出现空指针
                    now.next = now.next.next;
                    // now = now.next; // 不能这么操作，否则，now 节点就会失去对当前节点的控制能力
                }// 循环结束之后，所有重复节点就被删除了，当前节点已经不能往下挪，因为如果要能具备删除下个节点的能力，一定需要位于当前节点的前一个节点
            } else {
                now = now.next;
            }
        }
        return dummyHead.next;
    }
}
