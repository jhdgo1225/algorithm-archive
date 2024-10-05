// Baekjoon 2660번: 회장뽑기 BFS Version
#include <iostream>
#include <queue>
#include <stack>
using namespace std;

vector<int> graphs[51];
bool visited[51];
int main(void)
{
	int n, u, v;
	queue<pair<int, int> > q;
	deque<pair<int, int> > st;
	cin >> n;
	while (1)
	{
		cin >> u >> v;
		if (u == -1 && v == -1)
			break;
		graphs[u].push_back(v);
		graphs[v].push_back(u);
	}
	for (int i=1; i<=n; i++)
	{
		int tmp = 100;
		memset(visited, 0, sizeof(bool) * (n + 1));
		q.push({i, 0});
		visited[i] = true;
		while (!q.empty())
		{
			int cur = q.front().first;
			int score = q.front().second;
			q.pop();
			for (auto iter=graphs[cur].begin(); iter != graphs[cur].end(); iter++)
			{
				if (visited[*iter]) continue;
				visited[*iter] = true;
				q.push({*iter, score + 1});
				tmp = score + 1;
			}
		}
		while (!st.empty() && st.back().second > tmp)
			st.pop_back();
		if (st.empty() || st.back().second == tmp)
			st.push_back({i, tmp});
	}
	cout << st.back().second << ' ' << st.size() << endl; 
	while (!st.empty())
	{
		cout << st.front().first << ' ';
		st.pop_front();
	}
}
