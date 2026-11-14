# [문제] SWEA 4881 [SW 문제해결 기본] 5일차 - 배열 최소 합
# [유형] 백트래킹 / DFS + 가지치기
# [핵심 아이디어]
#   1. 행마다 열을 하나씩 고르되 이미 사용한 열은 피한다(순열 탐색).
#   2. 현재까지의 합이 이미 최소 답 이상이면 더 탐색하지 않고 가지치기한다.
#   3. 모든 행을 채우면 최소값을 갱신한다.
# [시간복잡도] O(N!) (가지치기로 크게 감소)
# [코테 가이드]
#   - '행마다 서로 다른 열 하나 선택'은 순열 DFS + visited 패턴이다.
#   - 현재 합이 최적해를 넘으면 중단하는 가지치기가 시간 초과를 막는 핵심이다.
#   - N 이 더 크면 비트마스크 DP(dp[row][mask])로 O(N^2 * 2^N) 에 풀 수 있다.

def dfs(row, tmp_sum, N, graph, visited):
    global answer

    if row == N:
        answer = min(answer, tmp_sum)
        return
    
    if row != N and answer <= tmp_sum:
        return

    for col in range(N):
        if visited[col]:
            continue

        visited[col] = True

        dfs(row + 1, tmp_sum + graph[row][col],
            N, graph, visited)

        visited[col] = False


T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    graph = [list(map(int, input().split())) for _ in range(N)]
    answer = float('inf')
    visited = [False] * N
    dfs(0, 0, N, graph, visited)
    print(f"#{tc} {answer}")
