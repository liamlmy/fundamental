//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1811 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        int up = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (up < bottom && left < right) {
            for (int i = left; i < right; i++) {
                ret.add(matrix[up][i]);
            }
            for (int i = up; i < bottom; i++) {
                ret.add(matrix[i][right]);
            }
            for (int i = right; i > left; i--) {
                ret.add(matrix[bottom][i]);
            }
            for (int i = bottom; i > up; i--) {
                ret.add(matrix[i][left]);
            }
            up++;
            bottom--;
            left++;
            right--;
        }
        if (up > bottom || left > right) {
            return ret;
        }
        if (left == right) {
            for (int i = up; i <= bottom; i++) {
                ret.add(matrix[i][left]);
            }
        } else {
            for (int i = left; i <= right; i++) {
                ret.add(matrix[up][i]);
            }
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
