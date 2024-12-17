//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1637 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ret = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        DFS(nums, 0, cur, ret);
        return new ArrayList<>(ret);
    }

    private void DFS(int[] nums, int idx, List<Integer> cur, Set<List<Integer>> ret) {
        // base case
        if (idx == nums.length) {
            ret.add(new ArrayList<>(cur));
            return;
        }
        // recursion rule
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            cur.add(nums[idx]);
            DFS(nums, idx + 1, cur, ret);
            cur.remove(cur.size() - 1);
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
