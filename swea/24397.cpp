#include <iostream>
using namespace std;
using ll = long long;

ll square(ll x, int y, ll mod)
{
	if (y == 1) return x;
	ll div = square(x, y / 2, mod) % mod;
	ll mul = (div * div) % mod;
	if (y & 1) return (mul * x) % mod;
	return mul % mod;
}

bool isBigThousands(ll x, ll y, ll z) {
    ll cur = 1, base = x, thr = z * 1000;
    while (y > 0) {
        if (y & 1) {
            cur *= base;
            if (cur >= thr) return true;
        }
        base = (base * base >= thr ? thr : base * base);
        y >>= 1;
    }
    return false;
}

int main()
{
	freopen("./inputs/24397_input.txt", "r", stdin);
	int T; cin >> T;
	while (T--)
	{
		ll x, y, z; cin >> x >> y >> z;
		ll m = square(x, y, z * 1000);
		ll integerPart = m / z;
		ll fractionPart = (m % z) * 1000 / z;
		string intString = to_string(integerPart);
		string fracString = to_string(fractionPart);
		if (intString.size() >= 3) cout << intString.substr(intString.size() - 3);
		else if (isBigThousands(x, y, z)) cout << string(3 - intString.size(), '0') << intString;
		else cout << intString;
		cout << ".";
		if (fracString.size() < 3) cout << string(3 - fracString.size(), '0') + fracString << '\n';
		else cout << fracString.substr(0, 3) << '\n';
	}
}