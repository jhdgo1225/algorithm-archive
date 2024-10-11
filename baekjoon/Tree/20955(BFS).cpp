// BOJ 20955번: 민서의 응급 수술 BFS
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> graphs[100001];
bool visited[100001];
int del;
void bfs(int start)
{
	queue<pair<int, int> > q;
	q.push({start, 0});
	visited[start] = true;
	while (!q.empty())
	{
		int cur = q.front().first;
		int before = q.front().second;
		q.pop();
		for (auto nxt : graphs[cur])
		{
			if (visited[nxt]) {
				if (before != nxt) del++;
				continue;
			}
			visited[nxt] = true;
			q.push({nxt, cur});
		}
	}
}

int main()
{
	int n, m, u, v, res = 0;
	cin >> n >> m;
	for (int i=0; i<m; i++)
	{
		cin >> u >> v;
		graphs[u].push_back(v);
		graphs[v].push_back(u);
	}
	for (int i=1; i<=n; i++)
	{
		if (!visited[i])
		{
			bfs(i);
			res++;
		}
	}
	cout << res + del/2 - 1;
}