//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
//
// Related Topics 数组 回溯 👍 1608 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(candidates);
        DFS(candidates, target, 0, cur, ret);
        return new ArrayList<>(ret);
    }

    private void DFS(int[] candidates, int target, int idx, List<Integer> cur, List<List<Integer>> ret) {
        // base case
        if (target == 0) {
            ret.add(new ArrayList<>(cur));
            return;
        }
        if (target < 0) {
            return;
        }
        // recursion rule
        for (int i = idx; i < candidates.length; i++) {
            cur.add(candidates[i]);
            DFS(candidates, target - candidates[i], i + 1, cur, ret);
            cur.remove(cur.size() - 1);
            while (i < candidates.length - 1 && candidates[i + 1] == candidates[i]) {
                i++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
