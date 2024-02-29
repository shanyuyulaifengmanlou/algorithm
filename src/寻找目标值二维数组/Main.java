package 寻找目标值二维数组;

// m*n 的二维数组 plants 记录了园林景观的植物排布情况，具有以下特性：
//
// 每行中，每棵植物的右侧相邻植物不矮于该植物；
// 每列中，每棵植物的下侧相邻植物不矮于该植物。
//
// 请判断 plants 中是否存在目标高度值 target。
//
// 示例 1：
// 输入：plants = [[2,3,6,8],[4,5,8,9],[5,9,10,12]], target = 8
// 输出：true
//
// 示例 2：
// 输入：plants = [[1,3,5],[2,5,7]], target = 4
// 输出：false
// 提示：
//         0 <= n <= 1000
//         0 <= m <= 1000

public class Main {
}

class Solution {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        int rowLen = plants.length; // 总共有多少行
        if (rowLen == 0) {
            return false;
        }
        int len = plants[0].length; // 列数，第一行总共有多少个元素就是多少列
        if (len == 0) {
            return false;
        }
        int i = 0;
        int j = len - 1; // 关键是  列要从大到小开始搜索，否则行和列从正向都是逐步增大的，无法通过搜索得到答案的
        for (; i < rowLen && j >= 0; ) {
            if (plants[i][j] > target) {
                j--;
            } else if (plants[i][j] < target) {
                i++;
            } else if (plants[i][j] == target) {
                return true;
            }
        }
        return false;
    }
}
