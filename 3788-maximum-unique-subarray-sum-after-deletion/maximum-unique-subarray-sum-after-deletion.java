import java.util.*;

class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            unique.add(num);
        }

        int sum = 0;
        int maxElement = Integer.MIN_VALUE;

        for (int num : unique) {
            if (num > 0) sum += num;
            maxElement = Math.max(maxElement, num);
        }

        return sum > 0 ? sum : maxElement;
    }
}
