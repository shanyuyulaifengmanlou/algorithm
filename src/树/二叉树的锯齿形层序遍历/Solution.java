package 树.二叉树的锯齿形层序遍历;

import 两个链表的相交节点.Main;

import java.util.*;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //    1
    //  2   3
    // 9   19 6
    // 上面这个树的输出内容是 1 3 2 9 19 6
    // 要求进行 之 字遍历，难点在于和普通的层序遍历不一样，写队列不是无脑地写，写完一层就得停下来
    // 原因在于上一层的数据要及时进行刹车，因为下一层到底有多少个节点，就靠队列中长度来判定
    // 也就是说，到了第几个元素，在 入队的时候就需要将顺序逆反过来了，可以依靠队列中数据长度
    // 这个巧妙构思来判定
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>(); // 返回值是一个二级的列表，每个子列表都代表一层数据
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);
        boolean left2Right = true;
        int levelSize = 0;
        while (!levelQueue.isEmpty()) {
            levelSize = levelQueue.size(); // 现在这层总共有多少个函数
            Deque<Integer> data = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) { // 每次只会拿出上一层的节点个数
                TreeNode now = levelQueue.poll();
                if (now == null) {
                    continue;
                }
                if (now.left != null) {
                    levelQueue.offer(now.left);
                }
                if (now.right != null) {
                    levelQueue.offer(now.right);
                }
                if (left2Right) {
                    data.offer(now.val);
                } else {
                    data.offerFirst(now.val);
                }
            }
            res.add(new LinkedList<>(data));
            left2Right = !left2Right;
        }
        return res;
    }
}

// 这种写法更加简洁，封装出了一个函数
// class Solution {
//     public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//         Queue<TreeNode> travel  =  new LinkedList<>();
//         List<List<Integer>> res = new LinkedList<>();
//         if (root == null) {
//             return res;
//         }
//         travel.offer(root);
//         int levelSize = 0 ;
//         boolean leftOrRight = false;
//         Deque<Integer> level  = new LinkedList<>();
//         level.offerFirst(root.val);
//         res.add(new ArrayList<>(level));
//         while(!travel.isEmpty()) {
//             levelSize = travel.size();
//             leftOrRight = !leftOrRight;
//             level  = new LinkedList<>();
//             while(levelSize -- > 0) {
//                 TreeNode node = travel.remove();
//                 if (node.left != null) {
//                     travel.offer(node.left);
//                     pushQueueDue2Direction(level,leftOrRight,node.left);
//                 }
//                 if (node.right != null) {
//                     travel.offer(node.right);
//                     pushQueueDue2Direction(level,leftOrRight,node.right);
//                 }
//             }
//             if(level.size()!= 0 ){
//                 res.add(new ArrayList<>(level));
//             }
//         }
//         return res;
//     }
//
//     public void pushQueueDue2Direction(Deque <Integer> level,boolean leftOrRight,TreeNode node) {
//         if(leftOrRight){
//             level.offerFirst(node.val);
//         }else{
//             level.offerLast(node.val);
//         }
//     }
// }

