#include <iostream>
using namespace std;
using ll = long long;
ll res;

void func(int n, int k, ll l, ll r)
{
	ll T = (l + r) / 2, small = 0, tmp;
	if (l <= r) {
		for (ll i=1; i<=n; i++) {
			tmp = T / i;
			if (tmp > n) tmp = n;
			small += tmp;
		}
		if (l == r) {res = l; return ;}
		if (small < k) func(n, k, T + 1, r);
		else func(n, k, l, T);
	}
}

int main()
{
	ll n, k;
	cin >> n >> k;
	func(n, k, 1, n*n);
	cout << res;
}