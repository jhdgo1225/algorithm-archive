#include <iostream>
using namespace std;

int arr[100001];
int main()
{
	int n, k, a, b, s = 0, e = -1;
	long long res = 0;
	cin >> n >> k;
	for (int i=1; i<=n; i++)
	{
		cin >> a >> b;
		if (e < a) {
			if (e != -1) arr[i] = arr[i-1] + (a - e);
			s = a; e = b;
		} else {
			if (e < b) e = b;
			arr[i] = arr[i-1];
		}
	}
	int cur = 1;
	while (k--)
	{
		cin >> a;
		res += abs(arr[a] - arr[cur]);
		cur = a;
	}
	cout << res;
}