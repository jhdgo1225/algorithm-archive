# [문제] SWEA 5102 [SW 문제해결 기본] 6일차 - 노드의 거리
# [유형] 그래프 / BFS (최단 거리)
# [핵심 아이디어]
#   1. 무방향 그래프를 인접 리스트로 저장한다.
#   2. 출발 노드 S 에서 BFS 를 하며 distance[next] = distance[cur] + 1 로 간선 수를 기록한다.
#   3. 도착 노드 G 에 도달하면 그 거리를 출력하고, 도달하지 못하면 0.
# [시간복잡도] O(V + E)
# [코테 가이드]
#   - 가중치가 없는 그래프의 최단 거리는 BFS 로 구한다.
#   - 도달 불가능할 때의 출력 값(0)을 문제에서 확인해 초기값으로 둔다.
#   - 무방향 그래프는 간선을 양쪽에 모두 추가한다.

from collections import deque

T = int(input())

for tc in range(1, T + 1):
    V, E = map(int, input().split())

    graph = [[] for _ in range(V + 1)]

    for _ in range(E):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    S, G = map(int, input().split())

    visited = [False] * (V + 1)
    distance = [0] * (V + 1)

    queue = deque([S])
    visited[S] = True

    answer = 0

    while queue:
        current = queue.popleft()

        if current == G:
            answer = distance[current]
            break

        for next_node in graph[current]:
            if not visited[next_node]:
                visited[next_node] = True
                distance[next_node] = distance[current] + 1
                queue.append(next_node)

    print(f"#{tc} {answer}")