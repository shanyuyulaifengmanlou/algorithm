package 移除K位数字得到最小值;

public class Solution {
    // 题目：也可以改成最大值，判断符号反过来即可
    // https://leetcode.cn/problems/remove-k-digits/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

    public String removeKdigits(String num, int k) {
        // golang 中的 stringBuilder 就是一二胡卵子
        // 只能后续新增，不能做灵活删除
        int len = num.length();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) { // 也可以采用 for element: num 的方式
            while (!strBuilder.isEmpty() &&
                    strBuilder.charAt(strBuilder.length() - 1) > num.charAt(i) && k > 0) {
                // 后一个数字可以消除前面一个数字，前提是后一个数字比前面一个数字 小
                // 由于不允许前面的数字出现比后面数字大的，因此反过来，最终得到的结果是 单调递增的序列
                strBuilder.deleteCharAt(strBuilder.length() - 1);
                k--;
            }
            strBuilder.append(num.charAt(i));
        }
        // 如果还不够，就从最后一个开始删除，因为这是要求在单调递增的序列中，得到一个最小的数字
        while (k > 0) {
            strBuilder.deleteCharAt(strBuilder.length() - 1);
            k--;
        }
        // 最后还有特殊情况，如果有前导 0  ，那么应该删除
        while (strBuilder.length() > 0 && strBuilder.charAt(0) == '0') {
            strBuilder.deleteCharAt(0);
        }
        if (strBuilder.length() == 0) {
            return "0";
        }
        return strBuilder.toString();
    }
}
