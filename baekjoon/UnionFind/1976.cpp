#include <iostream>
using namespace std;

int arr[201];
int arr2[1001];
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
	int n, m, info; cin >> n >> m;
	fill(arr, arr + n, -1);
	for (int i=0; i<n; i++) {
		for (int j=0; j<n; j++) {
			cin >> info;
			if (info == 1) uni(i, j);
		}
	}
	for (int i=0; i<m; i++) {
		cin >> arr2[i];
	}
	for (int i=0; i<m-1; i++) {
		if (!isSame(arr2[i] - 1, arr2[i + 1] - 1)) {
			cout << "NO\n";
			return (0);
		}
	}
	cout << "YES\n";
}