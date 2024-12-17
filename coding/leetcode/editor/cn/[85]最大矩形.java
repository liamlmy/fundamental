//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 1 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
//
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1701 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int ret = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            ret = Math.max(ret, maxArea(heights));
        }
        return ret;
    }

    private int maxArea(int[] heights) {
        int ret = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && h <= heights[stack.peekFirst()]) {
                int top = heights[stack.pollFirst()];
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                int width = i - left;
                ret = Math.max(ret, top * width);
            }
            stack.offerFirst(i);
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
