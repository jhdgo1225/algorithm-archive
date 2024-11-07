#include <iostream>
using namespace std;

bool visited[9];
int lotto[14];
int arr[7];
int n, m;

void dfs(int depth, int s)
{
	if (depth == m)
	{
		for (int i=0; i<depth; i++) cout << arr[i] << ' ';
		cout << '\n';
		return ;
	}
	for (int i=s; i<n - 6 + depth; i++)
	{
		arr[depth] = lotto[i];
		dfs(depth + 1, i + 1);
	}
}

int main(void)
{
	while (cin >> n, n)
	{
		// if (!n) break;
		for (int i=0; i<n; i++) cin >> lotto[i];
		dfs(0, 0);
	}
}