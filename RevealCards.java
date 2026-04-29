/*
Problem: Reveal Cards In Increasing Order
Given an array of unique integers, simulate revealing cards by:
- take top card, reveal it, then move next top card to bottom, repeat.
Return initial deck order that results in increasing reveal sequence.

Input example:
deck = [17,13,11,2,3,5,7]
Output:
[2,13,3,11,5,17,7]

Approach:
- Use deque and simulate reverse process: place largest card, then move bottom to top.
- Sort deck ascending, iterate from largest to smallest.
Time Complexity: O(n log n)
Space Complexity: O(n)
*/

import java.util.*;

public class RevealCards {
    public static int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = deck.length - 1; i >= 0; i--) {
            if (!dq.isEmpty()) dq.addFirst(dq.removeLast());
            dq.addFirst(deck[i]);
        }
        int[] res = new int[deck.length];
        int idx = 0;
        for (int v : dq) res[idx++] = v;
        return res;
    }

    public static void main(String[] args) {
        int[] deck = {17,13,11,2,3,5,7};
        System.out.println(Arrays.toString(deckRevealedIncreasing(deck)));
    }
}
