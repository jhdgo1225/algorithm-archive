#include <iostream>
using namespace std;

int K[101], S[101];
int dp[101][10001];
int main()
{
	int N, T; cin >> N >> T;
	for (int i=1; i<=N; i++) cin >> K[i] >> S[i];
	for (int i=1; i<=N; i++)
		for (int j=1; j<=T; j++)
			dp[i][j] = (j >= K[i]) ? max(dp[i-1][j], dp[i-1][j-K[i]] + S[i]) : dp[i-1][j];
	cout << dp[N][T];
}
