//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 7308 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 0) {
            return (findKth(nums1, 0, nums2, 0, (m + n) / 2) + findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1)) / 2.0;
        } else {
            return (double) findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        }
    }

    private int findKth(int[] nums1, int idx_1, int[] nums2, int idx_2, int k) {
        // corner case
        if (idx_1 >= nums1.length) {
            return nums2[idx_2 + k - 1];
        }
        if (idx_2 >= nums2.length) {
            return nums1[idx_1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[idx_1], nums2[idx_2]);
        }
        // recursion rule
        int n1 = idx_1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[idx_1 + k / 2 - 1];
        int n2 = idx_2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[idx_2 + k / 2 - 1];
        if (n1 > n2) {
            return findKth(nums1, idx_1, nums2, idx_2 + k / 2, k - k / 2);
        }
        return findKth(nums1, idx_1 + k / 2, nums2, idx_2, k - k / 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
