/*
Problem: Median of Two Sorted Arrays (LeetCode #4)
Given two sorted arrays nums1 and nums2 of size m and n, return the median of the two sorted arrays.
Overall run time complexity should be O(log (m+n)).

Input example:
nums1 = [1,3], nums2 = [2]
Output:
2.0

Approach:
- Binary search on the smaller array to find correct partition where left halves and right halves satisfy ordering.
- Compute median from max(lefts) and min(rights).
Time Complexity: O(log(min(m,n)))
Space Complexity: O(1)
*/

public class MedianTwoSortedArrays {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) return findMedianSortedArrays(B, A);
        int m = A.length, n = B.length;
        int imin = 0, imax = m, half = (m + n + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half - i;
            if (i < m && B[j - 1] > A[i]) imin = i + 1;
            else if (i > 0 && A[i - 1] > B[j]) imax = i - 1;
            else {
                int maxLeft;
                if (i == 0) maxLeft = B[j - 1];
                else if (j == 0) maxLeft = A[i - 1];
                else maxLeft = Math.max(A[i - 1], B[j - 1]);
                if ((m + n) % 2 == 1) return maxLeft;
                int minRight;
                if (i == m) minRight = B[j];
                else if (j == n) minRight = A[i];
                else minRight = Math.min(A[i], B[j]);
                return (maxLeft + minRight) / 2.0;
            }
        }
        throw new IllegalArgumentException("Input arrays not valid");
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        System.out.println("Median: " + findMedianSortedArrays(nums1, nums2));
    }
}
