import java.util.*;

class Solution {
    private int dfs(int start, HashMap<Integer, List<Integer>> map, boolean[] visited) {
        int cnt = 1;
        if (map.containsKey(start)) {
            for (int i=0; i<map.get(start).size(); i++) {
                int nextNode = map.get(start).get(i);
                if (!visited[nextNode - 1]) {
                    visited[nextNode - 1] = true;
                    cnt += dfs(nextNode, map, visited);
                }
            }
        }
        return cnt;
    }
    public int solution(int n, int[][] wires) {
        int answer = -1;
        for (int i=0; i<wires.length; i++) {
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            for (int j=0; j<wires.length; j++){
                if (i == j) continue;
                if (!map.containsKey(wires[j][0])) map.put(wires[j][0], new ArrayList<>());
                map.get(wires[j][0]).add(wires[j][1]);
                if (!map.containsKey(wires[j][1])) map.put(wires[j][1], new ArrayList<>());
                map.get(wires[j][1]).add(wires[j][0]);
            }
            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            visited[wires[i][0] - 1] = true;
            int towers = dfs(wires[i][0], map, visited);
            int tmp = Math.abs(n - 2 * towers);
            if (answer == -1 || answer > tmp) answer = tmp;
        }
        return answer;
    }
}