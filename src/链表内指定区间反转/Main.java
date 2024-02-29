package 链表内指定区间反转;

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}

public class Main {


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    // TODO：给定链表头和需求进行翻转的区间序号，要求进行链表翻转
    // 样例
    // {1,2,3,4,5},2,4
    // {1,4,3,2,5}
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        ListNode now = head;
        int count = 1;
        ListNode prev = head;
        ListNode Connect1 = null;
        ListNode Connect2 = null;
        while (now != null) {
            if (count < m) {
                count++;
                prev = now;
                now = now.next;
                continue;
            }
            if (count == m) {
                // 需要保存一下上一个节点
                Connect1 = prev;
                Connect2 = now;
            }
            ListNode tmp = now.next;
            if (count != m) {
                now.next = prev;
            }
            prev = now;
            now = tmp;
            if (count == n) {
                Connect1.next = prev;
                Connect2.next = now;
                break;
            }
            count++; // 补充一个 count ++
        }
        if (m == 1) {
            return prev;
        }
        return head;  // 需要纠正(在指定翻转区间的左端点就是头节点的时候需要进行区分
    }

    public static void main(String[] args) {
        // int []datas = new int []{9,-8,4,98,0,-666,314};
        int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int m = 4;
        int n = 4;
        int len = datas.length;
        ListNode fakeHead = new ListNode(-1);
        ListNode now = new ListNode(-1);
        fakeHead = now;
        for (int i = 0; i < len; i++) {
            now.next = new ListNode(datas[i]);
            now = now.next;
        }
        ListNode newTravel = fakeHead.next;
        System.out.println("原始序列：");
        while (newTravel != null) {
            System.out.print(newTravel.val + " ");
            newTravel = newTravel.next;
        }
        System.out.println("");
        Main test = new Main();
        System.out.println("逆转之后：");
        ListNode newList = test.reverseBetween(fakeHead.next, m, n);
        while (newList != null) {
            System.out.print(newList.val + " ");
            newList = newList.next;
        }
    }
}
