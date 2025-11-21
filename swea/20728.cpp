#include <iostream>
#include <algorithm>
using namespace std;
using ll = long long;

int main()
{
	freopen("./inputs/20728_input.txt", "r", stdin);
	int T;cin>>T;
	for (int i=0; i<T; i++)
	{
		int N, K, pockets[51], diff = 1e9; cin >> N >> K;
		for (int j=0; j<N; j++) {
			cin >> pockets[j];
		}
		sort(pockets, pockets + N);
		cout << "#" << (i + 1) << ' ';
		for (int j=0; j<N-K+1; j++) {
			diff = min(diff, pockets[j+K-1] - pockets[j]);
		}
		cout << diff << '\n';
	}
}