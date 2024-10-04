#include <iostream>
#include <algorithm>
#include <stack>
using namespace std;

int a[1000001];
int lis[1000001];
int idx[1000001];
stack<int> res;

int main(void)
{
	int n, tmp, cur = 0;
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> a[i];
		int tmp = lower_bound(lis, lis+cur, a[i]) - lis;
		if (tmp == cur) cur++;
		lis[tmp] = a[i];
		idx[i] = tmp;
	}
	cout << cur << '\n';
	for (int i=n-1; i>=0; i--)
	{
        if (idx[i] != cur - 1) continue;
    	res.push(a[i]);
		cur--;
	}
	while (!res.empty())
	{
		cout << res.top() << ' ';
		res.pop();
	}
}