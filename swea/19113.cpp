#include <iostream>
#include <algorithm>
#include <map>
using namespace std;
using ll = long long;

int main()
{
	freopen("./inputs/19113_input.txt", "r", stdin);
	int T;cin>>T;
	for (int testcase=0; testcase<T; testcase++)
	{
		ll N, a[201]; cin >> N;
		ll b[201];
		map<ll, int> mapper;
		for (int i=0; i<2*N; i++) {
			cin >> a[i];
			mapper[a[i]] += 1;
			b[i] = a[i] * 3 / 4;
		}
		cout << "#" << testcase + 1 << ' ';
		for (int i=0; i<2*N; i++) {
			if (mapper[b[i]]) {
				cout << b[i] << ' ';
				mapper[b[i]] -= 1;
			}
		}
		cout << '\n';
	}
}