#include <iostream>
using namespace std;

int main()
{
	int T; cin>>T;
	while (T--)
	{
		int cnt[101] = { 0 };
		int m, n, tmp, res = 0;
		cin >> m >> n;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				cin >> tmp;
				if (tmp == 1) cnt[j]++;
				else res += cnt[j];
			}
		}
		cout << res << '\n';
	}
	return 0;
}