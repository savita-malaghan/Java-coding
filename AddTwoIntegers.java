/*
Problem: Add Two Integers
Given two integers a and b, return sum without using + or - operators.

Input example:
a = 1, b = 2
Output:
3

Approach:
- Use bitwise operations: sum without carry = a ^ b; carry = (a & b) << 1.
- Iterate until carry is zero.
Time Complexity: O(1) (bounded by integer bit width)
Space Complexity: O(1)
*/

public class AddTwoIntegers {
    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
    }
}
