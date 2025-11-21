#include <iostream>
using namespace std;
using ll = long long;

int main()
{
	freopen("./inputs/17642_input.txt", "r", stdin);
	int T;cin>>T;
	for (int i=0; i<T; i++)
	{
		ll a, b; cin >> a >> b;
		cout << "#" << i + 1 << ' ';
		if (a > b || b - a == 1) cout << -1;
		else cout << (b - a) / 2;
		cout << '\n';
	}
}