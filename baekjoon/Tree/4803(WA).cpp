// BOJ 4803번: 트리의 부모 찾기(DFS, Wrong Answer)
#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[501];
int level[501];
bool dfs(int cur, int lv)
{
	bool status = true;
	for (auto it : graphs[cur])
	{
		if (level[it])
		{
			if (level[it] != lv - 1) return false;
			else continue;
		}
		level[it] = lv + 1;
		status = dfs(it, level[it]);
	}
	return (status);
}

int main()
{
	int n, m, u, v, cnt, tc=1;
	bool valid;
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
				level[i] = 1;
				valid = dfs(i, 1);
				cnt += valid;
			}
		}
		cout << "Case " << tc++ << ": ";
		if (cnt < 1) cout << "No trees.\n";
		else if (cnt == 1) cout << "There is one tree.\n";
		else cout << "A forest of " << cnt << " trees.\n";
		for (int i=1; i<=n; i++) graphs[i].clear();
		memset(level, 0, sizeof(int) * (n + 1));
	}
}