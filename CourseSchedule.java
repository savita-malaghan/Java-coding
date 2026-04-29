/*
Problem: Course Schedule (LeetCode #207)
Given numCourses and prerequisites pairs [a,b] meaning b -> a, determine if you can finish all courses.

Input example:
numCourses = 2, prerequisites = [[1,0]]
Output:
true

Explanation:
Detect cycle in directed graph using DFS with visited and recursion stack arrays.
Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] p : prerequisites) adj.get(p[1]).add(p[0]);
        boolean[] visited = new boolean[numCourses];
        boolean[] rec = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && dfs(i, adj, visited, rec)) return false;
        }
        return true;
    }

    private static boolean dfs(int u, List<List<Integer>> adj, boolean[] vis, boolean[] rec) {
        if (rec[u]) return true;
        if (vis[u]) return false;
        vis[u] = true;
        rec[u] = true;
        for (int v : adj.get(u)) if (dfs(v, adj, vis, rec)) return true;
        rec[u] = false;
        return false;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prereq = {{1,0}};
        System.out.println("Can finish courses: " + canFinish(numCourses, prereq));
    }
}
