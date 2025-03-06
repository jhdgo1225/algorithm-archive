#include <iostream>
using namespace std;

int coins[21];
int dp[21][10001];
int main()
{
	int T, N, M; cin >> T;
	while (T--) {
		cin >> N;
		for (int i=1; i<=N; i++) cin >> coins[i];
		cin >> M;
		for (int i=1; i<=M; i++)
			dp[1][i] = (i % coins[1] == 0);
		for (int i=2; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				dp[i][j] = dp[i-1][j] + (j % coins[i] == 0);
				int tmp = 1;
				while (j > coins[i] * tmp) {
					dp[i][j] += dp[i-1][j - coins[i] * tmp];
					tmp += 1;
				}
			}
		}
		cout << dp[N][M] << '\n';
		for (int i=1; i<=N; i++)
			fill(dp[i] + 1, dp[i] + M + 1, 0);
	}
}