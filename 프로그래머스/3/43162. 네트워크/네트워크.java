class Solution {
    private void dfs(int cur, int[][] computers, boolean[] isVisited) {
        isVisited[cur] = true;
        int[] curComputer = computers[cur];
        for (int i=0; i<curComputer.length; i++) {
            if (!isVisited[i] && curComputer[i] == 1) {
                isVisited[i] = true;
                dfs(i, computers, isVisited);
            }
        }
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] isVisited = new boolean[n];
        for (int i=0; i<n; i++) {
            if (!isVisited[i]) {
                dfs(i, computers, isVisited);
                answer++;
            }
        }
        return answer;
    }
}