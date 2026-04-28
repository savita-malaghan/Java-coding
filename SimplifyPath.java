/*
Problem: Simplify Path (LeetCode #71)
Given an absolute path for a file in Unix-style, simplify it.

Input: "/a/./b/../../c/"
Output: "/c"

Approach:
- Use a stack to handle directory traversal.
- Skip "." and empty parts, pop for "..".
- Join stack contents for final path.
- Time Complexity: O(n)
- Space Complexity: O(n)
*/

import java.util.*;

public class SimplifyPath {
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        for (String part : path.split("/")) {
            if (part.equals("") || part.equals(".")) continue;
            if (part.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(part);
            }
        }
        return "/" + String.join("/", stack);
    }

    public static void main(String[] args) {
        System.out.println("Simplified Path: " + simplifyPath("/a/./b/../../c/"));
    }
}
