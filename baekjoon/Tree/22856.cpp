// BOJ 22856번: 트리 순회
#include <iostream>
using namespace std;

int level, res;
int lc[100001];
int rc[100001];

void dfs(int cur, int depth)
{
	if (lc[cur] != -1)
	{
		res += 1;
		dfs(lc[cur], depth + 1);
		res += 1;
	}
	if (rc[cur] != -1)
	{
		res += 1;
		if (level == depth) level += 1;
		dfs(rc[cur], depth + 1);
		if (level < depth) res += 1;
	}
}

int main()
{
	int n, c, l, r;
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> c >> l >> r;
		lc[c] = l; rc[c] = r;
	}
	dfs(1, 0);
	cout << res;
}