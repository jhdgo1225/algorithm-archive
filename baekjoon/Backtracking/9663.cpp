#include <iostream>
using namespace std;

bool col[16];
bool diag1[33];
bool diag2[33];
int n, cnt;
void dfs(int depth)
{
	if (depth == n)
	{
		cnt++;
		return ;
	}
	for (int i=1; i<=n; i++)
	{
		if (col[i] || diag1[n - depth + i] || diag2[depth + i - 1])
			continue;
		col[i] = true;
		diag1[n - depth + i] = true;
		diag2[depth + i - 1] = true;
		dfs(depth + 1);
		diag2[depth + i - 1] = false;
		diag1[n - depth + i] = false;
		col[i] = false;
	}
}

int main()
{
	cin >> n;
	dfs(0);
	cout << cnt;
}