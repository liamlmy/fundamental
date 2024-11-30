//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
//
// Related Topics 哈希表 字符串 回溯 👍 2959 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    final String[] keyboard = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        // corner case
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        // DFS
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DFS(digits, 0, sb, ret);
        return ret;
    }

    private void DFS(String digits, int idx, StringBuilder sb, List<String> ret) {
        // base case
        if (idx == digits.length()) {
            ret.add(new String(sb));
            return;
        }
        // recursion rule
        String can = keyboard[digits.charAt(idx) - '0'];
        for (int i = 0; i < can.length(); i++) {
            sb.append(can.charAt(i));
            DFS(digits, idx + 1, sb, ret);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
