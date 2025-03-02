#include <iostream>
#include <algorithm>
using namespace std;

int arr[100001];
pair<pair<int, int>, int> legs[100001];

int find(int x) {
	if (arr[x] < 0) return x;
	return arr[x] = find(arr[x]);
}

void uni(int a, int b) {
	a = find(a);
	b = find(b);
	if (a == b) return ;
	if (arr[a] > arr[b]) swap(a, b);
	if (arr[a] == arr[b]) {
		if (a > b) swap(a, b);
		arr[a]--;
	}
	arr[b] = a;
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, m, x1, x2, y, a, b; cin >> n >> m;
	for (int i=0; i<n; i++) {
		cin >> x1 >> x2 >> y;
		legs[i].first.first = x1;
		legs[i].first.second = x2;
		legs[i].second = i;
	}
	fill(arr, arr + n, -1);
	sort(legs, legs + n);
	for (int i=0; i<n-1; i++) {
		if (legs[i].first.first <= legs[i+1].first.second && legs[i].first.second >= legs[i+1].first.first) {
			uni(legs[i].second, legs[i+1].second);
		}
	}
	for (int i=0; i<m; i++) {
		cin >> a >> b;
		cout << (find(a-1) == find(b-1)) << '\n';
	}
}