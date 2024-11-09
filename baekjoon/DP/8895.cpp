#include <iostream>
using namespace std;

long long dp[21][21][21];
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
	dp[1][1][1] = 1;
	for (int i=2; i<=20; i++)
		for (int j=1; j<=i; j++)
			for (int k=1; k<=i; k++)
				dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k] * (i - 2);
	int t, n, l, k; cin >> t;
	while (t--) {
		cin >> n >> l >> k;
		cout << dp[n][l][k] << '\n';
	}
}