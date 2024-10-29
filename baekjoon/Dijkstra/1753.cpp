// BOJ 1753번: 최단경로
#include <iostream>
#include <queue>
using namespace std;

vector<pair<int, int>> adj[20001];
int d[20001];
const int INF = 0x3f3f3f3f;

void lets_go_dijkstra(int start, int n)
{
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	fill(d, d + n + 1, INF);
	int cur, cost;
	d[start] = 0;
	pq.push({d[start], start});
	while (!pq.empty())
	{
		cur = pq.top().second;
		cost = pq.top().first;
		pq.pop();
		if (cost != d[cur]) continue;
		for (auto ptr : adj[cur])
		{
			if (d[ptr.first] <= d[cur] + ptr.second) continue;
			d[ptr.first] = d[cur] + ptr.second;
			pq.push({d[ptr.first], ptr.first});
		}
	}
}

int main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int v, e, s, a, b, c;
	cin >> v >> e;
	cin >> s;
	for (int i=0; i<e; i++)
	{
		cin >> a >> b >> c;
		adj[a].push_back({b, c});
	}
	lets_go_dijkstra(s, v);
	for (int i=1; i<=v; i++)
	{
		if (d[i] == INF) cout << "INF\n";
		else cout << d[i] << '\n';
	}
	return (0);
}
