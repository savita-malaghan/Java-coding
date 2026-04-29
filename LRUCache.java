/*
Problem: LRU Cache (LeetCode #146)
Design a data structure that supports get and put in O(1) time with LRU eviction policy.

Input example (sequence):
LRUCache cache = new LRUCache(2);
cache.put(1,1);
cache.put(2,2);
cache.get(1);       // returns 1
cache.put(3,3);    // evicts key 2
cache.get(2);       // returns -1

Approach:
- Use a HashMap for key->node and a doubly-linked list to maintain recency order.
- On get/put, move node to head; evict from tail when capacity exceeded.
Time Complexity: O(1) per operation
Space Complexity: O(capacity)
*/

import java.util.*;

public class LRUCache {
    private class Node {
        int key, val;
        Node prev, next;
        Node(int k, int v) { key = k; val = v; }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0,0); tail = new Node(0,0);
        head.next = tail; tail.prev = head;
    }

    private void addToHead(Node node) {
        node.next = head.next; node.prev = head;
        head.next.prev = node; head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next; node.next.prev = node.prev;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            if (map.size() > capacity) {
                Node toRemove = tail.prev;
                removeNode(toRemove);
                map.remove(toRemove.key);
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1)); // 1
        cache.put(3,3);
        System.out.println(cache.get(2)); // -1
    }
}
