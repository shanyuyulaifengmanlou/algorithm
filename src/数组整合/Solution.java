package 数组整合;

import java.util.ArrayList;

/*
实现函数，将两个整型升序数组合并成一个没有重复数字的整型升序数组
要求：
1. 输入数组不可修改，数组内，数组间可能存在重复数字
2. 不可手写或使用第三方的经典排序算法
3. 函数的signature足够调用方在调用后获得数组内容及其长度信息。
4. 保证正确的前提下，时间开销和空间开销要尽量小
5. 时间 15 ~ 20 分钟
示例：
arr1: [ 1, 1, 2, 3, 5 ]
arr2: [ 2, 4, 4, 5, 150 ]
res : [ 1, 2, 3, 4, 5, 150 ]
*/
public class Solution {
    public void Merge(int[] arr1, int[] arr2) {
        ArrayList<Integer> res = new ArrayList<>();
        int len1 = arr1.length;
        int i = 0;
        int len2 = arr2.length;
        int j = 0;
        int tmp = 0;
        while (i < len1 && j < len2) {
            if (res.size() == 0) {
                res.add(arr1[i]);
                i++;
            }
            if (arr1[i] < arr2[j]) {
                tmp = arr1[i];
                if (res.get(res.size() - 1) != tmp) {
                    res.add(tmp);
                }
                i++;
            } else {
                tmp = arr2[j];
                if (res.get(res.size() - 1) != tmp) {
                    res.add(tmp);
                }
                j++;
            }
        }
        if (j == len2) {
            for (; i < len1; i++) {
                tmp = arr1[i];
                if (res.get(res.size() - 1) != tmp) {
                    res.add(tmp);
                }
            }
        }

        if (i == len1) {
            for (; j < len2; j++) {
                tmp = arr2[j];
                if (res.get(res.size() - 1) != tmp) {
                    res.add(tmp);
                }
            }
        }
        // todo: TRAVEL
        for (int ii = 0; ii < res.size(); ii++) {
            System.out.print(res.get(ii) + " ");
        }
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        // res : [ 1, 2, 3, 4, 5, 150 ]
        int[] arr1 = new int[]{1, 1, 2, 3, 5};
        int[] arr2 = new int[]{2, 4, 4, 5, 150};
        test.Merge(arr1, arr2);
    }
}
