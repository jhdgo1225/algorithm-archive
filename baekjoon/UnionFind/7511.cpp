#include <iostream>
using namespace std;

int arr[1000001];

int find(int x) {
	if (arr[x] < 0) return x;
	return arr[x] = find(arr[x]);
}

void uni(int a, int b) {
	a = find(a);
	b = find(b);
	if (a == b) return ;
	if (arr[a] > arr[b]) swap(a, b);
	if (arr[a] == arr[b]) arr[a]--;
	arr[b] = a;
}

bool isSame(int a, int b)
{
	return (find(a) == find(b));
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int T, n, k, a, b, m, u, v;
	cin >> T;
	for (int i=1; i<=T; i++) {
		cin >> n >> k;
		fill(arr, arr + n, -1);
		for (int j=0; j<k; j++) {
			cin >> a >> b;
			uni(a, b);
		}
		cin >> m;
		cout << "Scenario " << i << ":\n";
		for (int j=0; j<m; j++) {
			cin >> u >> v;
			cout << isSame(u, v) << '\n';
		}
		cout << '\n';
	}
}