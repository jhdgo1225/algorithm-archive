#include <iostream>
using namespace std;

int arr[1000001];
int counts[1000001];

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
	counts[a] += (counts[b] + 1);
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	char q;
	int n, a, b, c; cin >> n;
	fill(arr, arr + 1000001, -1);
	for (int i=0; i<n; i++) {
		cin >> q;
		if (q == 'I') {
			cin >> a >> b;
			uni(a, b);
		}
		else {
			cin >> c;
			cout << counts[find(c)] + 1 << '\n';
		}
	}
}