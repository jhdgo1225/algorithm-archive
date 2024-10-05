// Baekjoon 5124번: 환승
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> graphs[101001];
bool visited[101001];
int step[101001];
int main()
{
	int n, k, m, u, v, tot;
	cin >> n >> k >> m;
	tot = n + m;
	for (int i=n+1; i<=tot; i++)
	{
		for (int j=0; j<k; j++)
		{
			cin >> v;
			graphs[i].push_back(v);
			graphs[v].push_back(i);
		}
	}
	queue<int> q;
	q.push(1);
	visited[1] = true;
	while (!q.empty())
	{
		int cur = q.front();
		q.pop();
		for (auto iter=graphs[cur].begin(); iter != graphs[cur].end(); iter++)
		{
			if (visited[*iter]) continue;
			q.push(*iter);
			visited[*iter] = true;
			if (*iter <= n) step[*iter] = step[cur] + 1;
			else step[*iter] = step[cur];
		}
	}
	if (n != 1 && !step[n]) cout << -1;
	else cout << step[n] + 1;
}