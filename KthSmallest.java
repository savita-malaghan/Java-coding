/*
Problem: Kth Smallest Element in a BST (LeetCode #230)
Given a BST, return the k-th smallest element.

Input example:
BST: [3,1,4,null,2], k = 1
Output:
1

Explanation:
Inorder traversal yields sorted order. Use iterative or recursive inorder and stop at k.
Time Complexity: O(h + k) where h is tree height
Space Complexity: O(h) for recursion/stack
*/

import java.util.*;

class TreeNode3 {
    int val;
    TreeNode3 left, right;
    TreeNode3(int x) { val = x; }
}

public class KthSmallest {
    public static int kthSmallest(TreeNode3 root, int k) {
        Deque<TreeNode3> stack = new ArrayDeque<>();
        TreeNode3 cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) { stack.push(cur); cur = cur.left; }
            cur = stack.pop();
            if (--k == 0) return cur.val;
            cur = cur.right;
        }
        throw new IllegalArgumentException("k too large");
    }

    public static void main(String[] args) {
        TreeNode3 root = new TreeNode3(3);
        root.left = new TreeNode3(1);
        root.right = new TreeNode3(4);
        root.left.right = new TreeNode3(2);
        System.out.println("Kth smallest (k=1): " + kthSmallest(root, 1));
    }
}
