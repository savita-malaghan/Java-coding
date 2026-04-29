/*
Problem: Minimum Limit of Balls in a Bag
Given an array nums and integer maxOperations. You may split a pile into two piles.
Return minimum possible maximum pile size after at most maxOperations splits.

Input example:
nums = [9], maxOperations = 2
Output:
3  // split 9 -> 3,3,3

Approach:
- Binary search on answer (max pile size).
- For candidate x, compute required operations = sum((num-1)/x).
- If required <= maxOperations, x feasible.
Time Complexity: O(n log m) where m = max(nums)
Space Complexity: O(1)
*/

public class MinimumLimitBalls {
    public static int minimumSize(int[] nums, int maxOperations) {
        int lo = 1, hi = 0;
        for (int n : nums) hi = Math.max(hi, n);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long ops = 0;
            for (int n : nums) ops += (n - 1) / mid;
            if (ops > maxOperations) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] nums = {9};
        System.out.println("Minimum max pile size: " + minimumSize(nums, 2));
    }
}
