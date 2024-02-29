package 动态规划.最大和子区间;

// https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
class Solution {
    public int maxSales(int[] sales) {
        int len = sales.length;
        if (len == 0) {
            return 0;
        }
        // 最终结果是在  整个动态规划过程中得出的，不一定就是 以最后一个元素作为结尾的区间和是最大的
        int res = sales[0];// 初始化成其中一个结果即可
        int[] dp = new int[len + 1];
        dp[1] = sales[0];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i - 1] + sales[i - 1], // 注意当前值是  sales[i-1] 而不是 sales[i] 注意越界问题
                    sales[i - 1] // 因为区间要求的连续性，所以如果放弃前面一个数字， 就等于放弃了前面 i-1 个数字
            ); // 转移方程不能写错，第二个选项绝对不是 dp[i-1]  因为以 i 元素结尾的区间一定要包含 i元素（下标 i-1）
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}