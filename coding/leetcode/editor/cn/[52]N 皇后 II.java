//n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
// 
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 
//
// Related Topics 回溯 👍 552 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int totalNQueens(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        DFS(n, 0, cur, ret);
        return ret.size();
    }

    private void DFS(int n, int idx, List<Integer> cur, List<List<Integer>> ret) {
        // base case
        if (idx == n) {
            ret.add(new ArrayList<>(cur));
            return;
        }
        // recursion rule
        for (int i = 0; i < n; i++) {
            if (check(cur, n, idx, i)) {
                cur.add(i);
                DFS(n, idx + 1, cur, ret);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean check(List<Integer> cur, int n, int idx, int i) {
        for (int k = 0; k < cur.size(); k++) {
            if (cur.get(k) == i) {
                return false;
            }
            if (Math.abs(cur.get(k) - i) == Math.abs(k - idx)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
