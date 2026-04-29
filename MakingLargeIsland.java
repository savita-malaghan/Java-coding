/*
Problem: Making A Large Island (LeetCode #827)
Given a grid of 0s and 1s, you may change at most one 0 to 1. Return the size of the largest island possible.

Input example:
grid = [
 [1,0],
 [0,1]
]
Output:
3

Explanation:
Label each island with an id and size via DFS. For each 0, sum sizes of unique neighboring islands + 1.
Time Complexity: O(n*m)
Space Complexity: O(n*m)
*/

import java.util.*;

public class MakingLargeIsland {
    private static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int id = 2; // start labeling from 2
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
            if (grid[i][j] == 1) {
                int size = dfsLabel(grid, i, j, id);
                sizeMap.put(id, size);
                id++;
            }
        }
        int res = 0;
        for (int sz : sizeMap.values()) res = Math.max(res, sz);
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
            if (grid[i][j] == 0) {
                Set<Integer> neigh = new HashSet<>();
                for (int[] d : dirs) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] > 1) neigh.add(grid[ni][nj]);
                }
                int sum = 1;
                for (int nid : neigh) sum += sizeMap.get(nid);
                res = Math.max(res, sum);
            }
        }
        return res == 0 ? n*n : res;
    }

    private static int dfsLabel(int[][] g, int i, int j, int id) {
        int n = g.length;
        if (i < 0 || i >= n || j < 0 || j >= n || g[i][j] != 1) return 0;
        g[i][j] = id;
        int cnt = 1;
        for (int[] d : dirs) cnt += dfsLabel(g, i + d[0], j + d[1], id);
        return cnt;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0},{0,1}};
        System.out.println("Largest island after one flip: " + largestIsland(grid));
    }
}
