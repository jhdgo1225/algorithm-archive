#include <iostream>
#include <deque>
#include <cmath>
using namespace std;

struct FireBall {
	int r;
	int c;
	int m;
	int s;
	int d;
};

int N, M, K, r, c, d, m, s;
int dy[8] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dx[8] = {0, 1, 1, 1, 0, -1, -1, -1};
deque<FireBall> grid[2500];
deque<FireBall> q;

void simulation() {
	for (int i=0; i<N*N; i++)
	{
		if (grid[i].empty()) continue;
		if (grid[i].size() >= 2)
		{
			int massSum = 0;
			int speedSum = 0;
			int cnt = grid[i].size();
			bool allSameParity = true;
			bool firstOdd = (grid[i][0].d % 2 == 1);
			for (int j = 0; j < cnt; j++) {
				if (j >= 1 && (grid[i][j].d % 2 == 1) != firstOdd)
					allSameParity = false;
				massSum += grid[i][j].m;
				speedSum += grid[i][j].s;
			}
			grid[i].clear();
			if (massSum / 5 == 0) continue;
			for (int j=0; j<4; j++)
				q.push_back({i/N, i%N, massSum/5, speedSum/cnt, 2 * j + !allSameParity});
		}
		else
		{
			q.push_back(grid[i].front());
			grid[i].pop_front();
		}
	}
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> N >> M >> K;
	for (int i=0; i<M; i++)
	{
		cin >> r >> c >> m >> s >> d;
		q.push_back({r-1, c-1, m, s, d});
	}
	while (K--)
	{
		for (int i=0; i<q.size(); i++)
		{
			int nx = q[i].c + dx[q[i].d] * q[i].s;
			int ny = q[i].r + dy[q[i].d] * q[i].s;
			if (nx >= N) nx %= N;
			if (ny >= N) ny %= N;
			if (nx < 0) nx = (nx % N + N) % N;
			if (ny < 0) ny = (ny % N + N) % N;
			grid[ny * N + nx].push_back({ny, nx, q[i].m, q[i].s, q[i].d});
		}
		q.clear();
		simulation();
	}
	int res = 0;
	for (int i=0; i<q.size(); i++)
		res += q[i].m;
	cout << res;
	return (0);
}