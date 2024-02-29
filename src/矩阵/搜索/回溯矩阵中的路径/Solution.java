package 矩阵.搜索.回溯矩阵中的路径;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Solution {
    private char[][] grid;
    private boolean[][] visited;
    private char[] word;// 本地变量
    private int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean wordPuzzle(char[][] grid, String target) {
        if (grid.length == 0) {
            return false;
        }
        this.grid = grid;
        this.word = target.toCharArray();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int k) {
        if (grid[i][j] != word[k]) {
            return false;// 提前返回
        } else if (k == word.length - 1) { // 已经遍历到最后一个元素了，并且这个元素是相等的
            return true;
        }
        int newi;
        int newj;
        char orignalW = grid[i][j];
        this.grid[i][j] = '\0';
        for (int d = 0; d < 4; d++) {
            newi = i + direction[d][0];
            newj = j + direction[d][1];
            if (newi < 0 || newi > this.grid.length || newj < 0 || newj > this.grid[0].length) {
                continue;
            }
            if (this.grid[newi][newj] == '\0') {
                continue;
            }
            if (dfs(newi, newj, k + 1)) {
                return true;
            }
        }
        this.grid[i][j] = orignalW; // 回溯
        return false;
    }
}
