#include <iostream>
using namespace std;

int arr[500001];
pair<int, int> order[1000001];

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

bool isSame(int a, int b) {
	return (find(a) == find(b));
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, m, a, b; cin >> n >> m;
	fill(arr, arr + n, -1);
	for (int i=0; i<m; i++) {
		cin >> a >> b;
		order[i].first = a;
		order[i].second = b;
	}
	for (int i=0; i<m; i++) {
		int u = order[i].first;
		int v = order[i].second;
		if (isSame(u, v)) {
			cout << i + 1;
			return (0);
		}
		uni(u, v);
	}
	cout << 0;
}