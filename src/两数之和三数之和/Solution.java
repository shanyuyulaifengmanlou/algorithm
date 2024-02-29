package 两数之和三数之和;

import java.util.*;

public class Solution {
    // 第一个版本，使用的是 hashmap 额外的空间，但是比较清晰
    // 这个版本的用时 和 内存 效果都比较差
    public int[] twoSum(int[] price, int target) {
        Map<Integer, Boolean> existMap = new HashMap<>();
        int len = price.length;
        int leftNum;
        for (int i = 0; i < len; i++) {
            leftNum = target - price[i];
            if (existMap.get(leftNum) != null) { // 如果不存在这个 key 那么返回的是 null
                return new int[]{leftNum, price[i]};
            }
            existMap.put(price[i], true);
        }
        return new int[]{};
    }

    // 第二个版本，不使用额外的空间，双指针，需要额外考虑
    // 耗时 就快很多
    public int[] twoSumByPointers(int[] price, int target) {
        int left = 0;
        int right = price.length - 1;
        int sum = 0;
        while (left < right) {
            sum = price[left] + price[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{price[left], price[right]};
            }
        }
        return new int[]{};
    }
    // 三数之和（改版成 target = 0 了，不过本质没有区别，可以手动将 0 改造成 target 即可）
    // 要求可能更加复杂一点，因为要求  三元组是不重复的，因此需要添加一些细节来保证没有重复

    // 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，
    // 使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。


    // 定义自定义 Comparator 如果有必要，就采用这种方式，作为 sort 函数的第二个参数
    // 但是不能直接使用，因为这里的 cmp 只能是
    // Comparator<Integer> cmp = new Comparator<Integer>() {
    //     @Override
    //     public int compare(Integer i1, Integer i2) {
    //         return i1 - i2;  // 正常的升序
    //     }
    // };

    public List<List<Integer>> threeSum(int[] nums) {
        // 大概的思路是，首先数据预处理，先排序，先固定其中给一个数字，然后其他数字，还是按照双指针的方式来移动
        // 由于每次固定其中一个数字，具体需要时间代价是 O-N 因此，总的时间复杂度是 O-N^2
        // 排序的复杂度是  O-NlogN
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Arrays.sort(nums); // 默认是按照从小到大进行排序
        int len = nums.length;
        int left, right, sum;

        for (int k = 0; k < len - 2; k++) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;// 不重复的三元组-细节1：相同的数字，之前的结果已经演算过了
            }
            left = k + 1;// 细节2： 之前的数字就不需要进行再次演算了，结构是对称的
            right = len - 1;
            while (left < right) {
                sum = nums[k] + nums[left] + nums[right];
                if (sum > 0) { // 可以是 target
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < 0) {
                    while (left < right && nums[left] == nums[++left]) ;
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[left], nums[right])));
                    // 从这个地方开始是 K神的理解
                    // 因为 left 和 right 会互相约束,所以不需要判读 left + 1 是否越界
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++; // 细节3
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--; // 细节4
                    }
                }
            }
        }
        return res;
    }


}