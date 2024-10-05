// Baekjoon 1260번: DFS와 BFS
#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

int n, m, v;
vector<int> graphs[1001];
bool visited[1001];

void dfs(int cur)
{
	if (visited[cur]) return ;
	cout << cur << ' ';
	visited[cur] = true;
	for (auto iter = graphs[cur].begin(); iter != graphs[cur].end(); iter++)
		dfs(*iter);
}

void bfs()
{
	queue<int> q;
	cout << v << ' ';
	visited[v] = true;
	q.push(v);
	while (!q.empty())
	{
		int cur = q.front();
		q.pop();
		for (auto iter = graphs[cur].begin(); iter != graphs[cur].end(); iter++)
		{
			if (visited[*iter]) continue;
			visited[*iter] = true;
			q.push(*iter);
			cout << *iter << ' ';
		}
	}
}

int main()
{
	
	int a, b;
	cin >> n >> m >> v;
	for (int i=0; i<m; i++)
	{
		cin >> a >> b;
		graphs[a].push_back(b);
		graphs[b].push_back(a);
	}
	for (int i=1; i<=n; i++)
		sort(graphs[i].begin(), graphs[i].end());
	dfs(v);
	cout << endl;
	memset(visited, 0, sizeof(bool) * (n + 1));
	bfs();
}
