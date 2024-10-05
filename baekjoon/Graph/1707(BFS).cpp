// Baekjoon 1707번: 이분 그래프 BFS Version
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int color[20001];
int main()
{
	int t, v, e, a, b;
	cin >> t;
	while (t--)
	{
		cin >> v >> e;
		vector<int> graphs[20001];
		queue<int> q;
		bool flag = false;
		memset(color, -1, sizeof(int) * v);
		while (e--)
		{
			cin >> a >> b;
			graphs[a-1].push_back(b-1);
			graphs[b-1].push_back(a-1);
		}
		for (int i=0; i<v; i++)
		{
			if (color[i] != -1) continue;
			q.push(i);
			color[i] = 0;
			while (!q.empty())
			{
				if (flag) break;
				int cur = q.front();
				q.pop();
				for (auto iter=graphs[cur].begin(); iter != graphs[cur].end(); iter++)
				{
					if (color[cur] == color[*iter]) 
					{
						flag = true;
						break;
					}
					if (color[*iter] != -1) continue;
					q.push(*iter);
					color[*iter] = (!color[cur]) ? 1 : 0;
				}
			}
		}
		if (flag) cout << "NO\n";
		else cout << "YES\n";
	}
}