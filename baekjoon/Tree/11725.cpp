// BOJ 11725번: 트리의 부모 찾기
#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[100001];
int parents[100001];

void dfs(int start)
{
	if (!graphs[start].size()) return ;
	for (auto it : graphs[start])
	{
		if (parents[it]) continue;
		parents[it] = start;
		dfs(it);
	}
}

int main()
{
	int n, u, v;
	cin >> n;
	for (int i=0; i<n-1; i++)
	{
		cin >> u >> v;
		graphs[u].push_back(v);
		graphs[v].push_back(u);
	}
	dfs(1);
	for (int i=2; i<=n; i++)
		cout << parents[i] << '\n';
}