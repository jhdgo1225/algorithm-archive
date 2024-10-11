// BOJ 1967번: 트리의 지름
#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int> > graphs[10001];
bool visited[10001];
int start = 1;
int res;
void dfs(int cur, int tmp)
{
	visited[cur] = true;
	int child = 0;
	for (auto nxt : graphs[cur])
	{
		if (visited[nxt.first]) continue;
		dfs(nxt.first, tmp + nxt.second);
		child++;
	}
	if (!child && res < tmp)
	{
		res = tmp;
		start = cur;
	}
}

int main(void)
{
	int n, u, v, w;
	cin >> n;
	for (int i=0; i<n-1; i++)
	{
		cin >> u >> v >> w;
		graphs[u].push_back({v, w});
		graphs[v].push_back({u, w});
	}
	dfs(1, 0);
	fill(visited, visited + n + 1, false);
	res = 0;
	dfs(start, 0);
	cout << res;
}