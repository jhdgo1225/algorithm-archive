// BOJ 1240번: 노드사이의 거리
#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[1001];
bool visited[1001];
int adj[1001][1001];
int dist[1001];

void dfs(int cur)
{
	visited[cur] = true;
	for (int nxt : graphs[cur])
	{
		if (!visited[nxt])
		{
			dist[nxt] = dist[cur] + adj[cur][nxt];
			dfs(nxt);
		}
	}
}

int main()
{
	int n, m, u, v, w;
	cin >> n >> m;
	for (int i=0; i<n-1; i++)
	{
		cin >> u >> v >> w;
		graphs[u].push_back(v);
		graphs[v].push_back(u);
		adj[u][v] = adj[v][u] = w;
	}
	while (m--)
	{
		cin >> u >> v;
		dfs(u);
		cout << dist[v] << '\n';
		memset(dist, 0, sizeof(int) * (n + 1));
		memset(visited, 0, sizeof(bool) * (n + 1));
	}
}
