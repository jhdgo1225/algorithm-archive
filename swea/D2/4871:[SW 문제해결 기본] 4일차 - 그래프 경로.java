/*
 * [문제] SWEA 4871 [SW 문제해결 기본] 4일차 - 그래프 경로
 * [유형] 그래프 / BFS (도달 가능성)
 * [핵심 아이디어]
 *   1. 방향 그래프를 인접 리스트로 저장한다.
 *   2. 시작 노드에서 BFS 로 탐색하며 도착 노드에 도달하면 1, 큐가 빌 때까지 못 가면 0.
 * [시간복잡도] O(V + E)
 * [코테 가이드]
 *   - 방향 그래프는 graph.get(start).add(end) 한 방향만 추가한다(무방향이면 양쪽).
 *   - 방문 체크는 큐에 넣을 때 하는 것이 중복 삽입을 막는다.
 *   - 큐는 ArrayDeque 의 offer / poll 을 쓴다. DFS(재귀/스택)로도 같은 결과를 얻는다.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int V = sc.nextInt(), E = sc.nextInt();
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
            boolean[] visited = new boolean[V + 1];

            // 방향성 간선 저장
            for (int i = 0; i < E; i++) {
                int s = sc.nextInt(), e = sc.nextInt();
                graph.get(s).add(e);
            }
            int start = sc.nextInt(), end = sc.nextInt();

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            visited[start] = true;
            int result = 0;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == end) {
                    result = 1;
                    break;
                }
                for (int next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            System.out.println("#" + test_case + " " + result);
		}
	}
}
