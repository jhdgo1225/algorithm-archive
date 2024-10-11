// 참고 코드: https://www.acmicpc.net/source/27077706
// BOJ 20955번: 민서의 응급 수술 Union-Find
#include <iostream>
using namespace std;

int n, m, a, b, k;
int p[100001];

int find(int x) {
	if (p[x] < 0) return x;
	return p[x] = find(p[x]);
}

bool merge(int u, int v) {
	u = find(u), v = find(v);
	if (u == v) return 0;
	p[v] = u;
	return 1;
}

int main() {
	cin.tie(NULL); cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n >> m;
	fill(p, p + n + 1, -1);
	for (int i = 0; i < m; i++) {
		cin >> a >> b;
		merge(a, b);
	}
	for (int i = 1; i <= n; i++) k += (p[i] < 0);
	cout << 2 * k + m - n - 1;
}