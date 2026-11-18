/*
 * [문제] SWEA 4881 [SW 문제해결 기본] 5일차 - 배열 최소 합
 * [유형] 백트래킹 / DFS + 가지치기
 * [핵심 아이디어]
 *   1. 행마다 열을 하나씩 고르되 이미 사용한 열은 피한다(순열 탐색).
 *   2. 현재까지의 합이 이미 최소 답 이상이면 더 탐색하지 않고 가지치기한다.
 *   3. 모든 행을 채우면 최소값을 갱신한다.
 * [시간복잡도] O(N!) (가지치기로 크게 감소)
 * [코테 가이드]
 *   - '행마다 서로 다른 열 하나 선택'은 순열 DFS + visited 패턴이다.
 *   - 현재 합이 최적해를 넘으면 중단하는 가지치기가 시간 초과를 막는 핵심이다.
 *   - 재귀에서 공유할 값(answer, graph)은 static 필드로 두면 매개변수가 줄어든다.
 *   - N 이 더 크면 비트마스크 DP(dp[row][mask])로 O(N^2 * 2^N) 에 풀 수 있다.
 */

import java.util.Scanner;

class Solution
{
    static int N, answer;
    static int[][] graph;
    static boolean[] visited;

    static void dfs(int row, int tmpSum) {
        if (row == N) {
            answer = Math.min(answer, tmpSum);
            return;
        }
        if (answer <= tmpSum) return;

        for (int col = 0; col < N; col++) {
            if (visited[col]) continue;
            visited[col] = true;
            dfs(row + 1, tmpSum + graph[row][col]);
            visited[col] = false;
        }
    }

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            graph = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) graph[i][j] = sc.nextInt();
            answer = Integer.MAX_VALUE;
            visited = new boolean[N];
            dfs(0, 0);
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
