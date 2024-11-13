#include <iostream>
using namespace std;

int n, total, limit, area[10001];

bool func(int mid)
{
	int tmp = 0;
	for (int i=0; i<n; i++)
	{
		if (area[i] <= mid) tmp += area[i];
		else tmp += mid;
	}
	if (tmp <= total) return (true);
	return (false);
}

int main()
{
	cin >> n;
	for (int i=0; i<n; i++) {
		cin >> area[i];
		limit = max(limit, area[i]);
	} 
	cin >> total;
	int s = 0, e = limit;
	while (s <= e) {
		int mid = (s + e) / 2;
		if (func(mid)) s = mid + 1;
		else e = mid - 1;
	}
	cout << e;
}