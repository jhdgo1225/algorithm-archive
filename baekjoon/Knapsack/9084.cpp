#include <iostream>
using namespace std;

int coins[21];
int dp[10001];
int main()
{
	int T, N, M; cin >> T;
	while (T--) {
		cin >> N;
		for (int i=1; i<=N; i++) cin >> coins[i];
		cin >> M;
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				if (j - coins[i] > 0) dp[j] += dp[j - coins[i]];
				else if (j - coins[i] == 0) dp[j]++;
			}
		}
		cout << dp[M] << '\n';
		fill(dp + 1, dp + M + 1, 0);
	}
}