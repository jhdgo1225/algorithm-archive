#include <iostream>
using namespace std;

int liquid[100001];
int main()
{
	int n, one, two, res = 2e9; cin >> n;
	for (int i=0; i<n; i++) cin >> liquid[i];
	for (int i=0; i<n-1; i++)
	{
		int s = i + 1, e=n;
		int idx = lower_bound(liquid + s, liquid + e, (-1) * liquid[i]) - liquid;
		if ((idx == n || idx < n && idx - 1 != i) && abs(liquid[i] + liquid[idx - 1]) <= res) {
			one = liquid[i];
			two = liquid[idx - 1];
			res = abs(liquid[i] + liquid[idx - 1]);
		}
		if (idx < n && abs(liquid[i] + liquid[idx]) <= res) {
			one = liquid[i];
			two = liquid[idx];
			res = abs(liquid[i] + liquid[idx]);
		}
	}
	cout << one << ' ' << two;
}
