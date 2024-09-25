#include <iostream>
#include <vector>
using namespace std;

int box[1001];
int memo[1001];

int func(int depth)
{
	memo[depth] = 1;
	if (depth == 1)
		return (1);
	int res = func(depth - 1);
	for (int i=1; i<depth; i++)
	{
		if (box[i] < box[depth])
		{
			memo[depth] = max(memo[depth], memo[i] + 1);
			res = max(res, memo[depth]);
		}
	}
	return (res);
}

int main()
{
	int n; cin >> n;
	for (int i=1; i<=n; i++)
		cin >> box[i];
	cout << func(n);
}
