//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 2640 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    public int numIslands(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // DFS
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ret += DFS(grid, i, j);
                }
            }
        }
        return ret;
    }

    private int DFS(char[][] grid, int i, int j) {
        // base case
        if (grid[i][j] != '1') {
            return 0;
        }
        // recursion rule
        grid[i][j] = '2';
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                DFS(grid, ni, nj);
            }
        }
        return 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
