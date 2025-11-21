#include <iostream>
using namespace std;
using ll = long long;

int main()
{
	freopen("./inputs/22759_input.txt", "r", stdin);
	int T; cin >> T;
	while (T--)
	{
		ll L,R;cin>>L>>R;
		double tmp = (double)(R + 1) / 2;
		cout << (((L % (R + 1) >= tmp) && (R % (R + 1) >= tmp)) ? "yes" : "no") << '\n';
	}
}