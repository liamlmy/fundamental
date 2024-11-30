//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 3729 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        // corner case
        if (n <= 0) {
            return new ArrayList<>();
        }
        // DFS
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DFS(n, n, sb, ret);
        return ret;
    }

    private void DFS(int left, int right, StringBuilder sb, List<String> ret) {
        // base case
        if (left == 0 && right == 0) {
            ret.add(new String(sb));
            return;
        }
        if (right < left) {
            return;
        }
        if (right < 0 || left < 0) {
            return;
        }
        // recursion rule
        sb.append('(');
        DFS(left - 1, right, sb, ret);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        DFS(left, right - 1, sb, ret);
        sb.deleteCharAt(sb.length() - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
