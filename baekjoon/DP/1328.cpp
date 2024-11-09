#include <iostream>
#define INF 1000000007
using namespace std;

long long dp[101][101][101];
int main()
{
	dp[1][1][1] = 1;
	for (int i=2; i<=100; i++)
		for (int j=1; j<=i; j++)
			for (int k=1; k<=i; k++)
				dp[i][j][k] = (dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k] * (i - 2)) % INF;
	int n, l, k;
	cin >> n >> l >> k;
	cout << dp[n][l][k] << '\n';
}