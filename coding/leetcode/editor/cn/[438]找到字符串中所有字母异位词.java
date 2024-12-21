//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1555 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int[] dict = buildDict(p);
        int i = 0;
        int j = 0;
        int[] seen = new int[256];
        while (j < s.length()) {
            seen[s.charAt(j)]++;
            if (j >= p.length() - 1) {
                if (check(seen, dict)) {
                    ret.add(i);
                }
                seen[s.charAt(i++)]--;
            }
            j++;
        }
        return ret;
    }

    private int[] buildDict(String p) {
        int[] dict = new int[256];
        for (int i = 0; i < p.length(); i++) {
            dict[p.charAt(i)]++;
        }
        return dict;
    }

    private boolean check(int[] seen, int[] dict) {
        for (int i = 0; i < seen.length; i++) {
            if (seen[i] != dict[i]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
