/*
Problem: Maximum Product Subarray (LeetCode #152)
Given an integer array nums, find the contiguous subarray within the array 
that has the largest product.

Input: nums = [2,3,-2,4]
Output: 6
Explanation: The subarray [2,3] has the largest product = 6.

Approach:
- Track both maximum and minimum products at each step (because negatives flip signs).
- Swap max/min when encountering a negative.
- Time Complexity: O(n)
- Space Complexity: O(1)
*/

public class MaxProductSubarray {
    public static int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println("Maximum product: " + maxProduct(nums));
    }
}
