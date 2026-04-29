/*
Problem: Product of Array Except Self (LeetCode #238)
Given an integer array nums, return an array answer such that answer[i] is the product of all elements of nums except nums[i].
Do this without using division and in O(n) time.

Input example:
nums = [1,2,3,4]
Output:
[24,12,8,6]

Approach:
- Compute prefix products into result array.
- Then multiply by suffix products computed on the fly.
Time Complexity: O(n)
Space Complexity: O(1) extra (output array not counted)
*/

import java.util.*;

public class ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) res[i] = res[i - 1] * nums[i - 1];
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * suffix;
            suffix *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }
}
