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
		deque<int> inputs, windows;
		int num, N,K; cin >> N >> K;
		for (int i=0; i<N; i++) {
			cin >> num;
			inputs.push_back(num);
		}
		sort(inputs.begin(), inputs.end());
		ll tmp = 0, res = 1e18;
		for (auto it: inputs) {
			cout << it << '\n';
			if (windows.size() == K) {
				ll aa = windows.front();
				windows.pop_front();
				tmp -= (windows.front() - aa);
			}
			if (windows.size()) {
				tmp += (it - windows.back());
				windows.push_back(it);
				res = min(res, tmp);
			}
			if (windows.empty()) windows.push_back(it);
		}
		cout << "#" << (i + 1) << ' ';
		cout << res << '\n';
	}
}