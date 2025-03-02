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
	int n, m, y, a, b; cin >> n >> m;
	for (int i=0; i<n; i++) {
		cin >> legs[i].first.first >> legs[i].first.second >> y;
		legs[i].second = i;
	}
	fill(arr, arr + n, -1);
	sort(legs, legs + n);
	int tmpX1 = legs[0].first.first, tmpX2 = legs[0].first.second, idx = legs[0].second;
	for (int i=1; i<n; i++) {
		if (tmpX1 <= legs[i].first.second && tmpX2 >= legs[i].first.first) {
			uni(idx, legs[i].second);
			if (tmpX2 < legs[i].first.second) tmpX2 = legs[i].first.second;
		}
		else {
			tmpX1 = legs[i].first.first;
			tmpX2 = legs[i].first.second;
			idx = legs[i].second;
		}
	}
	for (int i=0; i<m; i++) {
		cin >> a >> b;
		cout << (find(a-1) == find(b-1)) << '\n';
	}
}
