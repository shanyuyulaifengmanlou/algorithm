package 树.序列化和反序列化;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    // Encodes a tree to a single string.
    // 最终结果是输出  [1,1,3,45,null] 类似这种格式，需要有中括号和逗号
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        // 层序遍历
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        q.offer(root);
        // 有的层序遍历是需要记住每一层的 size 的，必须之字型打印，因为可能每一轮的内容要求多变
        // 在这道题中不用
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            // 这么写也不对，因为节点可能是空 不能直接访问 .val
            // sb.append(node.val + ",");
            // 这么写是会有大问题的，因为 如果是空，也会要求补充一个 null,所以直接入队即可
            // if (node.left != null) {
            //     q.offer(node.left);
            // }
            // if (node.right != null) {
            //     q.offer(node.right);
            // }
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val + ",");
                // 直接入队
                q.offer(node.left);
                q.offer(node.right);// 如果是这样写，最后一层的节点一定会有冗余的 NULL
                // 比如例子
                // 1 ，2 ，3， 4，N ,5 ,6 , N ,N , N ,7, 8, N  这里的  7 8  一定会有额外的 NULL 在冗余阶段 也就是
                // 1 ，2 ，3， 4，N ,5 ,6 , N ,N , N ,7, 8, N,N ,N
            }
        }
        sb.deleteCharAt(sb.length() - 1); // 删除最后一个逗号
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // 假设存在这么一个输出
    // 1 ，2 ，3， 4，N ,5 ,6 , N ,N , N ,7, 8, N,N ,N
    public TreeNode deserialize(String data) {
        StringBuilder sb = new StringBuilder(data);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        if (sb.isEmpty()) {
            return null;
        }
        // stringbuilder 没有 split 方法，string 有
        String[] numStrs = sb.toString().split(",");
        int len = numStrs.length;
        TreeNode root = new TreeNode(Integer.parseInt(numStrs[0]));
        int i = 1; // 表示下标 0 位置的元素已经被领取走
        // 同样也需要一个队列来记录
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 大概的原理就是，每一个 从 queue 中出来的节点，都会从数组中按照顺序领取走两个节点，这两个节点有可能都是null 也可能只有一个
        // 唯一需要注意的就是，如果是 N 节点，不能入队，主要是为了避免空节点出现子节点这种情况出现（而层序遍历是不会记录这种情况的
        // 因为一旦入队，就会从数组的后续中领走元素，为了避免后续的问题， N 节点已经不能入队
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i < len && !numStrs[i].equals("null")) { // 如果是空就没有必要  node.left = null 因为初始化时已经是空
                node.left = new TreeNode(Integer.parseInt(numStrs[i]));
                // 层序遍历必须入队
                queue.offer(node.left);
            }
            i++;// 已经领走了一个位置
            if (i < len && !numStrs[i].equals("null")) { // Java 语言中的 字符串相等比较需要使用内置方法
                node.right = new TreeNode(Integer.parseInt(numStrs[i]));
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
}
