#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<pair<int, int>> graphs[1001];
int d[1001];
int pre[1001];
const int INF = 0x3f3f3f3f;

void dijkstra(int start, int n)
{
	int cur, cost;
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({d[start], start});
	while (!pq.empty())
	{
		cur = pq.top().second;
		cost = pq.top().first;
		pq.pop();
		if (cost != d[cur]) continue;
		for (auto ptr : graphs[cur])
		{
			if (d[ptr.first] <= d[cur] + ptr.second) continue;
			d[ptr.first] = d[cur] + ptr.second;
			pre[ptr.first] = cur;
			pq.push({d[ptr.first], ptr.first});
		}
	}
}

int main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	deque<int> dq;
	int n, m;
	int a, b, c;
	int start, end;
	cin >> n >> m;
	for (int i=0; i<m; i++)
	{
		cin >> a >> b >> c;
		graphs[a].push_back({b, c});
	}
	cin >> start >> end;
	fill(d, d + n + 1, INF);
	d[start] = 0;
	dijkstra(start, n);
	cout << d[end] << '\n';
	int cur = end;
	while (cur != start)
	{
		dq.push_front(cur);
		cur = pre[cur];
	}
	dq.push_front(start);
	cout << dq.size() << '\n';
	while (!dq.empty())
	{
		cout << dq.front() << ' ';
		dq.pop_front();
	}
	return (0);
}
