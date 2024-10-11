#include <iostream>
using namespace std;
int lc[10001];
int rc[10001];
int parent[10001];
pair<int, int> nodes[10001];

int inorder(int cur, int depth, int left)
{
	int leftSub = 0;
	int rightSub = 0;
	if (lc[cur] != -1) {
		leftSub = inorder(lc[cur], depth + 1, left);
	}
	nodes[cur].first = depth;
	nodes[cur].second = left + leftSub;
	if (rc[cur] != -1) {
		rightSub = inorder(rc[cur], depth + 1, left + leftSub + 1);
	}
	return (leftSub + rightSub + 1);
}


int main()
{
	int n, p, l, r;
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> p >> l >> r;
		lc[p] = l; rc[p] = r;
		if (l != -1) parent[l] = p;
		if (r != -1) parent[r] = p;
	}
	int start = 0;
	for (int i=1; i<=n; i++)
		if (!parent[i]) start = i;
	inorder(start, 1, 1);
	sort(nodes, nodes + n + 1);
	int leftCol=0, rightCol=0, depth = 1;
	int res1=0, res2=0;
	for (int i=2; i<=n; i++)
	{
		if (depth < nodes[i].first)
		{
			if (res2 < rightCol - leftCol + 1)
			{
				res2 = rightCol - leftCol + 1;
				res1 = depth;
			}
			leftCol = rightCol = nodes[i].second;
			depth = nodes[i].first;
		}
		leftCol = min(leftCol, nodes[i].second);
		rightCol = max(rightCol, nodes[i].second);
	}
	if (res2 < rightCol - leftCol + 1)
	{
		res2 = rightCol - leftCol + 1;
		res1 = depth;
	}
	cout << res1 << ' ' << res2;
}