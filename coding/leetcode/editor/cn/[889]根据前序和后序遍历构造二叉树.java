//给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵
//树的后序遍历，重构并返回二叉树。 
//
// 如果存在多个答案，您可以返回其中 任何 一个。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [1], postorder = [1]
//输出: [1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= preorder.length <= 30 
// 1 <= preorder[i] <= preorder.length 
// preorder 中所有值都 不同 
// postorder.length == preorder.length 
// 1 <= postorder[i] <= postorder.length 
// postorder 中所有值都 不同 
// 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 401 👎 0


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
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // corner case
        if (preorder == null || postorder == null || preorder.length == 0 || postorder.length == 0 || preorder.length != postorder.length) {
            return null;
        }
        // DFS
        return DFS(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode DFS(int[] preorder, int preLeft, int preRight, int[] postorder, int postLeft, int postRight) {
        // base case
        if (preLeft > preRight) {
            return null;
        }
        if (preLeft == preRight) {
            return new TreeNode(preorder[preLeft]);
        }
        // recursion rule
        TreeNode root = new TreeNode(preorder[preLeft]);
        int leftCnt = getLeftCnt(postorder, postLeft, postRight, preorder[preLeft + 1]);
        root.left = DFS(preorder, preLeft + 1, preLeft + leftCnt, postorder, postLeft, postLeft + leftCnt - 1);
        root.right = DFS(preorder, preLeft + leftCnt + 1, preRight, postorder, postLeft + leftCnt, postRight - 1);
        return root;
    }

    private int getLeftCnt(int[] postorder, int postLeft, int postRight, int target) {
        for (int i = postLeft; i <= postRight; i++) {
            if (postorder[i] == target) {
                return i - postLeft + 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
