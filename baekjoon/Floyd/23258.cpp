#include <iostream>
#include <algorithm>
#define MAX 301
#define INF 1e9

using namespace std;
int C, s, e, N, Q;
int cost[MAX][MAX][MAX];

int main() {
	cin.tie(0);
	ios::sync_with_stdio(false);
	cin >> N >> Q;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> cost[i][j][0];
			if (i == j)
				continue;
			if (cost[i][j][0] == 0)
				cost[i][j][0] = INF;
		}
	}
	for (C = 1; C <= N; C++)
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				cost[i][j][C] = min(cost[i][j][C - 1], cost[i][C][C - 1] + cost[C][j][C - 1]);
	for (int i = 0; i < Q; i++) {
		cin >> C >> s >> e;
		if (cost[s][e][C - 1] == INF) cout << -1 << "\n";
		else cout << cost[s][e][C - 1] << "\n";
	}
	return 0;
}
