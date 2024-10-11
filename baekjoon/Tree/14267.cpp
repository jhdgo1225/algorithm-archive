// BOJ 14267번: 회사 문화 1
#include <iostream>
using namespace std;

int g[100001];
int p[100001];

int main()
{
	int n, m, u, v;
	cin >> n >> m;
	for (int i=1; i<=n; i++)
		cin >> p[i];
	p[1] = 0;
	while (m--)
	{
		cin >> u >> v;
		g[u] += v;
	}
	for (int i=1; i<=n; i++)
	{
		g[i] += g[p[i]];
		cout << g[i] << ' ';
	}
}