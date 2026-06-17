import java.util.*;

class Solution {
    private static int result;
    private void dfs(int depth, int fatigue, boolean[] visited, int[][] dungeons) {
        if (depth == visited.length || fatigue <= 0) {
            result = depth;
            return;
        }
        for (int i=0; i<visited.length; i++) {
            if (!visited[i] && fatigue >= dungeons[i][0]) {
                visited[i] = true;
                dfs(depth + 1, fatigue - dungeons[i][1], visited, dungeons);
                visited[i] = false;
            }
        }
        result = Math.max(result, depth);
    }
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        Arrays.fill(visited, false);
        dfs(0, k, visited, dungeons);
        return result;
    }
}