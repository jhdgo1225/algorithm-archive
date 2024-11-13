#include <iostream>
using namespace std;

int n, m;
int woods[1000001];

bool func(int mid)
{
	long long tmp = 0;
	for (int i=0; i<n; i++)
		if (mid < woods[i])
			tmp += (woods[i] - mid);
	if (tmp >= m) return (true);
	return (false);
}

int main()
{
	cin >> n >> m;
	for (int i=0; i<n; i++) cin >> woods[i];
	int s = 0, e = 1e9;
	while (s <= e)
	{
		int mid = (s + e) / 2;
		if (func(mid)) s = mid + 1;
		else e = mid - 1;
	}
	cout << e;
}