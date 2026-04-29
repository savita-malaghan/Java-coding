/*
Problem: Minimum Window Substring (LeetCode #76)
Given strings s and t, return the minimum window in s which contains all characters of t. If no such window exists, return "".

Input example:
s = "ADOBECODEBANC", t = "ABC"
Output:
"BANC"

Approach:
- Sliding window with two frequency arrays/maps.
- Expand right until window contains all chars, then shrink left to minimize.
Time Complexity: O(n + m) where m = |t|
Space Complexity: O(1) (alphabet size) or O(m)
*/

import java.util.*;

public class MinWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int[] need = new int[128];
        for (char c : t.toCharArray()) need[c]++;
        int required = t.length();
        int l = 0, r = 0, minLen = Integer.MAX_VALUE, start = 0;
        while (r < s.length()) {
            char c = s.charAt(r++);
            if (need[c] > 0) required--;
            need[c]--;
            while (required == 0) {
                if (r - l < minLen) { minLen = r - l; start = l; }
                char d = s.charAt(l++);
                need[d]++;
                if (need[d] > 0) required++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("Min window: " + minWindow(s, t));
    }
}
