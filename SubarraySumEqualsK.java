/*
Problem: Subarray Sum Equals K (LeetCode #560)
Given an integer array nums and an integer k, return the total number of continuous subarrays whose sum equals k.

Input example:
nums = [1,1,1], k = 2
Output:
2

Approach:
- Use prefix sum and HashMap to count occurrences of (currentSum - k).
- For each prefix sum, add count of (sum - k) to result.
Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;

public class SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);
        int sum = 0, res = 0;
        for (int num : nums) {
            sum += num;
            res += count.getOrDefault(sum - k, 0);
            count.put(sum, count.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println("Number of subarrays: " + subarraySum(nums, k));
    }
}
