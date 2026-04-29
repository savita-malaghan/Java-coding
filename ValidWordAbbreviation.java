/*
Problem: Valid Word Abbreviation
Given a word and an abbreviation, return whether the abbreviation is valid.
Digits in abbreviation represent number of characters skipped.

Input example:
word = "internationalization", abbr = "i12iz4n"
Output:
true

Explanation:
Parse abbreviation with two pointers; when digits appear, skip that many
characters in the word. Ensure no leading zeros in numeric parts.

Time Complexity: O(n + m) where n = len(word), m = len(abbr)
Space Complexity: O(1)
*/

public class ValidWordAbbreviation {
    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            char c = abbr.charAt(j);
            if (Character.isDigit(c)) {
                if (c == '0') return false; // leading zero invalid
                int num = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j) - '0');
                    j++;
                }
                i += num;
            } else {
                if (word.charAt(i) != abbr.charAt(j)) return false;
                i++; j++;
            }
        }
        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        System.out.println(validWordAbbreviation("internationalization", "i12iz4n"));
    }
}
