#include <iostream>
#define INF 0x3f3f3f3f
using namespace std;

int adj[251][251];
int main()
{
	int n, m, u, v, b, k, s, e;
	cin >> n >> m;
	for (int i=1; i<=n; i++)
		for (int j=1; j<=n; j++)
			adj[i][j] = ((i == j) ? 0 : INF);
	for (int i=0; i<m; i++)
	{
		cin >> u >> v >> b;
		adj[u][v] = adj[v][u] = 0;
		if (!b) adj[v][u] = 1;
	}
	for (int k=1; k<=n; k++)
		for (int i=1; i<=n; i++)
			for (int j=1; j<=n; j++)
				if (adj[i][j] > adj[i][k] + adj[k][j])
					adj[i][j] = adj[i][k] + adj[k][j];
	cin >> k;
	while (k--)
	{
		cin >> s >> e;
		cout << adj[s][e] << '\n';
	}
}