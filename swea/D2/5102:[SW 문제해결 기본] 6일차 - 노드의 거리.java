/*
 * [문제] SWEA 5102 [SW 문제해결 기본] 6일차 - 노드의 거리
 * [유형] 그래프 / BFS (최단 거리)
 * [핵심 아이디어]
 *   1. 무방향 그래프를 인접 리스트로 저장한다.
 *   2. 출발 노드 S 에서 BFS 를 하며 distance[next] = distance[cur] + 1 로 간선 수를 기록한다.
 *   3. 도착 노드 G 에 도달하면 그 거리를 출력하고, 도달하지 못하면 0.
 * [시간복잡도] O(V + E)
 * [코테 가이드]
 *   - 가중치가 없는 그래프의 최단 거리는 BFS 로 구한다.
 *   - 도달 불가능할 때의 출력 값(0)을 문제에서 확인해 초기값으로 둔다.
 *   - 무방향 그래프는 간선을 양쪽에 모두 추가한다.
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
            for (int i = 0; i < E; i++) {
                int a = sc.nextInt(), b = sc.nextInt();
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            int S = sc.nextInt(), G = sc.nextInt();

            boolean[] visited = new boolean[V + 1];
            int[] distance = new int[V + 1];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(S);
            visited[S] = true;
            int answer = 0;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (current == G) {
                    answer = distance[current];
                    break;
                }
                for (int next : graph.get(current)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        distance[next] = distance[current] + 1;
                        queue.offer(next);
                    }
                }
            }
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
