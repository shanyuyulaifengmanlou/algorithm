package LRU;

import java.util.*;

class LRUCache {
    class ListNode { // 定义双向链表的数据结构
        int key;
        int value;
        ListNode prev;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        ListNode() {
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size; // 记录当前的数据量，确认是否需要删除队尾
    private int capacity;// 记录当前的容量
    private HashMap<Integer, ListNode> cached = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    // 1.如果在cache中不存在数据，那么直接返回，题目规定返回-1
    // 2.如果 cache 中存在，那么拿到数据之后需要将数据放到双向队列的头部
    public int get(int key) {
        if (cached.get(key) == null) {
            return -1;
        }
        ListNode node = cached.get(key);
        moveToHead(node);
        return node.value;
    }

    // 1.如果 cache 中存在相同的key，则需要覆盖这个 value，并且由于节点已经被操作过，所以需要移动到队头
    // 2.如果不存在，就创建一个节点，往队头里面添加，并且size ++，然后对比size和capacity
    // 如果已经超标，那么需要删除队尾元素，断开队尾还没完，需要将这个节点在缓存里面的数据给删除掉
    public void put(int key, int value) {
        if (cached.get(key) != null) { // 已经存在相同的key
            ListNode node = cached.get(key);
            node.value = value; // rewrite value
            moveToHead(node);
            cached.put(key, node);// 有可能上面修改的是引用
        } else { // 是新数据
            ListNode node = new ListNode(key, value);
            addToHead(node);
            cached.put(key, node); // 易错点：需要将数据添加到缓存中
            size++;
            if (size > capacity) {
                ListNode tailNode = removeTail();
                // 将这个元素从缓存中删掉（是map的原子操作）
                cached.remove(tailNode.key);
                // 删除元素之后需要将 size 设置回来
                size--;
            }
        }
    }

    // 梳理上述流程，可以发现，总共有几个单元函数需要进行实现的
    public ListNode removeTail() { // 从队尾删除元素,并且返回这个元素
        ListNode node = tail.prev;
        removeNode(node);
        return node;
    }

    public void moveToHead(ListNode node) { // 将节点移动到队头：总共需要两步骤：1.删除这个元素 2.然后添加这个元素到队头
        removeNode(node);
        addToHead(node);
    }

    public void addToHead(ListNode node) { // 实际上这个函数是被 moveToHead 用到，所以这里单元函数之间还有层级关系
        node.prev = head;
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
    }

    // 由于Java语言的特性，类是传引用，因此，外层函数如果需要这个节点，直接使用入参即可
    public void removeNode(ListNode node) { // 这里还衍生出一个单元函数，将这个节点删除
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // 这里有没有必要将 node  的 prev 指针给断开？
        // 因为外层如果用来将其添加到队头，那么实际上这两个指针会被重新设置
    }


    public static void main(String[] args) {

    }
}
