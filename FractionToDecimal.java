/*
Problem: Fraction to Recurring Decimal
Given numerator and denominator, return decimal representation as string.
If fractional part repeats, enclose repeating part in parentheses.

Input example:
numerator = 4, denominator = 333
Output:
"0.(012)"

Approach:
- Handle sign and integer part.
- Use long to avoid overflow.
- Use map to record remainder -> position in result; when remainder repeats, insert parentheses.
Time Complexity: O(quotient length) ~ O(n)
Space Complexity: O(n) for map
*/

import java.util.*;

public class FractionToDecimal {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0)) sb.append("-");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        long rem = num % den;
        if (rem == 0) return sb.toString();
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                int idx = map.get(rem);
                sb.insert(idx, "(");
                sb.append(")");
                break;
            }
            map.put(rem, sb.length());
            rem *= 10;
            sb.append(rem / den);
            rem %= den;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(4, 333));
    }
}
