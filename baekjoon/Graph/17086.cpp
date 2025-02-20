#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;
int maxSafeDist = 0;
int dx[8] = {-1, 0, 1, 0, 1, -1, 1, -1};
int dy[8] = {0, -1, 0, 1, -1, 1, 1, -1};
int graph[51][51];

queue<pair<int, int>> q;

void bfs()
{
	while (!q.empty())
	{
		int cy = q.front().first;
		int cx = q.front().second;
		q.pop();
		for (int i=0; i<8; i++)
		{
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			if (nx >= 0 && nx < M && ny >= 0 && ny < N && graph[cy][cx] + 1 < graph[ny][nx])
			{
				q.push({ny, nx});
				graph[ny][nx] = graph[cy][cx] + 1;
				maxSafeDist = max(maxSafeDist, graph[ny][nx]);
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(0);
    cin.tie(0);
	cin >> N >> M;
	for (int i=0; i<N; i++)
	{
		for (int j=0; j<M; j++)
		{
			cin >> graph[i][j];
			if (graph[i][j]) {
				q.push({i, j});
				graph[i][j] = 0;
			}
			else graph[i][j] = 10000;
		}
	}
	bfs();
	cout << maxSafeDist;
}