// BOJ 15681번: 트리와 쿼리
#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[100001];
int nodes[100001];
void dfs(int cur)
{
	nodes[cur] = 1;
	for (auto nxt : graphs[cur])
	{
		if (nodes[nxt]) continue;
		dfs(nxt);
		nodes[cur] += nodes[nxt];
	}
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, r, q, u, v;
	cin >> n >> r >> q;
	for (int i=0; i<n-1; i++)
	{
		cin >> u >> v;
		graphs[u].push_back(v);
		graphs[v].push_back(u);
	}
	dfs(r);
	for (int i=0; i<q; i++)
	{
		cin >> u;
		cout << nodes[u] << '\n';
	}
}