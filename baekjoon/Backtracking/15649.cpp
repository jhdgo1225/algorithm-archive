#include <iostream>
using namespace std;

bool visited[9];
int arr[9];
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
		if (visited[i]) continue;
		arr[depth] = i;
		visited[i] = true;
		dfs(depth + 1);
		visited[i] = false;
	}
}

int main(void)
{
	cin >> n >> m;
	dfs(0);
}