#include <iostream>
#include <algorithm>
using namespace std;

int a[100001];
bool bs(int n, int target)
{
	int left = 0, right = n, mid;
	while (left < right)
	{
		mid = (left + right) >> 1;
		if (a[mid] >= target) right = mid;
		else left = mid + 1;
	}
    return (left < n && a[left] == target);
}

int main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, nn, m;
	cin >> n;
	for (int i=0; i<n; i++)
		cin >> a[i];
	sort(a, a + n);
	cin >> nn;
	for (int i=0; i<nn; i++)
	{
		cin >> m;
		cout << bs(n, m) << '\n';
	}
}
