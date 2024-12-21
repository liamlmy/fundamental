//给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1360 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int up = 0;
        int bottom = n - 1;
        int cur = 1;
        while (left < right && up < bottom) {
            for (int i = left; i < right; i++) {
                matrix[up][i] = cur;
                cur++;
            }
            for (int i = up; i < bottom; i++) {
                matrix[i][right] = cur;
                cur++;
            }
            for (int i = right; i > left; i--) {
                matrix[bottom][i] = cur;
                cur++;
            }
            for (int i = bottom; i > up; i--) {
                matrix[i][left] = cur;
                cur++;
            }
            left++;
            right--;
            up++;
            bottom--;
        }
        if (left == right) {
            matrix[up][left] = cur;
        }
        return matrix;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
