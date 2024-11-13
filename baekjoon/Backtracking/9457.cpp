#include <iostream>
#include <vector>
using namespace std;


bool visited[10];
bool dfs(vector<int> (&graphs)[10], int node)
{
	for (auto nxt : graphs[node])
	{
		
	}
}

int main()
{
	int T, e, s, d; cin >> T;
	while (T--)
	{
		vector<int> graphs[10];
		cin >> e;
		for (int i=0; i<e; i++)
		{
			cin >> s >> d;
			graphs[s].push_back(d);
			graphs[d].push_back(s);
		}
		bool flag = false;
		for (int i=1; i<=9; i++)
		{
			if (graphs[i].empty()) continue;
			flag = dfs(graphs, i);
			if (flag) break;
		}
		if (!flag) cout << "IMPOSSIBLE\n";
	}
}