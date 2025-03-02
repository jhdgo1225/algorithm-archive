#include <iostream>
using namespace std;

int arr[1000001];

int find(int a) {
	if (arr[a] < 0) return a;
	return arr[a] = find(arr[a]);
}

void uni(int a, int b) {
	a = find(a);
	b = find(b);
	if (a == b) return ;
	if (arr[a] > arr[b]) {
		swap(a, b);
	}
	if (arr[a] == arr[b]) arr[a]--;
	arr[b] = a;
}

bool isGroup(int a, int b) {
	return (find(a) == find(b));
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		int n, k, a, b, m, u, v;
		cin >> n >> k;
		fill(arr, arr + n, -1);
		for (int j=0; j<k; j++) {
			cin >> a >> b;
			uni(a, b);
		}
		cin >> m;
		cout << "Scenario " << i + 1 << ": \n";
		for (int j=0; j<m; j++) {
			cin >> u >> v;
			cout << isGroup(u, v) << '\n';
		}
		if (i != T - 1) cout << '\n';
	}
}