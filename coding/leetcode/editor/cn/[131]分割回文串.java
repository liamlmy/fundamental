//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 1906 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>>[] ret = new List[s.length() + 1];
        boolean[][] dp = new boolean[s.length()][s.length()];
        ret[0] = new ArrayList<>();
        ret[0].add(new ArrayList<>());
        for (int i = 0; i < s.length(); i++) {
            ret[i + 1] = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 1 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    String sub = s.substring(j, i + 1);
                    for (List<String> pre : ret[j]) {
                        List<String> cur = new ArrayList<>(pre);
                        cur.add(sub);
                        ret[i + 1].add(cur);
                    }
                }
            }
        }
        return ret[ret.length - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
