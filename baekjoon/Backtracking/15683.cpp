#include <iostream>
#include <vector>
using namespace std;

char room[8][8];
int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, 1, 0, -1};
int N, M, res=0x3f3f3f3f;
vector<pair<int, pair<int, int>>> type;

// dir: 0 -> 위, dir: 1 -> 오른쪽, dir: 2 -> 아래, dir: 3 -> 왼쪽
void move1(int y, int x, int dir, char (&arr)[8][8])
{
	while (1)
	{
		y += dy[dir]; x += dx[dir];
		if (y < 0 || y >= N || x < 0 || x >= M || arr[y][x] == '6')
			break;
		if (arr[y][x] >= '1' && arr[y][x] <= '5') continue;
		arr[y][x] = '#';
	}
}

void copy1(char (&a)[8][8], char (&b)[8][8])
{
	for (int i=0; i<N; i++)
		for (int j=0; j<M; j++)
			a[i][j] = b[i][j];
}

void dfs(int depth, char (&arr)[8][8])
{
	if (depth == type.size())
	{
		int cnt = 0;
		for (int i=0; i<N; i++)
			for (int j=0; j<M; j++)
				if (arr[i][j] == '0') cnt++;
		res = min(res, cnt);
		return ;
	}
	char tmp[8][8];
	for (int i=0; i<4; i++)
	{
		copy1(tmp, arr);
		move1(type[depth].second.first, type[depth].second.second, i, tmp);
		if (type[depth].first >= 3) move1(type[depth].second.first, type[depth].second.second, (i + 1) % 4, tmp);
		if (type[depth].first == 2 || type[depth].first == 4) move1(type[depth].second.first, type[depth].second.second, (i + 2) % 4, tmp);
		dfs(depth + 1, tmp);
	}
}

void init()
{
	cin >> N >> M;
	for (int i=0; i<N; i++)
		for (int j=0; j<M; j++)
			cin >> room[i][j];
	for (int i=0; i<N; i++)
	{
		for (int j=0; j<M; j++)
		{
			if (room[i][j] == '5') {
				for (int k=0; k<4; k++)
					move1(i, j, k, room);
			}
			else if (room[i][j] >= '1' && room[i][j] <= '4')
				type.push_back({room[i][j] - '0', {i, j}});
		}
	}
}

int main()
{
	init();
	dfs(0, room);
	cout << res;
}