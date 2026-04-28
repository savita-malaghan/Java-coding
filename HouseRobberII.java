/*
Problem: House Robber II (LeetCode #213)
You cannot rob adjacent houses; houses are arranged in a circle.

Input: nums = [2,3,2]
Output: 3
Explanation: Rob house 1 (money = 2) and house 3 (money = 2) is not allowed. 
Best option is house 2 (money = 3).

Approach:
- Split into two cases: rob from [0..n-2] or [1..n-1].
- Use linear DP for each case.
- Time Complexity: O(n)
- Space Complexity: O(1)
*/

public class HouseRobberII {
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robLinear(nums, 0, nums.length - 2),
                        robLinear(nums, 1, nums.length - 1));
    }

    private static int robLinear(int[] nums, int start, int end) {
        int prev = 0, curr = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(curr, prev + nums[i]);
            prev = curr;
            curr = temp;
        }
        return curr;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println("Max rob amount: " + rob(nums));
    }
}
