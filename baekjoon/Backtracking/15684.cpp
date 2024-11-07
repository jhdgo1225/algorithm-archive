#include <iostream>
using namespace std;

int N, M, H, label = 1, res = 0x3f3f3f3f;
int legs[271][11];

bool dfs(int cur, int depth, int limit)
{
	if (depth == limit)
	{
		int cnt = 0;
		for (int i=1; i<=N; i++)
		{
			int row = 1, col = i;
			while (row <= H)
			{
				if (col - 1 >= 1 && legs[row][col - 1] && legs[row][col - 1] == legs[row][col])
					col--;
				else if (col + 1 <= N && legs[row][col + 1] && legs[row][col + 1] == legs[row][col])
					col++;
				row++;
			}
			if (i == col) cnt++;
		}
		if (cnt == N) {
			cout << limit;
			return (true);
		}
		return (false);
	}
	for (int i=cur; i<N*H; i++)
	{
		int y = i / N + 1;
		int x = i % N + 1;
		if (x == N) continue;
		if (legs[y][x] == 0 && legs[y][x + 1] == 0)
		{
			legs[y][x] = legs[y][x + 1] = label++;
			if (dfs(i + 2, depth + 1, limit)) return (true);
			legs[y][x] = legs[y][x + 1] = 0;
			label--;
		}
	}
	return (false);
}

int main()
{
	cin >> N >> M >> H;
	if (M == 0)  {
		cout << 0;
		return (0);
	}
	int a, b;
	for (int i=0; i<M; i++)
	{
		cin >> a >> b;
		legs[a][b] = legs[a][b + 1] = label++;
	}
	bool flag = false;
	for (int i=0; i<=3; i++)
	{
		flag = dfs(0, 0, i);
		if (flag) break;
	}
	if (!flag) cout << -1;
}