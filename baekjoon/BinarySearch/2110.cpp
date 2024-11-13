#include <iostream>
#include <algorithm>
using namespace std;

int n, c;
int x[200001];
int main()
{
	cin >> n >> c;
	for (int i=0; i<n; i++) cin >> x[i];
	sort(x, x + n);
	int s=0, e=1e9;
	while (s <= e)
	{
		int mid = (s + e) / 2;
		int setup = 0, cnt = 1;
		for (int i=1; i<n; i++)
		{
			if (x[i] - x[setup] >= mid) {
				setup = i;
				cnt++;
			}
		}
		if (c <= cnt) s = mid + 1;
		else e = mid - 1;
	}
	cout << e;
}