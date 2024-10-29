#include <iostream>
#include <queue>
#include <tuple>
using namespace std;

int vertical[100][100];
int horizontal[100][100];
bool visited[100][100];
tuple<int, int, int> stat[100][100];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
int main(void)
{
	int t, m, n, l, g;
	cin >> t;
	while (t--)
	{
		cin >> m >> n >> l >> g;
		for (int i=0; i<m; i++)
			for (int j=0; j<n-1; j++)
				cin >> vertical[i][j];
		for (int i=0; i<m-1; i++)
			for (int j=0; j<n; j++)
				cin >> horizontal[i][j];
		queue<pair<int, int> > q;
		q.push({0, 0});
		while (!q.empty())
		{
			int x = q.front().first;
			int y = q.front().second;
			q.pop();
			for (int i=0; i<4; i++)
			{
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
				
			}
		}
	}
}