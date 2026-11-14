# [문제] SWEA 4871 [SW 문제해결 기본] 4일차 - 그래프 경로
# [유형] 그래프 / BFS (도달 가능성)
# [핵심 아이디어]
#   1. 방향 그래프를 인접 리스트로 저장한다.
#   2. 시작 노드에서 BFS 로 탐색하며 도착 노드에 도달하면 1, 큐가 빌 때까지 못 가면 0.
# [시간복잡도] O(V + E)
# [코테 가이드]
#   - 방향 그래프는 graph[start].append(end) 한 방향만 추가한다(무방향이면 양쪽).
#   - 방문 체크는 큐에 넣을 때 하는 것이 중복 삽입을 막는다.
#   - DFS(재귀/스택)로도 같은 결과를 얻는다.

from collections import deque

T = int(input())

for test_case in range(1, T + 1):
    V, E = map(int, input().split())

    graph = [[] for _ in range(V + 1)]
    visited = [False] * (V + 1)

    # 방향성 간선 저장
    for _ in range(E):
        start, end = map(int, input().split())
        graph[start].append(end)

    start, end = map(int, input().split())

    queue = deque([start])
    visited[start] = True

    result = 0

    while queue:
        cur = queue.popleft()

        if cur == end:
            result = 1
            break

        for next_node in graph[cur]:
            if not visited[next_node]:
                visited[next_node] = True
                queue.append(next_node)

    print(f"#{test_case} {result}")