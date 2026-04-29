/*
Problem: Trapping Rain Water (LeetCode #42)
Given n non-negative integers representing an elevation map where width of each bar is 1, compute how much water it can trap after raining.

Input example:
height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output:
6

Approach:
- Two-pointer technique: maintain left and right pointers and leftMax/rightMax.
- Accumulate water at each step based on min(leftMax, rightMax) - height[i].
Time Complexity: O(n)
Space Complexity: O(1)
*/

public class TrappingRainWater {
    public static int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = 0, res = 0;
        while (l <= r) {
            if (height[l] <= height[r]) {
                if (height[l] >= leftMax) leftMax = height[l];
                else res += leftMax - height[l];
                l++;
            } else {
                if (height[r] >= rightMax) rightMax = height[r];
                else res += rightMax - height[r];
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Trapped water: " + trap(height));
    }
}
