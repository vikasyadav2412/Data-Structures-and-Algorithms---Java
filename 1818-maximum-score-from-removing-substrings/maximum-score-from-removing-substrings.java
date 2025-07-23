// Maximum Score From Removing Substrings
class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            return maximumGain(s, y, x, "ba", "ab");
        }
        return maximumGain(s, x, y, "ab", "ba");
    }

    private int maximumGain(String s, int high, int low, String first, String second) {
        int score = 0;
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            sb.append(c);
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 2) == first.charAt(0) && sb.charAt(len - 1) == first.charAt(1)) {
                sb.delete(len - 2, len);
                score += high;
            }
        }

        StringBuilder leftover = new StringBuilder();
        for (char c : sb.toString().toCharArray()) {
            leftover.append(c);
            int len = leftover.length();
            if (len >= 2 && leftover.charAt(len - 2) == second.charAt(0) && leftover.charAt(len - 1) == second.charAt(1)) {
                leftover.delete(len - 2, len);
                score += low;
            }
        }

        return score;
    }
}
