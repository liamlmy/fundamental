//实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xⁿ ）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// n 是一个整数 
// 要么 x 不为零，要么 n > 0 。 
// -104 <= xⁿ <= 104 
// 
//
// Related Topics 递归 数学 👍 1413 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        // corner case
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        long N = n;
        if (N < 0) {
            return 1.0 / quickMul(x, -N);
        }
        return quickMul(x, N);
    }

    private double quickMul(double x, long N) {
        double ret = 1.0;
        double base = x;
        while (N > 0) {
            if (N % 2 == 1) {
                ret = ret * base;
            }
            base = base * base;
            N = N / 2;
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
