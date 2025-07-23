class Solution {
    List<Integer>[] graph;
    int[] bobTime;
    int res = Integer.MIN_VALUE;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        bobTime = new int[n];
        Arrays.fill(bobTime, Integer.MAX_VALUE);
        findBobPath(bob, -1, 0);

        dfsAlice(0, -1, 0, 0, amount);
        return res;
    }

    private boolean findBobPath(int node, int parent, int time) {
        bobTime[node] = time;
        if (node == 0) return true;
        for (int next : graph[node]) {
            if (next != parent && findBobPath(next, node, time + 1)) {
                return true;
            }
        }
        bobTime[node] = Integer.MAX_VALUE;
        return false;
    }

    private void dfsAlice(int node, int parent, int time, int profit, int[] amount) {
        if (time < bobTime[node]) profit += amount[node];
        else if (time == bobTime[node]) profit += amount[node] / 2;

        if (graph[node].size() == 1 && node != 0) {
            res = Math.max(res, profit);
            return;
        }

        for (int next : graph[node]) {
            if (next != parent) {
                dfsAlice(next, node, time + 1, profit, amount);
            }
        }
    }
}
