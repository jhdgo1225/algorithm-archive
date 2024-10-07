// BOJ 4803번: 트리 DFS Version
#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[501];
int level[501];
bool flag = 1;
void dfs(int cur, int lv)
{
	level[cur] = lv;
	for (auto it : graphs[cur])
	{
		if (!level[it]) dfs(it, lv + 1);
		else if (level[cur]-level[it] > 1) flag = 0;
	}
}

int main()
{
	int n, m, u, v, cnt, tc=1;
	while (1)
	{
		cnt = 0;
		cin >> n >> m;
		if (n<=0 && m<=0) break;
		for (int i=1; i<=m; i++)
		{
			cin >> u >> v;
			graphs[u].push_back(v);
			graphs[v].push_back(u);
		}
		for (int i=1; i<=n; i++)
		{
			if (!level[i])
			{
				flag = 1;
				dfs(i, 0);
				cnt += flag;
			}
		}
		cout << "Case " << tc++ << ": ";
		if (cnt < 1) cout << "No trees.\n";
		else if (cnt == 1) cout << "There is one tree.\n";
		else cout << "A forest of " << cnt << " trees.\n";
		for (int i=0; i<=n; i++) graphs[i].clear();
		memset(level, 0, sizeof(int) * (n + 1));
	}
}