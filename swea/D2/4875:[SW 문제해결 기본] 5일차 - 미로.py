# [문제] SWEA 4875 [SW 문제해결 기본] 5일차 - 미로
# [유형] 그래프 / BFS (미로 탐색)
# [핵심 아이디어]
#   1. 출발지(2)를 큐에 넣고 상하좌우 4방향으로 탐색한다.
#   2. 벽(1)이거나 이미 방문한 칸은 건너뛰고, 도착지(3)를 발견하면 1 로 종료한다.
# [시간복잡도] O(N^2)
# [코테 가이드]
#   - 2차원 격자 BFS 의 정석: dy/dx 배열 + 범위 검사 + visited 체크.
#   - 최단 거리가 필요하면 distance 배열을 함께 갱신하고, 도달 가능성만 필요하면 DFS 로도 충분하다.
#   - 입력이 공백 없이 붙어 있으면 list(map(int, input().strip())) 로 읽는다.

from collections import deque

T = int(input())

for test_case in range(1, T + 1):
    N = int(input())

    board = []
    visited = [[False] * N for _ in range(N)]
    queue = deque()

    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    for i in range(N):
        row = list(map(int, input().strip()))
        board.append(row)

        for j in range(N):
            if row[j] == 2:
                queue.append((i, j))
                visited[i][j] = True

    result = 0

    while result == 0 and queue:
        cur_y, cur_x = queue.popleft()

        for i in range(4):
            ny = cur_y + dy[i]
            nx = cur_x + dx[i]

            if (ny < 0 or ny >= N or nx < 0 or nx >= N
                    or board[ny][nx] == 1 or visited[ny][nx]):
                continue

            if board[ny][nx] == 3:
                result = 1
                break

            visited[ny][nx] = True
            queue.append((ny, nx))

    print(f"#{test_case} {result}")