/*
Problem: Longest Repeating Character Replacement (LeetCode)
Given a string s and integer k, return length of longest substring that can be
obtained by replacing at most k characters to make all characters same.

Input example:
s = "AABABBA", k = 1
Output:
4  // "AABA" or "ABBA"

Approach:
- Sliding window with frequency counts.
- Maintain max frequency in window; if window size - maxFreq > k, shrink left.
Time Complexity: O(n)
Space Complexity: O(1) (alphabet size)
*/

import java.util.*;

public class LongestRepeatingCharReplacement {
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxFreq = 0, res = 0;
        for (int right = 0; right < s.length(); right++) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(right) - 'A']);
            while (right - left + 1 - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
