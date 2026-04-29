/*
Problem: Insert Delete GetRandom O(1) (LeetCode #380)
Design a data structure that supports insert(val), remove(val), and getRandom() in average O(1) time.

Input example (sequence):
insert(1), insert(2), insert(2), remove(1), getRandom()
Output:
getRandom() returns 2 (only 2 remains)

Explanation:
Use an ArrayList to store values and a HashMap from value -> set of indices.
On remove, swap target with last element and update indices sets, then pop.
getRandom picks a random index from list.
Time Complexity: O(1) average for each operation.
Space Complexity: O(n)
*/

import java.util.*;

public class RandomizedCollection {
    private List<Integer> list;
    private Map<Integer, Set<Integer>> idx;
    private Random rand;

    public RandomizedCollection() {
        list = new ArrayList<>();
        idx = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        boolean contains = idx.containsKey(val);
        idx.computeIfAbsent(val, k -> new HashSet<>()).add(list.size());
        list.add(val);
        return !contains;
    }

    public boolean remove(int val) {
        if (!idx.containsKey(val) || idx.get(val).isEmpty()) return false;
        int removeIdx = idx.get(val).iterator().next();
        idx.get(val).remove(removeIdx);
        int last = list.get(list.size() - 1);
        list.set(removeIdx, last);
        idx.get(last).add(removeIdx);
        idx.get(last).remove(list.size() - 1);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    // Demo
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        rc.insert(1);
        rc.insert(2);
        rc.insert(2);
        rc.remove(1);
        System.out.println("Random element: " + rc.getRandom());
    }
}
