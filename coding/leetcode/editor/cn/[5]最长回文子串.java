//给你一个字符串 s，找到 s 中最长的 回文 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 7442 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() <= 1) {
            return s;
        }
        // 2 pointers
        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            System.out.println(i);
            int len1 = panlindrome(s, i, i);
            int len2 = panlindrome(s, i, i + 1);
            if (len1 > ret.length()) {
                ret = s.substring(i - len1 / 2, i - len1 / 2 + len1);
            }
            if (len2 > ret.length()) {
                ret = s.substring(i - (len2 - 1) / 2, i - (len2 - 1) / 2 + len2);
            }
        }
        return ret;
    }

    private int panlindrome(String s, int i, int j) {
        int ret = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                ret = Math.max(ret, j - i + 1);
                i--;
                j++;
            } else {
                break;
            }
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
