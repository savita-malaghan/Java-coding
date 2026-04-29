/*
Problem: N-Queens (LeetCode #51)
Place N queens on an N×N chessboard so that no two queens attack each other.
Return all distinct solutions where each solution is a list of strings representing the board.

Input example:
n = 4
Output (one valid set):
[
 [".Q..",
  "...Q",
  "Q...",
  "..Q."],
 ["..Q.",
  "Q...",
  "...Q",
  ".Q.."]
]

Explanation:
Use backtracking. Track columns, diag1 (r+c), diag2 (r-c) to check conflicts quickly.
Time Complexity: Exponential, roughly O(N!) in worst case.
Space Complexity: O(N^2) for storing solutions (plus recursion stack O(N)).
*/

import java.util.*;

public class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2*n];
        boolean[] diag2 = new boolean[2*n];
        backtrack(0, n, board, cols, diag1, diag2, res);
        return res;
    }

    private static void backtrack(int r, int n, char[][] board, boolean[] cols, boolean[] d1, boolean[] d2, List<List<String>> res) {
        if (r == n) {
            List<String> sol = new ArrayList<>();
            for (char[] row : board) sol.add(new String(row));
            res.add(sol);
            return;
        }
        for (int c = 0; c < n; c++) {
            if (cols[c] || d1[r+c] || d2[r-c + n]) continue;
            board[r][c] = 'Q';
            cols[c] = d1[r+c] = d2[r-c + n] = true;
            backtrack(r+1, n, board, cols, d1, d2, res);
            board[r][c] = '.';
            cols[c] = d1[r+c] = d2[r-c + n] = false;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> solutions = solveNQueens(n);
        System.out.println("Number of solutions: " + solutions.size());
        for (List<String> sol : solutions) {
            for (String row : sol) System.out.println(row);
            System.out.println();
        }
    }
}
