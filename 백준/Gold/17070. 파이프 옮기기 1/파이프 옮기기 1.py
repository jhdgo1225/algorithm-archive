import sys

input = sys.stdin.readline

N = int(input())
graphs = [list(map(int, input().split())) for _ in range(0, N)]
dp = [[[-1] * 3 for _ in range(N)] for _ in range(N)]

def dfs(y, x, direction):
	if y == N - 1 and x == N - 1:
		return 1
	if dp[y][x][direction] != -1:
		return dp[y][x][direction]
	cnt = 0
	if direction == 0:
		if x + 1 < N and graphs[y][x + 1] == 0:
			cnt += dfs(y, x + 1, 0)
		if y + 1 < N and x + 1 < N and graphs[y + 1][x + 1] == 0 \
				and graphs[y][x + 1] == 0 and graphs[y + 1][x] == 0:
			cnt += dfs(y + 1, x + 1, 1)
	elif direction == 1:
		if x + 1 < N and graphs[y][x + 1] == 0:
			cnt += dfs(y, x + 1, 0)
		if y + 1 < N and x + 1 < N and graphs[y + 1][x + 1] == 0 \
				and graphs[y][x + 1] == 0 and graphs[y + 1][x] == 0:
			cnt += dfs(y + 1, x + 1, 1)
		if y + 1 < N and graphs[y + 1][x] == 0:
			cnt += dfs(y + 1, x, 2)
	elif direction == 2:
		if y + 1 < N and x + 1 < N and graphs[y + 1][x + 1] == 0 \
				and graphs[y][x + 1] == 0 and graphs[y + 1][x] == 0:
			cnt += dfs(y + 1, x + 1, 1)
		if y + 1 < N and graphs[y + 1][x] == 0:
			cnt += dfs(y + 1, x, 2)
	dp[y][x][direction] = cnt
	return cnt
print(dfs(0, 1, 0))