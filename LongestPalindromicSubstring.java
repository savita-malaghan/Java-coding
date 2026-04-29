/*
Problem: Longest Palindromic Substring (LeetCode #5)
Given a string s, return the longest palindromic substring in s.

Input example:
s = "babad"
Output:
"bab"  // "aba" is also valid

Approach:
- Expand-around-center for each index (odd and even length centers).
- Track the longest palindrome seen.
Time Complexity: O(n^2) worst-case (expand for each center)
Space Complexity: O(1) (excluding output)
*/

public class LongestPalindromicSubstring {
    private static String expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; right++;
        }
        return s.substring(left + 1, right);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = expand(s, i, i);
            if (odd.length() > res.length()) res = odd;
            String even = expand(s, i, i + 1);
            if (even.length() > res.length()) res = even;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Longest palindrome: " + longestPalindrome(s));
    }
}
