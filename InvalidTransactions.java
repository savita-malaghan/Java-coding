/*
Problem: Invalid Transactions
Given an array of transaction strings "name,time,amount,city", return all transactions that are invalid.
A transaction is invalid if:
 - amount > 1000 OR
 - there exists another transaction with same name within 60 minutes in a different city.

Input example:
["alice,20,800,mtv","alice,50,100,beijing"]
Output:
["alice,20,800,mtv","alice,50,100,beijing"]

Explanation:
- Both transactions for alice are within 60 minutes but different cities -> both invalid.

Approach:
- Parse transactions into objects.
- Group by name, sort each group by time, then compare neighbors within 60 minutes.
- Also mark amount > 1000 invalid.
Time Complexity: O(n log n) due to sorting per name
Space Complexity: O(n)
*/

import java.util.*;

public class InvalidTransactions {
    static class Tx {
        String raw; String name; int time; int amount; String city;
        Tx(String raw) {
            this.raw = raw;
            String[] p = raw.split(",");
            name = p[0]; time = Integer.parseInt(p[1]); amount = Integer.parseInt(p[2]); city = p[3];
        }
    }

    public static List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        Tx[] txs = new Tx[n];
        for (int i = 0; i < n; i++) txs[i] = new Tx(transactions[i]);

        Map<String, List<Tx>> byName = new HashMap<>();
        for (Tx t : txs) byName.computeIfAbsent(t.name, k -> new ArrayList<>()).add(t);

        Set<String> invalid = new HashSet<>();
        for (List<Tx> list : byName.values()) {
            list.sort(Comparator.comparingInt(a -> a.time));
            for (int i = 0; i < list.size(); i++) {
                Tx a = list.get(i);
                if (a.amount > 1000) invalid.add(a.raw);
                for (int j = i + 1; j < list.size(); j++) {
                    Tx b = list.get(j);
                    if (b.time - a.time > 60) break;
                    if (!a.city.equals(b.city)) {
                        invalid.add(a.raw);
                        invalid.add(b.raw);
                    }
                }
            }
        }
        return new ArrayList<>(invalid);
    }

    public static void main(String[] args) {
        String[] txs = {"alice,20,800,mtv","alice,50,100,beijing"};
        System.out.println(invalidTransactions(txs));
    }
}
