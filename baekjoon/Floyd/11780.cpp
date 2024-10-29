#include <iostream>
using namespace std;

int graphs[101][101];
int pre[101][101];

int main(void)
{
	int n, m;
	int u, v, w;
	cin >> n >> m;
	for (int i=1; i<=n; i++)
	{
		fill(graphs[i]+1, graphs[i]+1+n, 0x3f3f3f3f);
		graphs[i][i] = 0;
	}
	while (m--)
	{
		cin >> u >> v >> w;
		graphs[u][v] = min(graphs[u][v], w);
		pre[u][v] = v;
	}
	for (int k=1; k<=n; k++)
	{
		for (int i=1; i<=n; i++)
		{
			for (int j=1; j<=n; j++)
			{
				if (graphs[i][j] > graphs[i][k] + graphs[k][j])
				{
					graphs[i][j] = graphs[i][k] + graphs[k][j];
					pre[i][j] = pre[i][k];
				}
			}
		}
	}
	for (int i=1; i<=n; i++)
	{
		for (int j=1; j<=n; j++)
		{
			if (graphs[i][j] == 0x3f3f3f3f) cout << "0 ";
			else cout << graphs[i][j] << ' ';
		}
		cout << '\n';
	}
	for (int i=1; i<=n; i++)
	{
		for (int j=1; j<=n; j++)
		{
			int from = i, to = j;
			int cnt = 1;
			if (!pre[from][to])
			{
				cout << "0\n";
				continue;
			}
			while (from != to)
			{
				cnt++;
				from = pre[from][to];
			}
			cout << cnt << ' ';
			for (from=i; from != 0; from = pre[from][to])
				cout << from << ' ';
			cout << '\n';
		}
	}
}