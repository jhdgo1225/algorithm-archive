#include <iostream>
using namespace std;

int tripleNum(int n)
{
	return (n * (n+1) / 2);
}

bool dfs(int cur, int dest, int depth, int limit)
{
	if (depth == limit)
	{
		if (!dest) return (true);
		return (false);
	}
	for (int i=cur; i>=1; i--)
		if (dfs(i, dest - tripleNum(i), depth + 1, limit))
			return (true);
	return (false);
}

int main()
{
	int t; cin >> t;
	while (t--)
	{
		int n, maxi = 1; cin >> n;
		while (tripleNum(maxi + 1) <= n)
			maxi++;
		if (dfs(maxi, n, 0, 3)) cout << "1\n";
		else cout << "0\n";
	}
}