#include <iostream>
using namespace std;
using ll = long long;

int k, n, ether[10001];
int main()
{
	ll s=1, e=2147483647;
	cin >> k >> n;
	for (int i=0; i<k; i++) cin >> ether[i];
	while (s <= e)
	{
		ll mid = (s + e) / 2, tmp = 0;
		for (int i=0; i<k; i++) tmp += (ether[i] / mid);
		if (n <= tmp) s = mid + 1;
		else e = mid - 1;
	}
	cout << e;
}