package 树.前序遍历和中序遍历构造二叉树;

import java.util.*;

class TreeNode {
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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    HashMap<Integer, Integer> indexCache; // 要求每个元素的数值都是唯一的，否则会出现覆盖的情况
    int[] preorder;
    int dataLen;

    public TreeNode deduceTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.indexCache = new HashMap<>();
        int index = 0;
        for (int value : inorder) { // 用来存储 中序遍历 的每个元素的位置，方便定位出来具体是
            indexCache.put(value, index++);
        }
        int len = this.preorder.length;
        dataLen = len;
        if (len == 0) {
            return null;
        }
        return buildTree(0, len - 1, 0, len - 1);
    }


    // 入参：前序遍历的左下标，前序遍历的右下标
    //      中序遍历的左下标，中序遍历的右下标
    // 前序遍历的下标主要是用来定位第一个元素的，第一个元素就是根节点
    // indexCache 作为信息沟通的桥梁，存储了 中序遍历中节点在数组中的下标
    // 根据 中序遍历的下标，找到中间节点的位置，从而根据中序遍历的左下标，就能定位出来，左子树的节点个数（区间长度），以及 有右子树总共有多少个节点（区间位置）
    // 根据 区间长度 就能知道  前序遍历中左子树和右子树的下一个递归的 入参 ，大概结构是这样的 ：  根节点 | 左节点以及左节点后续的子节点 | 右子树

    // 递归的终止条件是   left == right (表明当前递归的函数中，只有一个节点，返回当前节点即可)
    public TreeNode buildTree(int preOrderLeft, int preOrderRight, int inOrderLeft, int inOrderRight) {
        // 防止一些越界情况（因为在递归中没有验证合法性）
        if (preOrderLeft >= dataLen || preOrderRight >= dataLen || inOrderLeft >= dataLen || inOrderRight >= dataLen || preOrderLeft > preOrderRight || inOrderLeft > inOrderRight) {
            return null;
        }

        int rootValue = preorder[preOrderLeft];
        TreeNode nowRoot = new TreeNode(rootValue);
        if (preOrderLeft == preOrderRight) {
            return nowRoot; // 构造一个节点
        }
        // 前序 ：    preOrderLeft 根 | 左子树 | 右子树 preOrderRight
        // 中序 ：    inOrderLeft 左子树 | 根（inOrderRootIndex） | 右子树 inOrderRight
        //  1. 前序 第一个元素就是 根节点
        int inOrderRootIndex = indexCache.get(rootValue);
        int leftLen = inOrderRootIndex - inOrderLeft; // 总共有多少个节点（左子树）

        nowRoot.left = buildTree(preOrderLeft + 1, preOrderLeft + inOrderRootIndex - inOrderLeft,
                inOrderLeft, inOrderRootIndex - 1); // 前序左  前序右  中序左  中序右
        nowRoot.right = buildTree(preOrderLeft + inOrderRootIndex - inOrderLeft + 1, preOrderRight,
                inOrderRootIndex + 1, inOrderRight); // 前序左  前序右 中序左  中序右
        return nowRoot;
    }
}