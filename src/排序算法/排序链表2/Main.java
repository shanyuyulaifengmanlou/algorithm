package 排序算法.排序链表2;

public class Main {

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
    // pre 官方的题解有一种写的很丑——
    // 1.递归函数有左右区间，
    // 2.函数的终止条件有多个
    // 3.甚至在某些终止条件下，需要去判定是否需要断开链表
    // 4.需要推演不同的情况，链表剩下 4、3、2 个节点的时候，函数会在哪里终止
    // 5.需要推演函数如何保证找到中点之后，会把链表断开，否则，merge 的时候会乱掉

    // 函数入口
    // 归并排序，需要将数据一分为二，最后再进行 merge 合并，megre 的时候考虑按照从小到大的顺序来进行合并
    // 不用单独写函数，这个函数就是 力扣的入口函数，也就是递归函数
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next; // 最关键的setvalue，这种写法可以将函数断开，防止函数进入死循环
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;// 空值可以被判断，但是不能被调用
        }
        ListNode mid = slow.next;// 这里不会为空，需要从上面的终止条件和循环结束条件判断，最少两个节点的case才会走到后续的逻辑
        slow.next = null;// 必须断开，避免答案完全错误
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    public ListNode merge(ListNode p1, ListNode p2) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                node.next = p1;
                p1 = p1.next;
                node = node.next;
                continue;
            }
            node.next = p2;
            p2 = p2.next;
            node = node.next;
        }
        node.next = p1 != null ? p1 : p2;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
