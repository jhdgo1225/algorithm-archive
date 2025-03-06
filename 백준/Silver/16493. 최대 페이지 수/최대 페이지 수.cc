#include <iostream>
using namespace std;

int day[21], pages[101];
int dp[21][201];
int main()
{
	int N, M; cin >> N >> M;
	for (int i=1; i<=M; i++) cin >> day[i] >> pages[i];
	for (int i=1; i<=M; i++)
		for (int j=1; j<=N; j++)
			dp[i][j] = (j >= day[i]) ? max(dp[i-1][j], dp[i-1][j-day[i]] + pages[i]) : dp[i-1][j];
	cout << dp[M][N];
}
