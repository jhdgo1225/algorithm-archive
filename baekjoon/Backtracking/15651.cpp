#include <iostream>
using namespace std;

bool visited[8];
int arr[8];
int n, m;

void dfs(int depth)
{
	if (depth == m)
	{
		for (int i=0; i<depth; i++) cout << arr[i] << ' ';
		cout << '\n';
		return ;
	}
	for (int i=1; i<=n; i++)
	{
		arr[depth] = i;
		dfs(depth + 1);
	}
}

int main(void)
{
	cin >> n >> m;
	dfs(0);
}