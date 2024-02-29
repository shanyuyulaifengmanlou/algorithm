package 树.根据中序遍历和后序遍历构造二叉树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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

    private int postIndex;
    private int[] postOrder;
    private HashMap<Integer, Integer> inOrderVal2OrderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postOrder = postorder;
        postIndex = this.postOrder.length - 1;// 这个数组最后一个位置 (初始化必须完成)
        this.inOrderVal2OrderMap = new HashMap<>();
        int index = 0;// 一开始下标是0
        for (int value : inorder) {
            inOrderVal2OrderMap.put(value, index++);
        }
        return buildSon(0, inorder.length - 1);// 针对这个区间范围内的中序遍历的数组做构建
    }

    public TreeNode buildSon(int inLeft, int inRight) {
        if (inLeft > inRight) { // 这个区间已经没有任何数据
            return null;
        }
        int rootValue = this.postOrder[postIndex];
        TreeNode root = new TreeNode(rootValue);// 使用根节点的数值立马创还能一个节点
        postIndex--;
        int rootIndex = 0;
        if (inOrderVal2OrderMap.containsKey(rootValue)) {
            rootIndex = inOrderVal2OrderMap.get(rootValue);
        }
        // 这里需要注意的就是由于后序遍历是从 左 右 中 的顺序，因此从后序遍历最后一个节点获取的时候，总是先
        // 拿到右节点的数据，因此这里需要针对 root.right 先进行处理
        root.right = buildSon(rootIndex + 1, inRight); // left and right 决定了递归的深度
        root.left = buildSon(inLeft, rootIndex - 1);
        return root;
    }

    public void travel(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            System.out.print(now.val + " ");
            if (now.left != null) {
                queue.offer(now.left);
            }
            if (now.right != null) {
                queue.offer(now.right);
            }
        }
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        // int[] inOrder = new int[]{9, 3, 15, 20, 7};
        // int[] postOrder = new int[]{9, 15, 7, 20, 3};
        int[] inOrder = new int[]{15, 9, 10, 3, 20, 5, 7, 8, 4};
        int[] postOrder = new int[]{15, 10, 9, 5, 4, 8, 7, 20, 3};
        TreeNode root = test.buildTree(inOrder, postOrder);
        test.travel(root);
    }
}
