/*
Problem: Open the Lock (minimum moves)
You have a lock with 4 wheels (0000 start). Each move you can turn one wheel +1 or -1 (wrap).
Given a list of deadends and a target, return minimum moves to reach target or -1.

Input example:
deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output:
6

Approach:
- BFS from "0000", avoid deadends and visited states.
- Each state has 8 neighbors (4 wheels * 2 directions).
Time Complexity: O(10^4) worst-case (state space)
Space Complexity: O(10^4)
*/

import java.util.*;

public class OpenTheLock {
    private static List<String> neighbors(String s) {
        List<String> res = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < 4; i++) {
            char c = arr[i];
            arr[i] = (char) ((c - '0' + 1) % 10 + '0');
            res.add(new String(arr));
            arr[i] = (char) ((c - '0' + 9) % 10 + '0');
            res.add(new String(arr));
            arr[i] = c;
        }
        return res;
    }

    public static int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        int steps = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (cur.equals(target)) return steps;
                for (String nb : neighbors(cur)) {
                    if (!dead.contains(nb) && !seen.contains(nb)) {
                        seen.add(nb);
                        q.offer(nb);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] dead = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println("Min moves: " + openLock(dead, target));
    }
}
