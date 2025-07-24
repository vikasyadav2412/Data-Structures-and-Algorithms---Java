import java.util.*;

class Solution {
    
    long mod = (long)(1e9 + 7);
    
    public int countTrapezoids(int[][] points) {
        HashMap<Integer, HashSet<Integer>> y = new HashMap<>();
        
        for (int[] a : points) {
            if (!y.containsKey(a[1])) {
                y.put(a[1], new HashSet<>());
            }
            y.get(a[1]).add(a[0]);
        }
        
        long cc = 0;
        ArrayList<Integer> mm = new ArrayList<>(y.keySet());
        
        for (int i : mm) {
            long kk = y.get(i).size();
            cc = (cc % mod + ((kk * (kk - 1)) / 2) % mod) % mod;
        }
        
        long ans = 0;
        for (int i : mm) {
            long kk = y.get(i).size();
            long mkk = ((kk * (kk - 1)) / 2) % mod;
            cc = ((cc % mod) - (mkk % mod) + mod) % mod;
            ans = (ans % mod + ((cc % mod) * (mkk % mod)) % mod) % mod;
        }
        
        return (int) ans;
    }
}
