#include <iostream>
using namespace std;

bool visited[9];
int arr[9];
int n, m;

void dfs(int depth, int s)
{
	if (depth == m)
	{
		for (int i=0; i<depth; i++) cout << arr[i] << ' ';
		cout << '\n';
		return ;
	}
	for (int i=s; i<=n; i++)
	{
		arr[depth] = i;
		dfs(depth + 1, i + 1);
	}
}

int main(void)
{
	cin >> n >> m;
	dfs(0, 1);
}