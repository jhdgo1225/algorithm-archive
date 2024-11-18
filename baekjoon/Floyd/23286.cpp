#include <iostream>
#define INF 0x3f3f3f3f
using namespace std;

int adj[301][301];
int main()
{
	int N, M, T, u, v, h, s, e;
	cin >> N >> M >> T;
	for (int i=1; i<=N; i++)
		for (int j=1; j<=N; j++)
			adj[i][j] = (i == j) ? 0 : INF;
	for (int i=0; i<M; i++)
	{
		cin >> u >> v >> h;
		adj[u][v] = h;
	}
	for (int k=1; k<=N; k++)
		for (int i=1; i<=N; i++)
			for (int j=1; j<=N; j++)
				adj[i][j] = min(adj[i][j], max(adj[i][k], adj[k][j]));
	while (T--)
	{
		cin >> s >> e;
		cout << ((adj[s][e] == INF) ? -1 : adj[s][e]) << '\n';
	}
}
