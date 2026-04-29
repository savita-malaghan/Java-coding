/*
Problem: Serialize and Deserialize Binary Tree (LeetCode #297)
Design algorithms to serialize a binary tree to a string and deserialize back.

Input example:
Tree: [1,2,3,null,null,4,5]
Output:
Serialized string and reconstructed tree (print preorder to verify)

Explanation:
Use preorder traversal with null markers (e.g., "#") to serialize. Deserialize by reading tokens in order.
Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;

class TreeNode2 {
    int val;
    TreeNode2 left, right;
    TreeNode2(int x) { val = x; }
}

public class Codec {
    public String serialize(TreeNode2 root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode2 node, StringBuilder sb) {
        if (node == null) { sb.append("#,"); return; }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    public TreeNode2 deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(q);
    }

    private TreeNode2 deserializeHelper(Queue<String> q) {
        String token = q.poll();
        if (token.equals("#")) return null;
        TreeNode2 node = new TreeNode2(Integer.parseInt(token));
        node.left = deserializeHelper(q);
        node.right = deserializeHelper(q);
        return node;
    }

    // Demo
    public static void main(String[] args) {
        TreeNode2 root = new TreeNode2(1);
        root.left = new TreeNode2(2);
        root.right = new TreeNode2(3);
        root.right.left = new TreeNode2(4);
        root.right.right = new TreeNode2(5);

        Codec codec = new Codec();
        String s = codec.serialize(root);
        System.out.println("Serialized: " + s);
        TreeNode2 r2 = codec.deserialize(s);
        // print preorder to verify
        preorderPrint(r2);
    }

    private static void preorderPrint(TreeNode2 node) {
        if (node == null) { System.out.print("# "); return; }
        System.out.print(node.val + " ");
        preorderPrint(node.left);
        preorderPrint(node.right);
    }
}
