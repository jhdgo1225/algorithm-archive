#include <iostream>
#include <vector>
#include <queue>
using namespace std;
using ll = long long;

vector<pair<ll, ll>> adj[100001];
priority_queue<pair<ll,ll>,vector<pair<ll, ll>>,greater<pair<ll, ll>>> pq;
int N, M, K;
ll d[100001];
const ll INF = 1e18;

void input()
{
	cin >> N >> M >> K;
	int U, V, C;
	for (int i=0; i<M; i++)
	{
		cin >> U >> V >> C;
		adj[V].push_back({C, U});
	}
	fill(d + 1, d + 1 + N, INF);
	for (int i=0; i<K; i++)
	{
		int city; cin >> city;
		d[city] = 0;
		pq.push({d[city], city});
	}
}

void solve()
{
	while (!pq.empty()) {
		auto cur = pq.top();
		pq.pop();
		if (d[cur.second] < cur.first) continue;
		for (auto nxt : adj[cur.second]) {
			if (d[nxt.second] > d[cur.second] + nxt.first) {
				d[nxt.second] = d[cur.second] + nxt.first;
				pq.push({d[nxt.second], nxt.second});
			}
		}
	}
}

int main()
{
	input();
	solve();
	int idx=-1, res=-1;
	for (int i=1; i<=N; i++)
	{
		if (res < d[i])
		{
			idx = i;
			res = d[i];
		}
	}
	cout << idx << '\n' << res;
}