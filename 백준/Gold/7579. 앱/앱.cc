#include <iostream>
using namespace std;

int memory[101];
int cost[101];
int dp[10000001];
int swaps[10000001];
int main()
{
	int N, M; cin >> N >> M;
	for (int i=1; i<=N; i++) cin >> memory[i];
	for (int i=1; i<=N; i++) cin >> cost[i];
	const int INF = 0x3f3f3f3f;
	fill(dp + 1, dp + 1 + M, INF);
	fill(swaps + 1, swaps + 1 + M, INF);
	for (int i=1; i<=N; i++) {
		for (int j=1; j<=M; j++) {
			if (memory[i] <= j) swaps[j] = min(dp[j - memory[i]] + cost[i], dp[j]);
			else swaps[j] = min(cost[i], dp[j]);
		}
		for (int j=1; j<=M; j++) {
			dp[j] = swaps[j];
		}
	}
	cout << dp[M];
}