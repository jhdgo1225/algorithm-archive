#include <iostream>
#include <deque>
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
			long long dirFlag = 0;
			bool oddeven = false;
			int massSum = 0;
			int speedSum = 0;
			int cnt = grid[i].size();
			for (int j=0; j<cnt; j++)
			{
				if (grid[i][j].d & 1) dirFlag |= (1 << j);
				massSum += grid[i][j].m;
				speedSum += grid[i][j].s;
			}
			grid[i].clear();
			if (massSum / 5 == 0) continue;
			if (!dirFlag || dirFlag == (int)pow(2, cnt) - 1) oddeven = true;
			for (int j=0; j<4; j++)
				q.push_back({i/N, i%N, massSum/5, speedSum/cnt, 2 * j + !oddeven});
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
			if (nx < 0) {
				if (nx % N == 0) nx = q[i].c;
				else nx = N + (nx % N);
			}
			if (ny < 0) {
				if (ny % N == 0) ny = q[i].r;
				else ny = N + (ny % N);
			}
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