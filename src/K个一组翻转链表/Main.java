package K个一组翻转链表;

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


    // 按照K个元素作为一组，进行翻转，最后一组如果不满 K个，那么保留原样
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode start = head;
        ListNode end = dummy; // 一开始 end 要赋值成这个head 前面一个元素，因为需要和 k 个保持一致
        ListNode prev = dummy;// 这个节点用来链接前后区间，也就是说， prev 主要是会连接到下一个调用reverse 函数得到的 start
        ListNode next = null;// 这个主要用来保存带翻转链表的第一个元素，方便上一个区间完成之后可以链接到
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {// 如果 end 都等于空了，说明当前周期不满足k个元素
                break; // 跳出之后直接进行结算即可
            }
            start = prev.next;// 赋值
            next = end.next;// 先保存一下
            end.next = null;// 这点非常重要，很容易出错，因为如果不断开，那么这条链表
            // 调用  reverse 函数，直接全部进行翻转了, 会出现严重的错误
            prev.next = reverse(start); // 当上一个区间已经独立，也就是 end.next 已经设置 null，就可以进行翻转了
            start.next = next;
            prev = start;
            end = start;
        }
        return dummy.next;
    }

    // reverse 给定头结点，将这个链表进行翻转
    // TODO： 这个函数可以实现简单的单测
    public ListNode reverse(ListNode head) {
        ListNode now = head;
        ListNode next = null;
        ListNode prev = null;
        while (now != null) {
            next = now.next;
            now.next = prev;
            prev = now;
            now = next;
        }
        return prev;
    }

    public void travel(ListNode head) {
        ListNode now = head;
        while (now != null) {
            System.out.print(now.val + " ");
            now = now.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListNode dummy = new ListNode(-1);
        ListNode now = dummy;
        for (int i = 0; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            now.next = node;
            now = now.next;
        }
        Main test = new Main();
        test.travel(dummy.next);
        ListNode newHead = test.reverseKGroup(dummy.next, 3);
        test.travel(newHead);
    }

}
