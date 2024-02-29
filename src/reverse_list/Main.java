package reverse_list;

import java.util.*;

//题目：翻转一个链表
public class Main {

    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode ReverseList(ListNode head) {
        // write code here
        if (head == null) {
            return head;
        }
        ListNode Now = head;
        ListNode Tmp;
        ListNode Prev = null;
        while (Now != null) {
            System.out.println(Now.val);
            Tmp = Now.next;
            Now.next = Prev;
            Prev = Now;
            Now = Tmp;
        }
        return Prev;
    }
}
