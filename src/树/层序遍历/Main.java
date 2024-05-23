package 树.层序遍历;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //    1
    //   2 3
    // 4 5 6 7
    // 中 4 2 5 1 6 3 7  左中右
    // 先 1 2 4 5 3 6 7  中左右
    // 后                左右中
    public static void travel(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            if (now.left != null) {
                queue.offer(now.left);
            }
            if (now.right != null) {
                queue.offer(now.right);
            }
            System.out.print(now.val + " ");
        }
        return;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Online IDE!! Happy Coding :)");
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Main test = new Main();
        test.travel(node1);

    }
}
