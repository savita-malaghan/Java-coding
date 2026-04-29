/*
Problem: Squares of a Sorted Array
Given a sorted array of integers (may include negatives), return array of squares sorted.

Input example:
nums = [-4,-1,0,3,10]
Output:
[0,1,9,16,100]

Approach:
- Two-pointer from both ends; place larger square at end of result.
Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;

public class SquaresSorted {
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int l = 0, r = n - 1, idx = n - 1;
        while (l <= r) {
            int ls = nums[l] * nums[l], rs = nums[r] * nums[r];
            if (ls > rs) { res[idx--] = ls; l++; }
            else { res[idx--] = rs; r--; }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }
}
