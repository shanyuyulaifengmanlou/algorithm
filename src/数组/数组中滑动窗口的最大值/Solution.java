package 数组.数组中滑动窗口的最大值;

import com.sun.jdi.IntegerValue;

// https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/

import java.util.*;

class Solution {
    // 核心目的是返回 滑动窗口中的最大值，不过优先队列默认是 小顶堆，所以要进行调整
    // 并且其实优先队列内置的每一次调整堆的时间复杂度是 OlogK ， 并且由于要进行 N次的入堆和出堆，所以 时间复杂度是 2N*logK
    // public int[] maxAltitude(int[] heights, int limit) {
    //     if (heights.length == 0 || limit == 0) {
    //         return new int[]{};
    //     }
    //     // 大顶堆
    //     PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
    //         @Override
    //         public int compare(Integer o1, Integer o2) {
    //             return o2 - o1;
    //         }
    //     });
    //     ArrayList<Integer> list = new ArrayList<>();
    //     for (int i = 0; i < limit; i++) {
    //         queue.offer(heights[i]); // 初始化几个数据
    //     }
    //     list.add(queue.peek());
    //     int len = heights.length;
    //     for (int i = limit; i < len; i++) {
    //         queue.remove(heights[i - limit]);
    //         queue.add(heights[i]);
    //         list.add(queue.peek());
    //     }
    //     return list.stream().mapToInt(k -> k).toArray();
    // }

    // 测试
    // 用单调栈的思路（只不过这是单调队列） 并且需要注意的内容是这个队列需要主动删除元素
    // 比如 28 25 26 24 ，limit = 3 挪到24为止的时候，需要主动删除队列中的 28 元素，而且需要注意一定的技巧
    // 另外，如果 28 刚好是最大值，删除就顺理成章，因为从队列头部删除就行
    // 但是如果要删除的数字不是最大值，那么无关紧要，因为语意上是一样的，最大值还在，就能得到结果，至于它删除的时间，应该是后续出现了比它大的数字
    // 另外不需要担心，一个数字被跳过了删除的机会，后续无法再次被删除，导致数据出现了差错
    // 因为 比如 28 22 29 27， 数据入队的过程中， 22 就会因为 29 的存在而被删掉
    public int[] maxAltitude(int[] heights, int limit) {
        int len = heights.length;
        if (len == 0 || limit == 0) {
            return new int[]{};
        }
        Deque<Integer> queue = new LinkedList<>(); // 链表的实现
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            while (!queue.isEmpty() && queue.getLast() < heights[i]) {
                queue.removeLast();// 删除最后一个元素
            }
            queue.offerLast(heights[i]);
        }
        res.add(queue.getFirst());
        for (int i = limit; i < len; i++) {
            if (!queue.isEmpty() && queue.getFirst() == heights[i - limit]) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.getLast() < heights[i]) {
                queue.removeLast();
            }
            queue.offerLast(heights[i]);
            res.add(queue.getFirst());
        }
        return res.stream().mapToInt(k -> k).toArray();
    }

    // 最后总结一下这两种方法的区别
    // 第一个方法的好处就是简单明了，事情都交给 底层数据结构来，坏处就是需要记住  priorityqueue 的实例化方式 new Comparator public int compare 这个方法
    // 第二个方法的好处是 时间复杂度是 O-N 相比上一个的 ON*logK 优化了一些（但是不多
    // 优先选择第一个方法，因为容易记忆，相反，双端队列这种数据结构需要记忆的方法比 前者多

    public static void main(String[] args) {
        Solution s = new Solution();
        s.maxAltitude(new int[]{}, 3);
    }
}
