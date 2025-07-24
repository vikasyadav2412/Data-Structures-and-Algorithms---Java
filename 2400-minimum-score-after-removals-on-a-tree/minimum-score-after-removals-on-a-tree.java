import java.util.*;

class Solution {
    int[] nums;
    List<Integer>[] graph;
    int[] subtreeXor;
    int[] parent;
    int n, totalXor;
    
    public int minimumScore(int[] nums, int[][] edges) {
        this.nums = nums;
        n = nums.length;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        subtreeXor = new int[n];
        parent = new int[n];
        Arrays.fill(parent, -1);
        
        totalXor = dfs(0, -1);
        
        List<int[]> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int nei : graph[i]) {
                if (parent[nei] == i) {
                    edgeList.add(new int[]{i, nei});
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        int m = edgeList.size();
        for (int i = 0; i < m; i++) {
            int a1 = edgeList.get(i)[0], b1 = edgeList.get(i)[1];
            for (int j = i + 1; j < m; j++) {
                int a2 = edgeList.get(j)[0], b2 = edgeList.get(j)[1];
                
                int xor1, xor2, xor3;
                
                if (isAncestor(b1, b2)) {
                    xor2 = subtreeXor[b2];
                    xor1 = subtreeXor[b1] ^ xor2;
                    xor3 = totalXor ^ xor1 ^ xor2;
                } else if (isAncestor(b2, b1)) {
                    xor1 = subtreeXor[b1];
                    xor2 = subtreeXor[b2] ^ xor1;
                    xor3 = totalXor ^ xor1 ^ xor2;
                } else {
                    xor1 = subtreeXor[b1];
                    xor2 = subtreeXor[b2];
                    xor3 = totalXor ^ xor1 ^ xor2;
                }
                
                int maxVal = Math.max(xor1, Math.max(xor2, xor3));
                int minVal = Math.min(xor1, Math.min(xor2, xor3));
                res = Math.min(res, maxVal - minVal);
            }
        }
        
        return res;
    }
    
    private int dfs(int node, int par) {
        parent[node] = par;
        int val = nums[node];
        for (int nei : graph[node]) {
            if (nei != par) {
                val ^= dfs(nei, node);
            }
        }
        subtreeXor[node] = val;
        return val;
    }
    
    private boolean isAncestor(int u, int v) {
        while (v != -1) {
            if (v == u) return true;
            v = parent[v];
        }
        return false;
    }
}
