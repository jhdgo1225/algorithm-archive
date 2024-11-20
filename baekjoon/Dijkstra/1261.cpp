#include <iostream>
#include <queue>
#define INF 0x3f3f3f3f
using namespace std;

int N, M, vis[101][101];
char graphs[101][101];
int dy[4] = {-1, 1, 0, 0};
int dx[4] = {0, 0, -1, 1};

void input()
{
	cin >> M >> N;
	for (int i=0; i<N; i++)
	{
		for (int j=0; j<M; j++)
		{
			cin >> graphs[i][j];
			vis[i][j] = INF;
		}
	}
}

void Dijkstra()
{
	priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>> > pq;
	pq.push({0, {0, 0}});
	vis[0][0] = 0;
	while (!pq.empty())
	{
		int cnt = pq.top().first;
		int cy = pq.top().second.first;
		int cx = pq.top().second.second;
		pq.pop();
		for (int i=0; i<4; i++)
		{
			int ny = cy + dy[i];
			int nx = cx + dx[i];
			if (ny < 0 || ny >= N || nx < 0 || nx >= M || vis[ny][nx] <= cnt + (graphs[ny][nx] == '1')) continue;
			pq.push({cnt + (graphs[ny][nx] == '1'), {ny, nx}});
			vis[ny][nx] = cnt + (graphs[ny][nx] == '1');
		}
	}
}

int main()
{
	input();
	Dijkstra();
	cout << ((vis[N-1][M-1] == INF) ? -1 : vis[N-1][M-1]);
}