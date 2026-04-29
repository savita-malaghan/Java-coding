/*
Problem: Word Ladder (LeetCode #127)
Given beginWord, endWord, and a wordList, return the length of shortest transformation sequence from beginWord to endWord, changing one letter at a time and each intermediate word must be in wordList.

Input example:
beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output:
5  // "hit" -> "hot" -> "dot" -> "dog" -> "cog"

Approach:
- BFS from beginWord; for each word generate neighbors by changing one letter.
- Use a set for wordList and visited set.
Time Complexity: O(N * L^2) roughly (N words, L word length)
Space Complexity: O(N)
*/

import java.util.*;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int level = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String word = q.poll();
                if (word.equals(endWord)) return level;
                char[] arr = word.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char old = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        arr[j] = c;
                        String nxt = new String(arr);
                        if (dict.contains(nxt) && !visited.contains(nxt)) {
                            visited.add(nxt);
                            q.offer(nxt);
                        }
                    }
                    arr[j] = old;
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("Ladder length: " + ladderLength("hit", "cog", wordList));
    }
}
