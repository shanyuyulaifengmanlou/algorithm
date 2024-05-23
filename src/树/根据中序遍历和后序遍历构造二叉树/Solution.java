package 树.根据中序遍历和后序遍历构造二叉树;

import java.util.HashMap;

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
    private int[] inOrder;
    private HashMap<Integer, Integer> inOrderVal2OrderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postOrder = postorder;
        postIndex = this.postOrder.length - 1;// 这个数组最后一个位置 (初始化必须完成)
        this.inOrder = inorder;
        this.inOrderVal2OrderMap = new HashMap<>();
        int index = 0;// 一开始下标是0
        for (int value : inorder) {
            inOrderVal2OrderMap.put(value, index++);
        }
        return buildSon(0, inorder.length - 1);// 针对这个区间范围内的中序遍历的数组做构建
    }

    public TreeNode buildSon(int inLeft, int inRight) {
        int rootValue = this.postOrder[postIndex];
        TreeNode root = new TreeNode(rootValue);// 使用根节点的数值立马创还能一个节点
        postIndex--;
        int rootIndex = 0;
        if (inOrderVal2OrderMap.containsKey(rootValue)) {
            rootIndex = inOrderVal2OrderMap.get(rootValue);
        }
        root.right = buildSon(rootIndex + 1, inRight);
        root.left = buildSon(inLeft, rootIndex - 1);
        return root;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        test.buildTree(inOrder, postOrder);
    }
}
