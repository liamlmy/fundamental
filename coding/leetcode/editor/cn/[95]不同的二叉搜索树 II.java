//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
// 
// 
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
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
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树 👍 1588 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        // corner case
        if (n == 0) {
            return new ArrayList<>();
        }
        // DFS
        return DFS(1, n);
    }

    private List<TreeNode> DFS(int left, int right) {
        List<TreeNode> cur = new ArrayList<>();
        // base case
        if (left > right) {
            cur.add(null);
            return cur;
        }
        // recursion rule
        for (int i = left; i <= right; i++) {
            List<TreeNode> lefts = DFS(left, i - 1);
            List<TreeNode> rights = DFS(i + 1, right);
            for (TreeNode l: lefts) {
                for (TreeNode r : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    cur.add(root);
                }
            }
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
